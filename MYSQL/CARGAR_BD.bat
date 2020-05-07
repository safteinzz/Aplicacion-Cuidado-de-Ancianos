@echo off
@cls
echo Por favor copia la direccion de tu mysql.exe
echo Ejemplo - "C:\Program Files\MySQL\MySQL Server 8.0\bin"
set /p ruta="Pegala aqui: " 
set ruta=%ruta%\mysql
echo User de la base de datos (deberia ser root)
set /p user=": " 
echo Password de la base de datos (deberia ser 123456789)
set /p pass=": " 

@echo on
REM FOR %%A IN ("*.sql") DO "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql" --user=root --password=123456789 DBPInf < %%A >output.tab
"%ruta%" --user=%user% --password=%pass% < "Tablas/Provincias y Municipios.sql"
"%ruta%" --user=%user% --password=%pass% DBPInf < "Tablas/Resto de tablas.sql"
"%ruta%" --user=%user% --password=%pass% DBPInf < "Utiles/DATOS EJEMPLO.sql"
"%ruta%" --user=%user% --password=%pass% DBPInf < "Procedimientos/Procedimiento Ritmo Cardiaco.sql"
"%ruta%" --user=%user% --password=%pass% DBPInf < "Procedimientos/Procedimiento Puerta Calle.sql"
"%ruta%" --user=%user% --password=%pass% DBPInf < "Procedimientos/Procedimiento Presencia.sql"
"%ruta%" --user=%user% --password=%pass% DBPInf < "Eventos.sql"

@echo off
@cls
echo Base de datos cargada con exito
TIMEOUT 12
