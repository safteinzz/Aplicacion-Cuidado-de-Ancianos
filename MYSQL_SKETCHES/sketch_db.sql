use DBPInf;

CREATE TABLE IF NOT EXISTS ROLES (
	#ATRIBUTOS
		#PRIMARY KEY
			ID_ROL INT NOT NULL PRIMARY KEY,
		#RESTO
			NOMBRE VARCHAR(25) NOT NULL,
			DESCRIPCION VARCHAR(100)
);

INSERT INTO ROLES
	(ID_ROL, NOMBRE, DESCRIPCION)
VALUES
	(1,'Administrador', 'Encargado de registrar y editar usuarios de la aplicación'),
    (2,'Medico', 'Encargado sanitario de pacientes, tiene asignados N pacientes'),
    (3,'Familiar', 'Encargado familiar del paciente, tiene asignados N pacientes'),
    (4,'Paciente', 'Paciente, tiene asignado 1 medico y 1 familiar');

CREATE TABLE IF NOT EXISTS USUARIO (
	#RELACIONES
		ID_ROL INT NOT NULL,
		ID_MUNICIPIO INT,
    
    #ATRIBUTOS
		#PRIMARY KEY
			DNI VARCHAR(9) PRIMARY KEY,
		
        #CONTRASENA
			PASS_HASHED VARCHAR(256) NOT NULL,
			SALT blob NOT NULL,
		
        #RESTO
			NOMBRE VARCHAR(25),
			APELLIDOS VARCHAR(25),
			EMAIL VARCHAR(50),
			DIRECCION VARCHAR(50),
			TLF_MOVIL VARCHAR(9),
			TLF_FIJO VARCHAR(9),	  
            
	#CLAVES FORANEAS		
		FOREIGN KEY FK_ROLES(ID_ROL) REFERENCES ROLES(ID_ROL),
		FOREIGN KEY FK_MUNICIPIO(ID_MUNICIPIO) REFERENCES MUNICIPIOS(ID_MUNICIPIO)
);

CREATE TABLE IF NOT EXISTS TIPO_ASIGNACION (
	#ATRIBUTOS
		#PRIMARY KEY
			ID_TIPO INT NOT NULL PRIMARY KEY,
		#RESTO
			DESCRIPCION VARCHAR(50)
);

INSERT INTO TIPO_ASIGNACION
	(ID_TIPO, DESCRIPCION)
VALUES
	(1,'Medico - Paciente / Paciente - Medico'),
    (2,'Familiar - Paciente / Paciente - Familiar');


CREATE TABLE IF NOT EXISTS ASIGNACION (
	#RELACIONES
		#USUARIO
			DNI_ASOCIADO VARCHAR(9) NOT NULL,
			DNI_ASIGNADO VARCHAR(9) NOT NULL,			
        #TIPO_ASIGNACION
			ID_TIPO INT NOT NULL,
        
	#ATRIBUTOS
		DESCRIPCION VARCHAR(25),
        
    #CLAVES FORANEAS
		FOREIGN KEY FK_RESPONSABLE1(DNI_RESPONSABLE) REFERENCES USUARIO(DNI),
		FOREIGN KEY FK_RESPONSABLE2(DNI_USUARIO) REFERENCES USUARIO(DNI),
		FOREIGN KEY FK_TIPO1(ID_TIPO) REFERENCES TIPO_ASIGNACION(ID_TIPO)
);

CREATE TABLE IF NOT EXISTS MENSAJERIA (
	#RELACIONES
		DNI_EMISOR VARCHAR(9) NOT NULL,
		DNI_RECEPTOR VARCHAR(9) NOT NULL,
        
	#ATRIBUTOS
		LEIDO BOOL NOT NULL,
		ASUNTO VARCHAR(100),
		MENSAJE VARCHAR(2500),
        
	#CLAVES FORANEAS
		FOREIGN KEY FK_EMISOR(DNI_EMISOR) REFERENCES USUARIO(DNI),
		FOREIGN KEY FK_RECEPTOR(DNI_RECEPTOR) REFERENCES USUARIO(DNI)
);

/* Aun no esta claro
CREATE TABLE IF NOT EXISTS MEDICIONES (
	ID_MEDICION INT NOT NULL PRIMARY KEY auto_increment,
	DNI_USUARIO INT NOT NULL,
	SENSOR VARCHAR(25) NOT NULL,
	MEDICION VARCHAR(25) NOT NULL,
    FOREIGN KEY FK_MEDICIONES(ID_USUARIO) REFERENCES USUARIO(ID_USUARIO)
);


CREATE TABLE IF NOT EXISTS ACCION (
	ID_ACCION INT NOT NULL PRIMARY KEY auto_increment,
	DESCRIPCION VARCHAR(25)
);

CREATE TABLE IF NOT EXISTS HISTORICO (
	ID_HISTORICO INT NOT NULL PRIMARY KEY auto_increment,
    ID_USUARIO INT NOT NULL,
	ID_ACCION INT NOT NULL,
	CAMBIO_ANTIGUO VARCHAR(50) NOT NULL,
	CAMBIO_NUEVO VARCHAR(50) NOT NULL,
    FOREIGN KEY FK_HISTORICO1(ID_USUARIO) REFERENCES USUARIO(ID_USUARIO),
    FOREIGN KEY FK_HISTORICO2(ID_ACCION) REFERENCES ACCION(ID_ACCION)
);
*/



 
