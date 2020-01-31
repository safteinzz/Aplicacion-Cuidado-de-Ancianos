ALTER USER 'safteinzz'@'%' IDENTIFIED BY '21505519';
UPDATE mysql.user SET Host='localhost' WHERE Host='%' AND User='safteinzz';
FLUSH PRIVILEGES;
select * from mysql.user;

ALTER USER 'root'@'localhost' IDENTIFIED BY '123456789';

UPDATE mysql.user
    SET authentication_string = PASSWORD('123456789'), password_expired = 'N'
    WHERE User = 'root' AND Host = 'localhost';
FLUSH PRIVILEGES;



use DBPInf;

select * from usuario;
select id_Rol from roles;
select * from municipios;
select * from sensor_ritmo_cardiaco;

SELECT USUARIO.* FROM USUARIO,ROL WHERE USUARIO.ID_ROL=ROL.ID_ROL AND ROL.NOMBRE = 'administrador';


insert into USUARIO(DNI, PASS_HASHED, ID_ROL, NOMBRE, APELLIDOS, EMAIL, TLF_MOVIL, TLF_FIJO, ID_MUNICIPIO, DIRECCION, SALT) values 
("root","asdfasf",1,"","","","","",null,"",432);



