use pr_itcom;

#Para el sensor de presencia se creará un procedimiento automático que registre la diferencia de horas entre un valor y otro, si el valor registrado 
#es superior a 4 horas entonces creará un registro en la tabla alerta con los siguientes valores: 
# "DNI_PACIENTE" = SENSOR_PRESENCIA.DNI_PACIENTE, 
# "SENSOR" = "PRESENCIA", 
# "VALOR" = SENSOR_PRESENCIA.ID_TIPO_PRESENCIA y 
# "FECHA" = SENSOR_PRESENCIA.FECHA. (A modo de mejora, si el id_tipo_presencia es el de la habitación, entonces la diferencia horaria tiene que ser superior a 10 horas).


DROP FUNCTION IF EXISTS CHECK_ALERTA_PRESENCIA;
DROP PROCEDURE IF EXISTS ALERTA_PRESENCIA;



DELIMITER $$

CREATE FUNCTION CHECK_ALERTA_PRESENCIA(DNI_AUX varchar(9), FECHA_AUX DATETIME, TIPO INT) RETURNS INT
	BEGIN
		DECLARE ALERTA INT DEFAULT 0;
        
        
		SET ALERTA = 
        (
			SELECT TIMESTAMPDIFF
            (
				SECOND,
				(
					SELECT SENSOR_PRESENCIA.FECHA 
					FROM SENSOR_PRESENCIA 
					WHERE SENSOR_PRESENCIA.FECHA = FECHA_AUX
					AND SENSOR_PRESENCIA.DNI_PACIENTE = DNI_AUX
					AND SENSOR_PRESENCIA.ID_TIPO_PRESENCIA = TIPO
					ORDER BY SENSOR_PRESENCIA.FECHA ASC
					LIMIT 1
				),
				(
					SELECT SENSOR_PRESENCIA.FECHA 
					FROM SENSOR_PRESENCIA 
					WHERE SENSOR_PRESENCIA.FECHA > FECHA_AUX
					AND SENSOR_PRESENCIA.DNI_PACIENTE = DNI_AUX
					AND SENSOR_PRESENCIA.ID_TIPO_PRESENCIA = TIPO
					ORDER BY SENSOR_PRESENCIA.FECHA ASC
					LIMIT 1
				)
			)
		);
        
		RETURN ALERTA;
	END;$$

CREATE PROCEDURE ALERTA_PRESENCIA()
    BEGIN
        DECLARE finished INTEGER DEFAULT 0;  
        
        DECLARE DNI_PACIENTE VARCHAR(9);
        DECLARE TIPO INT;
        DECLARE FECHA DATETIME; 
        
        DECLARE ALERTA INT DEFAULT 0;
        
        DECLARE cursor_Presencias CURSOR FOR
            SELECT SENSOR_PRESENCIA.DNI_PACIENTE, SENSOR_PRESENCIA.ID_TIPO_PRESENCIA, SENSOR_PRESENCIA.FECHA
			FROM 	SENSOR_PRESENCIA
			WHERE   SENSOR_PRESENCIA.FECHA >= NOW() - INTERVAL 1 DAY
            ORDER BY SENSOR_PRESENCIA.FECHA ASC;         
		  
	   DECLARE CONTINUE HANDLER 
            FOR NOT FOUND SET finished = 1;
		
        OPEN cursor_Presencias;        
            lop1: LOOP

                FETCH cursor_Presencias
                    INTO DNI_PACIENTE, TIPO, FECHA;        
                    
                
                IF finished = 1 THEN
					CLOSE cursor_Presencias;
                    LEAVE lop1;                    
                END IF;		
                
                SET ALERTA = CHECK_ALERTA_PRESENCIA(DNI_PACIENTE, FECHA, TIPO);  
                IF TIPO = 3
					THEN
                    IF ALERTA > 36000
						THEN
                        INSERT INTO ALERTA
							(DNI_PACIENTE, SENSOR, FECHA, VALOR)
						VALUES (DNI_PACIENTE, 'PRESENCIA', FECHA, TIPO);  
					END IF;
				ELSE
					IF ALERTA > 14400
						THEN
                        INSERT INTO ALERTA
							(DNI_PACIENTE, SENSOR, FECHA, VALOR)
						VALUES (DNI_PACIENTE, 'PRESENCIA', FECHA, TIPO);    
					END IF;
				END IF;
                
            END LOOP lop1;
            
    END $$
 
DELIMITER ;

#CALL ALERTA_PRESENCIA();