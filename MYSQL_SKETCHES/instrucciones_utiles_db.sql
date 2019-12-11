use DBPInf;

select * from usuario;
select id_Rol from roles;

SELECT USUARIO.* FROM USUARIO,ROL WHERE USUARIO.ID_ROL=ROL.ID_ROL AND ROL.NOMBRE = 'administrador';


insert into USUARIO(DNI, PASS_HASHED, ID_ROL, NOMBRE, APELLIDOS, EMAIL, TLF_MOVIL, TLF_FIJO, ID_MUNICIPIO, DIRECCION, SALT) values 
("root","asdfasf",1,"","","","","",null,"",432);



