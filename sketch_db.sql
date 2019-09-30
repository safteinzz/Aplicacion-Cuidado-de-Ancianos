DROP DATABASE IF EXISTS DBPInf;
CREATE DATABASE DBPInf;
use DBPInf;

CREATE TABLE IF NOT EXISTS USUARIO (
	ID_USUARIO INT NOT NULL PRIMARY KEY auto_increment,
	NOMBRE VARCHAR(25) NOT NULL,
	APELLIDOS VARCHAR(25) NOT NULL,
    EMAIL VARCHAR(50) NOT NULL,
    PAIS VARCHAR(25) NOT NULL,
    LOCALIDAD VARCHAR(25) NOT NULL,
    DIRECCION VARCHAR(50) NOT NULL,
    TLF_MOVIL VARCHAR(9) NOT NULL,
	TLF_FIJO VARCHAR(9),	
    NOMBRE_USUARIO VARCHAR(25) NOT NULL,
    CONTRASENA_USUARIO VARCHAR(256) NOT NULL,
    SAL VARCHAR(11) NOT NULL
);

CREATE TABLE IF NOT EXISTS ASIGNACION (
	ID_ASIGNACION INT NOT NULL PRIMARY KEY auto_increment,
	ID_RESPONSABLE INT NOT NULL,
	ID_USUARIO INT NOT NULL,
    DESCRIPCION VARCHAR(25),
    FOREIGN KEY FK_RESPONSABLE1(ID_RESPONSABLE) REFERENCES USUARIO(ID_USUARIO),
    FOREIGN KEY FK_RESPONSABLE2(ID_USUARIO) REFERENCES USUARIO(ID_USUARIO)
);

CREATE TABLE IF NOT EXISTS ROL (
	ID_ROL INT NOT NULL PRIMARY KEY,
	NOMBRE VARCHAR(25) NOT NULL,
	DESCRIPCION VARCHAR(25)
);

/* Tabla relacion n:n entre usuario y rol */
CREATE TABLE IF NOT EXISTS ROLES (
	ID_USUARIO INT NOT NULL,
	ID_ROL INT NOT NULL,
    PRIMARY KEY (ID_USUARIO, ID_ROL),
    FOREIGN KEY FK_ROLES1(ID_USUARIO) REFERENCES USUARIO(ID_USUARIO),
    FOREIGN KEY FK_ROLES2(ID_ROL) REFERENCES ROL(ID_ROL)
);

CREATE TABLE IF NOT EXISTS MEDICIONES (
	ID_MEDICION INT NOT NULL PRIMARY KEY,
	ID_USUARIO INT NOT NULL,
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

CREATE TABLE IF NOT EXISTS PROVINCIAS (
  ID INT(10) UNSIGNED NOT NULL PRIMARY KEY,
  PROVINCIA VARCHAR(255) COLLATE UTF8_SPANISH_CI NOT NULL
);

CREATE TABLE PAISES (
ID INT(11) NOT NULL PRIMARY KEY,
ISO CHAR(2) DEFAULT NULL,
NOMBRE VARCHAR(80) DEFAULT NULL
);

INSERT INTO PROVINCIAS 
	(ID, PROVINCIA) 
VALUES
	(1,'ÁLAVA'),
	(2,'ALBACETE'),
	(3, 'ALICANTE'),
	(4, 'ALMERÍA'),
	(5, 'ÁVILA'),
	(6, 'BADAJOZ'),
	(7, 'ILLES BALEARS'),
	(8, 'BARCELONA'),
	(9, 'BURGOS'),
	(10, 'CÁCERES'),
	(11, 'CÁDIZ'),
	(12, 'CASTELLÓN'),
	(13, 'CIUDAD REAL'),
	(14, 'CÓRDOBA'),
	(15, 'A CORUÑA'),
	(16, 'CUENCA'),
	(17, 'GIRONA'),
	(18, 'GRANADA'),
	(19, 'GUADALAJARA'),
	(20, 'GUIPÚZCOA'),
	(21, 'HUELVA'),
	(22, 'HUESCA'),
	(23, 'JAÉN'),
	(24, 'LEÓN'),
	(25, 'LLEIDA'),
	(26, 'LA RIOJA'),
	(27, 'LUGO'),
	(28, 'MADRID'),
	(29, 'MÁLAGA'),
	(30, 'MURCIA'),
	(31, 'NAVARRA'),
	(32, 'OURENSE'),
	(33, 'ASTURIAS'),
	(34, 'PALENCIA'),
	(35, 'LAS PALMAS'),
	(36, 'PONTEVEDRA'),
	(37, 'SALAMANCA'),
	(38, 'SANTA CRUZ DE TENERIFE'),
	(39, 'CANTABRIA'),
	(40, 'SEGOVIA'),
	(41, 'SEVILLA'),
	(42, 'SORIA'),
	(43, 'TARRAGONA'),
	(44, 'TERUEL'),
	(45, 'TOLEDO'),
	(46, 'VALENCIA'),
	(47, 'VALLADOLID'),
	(48, 'VIZCAYA'),
	(49, 'ZAMORA'),
	(50, 'ZARAGOZA'),
	(51, 'CEUTA'),
	(52, 'MELILLA');
 
INSERT INTO PAISES
	(ID,ISO,NOMBRE)
VALUES
	(1, 'AF', 'AFGANISTÁN'),
	(2, 'AX', 'ISLAS GLAND'),
	(3, 'AL', 'ALBANIA'),
	(4, 'DE', 'ALEMANIA'),
	(5, 'AD', 'ANDORRA'),
	(6, 'AO', 'ANGOLA'),
	(7, 'AI', 'ANGUILLA'),
	(8, 'AQ', 'ANTÁRTIDA'),
	(9, 'AG', 'ANTIGUA Y BARBUDA'),
	(10, 'AN', 'ANTILLAS HOLANDESAS'),
	(11, 'SA', 'ARABIA SAUDÍ'),
	(12, 'DZ', 'ARGELIA'),
	(13, 'AR', 'ARGENTINA'),
	(14, 'AM', 'ARMENIA'),
	(15, 'AW', 'ARUBA'),
	(16, 'AU', 'AUSTRALIA'),
	(17, 'AT', 'AUSTRIA'),
	(18, 'AZ', 'AZERBAIYÁN'),
	(19, 'BS', 'BAHAMAS'),
	(20, 'BH', 'BAHRÉIN'),
	(21, 'BD', 'BANGLADESH'),
	(22, 'BB', 'BARBADOS'),
	(23, 'BY', 'BIELORRUSIA'),
	(24, 'BE', 'BÉLGICA'),
	(25, 'BZ', 'BELICE'),
	(26, 'BJ', 'BENIN'),
	(27, 'BM', 'BERMUDAS'),
	(28, 'BT', 'BHUTÁN'),
	(29, 'BO', 'BOLIVIA'),
	(30, 'BA', 'BOSNIA Y HERZEGOVINA'),
	(31, 'BW', 'BOTSUANA'),
	(32, 'BV', 'ISLA BOUVET'),
	(33, 'BR', 'BRASIL'),
	(34, 'BN', 'BRUNÉI'),
	(35, 'BG', 'BULGARIA'),
	(36, 'BF', 'BURKINA FASO'),
	(37, 'BI', 'BURUNDI'),
	(38, 'CV', 'CABO VERDE'),
	(39, 'KY', 'ISLAS CAIMÁN'),
	(40, 'KH', 'CAMBOYA'),
	(41, 'CM', 'CAMERÚN'),
	(42, 'CA', 'CANADÁ'),
	(43, 'CF', 'REPÚBLICA CENTROAFRICANA'),
	(44, 'TD', 'CHAD'),
	(45, 'CZ', 'REPÚBLICA CHECA'),
	(46, 'CL', 'CHILE'),
	(47, 'CN', 'CHINA'),
	(48, 'CY', 'CHIPRE'),
	(49, 'CX', 'ISLA DE NAVIDAD'),
	(50, 'VA', 'CIUDAD DEL VATICANO'),
	(51, 'CC', 'ISLAS COCOS'),
	(52, 'CO', 'COLOMBIA'),
	(53, 'KM', 'COMORAS'),
	(54, 'CD', 'REPÚBLICA DEMOCRÁTICA DEL CONGO'),
	(55, 'CG', 'CONGO'),
	(56, 'CK', 'ISLAS COOK'),
	(57, 'KP', 'COREA DEL NORTE'),
	(58, 'KR', 'COREA DEL SUR'),
	(59, 'CI', 'COSTA DE MARFIL'),
	(60, 'CR', 'COSTA RICA'),
	(61, 'HR', 'CROACIA'),
	(62, 'CU', 'CUBA'),
	(63, 'DK', 'DINAMARCA'),
	(64, 'DM', 'DOMINICA'),
	(65, 'DO', 'REPÚBLICA DOMINICANA'),
	(66, 'EC', 'ECUADOR'),
	(67, 'EG', 'EGIPTO'),
	(68, 'SV', 'EL SALVADOR'),
	(69, 'AE', 'EMIRATOS ÁRABES UNIDOS'),
	(70, 'ER', 'ERITREA'),
	(71, 'SK', 'ESLOVAQUIA'),
	(72, 'SI', 'ESLOVENIA'),
	(73, 'ES', 'ESPAÑA'),
	(74, 'UM', 'ISLAS ULTRAMARINAS DE ESTADOS UNIDOS'),
	(75, 'US', 'ESTADOS UNIDOS'),
	(76, 'EE', 'ESTONIA'),
	(77, 'ET', 'ETIOPÍA'),
	(78, 'FO', 'ISLAS FEROE'),
	(79, 'PH', 'FILIPINAS'),
	(80, 'FI', 'FINLANDIA'),
	(81, 'FJ', 'FIYI'),
	(82, 'FR', 'FRANCIA'),
	(83, 'GA', 'GABÓN'),
	(84, 'GM', 'GAMBIA'),
	(85, 'GE', 'GEORGIA'),
	(86, 'GS', 'ISLAS GEORGIAS DEL SUR Y SANDWICH DEL SUR'),
	(87, 'GH', 'GHANA'),
	(88, 'GI', 'GIBRALTAR'),
	(89, 'GD', 'GRANADA'),
	(90, 'GR', 'GRECIA'),
	(91, 'GL', 'GROENLANDIA'),
	(92, 'GP', 'GUADALUPE'),
	(93, 'GU', 'GUAM'),
	(94, 'GT', 'GUATEMALA'),
	(95, 'GF', 'GUAYANA FRANCESA'),
	(96, 'GN', 'GUINEA'),
	(97, 'GQ', 'GUINEA ECUATORIAL'),
	(98, 'GW', 'GUINEA-BISSAU'),
	(99, 'GY', 'GUYANA'),
	(100, 'HT', 'HAITÍ'),
	(101, 'HM', 'ISLAS HEARD Y MCDONALD'),
	(102, 'HN', 'HONDURAS'),
	(103, 'HK', 'HONG KONG'),
	(104, 'HU', 'HUNGRÍA'),
	(105, 'IN', 'INDIA'),
	(106, 'ID', 'INDONESIA'),
	(107, 'IR', 'IRÁN'),
	(108, 'IQ', 'IRAQ'),
	(109, 'IE', 'IRLANDA'),
	(110, 'IS', 'ISLANDIA'),
	(111, 'IL', 'ISRAEL'),
	(112, 'IT', 'ITALIA'),
	(113, 'JM', 'JAMAICA'),
	(114, 'JP', 'JAPÓN'),
	(115, 'JO', 'JORDANIA'),
	(116, 'KZ', 'KAZAJSTÁN'),
	(117, 'KE', 'KENIA'),
	(118, 'KG', 'KIRGUISTÁN'),
	(119, 'KI', 'KIRIBATI'),
	(120, 'KW', 'KUWAIT'),
	(121, 'LA', 'LAOS'),
	(122, 'LS', 'LESOTHO'),
	(123, 'LV', 'LETONIA'),
	(124, 'LB', 'LÍBANO'),
	(125, 'LR', 'LIBERIA'),
	(126, 'LY', 'LIBIA'),
	(127, 'LI', 'LIECHTENSTEIN'),
	(128, 'LT', 'LITUANIA'),
	(129, 'LU', 'LUXEMBURGO'),
	(130, 'MO', 'MACAO'),
	(131, 'MK', 'ARY MACEDONIA'),
	(132, 'MG', 'MADAGASCAR'),
	(133, 'MY', 'MALASIA'),
	(134, 'MW', 'MALAWI'),
	(135, 'MV', 'MALDIVAS'),
	(136, 'ML', 'MALÍ'),
	(137, 'MT', 'MALTA'),
	(138, 'FK', 'ISLAS MALVINAS'),
	(139, 'MP', 'ISLAS MARIANAS DEL NORTE'),
	(140, 'MA', 'MARRUECOS'),
	(141, 'MH', 'ISLAS MARSHALL'),
	(142, 'MQ', 'MARTINICA'),
	(143, 'MU', 'MAURICIO'),
	(144, 'MR', 'MAURITANIA'),
	(145, 'YT', 'MAYOTTE'),
	(146, 'MX', 'MÉXICO'),
	(147, 'FM', 'MICRONESIA'),
	(148, 'MD', 'MOLDAVIA'),
	(149, 'MC', 'MÓNACO'),
	(150, 'MN', 'MONGOLIA'),
	(151, 'MS', 'MONTSERRAT'),
	(152, 'MZ', 'MOZAMBIQUE'),
	(153, 'MM', 'MYANMAR'),
	(154, 'NA', 'NAMIBIA'),
	(155, 'NR', 'NAURU'),
	(156, 'NP', 'NEPAL'),
	(157, 'NI', 'NICARAGUA'),
	(158, 'NE', 'NÍGER'),
	(159, 'NG', 'NIGERIA'),
	(160, 'NU', 'NIUE'),
	(161, 'NF', 'ISLA NORFOLK'),
	(162, 'NO', 'NORUEGA'),
	(163, 'NC', 'NUEVA CALEDONIA'),
	(164, 'NZ', 'NUEVA ZELANDA'),
	(165, 'OM', 'OMÁN'),
	(166, 'NL', 'PAÍSES BAJOS'),
	(167, 'PK', 'PAKISTÁN'),
	(168, 'PW', 'PALAU'),
	(169, 'PS', 'PALESTINA'),
	(170, 'PA', 'PANAMÁ'),
	(171, 'PG', 'PAPÚA NUEVA GUINEA'),
	(172, 'PY', 'PARAGUAY'),
	(173, 'PE', 'PERÚ'),
	(174, 'PN', 'ISLAS PITCAIRN'),
	(175, 'PF', 'POLINESIA FRANCESA'),
	(176, 'PL', 'POLONIA'),
	(177, 'PT', 'PORTUGAL'),
	(178, 'PR', 'PUERTO RICO'),
	(179, 'QA', 'QATAR'),
	(180, 'GB', 'REINO UNIDO'),
	(181, 'RE', 'REUNIÓN'),
	(182, 'RW', 'RUANDA'),
	(183, 'RO', 'RUMANIA'),
	(184, 'RU', 'RUSIA'),
	(185, 'EH', 'SAHARA OCCIDENTAL'),
	(186, 'SB', 'ISLAS SALOMÓN'),
	(187, 'WS', 'SAMOA'),
	(188, 'AS', 'SAMOA AMERICANA'),
	(189, 'KN', 'SAN CRISTÓBAL Y NEVIS'),
	(190, 'SM', 'SAN MARINO'),
	(191, 'PM', 'SAN PEDRO Y MIQUELÓN'),
	(192, 'VC', 'SAN VICENTE Y LAS GRANADINAS'),
	(193, 'SH', 'SANTA HELENA'),
	(194, 'LC', 'SANTA LUCÍA'),
	(195, 'ST', 'SANTO TOMÉ Y PRÍNCIPE'),
	(196, 'SN', 'SENEGAL'),
	(197, 'CS', 'SERBIA Y MONTENEGRO'),
	(198, 'SC', 'SEYCHELLES'),
	(199, 'SL', 'SIERRA LEONA'),
	(200, 'SG', 'SINGAPUR'),
	(201, 'SY', 'SIRIA'),
	(202, 'SO', 'SOMALIA'),
	(203, 'LK', 'SRI LANKA'),
	(204, 'SZ', 'SUAZILANDIA'),
	(205, 'ZA', 'SUDÁFRICA'),
	(206, 'SD', 'SUDÁN'),
	(207, 'SE', 'SUECIA'),
	(208, 'CH', 'SUIZA'),
	(209, 'SR', 'SURINAM'),
	(210, 'SJ', 'SVALBARD Y JAN MAYEN'),
	(211, 'TH', 'TAILANDIA'),
	(212, 'TW', 'TAIWÁN'),
	(213, 'TZ', 'TANZANIA'),
	(214, 'TJ', 'TAYIKISTÁN'),
	(215, 'IO', 'TERRITORIO BRITÁNICO DEL OCÉANO ÍNDICO'),
	(216, 'TF', 'TERRITORIOS AUSTRALES FRANCESES'),
	(217, 'TL', 'TIMOR ORIENTAL'),
	(218, 'TG', 'TOGO'),
	(219, 'TK', 'TOKELAU'),
	(220, 'TO', 'TONGA'),
	(221, 'TT', 'TRINIDAD Y TOBAGO'),
	(222, 'TN', 'TÚNEZ'),
	(223, 'TC', 'ISLAS TURCAS Y CAICOS'),
	(224, 'TM', 'TURKMENISTÁN'),
	(225, 'TR', 'TURQUÍA'),
	(226, 'TV', 'TUVALU'),
	(227, 'UA', 'UCRANIA'),
	(228, 'UG', 'UGANDA'),
	(229, 'UY', 'URUGUAY'),
	(230, 'UZ', 'UZBEKISTÁN'),
	(231, 'VU', 'VANUATU'),
	(232, 'VE', 'VENEZUELA'),
	(233, 'VN', 'VIETNAM'),
	(234, 'VG', 'ISLAS VÍRGENES BRITÁNICAS'),
	(235, 'VI', 'ISLAS VÍRGENES DE LOS ESTADOS UNIDOS'),
	(236, 'WF', 'WALLIS Y FUTUNA'),
	(237, 'YE', 'YEMEN'),
	(238, 'DJ', 'YIBUTI'),
	(239, 'ZM', 'ZAMBIA'),
	(240, 'ZW', 'ZIMBABUE');