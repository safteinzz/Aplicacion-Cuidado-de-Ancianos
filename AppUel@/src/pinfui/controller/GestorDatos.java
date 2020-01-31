/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.UnknownHostException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import pinfui.dto.ConstantesAplicacion;
import pinfui.dto.TipoRango;
import pinfui.entidades.Asignacion;
import pinfui.entidades.Mensaje;
import pinfui.entidades.Municipio;
import pinfui.entidades.Presencia;
import pinfui.entidades.Provincia;
import pinfui.entidades.PuertaCalle;
import pinfui.entidades.RitmoCardiaco;
import pinfui.entidades.Rol;
import pinfui.entidades.TipoAsignacion;
import pinfui.entidades.TipoPresencia;
import pinfui.entidades.Usuario;

/**
 * Clase especifica para el control de datos tanto en JSON como en BD SQL
 *
 * @author ITCOM
 */
public class GestorDatos {

	// DATOS BD
	private Connection conn;
	private String myDriver = "com.mysql.cj.jdbc.Driver";
	private String myUrl = "jdbc:mysql://localhost/DBPInf";
	private String userBD = "root";
	private String passBD = "123456789";

	private boolean modeDB = true;
	// True = Se usa base de datos
	// False = Se usa JSON

	// DATOS JSON
//	private String RUTA_JSON = "F:\\JSON\\"; // SERGIO
        private String RUTA_JSON = "../JSON_FILES/"; //Ruta relativa
//        private String RUTA_JSON = System.getProperty("user.dir") + "/JSON_FILES/"; //Ruta relativa

	private String EXT_JSON = ".json";
	public String RITMOCARDIACO_JSON = "JSON_RitmoCardiaco";
	public String USUARIO_JSON = "JSON_Usuario";
	public String ROL_JSON = "JSON_Rol";
	public String ASIGNACION_JSON = "JSON_Asignacion";
	public String TIPOASIGNACION_JSON = "JSON_TipoAsignacion";
	public String MENSAJE_JSON = "JSON_Mensaje";
	public String PRESENCIA_JSON = "JSON_Presencia";
	public String PUERTACALLE_JSON = "JSON_PuertaCalle";
	public String PROVINCIA_JSON = "JSON_Provincias";
	public String MUNICIPIO_JSON = "JSON_Municipios";
	public String TIPOPRESENCIA_JSON = "JSON_TipoPresencia";

	public boolean isModeDB() {
		return modeDB;
	}

	public void setModeDB(boolean modeDB) {
		this.modeDB = modeDB;
	}

	/**
	 * Metodo encargado de listar todos los usuarios que aparecen en el archivo JSON
	 * Se puede filtrar por dni y/o por id del rol
	 *
	 * @param dniLista        Lista con todos los DNIs que se quieren buscar
	 * @param idRol           Id del rol por el que se quiere buscar los usuarios
	 * @param asignarUsuarios 'TRUE' si se quiere con los objetos Asignacion vengan
	 *                        con el objeto Usuario relacionado
	 * @param cargarMensajes  'TRUE' si se quiere cargar todos los mensajes del
	 *                        usuario
	 * @return Devuelve una lista con todos los usuarios encontrados
	 * @throws java.sql.SQLException
	 */
	public List<Usuario> getUsuarios(List<String> dniLista, Integer idRol, boolean asignarUsuarios,
                    boolean cargarMensajes) throws SQLException {
		List<Usuario> listaReturn = new ArrayList<Usuario>();

		List<Usuario> listaTemporal = (List<Usuario>) JSONtoObject(USUARIO_JSON);

		List<Rol> roles = getRoles();
		List<Asignacion> asignaciones = getAsignaciones(asignarUsuarios);

		List<Municipio> listaMunicipios = getMunicipios();
		List<Provincia> listaProvincias = getProvincias();

		for (Usuario usuario : listaTemporal) {
			// Si no se filta por dni inserta todos los usuarios que encuentre
			// si se filtra por dni insertara solo los que encuentre
			boolean dniEncontrado = false;
			if (dniLista != null) {
				for (String dni : dniLista) {
					if (usuario.getDni().equalsIgnoreCase(dni)) {
						dniEncontrado = true;
					}
				}
			}

			if (dniLista == null || dniEncontrado) {

				// Agregar los roles a los usuarios
				for (Rol rol : roles) {

					// Si no se filta por rol inserta todos los usuarios que encuentre
					// si se filtra por dni insertara solo los que encuentre
					if (idRol == null || rol.getId_Rol() == idRol) {

						// Solo agregara el rol que coincida con su mismo id
						if (rol.getId_Rol() == usuario.getId_Rol()) {
							usuario.setRol(rol);
                                                        
                                                        if(asignaciones != null && !asignaciones.isEmpty()){
                                                            // Agregar las asignaciones a los usuarios
                                                            for (Asignacion asignacion : asignaciones) {
                                                                    if (asignacion.getDni_Asociado().equalsIgnoreCase(usuario.getDni()) || asignacion.getDni_Asignado().equalsIgnoreCase(usuario.getDni())) {
                                                                            usuario.getListaAsignacion().add(asignacion);
                                                                    }
                                                            }
                                                        }

							// Agregar el municipio al usuario
							for (Municipio municipio : listaMunicipios) {
								if (municipio.getId_Municipio() == usuario.getId_Muncipio()) {

									// Agregar la provincia al municipio
									for (Provincia provincia : listaProvincias) {
										if (municipio.getId_Provincia() == provincia.getId_Provincia()) {
											municipio.setProvincia(provincia);
											usuario.setMunicipio(municipio);
											break;
										}
									}
									break;
								}
							}

							if (cargarMensajes) {
								usuario.setListaMensajes(getMensajes(usuario.getDni(), null));
							}

							listaReturn.add(usuario);
							break;
						}
					}
				}
			}
		}

		return listaReturn;
	}

	/**
	 * Metodo encargado de listar todos las asignaciones encontrados en el archivo
	 * JSON
	 *
	 * @param asignarUsuarios 'TRUE' si se quiere con los objetos Asignacion vengan
	 *                        con el objeto Usuario relacionado
	 * @return Lista con todas las asociaciones de usuarios
	 * @throws SQLException
	 */
	public List<Asignacion> getAsignaciones(boolean asignarUsuarios) throws SQLException {
		List<Asignacion> listaReturn = (List<Asignacion>) JSONtoObject(ASIGNACION_JSON);

		List<String> dniUsuarioAsignado = new ArrayList<String>();

		// Agregar el objeto tipo asignacion a las asignaciones
		for (TipoAsignacion tipoAsignacion : getTiposAsignacion()) {
			for (Asignacion asignacion : listaReturn) {
				if (asignacion.getId_Tipo() == tipoAsignacion.getIdTipoAsignacion()) {
					asignacion.setTipoAsignacion(tipoAsignacion);
					if (asignarUsuarios) {
						agregarValorNoRepetidoLista(dniUsuarioAsignado, asignacion.getDni_Asociado());
						agregarValorNoRepetidoLista(dniUsuarioAsignado, asignacion.getDni_Asignado());
					}
				}
			}
		}

		// Si el booleano esta a true, agregamos el objeto de usuario a la asignacion.
		if (asignarUsuarios) {
			for (Usuario usuario : getUsuarios(dniUsuarioAsignado, null, false, false)) {
				for (Asignacion asignacion : listaReturn) {
					if (asignacion.getDni_Asociado().equalsIgnoreCase(usuario.getDni())) {
						asignacion.setUsuarioAsociado(usuario);
					}

					if (asignacion.getDni_Asignado().equalsIgnoreCase(usuario.getDni())) {
						asignacion.setUsuarioAsignado(usuario);
					}
				}
			}
		}

		return listaReturn;
	}

	/**
	 * Metodo encargado de agregar un valor no repetido en una lista
	 *
	 * @param lista
	 * @param valor
	 */
	private void agregarValorNoRepetidoLista(List<String> lista, String valor) {
		boolean valorRepetido = false;

		for (String string : lista) {
			if (string.equalsIgnoreCase(valor)) {
				valorRepetido = true;
				break;
			}
		}

		if (!valorRepetido) {
			lista.add(valor);
		}
	}

	/**
	 * Metodo encargado de listar todos los tipos de asignacion encontrados en el
	 * archivo JSON
	 *
	 * @return Devuelve una lista con todos los tipos de asociaciones que existen
	 */
	public List<TipoAsignacion> getTiposAsignacion() {
		List<TipoAsignacion> listaReturn = (List<TipoAsignacion>) JSONtoObject(TIPOASIGNACION_JSON);

		return listaReturn;
	}

	/**
	 * Metodo encargado de recuperar un rol especifico entre los roles del archivo
	 * JSON
	 *
	 * @param idRol Id del rol a buscar
	 * @return Devuelve un rol con todo
	 * @throws SQLException
	 */
	public Rol getRol(int idRol) throws SQLException {
		for (Rol rol : getRoles()) {
			if (rol.getId_Rol() == idRol) {
				return rol;
			}
		}

		return null;
	}

	/**
	 * Metodo de extracción de roles de base de datos o JSON La extracción se hace
	 * en base a la variable modeDB
	 *
	 * @return Retorna una lista con objetos rol
	 * @throws java.sql.SQLException
	 */
	public List<Rol> getRoles() throws SQLException {
		List<Rol> listaRoles = new ArrayList<Rol>();

		// Modo base de datos
		if (modeDB) {
			final String query = "select * from roles";
			Statement statement;
			ResultSet rs;
			conn = DriverManager.getConnection(myUrl, userBD, passBD);
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				Rol temporal = new Rol(Integer.parseInt(rs.getString("ID_ROL")), rs.getString("NOMBRE").toString());
				listaRoles.add(temporal);
			}
			conn.close();

		} // Modo JSON
		else {
			listaRoles = (List<Rol>) JSONtoObject(ROL_JSON);
		}

		return listaRoles;
	}

	/**
	 * Metodo de extracción de municipios de base de datos o JSON La extracción se
	 * hace en base a la variable modeDB
	 *
	 * @return Retorna una lista con objetos municipio
	 * @throws java.sql.SQLException
	 */
	public List<Municipio> getMunicipios() throws SQLException {
		List<Municipio> listaMunicipios = new ArrayList<Municipio>();

		// Modo Base de datos
		if (modeDB) {
			final String query = "select * from municipios";
			Statement statement;
			ResultSet rs;
			conn = DriverManager.getConnection(myUrl, userBD, passBD);
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				Municipio temporal = new Municipio(rs.getInt("ID_PROVINCIA"), rs.getInt("ID_MUNICIPIO"),
						rs.getInt("COD_MUNICIPIO"), rs.getInt("DC"), rs.getString("NOMBRE"));
				listaMunicipios.add(temporal);
			}
			conn.close();
		} // Modo JSON
		else {
			listaMunicipios = (List<Municipio>) JSONtoObject(MUNICIPIO_JSON);
		}

		return listaMunicipios;
	}

	/**
	 * Metodo de extracción de provincias de base de datos o JSON La extracción se
	 * hace en base a la variable modeDB
	 *
	 * @return Retorna una lista con objetos provincia
	 * @throws java.sql.SQLException
	 */
	public List<Provincia> getProvincias() throws SQLException {
		List<Provincia> listaProvincias = new ArrayList<Provincia>();

		// Modo Base de datos
		if (modeDB) {
			final String query = "select * from provincias";
			Statement statement;
			ResultSet rs;

			conn = DriverManager.getConnection(myUrl, userBD, passBD);
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				Provincia temporal = new Provincia(rs.getInt("ID_PROVINCIA"), rs.getString("PROVINCIA"));
				listaProvincias.add(temporal);
			}
			conn.close();

			return listaProvincias;
		} // Modo JSON
		else {
			listaProvincias = (List<Provincia>) JSONtoObject(PROVINCIA_JSON);
		}

		return listaProvincias;
	}

	/**
	 * Devuelve todos los mensajes de un usuario filtrando por DNI del usuario y si
	 * este ha sido leido o no ha sido leido Si se quiere todos los mensajes sin que
	 * este haya sido leido o no hay que poner el booleano de leido a null
	 *
	 * @param dniUsuario DNI del usuario a buscar sus mensajes
	 * @param leido      Booleano para buscar los mensajes segun el booleano, si se
	 *                   pone a null se buscan todos
	 * @return
	 */
	private List<Mensaje> getMensajes(String dniUsuario, Boolean leido) {
		List<Mensaje> listaReturn = new ArrayList<Mensaje>();

		List<Mensaje> listaTemporal = getMensajes();

		for (Mensaje mensaje : listaTemporal) {
			if (mensaje.getDniUsuario().equalsIgnoreCase(dniUsuario)) {
				if (leido == null || leido == mensaje.isLeido()) {
					listaReturn.add(mensaje);
				}
			}
		}

		return listaReturn;
	}

	/**
	 * Metodo encargado de listar todos los mensajes encontrados en el archivo JSON
	 *
	 * @return Devuelve una lista con todos los mensajes del archivo JSON
	 */
	public List<Mensaje> getMensajes() {
		List<Mensaje> listaReturn = (List<Mensaje>) JSONtoObject(MENSAJE_JSON);

		return listaReturn;
	}

	/**
	 * Metodo encargado de recuperar las mediciones de Ritmo cardiaco guardados en
	 * un archivio JSON Se filtra por:
	 *
	 * @param dni        Solo se recuperaran los valores del dni marcado
	 * @param tipoRango  Indica a que tipo hay que sumar la cantidad del rango para
	 *                   el proximo punto (ANIO, MES, DIA, HORA, MINUTO)
	 * @param rango      Cantidad a sumar para el proximo punto
	 * @param fechaDesde Fecha desde donde se quiere empezar a tomar las mediciones
	 * @param fechaHasta Fecha hasta donde se quieren tomar las mediciones
	 * @return Devuelve una lista con todos los Ritmos cardiacos encontrados segun
	 *         el filtro en un archivo JSON
	 */
	public List<RitmoCardiaco> getRitmoCardiaco(String dni, TipoRango tipoRango, int rango, Date fechaDesde,
			Date fechaHasta) {
		System.out.println("Tiempo traer los datos inicio: " + new Date());
		List<RitmoCardiaco> listaReturn = new ArrayList<RitmoCardiaco>();

		// Leer json
		List<RitmoCardiaco> temporal = (List<RitmoCardiaco>) JSONtoObject(RITMOCARDIACO_JSON);

		for (RitmoCardiaco prueba : temporal) {
			if (prueba.getDniPaciente().equals(dni)) {
				boolean permisos = true;

				// Se valida si el ritmo cardiaco esta entre las fechas seleccionadas
				// en caso de no seleccionar las fechas coge todos los ritmos cardiacos
				if (fechaDesde != null && fechaDesde.compareTo(prueba.getFecha()) > 0) {
					permisos = false;
				}

				if (fechaHasta != null && fechaHasta.compareTo(prueba.getFecha()) < 0) {
					permisos = false;
				}

				if (permisos) {
					listaReturn.add(prueba);
				}
			}
		}

		System.out.println("Tiempo traer los datos fin: " + new Date());
		return listaReturn;
	}

	/**
	 * Metodo encargado de filtrar las presencias por DNI del paciente
	 * 
	 * @param dniPaciente     DNI del paciente del que se desea buscar las
	 *                        presencias
	 * @param fechaDesde      Fecha desde donde hay que empezar a coger los valores
	 * @param fechaHasta      Fecha hasta donde hay que coger los valores
	 * @param idTipoPresencia Buscar solo por tipo de presencia, si este valor es
	 *                        NULL se buscarán todos los tipos de presencias
	 * @return
	 */
	public List<Presencia> getPresencias(String dniPaciente, Date fechaDesde, Date fechaHasta,
			TipoPresencia idTipoPresencia) {
		List<Presencia> listaReturn = new ArrayList<Presencia>();

		List<Presencia> listaTemporal = getPresencias();

		List<TipoPresencia> tiposPresencias = getTiposPresencias();

		for (Presencia presencia : listaTemporal) {
			boolean permisos = true;

			if (presencia.getDni_paciente().equalsIgnoreCase(dniPaciente)) {

				if (fechaDesde != null && fechaDesde.compareTo(presencia.getFecha()) > 0) {
					permisos = false;
				}

				if (fechaHasta != null && fechaHasta.compareTo(presencia.getFecha()) < 0) {
					permisos = false;
				}

				if (permisos) {
					// Setear el objeto tipoPresencia
					for (TipoPresencia tipoPresencia : tiposPresencias) {
						if (idTipoPresencia == null || idTipoPresencia.getId() == tipoPresencia.getId()) {
							if (tipoPresencia.getId() == presencia.getId_tipo_presencia()) {
								presencia.setTipoPresencia(tipoPresencia);
								listaReturn.add(presencia);
								break;
							}
						}
					}
				}
			}
		}

		return listaReturn;
	}

	/**
	 * Metodo encargado de listar todas las presencias encontrados en el archivo
	 * JSON
	 *
	 * @return Devuelve una lista con todos las presencias del archivo JSON
	 */
	private List<Presencia> getPresencias() {
		List<Presencia> listaReturn = (List<Presencia>) JSONtoObject(PRESENCIA_JSON);

		return listaReturn;
	}

	/**
	 * Metodo encargado de listar todos los tipos de presencias encontrados en el
	 * archivo JSON
	 *
	 * @return Devuelve una lista con todos los tipos de presencias del archivo JSON
	 */
	public List<TipoPresencia> getTiposPresencias() {
		List<TipoPresencia> listaReturn = (List<TipoPresencia>) JSONtoObject(TIPOPRESENCIA_JSON);

		return listaReturn;
	}

	/**
	 * Metodo encargado de filtrar los valores de la puerta de la calle por DNI del
	 * paciente
	 *
	 * @param dniPaciente DNI del paciente del que se desea buscar los valores de la
	 *                    puerta de la calle
         * @param fechaDesde      Fecha desde donde hay que empezar a coger los valores
	 * @param fechaHasta      Fecha hasta donde hay que coger los valores
	 * @return
	 */
	public List<PuertaCalle> getPuertaCalle(String dniPaciente, Date fechaDesde, Date fechaHasta) {
		List<PuertaCalle> listaReturn = new ArrayList<PuertaCalle>();

		List<PuertaCalle> listaTemporal = (List<PuertaCalle>) JSONtoObject(PUERTACALLE_JSON);

		for (PuertaCalle puertaCalle : listaTemporal) {
                    if (puertaCalle.getDniPaciente().equalsIgnoreCase(dniPaciente)) {
                        boolean permisos = true;    
                        if (fechaDesde != null && fechaDesde.compareTo(puertaCalle.getFecha()) > 0) {
                                permisos = false;
                        }

                        if (fechaHasta != null && fechaHasta.compareTo(puertaCalle.getFecha()) < 0) {
                                permisos = false;
                        }

                        if (permisos) {
                            listaReturn.add(puertaCalle);
                        }
                    }
		}

		return listaReturn;
	}

	/**
	 * Metodo encargado de listar todos los valores de la puerta de la calle
	 * encontrados en el archivo JSON
	 *
	 * @return Devuelve una lista con todos los valores de la puerta de la calle del
	 *         archivo JSON
	 */
	public List<PuertaCalle> getPuertaCalle() {
		List<PuertaCalle> listaReturn = (List<PuertaCalle>) JSONtoObject(PUERTACALLE_JSON);

		return listaReturn;
	}

	/**
	 * Metodo encargado de convertir un archivo JSON en cualquier tipo de objeto,
	 * dentro de sus posibilidades
	 *
	 * @param fileJson
	 * @return Lista de los objetos del archivo json seleccionado
	 */
	private List<?> JSONtoObject(String fileJson) {
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(RUTA_JSON + fileJson + EXT_JSON), "UTF8"));
			Type listType = null;

			if (fileJson.equals(RITMOCARDIACO_JSON)) {
				listType = new TypeToken<ArrayList<RitmoCardiaco>>() {
				}.getType();
			} else if (fileJson.equals(USUARIO_JSON)) {
				listType = new TypeToken<ArrayList<Usuario>>() {
				}.getType();
			} else if (fileJson.equals(ROL_JSON)) {
				listType = new TypeToken<ArrayList<Rol>>() {
				}.getType();
			} else if (fileJson.equals(TIPOASIGNACION_JSON)) {
				listType = new TypeToken<ArrayList<TipoAsignacion>>() {
				}.getType();
			} else if (fileJson.equals(ASIGNACION_JSON)) {
				listType = new TypeToken<ArrayList<Asignacion>>() {
				}.getType();
			} else if (fileJson.equals(MUNICIPIO_JSON)) {
				listType = new TypeToken<ArrayList<Municipio>>() {
				}.getType();
			} else if (fileJson.equals(PROVINCIA_JSON)) {
				listType = new TypeToken<ArrayList<Provincia>>() {
				}.getType();
			} else if (fileJson.equals(MENSAJE_JSON)) {
				listType = new TypeToken<ArrayList<Mensaje>>() {
				}.getType();
			} else if (fileJson.equals(PRESENCIA_JSON)) {
				listType = new TypeToken<ArrayList<Presencia>>() {
				}.getType();
			} else if (fileJson.equals(TIPOPRESENCIA_JSON)) {
				listType = new TypeToken<ArrayList<TipoPresencia>>() {
				}.getType();
			} else if (fileJson.equals(PUERTACALLE_JSON)) {
				listType = new TypeToken<ArrayList<PuertaCalle>>() {
				}.getType();
			}

			return new Gson().fromJson(in, listType);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Metodo encargado de guardar un mensaje dentro de un archivo JSON el metodo se
	 * conecta al servidor gestor datos para poder guardar mensajes simultaneamente
	 *
	 * @param mensaje Objeto mensaje que se desea guardar
	 */
	public static void guardarMensaje(Mensaje mensaje) {
		try {
			Socket socket = new Socket("localhost", ConstantesAplicacion.PORT_SERVER);

			System.out.println("Connected!");

			// get the output stream from the socket.
			OutputStream outputStream = socket.getOutputStream();
			// create an object output stream from the output stream so we can send an
			// object through it
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

			System.out.println("Enviando objeto");
			objectOutputStream.writeObject(mensaje);

			socket.close();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Guarda cualquiera lista de objetos en el JSON seleccionado por parametros
	 * IMPORTANTE: NO SE AGREGA AL FINAL, LA LISTA TIENE QUE IR CON LOS DATOS
	 * ANTERIORES
	 *
	 * @param lista    Lista con todos los objetos a guardar
	 * @param fileJson Nombre del archivo donde se quiere guardar los objetos
	 */
	public void ObjecttoJson(List<?> lista, String fileJson) {
		try {
			try (Writer writer = new FileWriter(RUTA_JSON + fileJson + EXT_JSON)) {
				Gson gson = new GsonBuilder().create();
				gson.toJson(lista, writer);
			}
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// En pruebas
	private List<?> BDtoObject(String query, HashMap<Integer, Object> listaParametros, Class<?> clase) {

		List<Object> listaReturn = new ArrayList<Object>();

		try {
			conn = DriverManager.getConnection(myUrl, userBD, passBD); // SI HACES ESTE METODO ACUERDATE DE CERRAR LA
																		// CONEXIÓN LUEGO (conn.close();)
			PreparedStatement statement = conn.prepareStatement(query);

			for (Map.Entry<Integer, Object> entry : listaParametros.entrySet()) {
				if (entry.getValue() instanceof Integer) {
					statement.setInt(entry.getKey(), (Integer) entry.getValue());
				} else if (entry.getValue() instanceof String) {
					statement.setString(entry.getKey(), (String) entry.getValue());
				} else if (entry.getValue() instanceof Date) {
					statement.setDate(entry.getKey(), new java.sql.Date(((Date) entry.getValue()).getTime()));
				}
			}
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				Object objeto = null;
				try {
					objeto = clase.newInstance();

					Field[] atributos = clase.getDeclaredFields();
					for (int x = 0; x < atributos.length; x++) {
						System.out.println(atributos[x].getName());

						// llamar al metodo para traer de BD
						System.out.println("Tipo de clase " + atributos[x].getType());

						Object value = null;

						if (atributos[x].getType().equals(String.class)) {
							System.out.println("clase string");
							value = rs.getString(atributos[x].getName().toUpperCase());
						} else if (atributos[x].getType().equals(int.class)) {
							System.out.println("clase int");
							value = rs.getInt(atributos[x].getName().toUpperCase());
						} else if (atributos[x].getType().equals(Date.class)) {
							System.out.println("clase date");
							value = rs.getDate(atributos[x].getName().toUpperCase());
						}

						// llamar al set
						String metodoClase = "set" + atributos[x].getName().substring(0, 1).toUpperCase()
								+ atributos[x].getName().substring(1);
						try {
							Method metodo = clase.getDeclaredMethod(metodoClase, Integer.class);
							metodo.invoke(objeto, value);
						} catch (NoSuchMethodException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					listaReturn.add(clase.cast(objeto));

				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaReturn;
	}

	/**
	 * Metodo para el logeo de usuarios
	 *
	 * @param dni  DNI a buscar entre los usuarios
	 * @param pass Contraseña del usuario
	 * @return Devuelve un objeto Usuario si se encuentra un usuario que coincida
	 *         con el DNI y la contraseña
	 * @throws SQLException
	 */
	public Usuario login(String dni, String pass) throws SQLException {
		// MODO DB
		if (modeDB) {
			final String query = "SELECT * FROM USUARIO WHERE DNI = ?";
			conn = DriverManager.getConnection(myUrl, userBD, passBD);
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dni);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Usuario user = new Usuario(resultSet.getInt("ID_ROL"), resultSet.getInt("ID_MUNICIPIO"),
						resultSet.getString("DNI"), resultSet.getString("NOMBRE"), resultSet.getString("APELLIDOS"),
						resultSet.getString("PASS_HASHED"), resultSet.getBytes("SALT"), resultSet.getString("EMAIL"),
						resultSet.getString("TLF_MOVIL"), resultSet.getString("TLF_FIJO"),
						resultSet.getString("DIRECCION"));
				if (getHash(pass, user.getSalt()).equals(resultSet.getString("PASS_HASHED"))) {
					conn.close();
					return user;
				}
			}
			conn.close();
		} // MODO JSON
		else {
			List<String> listaDni = new ArrayList<String>();
			listaDni.add(dni);
			List<Usuario> listaUsuario = getUsuarios(listaDni, null, true, true);

			if (listaUsuario != null && !listaUsuario.isEmpty()) {
				for (Usuario usuario : listaUsuario) {
					if (getHash(pass, usuario.getSalt()).equals(usuario.getPass_Hashed())) {
						return usuario;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Metodo para comprobar si el dni existe en la base de datos
	 *
	 * @param user recibe el usuario del cual comprobaremos dni
	 * @return true si esta repetido, false si no lo esta
	 * @throws SQLException
	 */
	public boolean DNIRepetido(Usuario user) throws SQLException {
		// Modo Base de datos
		if (modeDB) {
			final String query = "SELECT * FROM USUARIO WHERE DNI = ?";
			conn = DriverManager.getConnection(myUrl, userBD, passBD);
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getDni());
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				conn.close();
				return true;
			}
			conn.close();
		} 
                // Modo JSON
		else {
			List<Usuario> listaUsuario = getUsuarios(null, null, false, false);

			for (Usuario usuarioLista : listaUsuario) {
				if (usuarioLista.getDni().equalsIgnoreCase(user.getDni())) {
					return true;
				}
			}
		}
		return false;
	}

        
        /**
        * Metodo de registro de usuarios
        *
        * @param editar parametro que nos dice que es un nuevo registro o una update a uno ya existente
        * @param passCambiada para saber si ha cambiado la contraseña
        * @param user mandas usuario reyenado
        * @param pass mandas contraseña para hashear
        * @param aEditar valores del usuario antes de editar
        * @return retorna true si ha tenido exito al crear el usuario o false si no
        * @throws SQLException
        * @throws              java.security.NoSuchAlgorithmException
        */
	public boolean registroUsuario(boolean editar, boolean passCambiada, Usuario user, String pass, Usuario aEditar) throws SQLException, NoSuchAlgorithmException 
        {
            if (!DNIRepetido(user) || editar) 
            {
                if (passCambiada)
                {
                    byte[] salt = null;
                    salt = getSalt();
                    String contrasenaHashed = getHash(pass, salt);

                    user.setSalt(salt);
                    user.setPass_Hashed(contrasenaHashed);
                }

                // MODO BDATOS
                if (modeDB) 
                {
                    //SI ESTA EN MODO EDITAR ES UN UPDATE NO UN INSERT
                    //esto lo hare mas adelante ***
                    if (editar)
                    {
                        
                    }
                    else
                    {
                        String query = "insert into USUARIO(DNI, PASS_HASHED, ID_ROL, NOMBRE, APELLIDOS, EMAIL, TLF_MOVIL, TLF_FIJO, ID_MUNICIPIO, DIRECCION, SALT)"
                                        + " values (?,?,?,?,?,?,?,?,?,?,?);";

                        conn = DriverManager.getConnection(myUrl, userBD, passBD);


                        // Preparar el statement
                        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

                            pstmt.setString(1, user.getDni());
                            pstmt.setString(2, user.getPass_Hashed());
                            pstmt.setInt(3, user.getId_Rol());
                            pstmt.setString(4, user.getNombre());
                            pstmt.setString(5, user.getApellidos());
                            pstmt.setString(6, user.getEmail());
                            pstmt.setString(7, user.getTlf_Movil());
                            pstmt.setString(8, user.getTlf_Fijo());
                            if (user.getId_Muncipio() == -1)
                                pstmt.setNull(9, java.sql.Types.INTEGER);
                            else
                                pstmt.setInt(9, user.getId_Muncipio());
                            pstmt.setString(10, user.getDireccion());
                            pstmt.setBytes(11, user.getSalt());
                            pstmt.executeUpdate();

                        }
                        conn.close();
                        return true;
                    }
                }
                //MODO JSON
                else 
                {
                    List<Usuario> listaUsuario = getUsuarios(null, null, false, false);
                    if (editar)
                    {
                        for(int i = 0; i < listaUsuario.size(); i++) 
                        {        
                            Usuario u = listaUsuario.get(i);
                            
//                            String a1 = u.getDni();
//                            String a2 = user.getDni();
//                            System.out.println(i + " = \n\"" + u.getDni() + "\"" + "\n\"" + user.getDni() + "\"\n");
                            if (u.getDni() == null ? aEditar.getDni() == null : u.getDni().equals(aEditar.getDni()))
                            {
                                System.out.println("test");
                                listaUsuario.set(i, user);
                            }
                        }
//                        listaUsuario.stream().filter((u) -> (u.getDni().equals(user.getDni()))).forEachOrdered((u) -> {
//                            u = user;
//                        });
                    }
                    else 
                    {
                        listaUsuario.add(user);
                    }
                    ObjecttoJson(listaUsuario, USUARIO_JSON);
                    return true;
                }
            }
            //SI EL DNI ESTA REPETIDO RETURN FALSE
            return false;
	}

        /**
         * Metodo registro de asignacion, trae una asignacion ya creada y la guarda en BD o JSON
        * @param editar parametro que nos dice que es un nuevo registro o una update a uno ya existente
        * @param pListAsignacion asignaciones que deberemos actualizar o insertar
        * @return
        * @throws SQLException 
        */
        public boolean registroAsignacion(boolean editar, List<Asignacion> pListAsignacion) throws SQLException
        {
            //Modo base de datos
            if (modeDB)
            {
                return true;
            }
            //Modo JSON
            else
            {                
                List<Asignacion> listaAsignacion = getAsignaciones(false);
                
                //SI ESTA EN MODO EDITAR TRAEMOS ASIGNACIONES Y BORRAMOS LAS QUE TENGA EL USUARIO
                if (editar)
                {
                    for(int i = 0; i < listaAsignacion.size(); i++) 
                    {        
                        Asignacion a = listaAsignacion.get(i);
                        
                        for (Asignacion a2 : pListAsignacion)
                        {
                            if (a2.getId_Tipo() == a.getId_Tipo() && 
                                    a2.getDni_Asociado().equals(a.getDni_Asociado()))
                            {
                                listaAsignacion.set(i, a2);
                            }
                        }
                    }
                }
                else
                {
                   pListAsignacion.forEach((as) -> {
                    listaAsignacion.add(as);
                    }); 
                }
                
                ObjecttoJson(listaAsignacion, ASIGNACION_JSON);
                return true;
            }
        }
        
	/**
	 * Metodo para encriptar una contraseña a partir de un SALT
	 *
	 * @param passwordToHash Contraseña a encriptar
	 * @param salt           SALT con el que se encriptara la contraseña
	 * @return Devuelve la contraseña encriptada
	 */
	private String getHash(String passwordToHash, byte[] salt) {
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(salt);
			// Get the hash's bytes
			byte[] bytes = md.digest(passwordToHash.getBytes());
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	/**
	 * Metodo para la creación de un Array de bytes aleatorio
	 *
	 * @return Devuelve un Array de bytes aleatorio para usar como agregado en el
	 *         hash para conseguir que contraseñas iguales sean diferentes hashes
	 * @throws NoSuchAlgorithmException
	 */
	private byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		// Creacion del tamaño del array
		byte[] salt = new byte[16];
		// Inserta el SALT en el array creado
		sr.nextBytes(salt);

		return salt;
	}
}
