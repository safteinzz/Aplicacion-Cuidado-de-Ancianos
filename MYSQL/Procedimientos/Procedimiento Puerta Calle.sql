use pr_itcom;

#Para el sensor de puerta calle se creará un procedimiento automático que se registrará una alerta si el tiempo que ha estado abierta la puerta de la calle
#ha sido superior a 30 minutos (1800 segundos) (se puede poner 1 hora para utilizar las mismas funciones que el anterior).
#Los valores que registrará serán:
# "DNI_PACIENTE" = SENSOR_PUERTA_CALLE.DNI_PACIENTE,
# "SENSOR" = "PUERTACALLE",
# "VALOR" = es del tipo booleano, pero esta contendrá un '0' cuando esté cerrada y un '1' cuando esté abierta (si se ha quedado abierta sin cerrar)
# "FECHA" = SENSOR_PUERTA_CALLE.FECHA


DROP FUNCTION IF EXISTS CHECK_ALERTA_PUERTA;
DROP PROCEDURE IF EXISTS ALERTA_PUERTA;

#SET GLOBAL log_bin_trust_function_creators = 1;

DELIMITER $$

CREATE FUNCTION CHECK_ALERTA_PUERTA(DNI_AUX varchar(9), FECHA_AUX DATETIME) RETURNS INT
	BEGIN
		DECLARE ALERTA INT DEFAULT 0;
        
        
		SET ALERTA = 
        (
			SELECT TIMESTAMPDIFF
            (
				SECOND,
				(
					SELECT SENSOR_PUERTA_CALLE.FECHA 
					FROM SENSOR_PUERTA_CALLE 
					WHERE SENSOR_PUERTA_CALLE.FECHA = FECHA_AUX
					AND SENSOR_PUERTA_CALLE.DNI_PACIENTE = DNI_AUX
					AND SENSOR_PUERTA_CALLE.ABIERTA = TRUE
					ORDER BY SENSOR_PUERTA_CALLE.FECHA ASC
					LIMIT 1
				),
				(
					SELECT SENSOR_PUERTA_CALLE.FECHA 
					FROM SENSOR_PUERTA_CALLE 
					WHERE SENSOR_PUERTA_CALLE.FECHA > FECHA_AUX
					AND SENSOR_PUERTA_CALLE.DNI_PACIENTE = DNI_AUX
					AND SENSOR_PUERTA_CALLE.ABIERTA = FALSE
					ORDER BY SENSOR_PUERTA_CALLE.FECHA ASC
					LIMIT 1
				)
			)
		);
        
		RETURN ALERTA;
	END;$$




CREATE PROCEDURE ALERTA_PUERTA()
    BEGIN
        DECLARE finished INTEGER DEFAULT 0;  
        
        DECLARE DNI_ACTUAL VARCHAR(9);
        DECLARE ABIERTA BOOLEAN;
        DECLARE FECHA DATETIME; 
        
        DECLARE ALERTA INT DEFAULT 0;
        
        DECLARE cursor_Paciente CURSOR FOR
            SELECT SENSOR_PUERTA_CALLE.DNI_PACIENTE
			FROM 	SENSOR_PUERTA_CALLE
			WHERE   SENSOR_PUERTA_CALLE.FECHA >= NOW() - INTERVAL 1 DAY
			GROUP BY SENSOR_PUERTA_CALLE.DNI_PACIENTE;
            
		DECLARE cursor_Fecha CURSOR FOR
            SELECT SENSOR_PUERTA_CALLE.ABIERTA, SENSOR_PUERTA_CALLE.FECHA
			FROM 	SENSOR_PUERTA_CALLE
			WHERE   SENSOR_PUERTA_CALLE.FECHA >= NOW() - INTERVAL 1 DAY
            AND SENSOR_PUERTA_CALLE.ABIERTA = TRUE
            AND SENSOR_PUERTA_CALLE.DNI_PACIENTE = DNI_ACTUAL;
            
	   DECLARE CONTINUE HANDLER 
            FOR NOT FOUND SET finished = 1;
		
        OPEN cursor_Paciente;        
            lop1: LOOP

                FETCH cursor_Paciente
                    INTO DNI_ACTUAL;                   
                
                IF finished = 1 THEN
					CLOSE cursor_Paciente;
                    LEAVE lop1;                    
                END IF;		
                
                OPEN cursor_Fecha;
					lop2: LOOP
                    
						FETCH cursor_Fecha
							INTO ABIERTA, FECHA;
                          
						IF finished = 1 THEN
							SET finished = 0;
							CLOSE cursor_Fecha;
							LEAVE lop2;                    
						END IF;	
                        
                        SET ALERTA = CHECK_ALERTA_PUERTA(DNI_ACTUAL, FECHA);                        
						
						IF ALERTA > 1800 
							THEN
                            
							INSERT INTO ALERTA
								(DNI_PACIENTE, SENSOR, FECHA, VALOR)
							VALUES (DNI_ACTUAL, 'PUERTACALLE', FECHA, 0);  
                            
						ELSEIF (ALERTA IS NULL) #sigue abierta
							THEN
                            
                            INSERT INTO ALERTA
								(DNI_PACIENTE, SENSOR, FECHA, VALOR)
							VALUES (DNI_ACTUAL, 'PUERTACALLE', FECHA, 1); 
                            
						END IF;                    
                        
					END LOOP lop2;
                
            END LOOP lop1;
            
    END $$
 
DELIMITER ;

#CALL ALERTA_PUERTA();