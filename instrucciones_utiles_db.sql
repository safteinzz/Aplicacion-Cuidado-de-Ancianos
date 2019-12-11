use DBPInf;

select * from usuario;
select * from rol;

SELECT USUARIO.* FROM USUARIO,ROL WHERE USUARIO.ID_ROL=ROL.ID_ROL AND ROL.NOMBRE = 'administrador';

select * from provincias;
select * from municipios;


