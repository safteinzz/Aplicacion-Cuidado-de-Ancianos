use pr_itcom;

ALTER USER 'safteinzz'@'%' IDENTIFIED BY '21505519';
UPDATE mysql.user SET Host='localhost' WHERE Host='%' AND User='safteinzz';
FLUSH PRIVILEGES;
select * from mysql.user;

ALTER USER 'root'@'localhost' IDENTIFIED BY '123456789';

UPDATE mysql.user
    SET authentication_string = PASSWORD('123456789'), password_expired = 'N'
    WHERE User = 'root' AND Host = 'localhost';
FLUSH PRIVILEGES;

#rs.getInt("VALOR")
#rs.getString("DNI_PACIENTE")
#rs.getBoolean("LEIDO")
#rs.getDate("FECHA")

SET FOREIGN_KEY_CHECKS=0;

#DELETE FROM usuario WHERE dni = 
delete from asignacion where DNI_ASOCIADO = '70355541N';

insert into ASIGNACION(DNI_ASOCIADO, DNI_ASIGNADO, ID_TIPO)
values 
('70355541N', '70355539N', 1),
('70355541N', '70355540N', 2);




select * from usuario;
select id_Rol from roles;
select * from municipios;
select * from sensor_ritmo_cardiaco where DNI_PACIENTE = '70355541N';
select * from tipo_asignacion;
select * from asignacion;

select * from mensajeria;
select * from etiqueta;
select * from sensor_ritmo_cardiaco WHERE SENSOR_PUERTA_CALLE.DNI_PACIENTE = '98725612P';

select * from sensor_puerta_calle WHERE SENSOR_PUERTA_CALLE.DNI_PACIENTE = '45932542P' and SENSOR_PUERTA_CALLE.FECHA >= NOW() - INTERVAL 1 DAY ORDER BY FECHA DESC;
select * from sensor_puerta_calle WHERE SENSOR_PUERTA_CALLE.DNI_PACIENTE = '98725612P' and SENSOR_PUERTA_CALLE.FECHA >= NOW() - INTERVAL 1 DAY ORDER BY FECHA DESC;
select * from sensor_presencia WHERE SENSOR_PRESENCIA.DNI_PACIENTE = '98725612P' and SENSOR_PRESENCIA.FECHA >= NOW() - INTERVAL 1 DAY ORDER BY FECHA DESC;


 
select * from alerta;


INSERT INTO ALERTA
	(DNI_PACIENTE,FECHA)
VALUES ('46804789K',CURDATE()+1);  

SELECT SENSOR_PUERTA_CALLE.FECHA 
					FROM SENSOR_PUERTA_CALLE 
					WHERE SENSOR_PUERTA_CALLE.FECHA = '2020-02-07 10:00:56'
					AND SENSOR_PUERTA_CALLE.DNI_PACIENTE = '98725612P'
					AND SENSOR_PUERTA_CALLE.ABIERTA = TRUE
					ORDER BY SENSOR_PUERTA_CALLE.FECHA ASC
					LIMIT 1;


#ABIERTA Y CERRADO DEPENDIENDO DE FECHA QUE PASES
SELECT * 
FROM SENSOR_PUERTA_CALLE 
WHERE SENSOR_PUERTA_CALLE.FECHA >= '2020-02-06 14:00:56'
AND SENSOR_PUERTA_CALLE.DNI_PACIENTE = '98725612P'
ORDER BY SENSOR_PUERTA_CALLE.FECHA ASC
LIMIT 2;

SELECT SENSOR_PUERTA_CALLE.DNI_PACIENTE
FROM 	SENSOR_PUERTA_CALLE
WHERE   SENSOR_PUERTA_CALLE.FECHA >= NOW() - INTERVAL 1 DAY
GROUP BY SENSOR_PUERTA_CALLE.DNI_PACIENTE;

SELECT TIMESTAMPDIFF(SECOND,
			(SELECT  SENSOR_PUERTA_CALLE.FECHA
            FROM    SENSOR_PUERTA_CALLE
            WHERE   SENSOR_PUERTA_CALLE.FECHA >= NOW() - INTERVAL 1 DAY
            AND SENSOR_PUERTA_CALLE.ABIERTA = TRUE
            AND DNI_PACIENTE = '98725612P'
            ORDER BY SENSOR_PUERTA_CALLE.FECHA DESC
            LIMIT 1),
            (SELECT  SENSOR_PUERTA_CALLE.FECHA
            FROM    SENSOR_PUERTA_CALLE
            WHERE   SENSOR_PUERTA_CALLE.FECHA >= NOW() - INTERVAL 1 DAY
            AND SENSOR_PUERTA_CALLE.ABIERTA = FALSE
            AND DNI_PACIENTE = '98725612P'
            ORDER BY SENSOR_PUERTA_CALLE.FECHA DESC
            LIMIT 1)
);

INSERT INTO SENSOR_RITMO_CARDIACO 
	(DNI_PACIENTE, FECHA, VALOR)
VALUES
    ('45932542P','2020-02-06 16:00:56',180);
    
SELECT  DNI_PACIENTE,VALOR, FECHA
FROM    SENSOR_RITMO_CARDIACO
WHERE   FECHA >= NOW() - INTERVAL 1 DAY;
               

INSERT INTO SENSOR_PUERTA_CALLE
	(DNI_PACIENTE, FECHA, ABIERTA)
 VALUES
    ('98725612P','2020-02-07 15:00:56',True);


INSERT INTO ALERTA
	(DNI_PACIENTE, SENSOR, FECHA, VALOR)
VALUES ('46080696N', 'RITMOCARDIACO', '2020-02-06 15:00:56', 150);    

INSERT INTO ALERTA
	(DNI_PACIENTE, SENSOR, FECHA, VALOR)
VALUES (DNI_PACIENTE, 'RITMOCARDIACO', FECHA, VALOR);



SELECT USUARIO.* FROM USUARIO,ROL WHERE USUARIO.ID_ROL=ROL.ID_ROL AND ROL.NOMBRE = 'administrador';


insert into USUARIO(DNI, PASS_HASHED, ID_ROL, NOMBRE, APELLIDOS, EMAIL, TLF_MOVIL, TLF_FIJO, ID_MUNICIPIO, DIRECCION, SALT) values 
("root","asdfasf",1,"","","","","",null,"",432);



