use DBPInf;


# =============================================================================
# ~Tabla ROLES
#
#		Se relaciona con USUARIO
#			@ID_ROL, No se necesita auto_increment porque los valores son fijos
#
# =============================================================================
CREATE TABLE IF NOT EXISTS ROLES (
	
    #RELACIONES
    
		#PRIMARY KEY
        
			 ID_ROL INT NOT NULL PRIMARY KEY,
             
    #ATRIBUTOS
			
		NOMBRE VARCHAR(25) NOT NULL,
		DESCRIPCION VARCHAR(100)
);


##
#	Insert a ROLES
##
INSERT INTO ROLES
	(ID_ROL, NOMBRE, DESCRIPCION)
VALUES
	(1,'Administrador', 'Encargado de registrar y editar usuarios de la aplicación'),
    (2,'Medico', 'Encargado sanitario de pacientes, tiene asignados N pacientes'),
    (3,'Familiar', 'Encargado familiar del paciente, tiene asignados N pacientes'),
    (4,'Paciente', 'Paciente, tiene asignado 1 medico y 1 familiar');


# =============================================================================
# ~Tabla USUARIO
#
#		Se relaciona con ROLES
#			@ID_ROL, Clave Foranea
#
#
#		Se relaciona con MUNICIPIO
#			@ID_MUNICIPIO, Clave Foranea
#
#
#		Se relaciona con MENSAJERIA
#			@DNI, se utiliza una relación n a n con sigo mismo
#
#
#		Se relaciona con ASIGNACION
#			@DNI, se utiliza una relación n a n con sigo mismo
#
#
#		Se relaciona con SENSOR_RITMO_CARDIACO
#			@DNI, se utiliza como relación 1 a n
#
#
#		Se relaciona con SENSOR_PUERTA_CALLE
#			@DNI, se utiliza como relación 1 a n
#
#
#		Se relaciona con SENSOR_PRESENCIA
#			@DNI, se utiliza como relación 1 a n
#
# =============================================================================
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


# =============================================================================
# ~Tabla TIPO_ASIGNACION
#
#		Se relaciona con ASIGNACION
#			@ID_ROL, No se necesita auto_increment porque los valores son fijos
#
# =============================================================================
CREATE TABLE IF NOT EXISTS TIPO_ASIGNACION (

	#RELACIONES
    
		#PRIMARY KEY
        
			ID_TIPO INT NOT NULL PRIMARY KEY,
    
	#ATRIBUTOS
        
		DESCRIPCION VARCHAR(50)
);


##
#	Inserts a TIPO_ASIGNACION
##
INSERT INTO TIPO_ASIGNACION
	(ID_TIPO, DESCRIPCION)
VALUES
	(1,'Medico - Paciente / Paciente - Medico'),
    (2,'Familiar - Paciente / Paciente - Familiar');


# =============================================================================
# ~Tabla ASIGNACION
#
#		Se relaciona con USUARIO (Se usa como tabla en la relacion n a n)
#			@DNI_ASOCIADO, Clave foranea
#			@DNI_ASIGNADO, Clave foranea
#
#
#		Se relaciona con TIPO_ASIGNACION
#			@ID_TIPO, Clave Foranea
#
# =============================================================================
CREATE TABLE IF NOT EXISTS ASIGNACION (
	
    #RELACIONES
    
		#USUARIO
        
			DNI_ASOCIADO VARCHAR(9) NOT NULL,
			DNI_ASIGNADO VARCHAR(9) NOT NULL,			
        
        #TIPO DE ASIGNACION
        
			ID_TIPO INT NOT NULL,        
        
    #CLAVES FORANEAS
    
		FOREIGN KEY FK_RESPONSABLE1(DNI_ASIGNADO) REFERENCES USUARIO(DNI),
		FOREIGN KEY FK_RESPONSABLE2(DNI_ASOCIADO) REFERENCES USUARIO(DNI),
		FOREIGN KEY FK_TIPO1(ID_TIPO) REFERENCES TIPO_ASIGNACION(ID_TIPO)
);


# =============================================================================
# ~Tabla ETIQUETA
#
#		Se relaciona con MENSAJERIA
#			@ID_ETIQUETA, No se necesita auto_increment porque los valores son fijos
#
# =============================================================================
CREATE TABLE IF NOT EXISTS ETIQUETA (

	#RELACIONES
    
		ID_ETIQUETA INT NOT NULL PRIMARY KEY,
        
	#ATRIBUTOS
    
		ETIQUETA VARCHAR(11) NOT NULL        
);


##
#	Inserts a la tabla ETIQUETA
##
INSERT INTO ETIQUETA
	(ID_ETIQUETA, ETIQUETA)
VALUES
	(1,'Consulta'),
    (2,'Revisión'),
    (3,'Resultados'),
    (4,'Tratamiento');


# =============================================================================
# ~Tabla MENSAJERIA
#
#		Se relaciona con USUARIO (Se usa como tabla en la relacion n a n)
#			@DNI_EMISOR, Clave foranea
#           @DNI_RECEPTOR, Clave foranea
#
#		Se relaciona con ETIQUETA
#			@ID_ETIQUETA, Clave foranea
#
# =============================================================================
CREATE TABLE IF NOT EXISTS MENSAJERIA (
	
    #RELACIONES
    
		#USUARIO
    
			DNI_EMISOR VARCHAR(9) NOT NULL,
			DNI_RECEPTORES VARCHAR(200) NOT NULL,
            
		#ETIQUETA
        
			ID_ETIQUETA INT,
        
	#ATRIBUTOS
    
		LEIDO BOOL NOT NULL,
		ASUNTO VARCHAR(100),
		MENSAJE VARCHAR(2500),
        
	#CLAVES FORANEAS
    
		FOREIGN KEY FK_EMISOR(DNI_EMISOR) REFERENCES USUARIO(DNI),
		FOREIGN KEY FK_RECEPTOR(DNI_RECEPTORES) REFERENCES USUARIO(DNI),
        FOREIGN KEY FK_ETIQUETA(ID_ETIQUETA) REFERENCES ETIQUETA(ID_ETIQUETA)
);


# =============================================================================
# ~Tabla SENSOR_RITMO CARDIACO
#
#		Se relaciona con USUARIO en la relacion 1 a n 
#			@DNI_PACIENTE, Clave foranea
#
# =============================================================================
CREATE TABLE IF NOT EXISTS SENSOR_RITMO_CARDIACO (
	
    #RELACIONES
		
        DNI_PACIENTE VARCHAR(9) NOT NULL,
        
	#ATRIBUTOS
        
		VALOR INT NOT NULL,
        FECHA DATETIME NOT NULL,
            
	#CLAVES FORANEAS
    
		FOREIGN KEY FK_RITMO_CARDIACO(DNI_PACIENTE) REFERENCES USUARIO(DNI)
);


# =============================================================================
# ~Tabla SENSOR_PUERTA_CALLE
#
#		Se relaciona con USUARIO en la relacion 1 a n 
#			@DNI_PACIENTE, Clave foranea
#
# =============================================================================
CREATE TABLE IF NOT EXISTS SENSOR_PUERTA_CALLE (
	
    #RELACIONES
		
        DNI_PACIENTE VARCHAR(9) NOT NULL,
        
	#ATRIBUTOS
        
		ABIERTA BOOLEAN NOT NULL,
        FECHA DATETIME NOT NULL,
            
	#CLAVES FORANEAS
    
		FOREIGN KEY FK_RITMO_CARDIACO(DNI_PACIENTE) REFERENCES USUARIO(DNI)
);


# =============================================================================
# ~Tabla TIPO_PRESENCIA
#
#		Se relaciona con SENSOR_PRESENCIA en la relacion 1 a n 
#			@ID_PRESENCIA, No se necesita auto_increment porque los valores son fijos
#
# =============================================================================
CREATE TABLE IF NOT EXISTS TIPO_PRESENCIA(
	
    #RELACIONES
    
		#PRIMARY KEY
			
            ID_PRESENCIA INT NOT NULL PRIMARY KEY,
	
    #ATRIBUTOS    
		
		LUGAR VARCHAR(50) NOT NULL
);


##
#	Inserts a TIPO_PRESENCIA
##
INSERT INTO TIPO_PRESENCIA 
	VALUES
    (1,'Salón'),
    (2,'Cocina'),
    (3,'Habitación');


# =============================================================================
# ~Tabla SENSOR_PRESENCIA
#
#		Se relaciona con USUARIO en la relacion 1 a n 
#			@DNI_PACIENTE, Clave foranea
#
#
#		Se relaciona con TIPO_PRESENCIA en la relacion 1 a n 
#			@ID_TIPO_PRESENCIA, Clave foranea
#
# =============================================================================
CREATE TABLE IF NOT EXISTS SENSOR_PRESENCIA (
	
    #RELACIONES
		
        DNI_PACIENTE VARCHAR(9) NOT NULL,
		ID_TIPO_PRESENCIA INT NOT NULL,
        
	#ATRIBUTOS
        
        FECHA DATETIME NOT NULL,
            
	#CLAVES FORANEAS
    
		FOREIGN KEY FK_RITMO_CARDIACO(DNI_PACIENTE) REFERENCES USUARIO(DNI),
        FOREIGN KEY FK_TIPO_PRESENCIA(ID_TIPO_PRESENCIA) REFERENCES TIPO_PRESENCIA(ID_PRESENCIA)
);


# =============================================================================
# ~Tabla NOTA
#
#		Se relaciona con USUARIO en la relacion 1 a n 
#			@DNI, Clave foranea
#
# =============================================================================
CREATE TABLE IF NOT EXISTS NOTA (

	#RELACIONES
    
		DNI VARCHAR(9) NOT NULL,
        
	#ATRIBUTOS
    
		FECHA DATE,
        NOTA VARCHAR(500),
        
	#CLAVES FORANEAS
    
		FOREIGN KEY FK_NOTA(DNI) REFERENCES USUARIO(DNI)
);


# =============================================================================
# ~Tabla ALERTA
#
#		Se relaciona con USUARIO en la relacion 1 a n 
#			@DNI, Clave foranea
#
# =============================================================================
CREATE TABLE IF NOT EXISTS ALERTA (

	#RELACIONES
    
		DNI_PACIENTE VARCHAR(9) NOT NULL,
        
	#ATRIBUTOS
    
		SENSOR VARCHAR(15),
        VALOR INT,
		FECHA DATETIME,
        
        
	#CLAVES FORANEAS
    
		FOREIGN KEY FK_ALERTA(DNI_PACIENTE) REFERENCES USUARIO(DNI)
);

/* ESTO ESTA EN LINEAS FUTURAS
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



 
