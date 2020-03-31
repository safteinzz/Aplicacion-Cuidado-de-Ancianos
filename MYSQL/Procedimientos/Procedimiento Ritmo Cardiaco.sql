use DBPInf;

#https://dev.mysql.com/doc/refman/8.0/en/create-procedure.html
# Para el sensor de ritmo cardiaco se creará un procedimiento que registrará una alerta si el valor registrado por el sensor es superior a 160 pulsaciones
# o inferior a 60 pulsaciones (propongo estos datos para que por lo menos haya alguna alerta de este tipo, incluso diría que algo más pequeño para registrar alguna).
# Los valores a registrar son:
#  "DNI_PACIENTE" = SENSOR_RITMO_CARDIACO.DNI_PACIENTE
#  "SENSOR" = "RITMOCARDIACO"
#  "VALOR" = SENSOR_RITMO_CARDIACO.VALOR
#  "FECHA" = SENSOR_RITMO_CARDIACO.FECHA


DROP PROCEDURE IF EXISTS ALERTA_RITMO_CARDIACO;
 
DELIMITER $$
 
CREATE PROCEDURE ALERTA_RITMO_CARDIACO()
    BEGIN
        DECLARE finished INTEGER DEFAULT 0;  
        
        DECLARE DNI_PACIENTE VARCHAR(9);
        DECLARE VALOR INT;
        DECLARE FECHA DATETIME;   
        
        DECLARE cursor_ritmoCardiaco CURSOR FOR
            SELECT  SENSOR_RITMO_CARDIACO.DNI_PACIENTE,SENSOR_RITMO_CARDIACO.VALOR,SENSOR_RITMO_CARDIACO.FECHA
            FROM    SENSOR_RITMO_CARDIACO
            WHERE   SENSOR_RITMO_CARDIACO.FECHA >= NOW() - INTERVAL 1 DAY;
            
	   DECLARE CONTINUE HANDLER 
            FOR NOT FOUND SET finished = 1;
            
        OPEN cursor_ritmoCardiaco;
        
            checkPulsaciones: LOOP
            
                FETCH cursor_ritmoCardiaco
                    INTO DNI_PACIENTE,VALOR,FECHA;                   
                
                IF finished = 1 THEN
                    LEAVE checkPulsaciones;                    
                END IF;			
                
                IF VALOR > 160 || VALOR < 60
                    THEN                  
                    INSERT INTO ALERTA
                        (DNI_PACIENTE, SENSOR, FECHA, VALOR)
                    VALUES (DNI_PACIENTE, 'RITMOCARDIACO', FECHA, VALOR);                
                END IF;  
                
            END LOOP checkPulsaciones;
            
        CLOSE cursor_ritmoCardiaco;
        
    END $$

DELIMITER ;

#CALL ALERTA_RITMO_CARDIACO();
