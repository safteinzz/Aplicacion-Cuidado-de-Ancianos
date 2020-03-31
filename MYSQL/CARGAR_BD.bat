@echo off
@cls
echo Por favor copia la direccion de tu mysql.exe
echo Ejemplo - "C:\Program Files\MySQL\MySQL Server 8.0\bin"
set /p ruta="Pegala aqui: " 
set ruta=%ruta%\mysql

@echo on
REM FOR %%A IN ("*.sql") DO "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql" --user=root --password=123456789 DBPInf < %%A >output.tab
"%ruta%" --user=root --password=123456789 < "Tablas/Provincias y Municipios.sql"
"%ruta%" --user=root --password=123456789 DBPInf < "Tablas/Resto de tablas.sql"
"%ruta%" --user=root --password=123456789 DBPInf < "Utiles/DATOS EJEMPLO.sql"
"%ruta%" --user=root --password=123456789 DBPInf < "Procedimientos/Procedimiento Ritmo Cardiaco.sql"
"%ruta%" --user=root --password=123456789 DBPInf < "Procedimientos/Procedimiento Puerta Calle.sql"
"%ruta%" --user=root --password=123456789 DBPInf < "Procedimientos/Procedimiento Presencia.sql"
"%ruta%" --user=root --password=123456789 DBPInf < "Eventos.sql"

@echo off
@cls
echo Base de datos cargada con exito
TIMEOUT 12
