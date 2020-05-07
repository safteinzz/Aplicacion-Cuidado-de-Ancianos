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
import java.sql.Array;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import pinfui.dto.ConstantesAplicacion;
import pinfui.dto.TipoRango;
import pinfui.entidades.Alerta;
import pinfui.entidades.Asignacion;
import pinfui.entidades.Etiqueta;
import pinfui.entidades.Mensaje;
import pinfui.entidades.Municipio;
import pinfui.entidades.Nota;
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
	private String myUrl = "jdbc:mysql://localhost/DBPInf?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String userBD = "root";
	private String passBD = "123456789";

	private boolean modeDB = true;
	// True = Se usa base de datos
	// False = Se usa JSON

	// DATOS JSON
//	private String RUTA_JSON = "D:\\temp\\JSON_FILES\\"; // SERGIO
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
        public String ETIQUETA_JSON = "JSON_Etiqueta";
        public String NOTA_JSON = "JSON_Nota";
        

	public boolean isModeDB() {
		return modeDB;
	}

	public void setModeDB(boolean modeDB) {
		this.modeDB = modeDB;
	}
	
        /**
         * Metodo para recuperar usuarios
         * Este metodo dirige a:
         *  @getUsuariosBD si @modeDB = true
         *  @getUsuariosJSON si @modeDB = false
         * @return Devuelve una lista con todos los usuarios encontrados
         */
	public List<Usuario> getUsuarios(List<String> dniLista, Integer idRol, boolean asignarUsuarios,
            boolean cargarMensajes)
        {		
            if(isModeDB()) {
                try 
                {
                    return getUsuariosBD(dniLista, idRol, asignarUsuarios, cargarMensajes);
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                }
            } 
            else 
            {
                return getUsuariosJSON(dniLista, idRol, asignarUsuarios, cargarMensajes);
            }

            return null;
	}
	
	/**
         * BD
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
	private List<Usuario> getUsuariosBD(List<String> dniLista, Integer idRol, boolean asignarUsuarios,
            boolean cargarMensajes) throws SQLException{
		List<Usuario> listaReturn = new ArrayList<Usuario>();
		
		//1- Traer todos los usuarios de que tengan su dni en dniLista
		//también se puede buscar solo por rol, por lo que tendrás que traer todos los usuarios que tengan ese rol
		
		//2- Por cada uno tendrás que recuperar sus objetos, como se hace para el json
		//intenta que esto sea diferente a JSON, con BD te da la posibilidad de buscar sólo el que quieres
		
		String query = "SELECT * FROM USUARIO";
		conn = DriverManager.getConnection(myUrl, userBD, passBD);
		PreparedStatement pstmt = conn.prepareStatement(query);
		
                if((dniLista != null && !dniLista.isEmpty()) || (idRol != null)){
                    query += " WHERE ";
                }
                
		//Agregamos a la query la consulta de DNIs por lista
		if(dniLista != null && !dniLista.isEmpty()) {
			query += "DNI IN (" ;
                        
                        for(int x = 0; x < dniLista.size(); x++){
                            query += "'" + dniLista.get(x) + "'";
                            if(x != dniLista.size() - 1){
                                query += ", ";
                            }
                        }
                        query += ") ";
			
                        pstmt = conn.prepareStatement(query);
		}
		
		//Si se quiere buscar por los dos casos, se agrega un AND entre medias
		if(dniLista != null && !dniLista.isEmpty() && idRol != null) {
			query += " AND ";
		}
		
		//Agregamos a la query la consulta por idRol
		if(idRol != null) {
			query += "ID_ROL = ?";
                        pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idRol);
		}
		
		
		
		ResultSet resultSet = pstmt.executeQuery();
		while (resultSet.next()) {
			listaReturn.add(new Usuario(resultSet.getInt("ID_ROL"), resultSet.getInt("ID_MUNICIPIO"),
					resultSet.getString("DNI"), resultSet.getString("NOMBRE"), resultSet.getString("APELLIDOS"),
					resultSet.getString("PASS_HASHED"), resultSet.getBytes("SALT"), resultSet.getString("EMAIL"),
					resultSet.getString("TLF_MOVIL"), resultSet.getString("TLF_FIJO"),
					resultSet.getString("DIRECCION")));
		}
		conn.close();
		
		//recorremos el for de lisa de usuarios para agregar los diferentes objetos
		for(Usuario user : listaReturn) {
			//asignacion - Si asignarUsuarios esta false no se agregaran sus asociaciones
			user.setListaAsignacion(getAsignaciones(user.getDni(), user.getId_Rol(), asignarUsuarios));
			
			//rol
			user.setRol(getRol(user.getId_Rol()));
			
			//municipio
			user.setMunicipio(getMunicipioBD(user.getId_Muncipio()));
			
			if(cargarMensajes) {
				user.setListaMensajes(getMensajes(user.getDni(), null));
			}
			
		}
		pstmt.close();
                resultSet.close();
		return listaReturn;
	}

	/**
         * JSON
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
	 */
	private List<Usuario> getUsuariosJSON(List<String> dniLista, Integer idRol, boolean asignarUsuarios,
                    boolean cargarMensajes) {
		List<Usuario> listaReturn = new ArrayList<Usuario>();

		List<Usuario> listaTemporal = (List<Usuario>) JSONtoObject(USUARIO_JSON);

		List<Rol> roles = getRoles();
		List<Asignacion> asignaciones = getAsignaciones(null, null, asignarUsuarios);

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

//							if (cargarMensajes) {
//								usuario.setListaMensajes(getMensajes(usuario.getDni(), null));
//							}

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
         * Metodo para recuperar Asociaciones
         * Este metodo dirige a:
         *  @getAsignacionesBD si @modeDB = true
         *  @getAsignacionesJSON si @modeDB = false
         * @return Lista con todas las asociaciones
         */
	public List<Asignacion> getAsignaciones(String dni, Integer idRol, boolean asignarUsuarios){
		
		if(isModeDB()) {
			try {
				return getAsignacionesBD(dni, idRol, asignarUsuarios);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return getAsignacionesJSON(asignarUsuarios);
		}
		
		return null;
	}
	
	/**
         * BD
	 * Metodo para recuperar Asociaciones
	 * @return Lista con todas las asociaciones
	 */
	private List<Asignacion> getAsignacionesBD(String dni, Integer idRol, boolean asignarUsuarios) throws SQLException {
		List<Asignacion> listaReturn = new ArrayList<Asignacion>();
		
		List<TipoAsignacion> listaTipoAsignacion = getTiposAsignacion();
		List<String> dniUsuarioAsignado = new ArrayList<String>();
		
		//Buscamos las asignaciones por el dni del usuario
		String query = "SELECT * FROM ASIGNACION WHERE ";
		
		//Si el rol es de paciente, tendremos que buscar el dni en DNI_ASIGNADO, por el contrario en DNI_ASOCIADO
		if(idRol != ConstantesAplicacion.ROL_PACIENTE) {
			query += "DNI_ASIGNADO = ?";
		} else {
			query += "DNI_ASOCIADO = ?";
		}
		
		conn = DriverManager.getConnection(myUrl, userBD, passBD);
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		pstmt.setString(1, dni);
		
		ResultSet resultSet = pstmt.executeQuery();
		while (resultSet.next()) {
			Asignacion asignacion = new Asignacion(resultSet.getString("DNI_ASOCIADO"), resultSet.getString("DNI_ASIGNADO"), 0);
			int idTipoAsignacion = resultSet.getInt("ID_TIPO");
			
			for(TipoAsignacion tipoAsignacion : listaTipoAsignacion) {
				if(tipoAsignacion.getIdTipoAsignacion() == idTipoAsignacion) {
					asignacion.setTipoAsignacion(tipoAsignacion);
					break;
				}
			}
			
			//Agregamos la asignacion, con solo los datos primitivos, a la lista a devolver
			listaReturn.add(asignacion);
			
			//Creamos una lista con los DNIs sin repetir de todos los usuarios que estan en las asignaciones
			
			if(idRol != ConstantesAplicacion.ROL_PACIENTE) {
				agregarValorNoRepetidoLista(dniUsuarioAsignado, asignacion.getDni_Asociado());
			} else {
				agregarValorNoRepetidoLista(dniUsuarioAsignado, asignacion.getDni_Asignado());
			}
		}
		conn.close();
		pstmt.close();
                resultSet.close();
		if (asignarUsuarios) {
			asignarUsuarios(listaReturn, dniUsuarioAsignado);
		}
		
		return listaReturn;
	}
	
	
	/**
         * JSON
	 * Metodo para recuperar Asociaciones
	 * @return Lista con todas las asociaciones
	 */
	private List<Asignacion> getAsignacionesJSON(boolean asignarUsuarios){
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
			asignarUsuarios(listaReturn, dniUsuarioAsignado);
		}

		return listaReturn;		
	}
        
        /**
	 * Metodo para asignar el objeto usuario a las asignaciones encontradas
	 * @param listaAsignacion
	 * @param dniUsuarioAsignado
	 */
	private void asignarUsuarios(List<Asignacion> listaAsignacion, List<String> dniUsuarioAsignado) {
		for (Usuario usuario : getUsuarios(dniUsuarioAsignado, null, false, false)) {
			for (Asignacion asignacion : listaAsignacion) {
				if (asignacion.getDni_Asociado().equalsIgnoreCase(usuario.getDni())) {
					asignacion.setUsuarioAsociado(usuario);
				}

				if (asignacion.getDni_Asignado().equalsIgnoreCase(usuario.getDni())) {
					asignacion.setUsuarioAsignado(usuario);
				}
			}
		}
	}

	
        /**
         * Metodo para recuperar tipos de asignacion
         * Este metodo dirige a:
         *  @getTiposAsignacionBD si @modeDB = true
         *  @getTiposAsignacionJSON si @modeDB = false
         * @return Devuelve una lista con todos los tipos de asociaciones que existen
         */
	public List<TipoAsignacion> getTiposAsignacion() {
		
		if(isModeDB()) {
			try {
				return getTiposAsignacionBD();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return getTiposAsignacionJSON();
		}
		
		return null;
	}
	
	/**
         * BD
         * Metodo para recuperar tipos de asignacion
         * @return Devuelve una lista con todos los tipos de asociaciones que existen
         * @throws SQLException 
         */
	public List<TipoAsignacion> getTiposAsignacionBD() throws SQLException {
		List<TipoAsignacion> listaReturn = new ArrayList<>();
		
		final String query = "select * from tipo_asignacion";
		Statement statement;
		ResultSet rs;
		conn = DriverManager.getConnection(myUrl, userBD, passBD);
		statement = conn.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next()) {
                        TipoAsignacion temporal = new TipoAsignacion(rs.getInt("ID_TIPO"), rs.getString("DESCRIPCION"));
			listaReturn.add(temporal);
		}
		conn.close();
		
		return listaReturn;
	}

	/**
         * JSON
         * Metodo para recuperar tipos de asignacion
         * @return Devuelve una lista con todos los tipos de asociaciones que existen
         */
	public List<TipoAsignacion> getTiposAsignacionJSON() {
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
	public Rol getRol(int idRol){
		if(isModeDB()) {
			try {
				return getRolBD(idRol);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			for (Rol rol : getRoles()) {
				if (rol.getId_Rol() == idRol) {
					return rol;
				}
			}
		}

		return null;
	}
	
	//recuperar un unico rol de BD
	private Rol getRolBD(int idRol) throws SQLException {
		
		final String query = "select * from roles where ID_ROL = ?";
		
		conn = DriverManager.getConnection(myUrl, userBD, passBD);
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		pstmt.setInt(1, idRol);
		
		ResultSet resultSet = pstmt.executeQuery();
		if (resultSet.next()) {
			Rol rol = new Rol(resultSet.getInt("ID_ROL"), resultSet.getString("NOMBRE"));
			return rol;
		}
		conn.close();
                pstmt.close();
                resultSet.close();
		return null;
	}
	
	/**
         * Metodo para recuperar roles
         * Este metodo dirige a:
         *  @getRolesBD si @modeDB = true
         *  @getRolesJSON si @modeDB = false
         * @return Devuelve una lista con todos los roles que existen
         */
	public List<Rol> getRoles(){
		if(isModeDB()) {
			try {
				return getRolesBD();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return getRolesJSON();
		}
		
		return null;
	}
	
        /**
         * BD
         * Metodo para recuperar roles
         * @return Devuelve una lista con todos los roles que existen
         * @throws SQLException 
         */
	private List<Rol> getRolesBD() throws SQLException{
		List<Rol> listaRoles = new ArrayList<Rol>();
		
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
		statement.close();
                rs.close();
		return listaRoles;
	}
	
        /**
         * JSON
         * Metodo para recuperar roles
         * @return Devuelve una lista con todos los roles que existen
         */
	private List<Rol> getRolesJSON(){
		List<Rol> listaReturn = (List<Rol>) JSONtoObject(ROL_JSON);

		return listaReturn;
	}
	
	/**
         * Metodo para recuperar municipios
         * Este metodo dirige a:
         *  @getMunicipiosBD si @modeDB = true
         *  @getMunicipiosJSON si @modeDB = false
         * @return Devuelve una lista con todos los municipios
         */
	public List<Municipio> getMunicipios(){
		if(isModeDB()) {
			try {
				return getMunicipiosBD();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return getMunicipiosJSON();
		}
		
		return null;
	}
	
        /**
         * BD
         * Metodo para recuperar municipios
         * @return Devuelve una lista con todos los municipios
         * @throws SQLException 
         */
	private List<Municipio> getMunicipiosBD() throws SQLException{
		List<Municipio> listaMunicipios = new ArrayList<Municipio>();
		
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
		statement.close();
                rs.close();
		return listaMunicipios;
	}
	
        /**
         * JSON
         * Metodo para recuperar municipios
         * @return Devuelve una lista con todos los municipios
         */
	private List<Municipio> getMunicipiosJSON(){
		List<Municipio> listaReturn = (List<Municipio>) JSONtoObject(MUNICIPIO_JSON);

		return listaReturn;
	}
        
        private Municipio getMunicipioBD(int idMunicipio) throws SQLException {
		Municipio municipio = null;
		
		final String query = "select * from municipios where ID_MUNICIPIO = ?";
		conn = DriverManager.getConnection(myUrl, userBD, passBD);
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		pstmt.setInt(1, idMunicipio);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
                        municipio = new Municipio();
			municipio = new Municipio(rs.getInt("ID_PROVINCIA"), rs.getInt("ID_MUNICIPIO"),
					rs.getInt("COD_MUNICIPIO"), rs.getInt("DC"), rs.getString("NOMBRE"));
			
			municipio.setCod_Municipio(rs.getInt("COD_MUNICIPIO"));
			municipio.setDC(rs.getInt("DC"));
			municipio.setId_Municipio(rs.getInt("ID_MUNICIPIO"));
			municipio.setId_Provincia(rs.getInt("ID_PROVINCIA"));
			municipio.setNombre(rs.getString("NOMBRE"));
                        
                        municipio.setProvincia(getProvinciaBD(municipio.getId_Provincia()));
		}
		conn.close();
		pstmt.close();
                rs.close();
		
		
		
		return municipio;
	}
	
	/**
         * Metodo para recuperar provincias
         * Este metodo dirige a:
         *  @getProvinciasBD si @modeDB = true
         *  @getProvinciasJSON si @modeDB = false
         * @return Devuelve una lista con todas las provincias
         */
	public List<Provincia> getProvincias(){
		if(isModeDB()) {
			try {
				return getProvinciasBD();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return getProvinciasJSON();
		}
		
		return null;
	}
	
        /**
         * BD
         * Metodo para recuperar provincias
         * @return Devuelve una lista con todas las provincias
         * @throws SQLException 
         */
	private List<Provincia> getProvinciasBD() throws SQLException{
		List<Provincia> listaProvincias = new ArrayList<Provincia>();
		
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
                statement.close();
                rs.close();
		return listaProvincias;
	}
	
        /**
         * JSON
         * Metodo para recuperar provincias
         * @return Devuelve una lista con todas las provincias
         */
	private List<Provincia> getProvinciasJSON(){
		List<Provincia> listaReturn = (List<Provincia>) JSONtoObject(PROVINCIA_JSON);

		return listaReturn;
	}
        
        private Provincia getProvinciaBD(int idProvincia) throws SQLException {
		final String query = "select * from PROVINCIAS where ID_PROVINCIA = ?";
		conn = DriverManager.getConnection(myUrl, userBD, passBD);
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		pstmt.setInt(1, idProvincia);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Provincia provincia = new Provincia(rs.getInt("ID_PROVINCIA"), rs.getString("PROVINCIA"));
			return provincia;
		}
		conn.close();
		pstmt.close();
                rs.close();
                
		return null;
	}
	
        /**
         * Metodo para recuperar mensajes
         * Este metodo dirige a:
         *  @getMensajesBD si @modeDB = true
         *  @getMensajesJSON si @modeDB = false
         * @return Devuelve una lista con todos los mensajes
         */
	public List<Mensaje> getMensajes(String dniUsuario, Boolean leido) {
		if(isModeDB()) {
			try {
				return getMensajesBD(dniUsuario, leido);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return getMensajesJSON(dniUsuario, leido);
		}
		
		return null;
	}
	
	//recuperar los mensajes de BD
	private List<Mensaje> getMensajesBD(String dniUsuario, Boolean leido) throws SQLException {
		List<Mensaje> listaReturn = new ArrayList<Mensaje>();
		
		final String query = "select * from MENSAJERIA where DNI_EMISOR = ? or DNI_RECEPTORES = ?";
		conn = DriverManager.getConnection(myUrl, userBD, passBD);
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		pstmt.setString(1, dniUsuario);
		pstmt.setString(2, dniUsuario);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			listaReturn.add(new Mensaje(rs.getString("DNI_EMISOR"), rs.getString("DNI_RECEPTORES"), 
					rs.getString("ASUNTO"), rs.getString("MENSAJE"), rs.getBoolean("LEIDO"), rs.getString("ETIQUETA")));
		}
		conn.close();
		pstmt.close();
                rs.close();
                
		return listaReturn;
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
	private List<Mensaje> getMensajesJSON(String dniUsuario, Boolean leido) {
		List<Mensaje> listaReturn = new ArrayList<Mensaje>();

		List<Mensaje> listaTemporal = getMensajes();

		for (Mensaje mensaje : listaTemporal) {
			if (mensaje.getDni_Emisor().equalsIgnoreCase(dniUsuario) || mensaje.getDni_Receptor().equalsIgnoreCase(dniUsuario)) {
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
	private List<Mensaje> getMensajes() {
		List<Mensaje> listaReturn = (List<Mensaje>) JSONtoObject(MENSAJE_JSON);

		return listaReturn;
	}

        /**
         * Metodo para recuperar etiquetas
         * Este metodo dirige a:
         *  @getMensajesBD si @modeDB = true
         *  @getMensajesJSON si @modeDB = false
         * @return Devuelve una lista con todas las etiquetas
         */
        public List<Etiqueta> getEtiquetas() {
                if(isModeDB()) {
			try {
				return getEtiquetasBD();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return getEtiquetasJSON();
		}
                
                return null;
        }	
        
        /**
         * BD
         * Metodo para recuperar etiquetas
         * @return Devuelve una lista con todas las etiquetas
         * @throws SQLException 
         */
        public List<Etiqueta> getEtiquetasBD() throws SQLException {
                List<Etiqueta> listaReturn = new ArrayList<>();
		
		final String query = "select * from etiqueta";
		Statement statement;
		ResultSet rs;
		conn = DriverManager.getConnection(myUrl, userBD, passBD);
		statement = conn.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next()) {
			Etiqueta temporal = new Etiqueta(rs.getInt("ID_ETIQUETA"), rs.getString("ETIQUETA"));
			listaReturn.add(temporal);
		}
		conn.close();
		statement.close();
                rs.close();
		return listaReturn;
        }
        
        /**
         * JSON
         * Metodo para recuperar etiquetas
         * @return Devuelve una lista con todas las etiquetas
         */
        public List<Etiqueta> getEtiquetasJSON(){
                List<Etiqueta> listaReturn = (List<Etiqueta>) JSONtoObject(ETIQUETA_JSON);

		return listaReturn;
        }
        
        /**
         * Metodo para recuperar etiquetas
         * Este metodo dirige a:
         *  @getMensajesBD si @modeDB = true
         *  @getMensajesJSON si @modeDB = false
         * @return Devuelve una lista con todas las etiquetas
         */
        public List<Nota> getNotas(String dni) {
                if(isModeDB()) {
			try {
				return getNotasBD(dni);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return getNotasJSON();
		}
                
                return null;
        }	
        
        /**
         * BD
         * Metodo para recuperar etiquetas
         * @return Devuelve una lista con todas las etiquetas
         * @throws SQLException 
         */
        public List<Nota> getNotasBD(String dni) throws SQLException {
                List<Nota> listaReturn = new ArrayList<>();
                
                final String query = "select * from nota where dni = ?";
		conn = DriverManager.getConnection(myUrl, userBD, passBD);
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		pstmt.setString(1, dni);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Nota temporal = new Nota(rs.getInt("ID"),rs.getString("DNI"), rs.getDate("FECHA"), rs.getString("NOTA"));
			listaReturn.add(temporal);
		}
		conn.close();
		pstmt.close();
                rs.close();
                
		return listaReturn;
        }
        
        /**
         * JSON
         * Metodo para recuperar etiquetas
         * @return Devuelve una lista con todas las etiquetas
         */
        public List<Nota> getNotasJSON(){
                List<Nota> listaReturn = (List<Nota>) JSONtoObject(NOTA_JSON);

		return listaReturn;
        }
	
        /**
         * Metodo para recuperar datos ritmo cardiaco
         * Este metodo dirige a:
         *  @getRitmoCardiacoBD si @modeDB = true
         *  @getRitmoCardiacoJSON si @modeDB = false
         * 
         * @param dni        Solo se recuperaran los valores del dni marcado
	 * @param tipoRango  Indica a que tipo hay que sumar la cantidad del rango para
	 *                   el proximo punto (ANIO, MES, DIA, HORA, MINUTO)
	 * @param rango      Cantidad a sumar para el proximo punto
	 * @param fechaDesde Fecha desde donde se quiere empezar a tomar las mediciones
	 * @param fechaHasta Fecha hasta donde se quieren tomar las mediciones
         * 
	 * @return Devuelve una lista con todos los Ritmos cardiacos encontrados segun el filtro 
         */
	public List<RitmoCardiaco> getRitmoCardiaco(String dni, TipoRango tipoRango, int rango, Date fechaDesde,
			Date fechaHasta) {
		System.out.println("Tiempo traer los datos inicio: " + new Date());
                
                List<RitmoCardiaco> listaReturn = new ArrayList<RitmoCardiaco>();
                List<RitmoCardiaco> temporal = new ArrayList<RitmoCardiaco>();
                
                if(isModeDB()) {
			try {
				temporal = getRitmoCardiacoBD();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			temporal = getRitmoCardiacoJSON();
		}
		
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
         * BD
         * Metodo para recuperar datos ritmo cardiaco
         * @return Devuelve una lista con todos los Ritmos cardiacos
         * @throws SQLException 
         */
	private List<RitmoCardiaco> getRitmoCardiacoBD() throws SQLException {
		List<RitmoCardiaco> listaReturn = new ArrayList<>();
		
		final String query = "select * from sensor_ritmo_cardiaco";
		Statement statement;
		ResultSet rs;
		conn = DriverManager.getConnection(myUrl, userBD, passBD);
		statement = conn.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next()) {
			RitmoCardiaco temporal = new RitmoCardiaco(rs.getString("DNI_PACIENTE"), rs.getDate("FECHA"), rs.getInt("VALOR"));
			listaReturn.add(temporal);
		}
		conn.close();
		statement.close();
                rs.close();
                
		return listaReturn;
	}

	/**
         * JSON
         * Metodo para recuperar datos ritmo cardiaco
         * @return Devuelve una lista con todos los Ritmos cardiacos
         */
	private List<RitmoCardiaco> getRitmoCardiacoJSON() {
		List<RitmoCardiaco> listaReturn = (List<RitmoCardiaco>) JSONtoObject(RITMOCARDIACO_JSON);
                
                return listaReturn;		
	}
	
	public List<Presencia> getPresencias(String dniPaciente, Date fechaDesde, Date fechaHasta,
			TipoPresencia idTipoPresencia) 
        {
            List<Presencia> listaReturn = new ArrayList<>();

            List<Presencia> listaTemporal = new ArrayList<>();

            List<TipoPresencia> tiposPresencias = getTiposPresencias();
            
            if(isModeDB()) 
            {
                try {
                        listaTemporal = getPresenciasBD();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
            } 
            else 
            {
                    listaTemporal = getPresenciasJSON();
            }

            

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
	
	private List<Presencia> getPresenciasBD() throws SQLException 
        {
            List<Presencia> listaReturn = new ArrayList<>();
		
            final String query = "select * from SENSOR_PRESENCIA";
            Statement statement;
            ResultSet rs;
            conn = DriverManager.getConnection(myUrl, userBD, passBD);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                    Presencia temporal = new Presencia(rs.getString("DNI_PACIENTE"), rs.getDate("FECHA"), rs.getInt("ID_TIPO_PRESENCIA"));
                    listaReturn.add(temporal);
            }
            conn.close();
            statement.close();
            rs.close();
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
	private List<Presencia> getPresenciasJSON() {
		List<Presencia> listaReturn = (List<Presencia>) JSONtoObject(PRESENCIA_JSON);
                
                return listaReturn;
	}

	public List<TipoPresencia> getTiposPresencias(){
		if(isModeDB()) {
			try {
				return getTiposPresenciasBD();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
                    return getTiposPresenciasJSON();
		}
		
		return null;
	}
	
	//Recuperar todos los tipos de presencias de BD
	private List<TipoPresencia> getTiposPresenciasBD() throws SQLException
        {
            List<TipoPresencia> listaReturn = new ArrayList<>();
		
            final String query = "select * from TIPO_PRESENCIA";
            Statement statement;
            ResultSet rs;
            conn = DriverManager.getConnection(myUrl, userBD, passBD);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) 
            {
                TipoPresencia temporal = new TipoPresencia(rs.getInt("ID_PRESENCIA"), rs.getString("LUGAR"));
                listaReturn.add(temporal);
            }
            conn.close();
            statement.close();
            rs.close();
            
            return listaReturn;
	}
	
	/**
	 * Metodo encargado de listar todos los tipos de presencias encontrados en el
	 * archivo JSON
	 *
	 * @return Devuelve una lista con todos los tipos de presencias del archivo JSON
	 */
	private List<TipoPresencia> getTiposPresenciasJSON() {
		List<TipoPresencia> listaReturn = (List<TipoPresencia>) JSONtoObject(TIPOPRESENCIA_JSON);

		return listaReturn;
	}
	
	public List<PuertaCalle> getPuertaCalle(String dniPaciente, Date fechaDesde, Date fechaHasta) {
            List<PuertaCalle> listaReturn = new ArrayList<>();
            List<PuertaCalle> listaTemporal = new ArrayList<>();
            
            if(isModeDB()) {
                    try {
                        listaTemporal = getPuertaCalleBD();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
            } else {
                    listaTemporal = getPuertaCalleJSON();
            }
                
            

            for (PuertaCalle puertaCalle : listaTemporal) 
            {
                if (puertaCalle.getDniPaciente().equalsIgnoreCase(dniPaciente)) 
                {
                    boolean permisos = true;    
                    if (fechaDesde != null && fechaDesde.compareTo(puertaCalle.getFecha()) > 0) 
                    {
                            permisos = false;
                    }

                    if (fechaHasta != null && fechaHasta.compareTo(puertaCalle.getFecha()) < 0) 
                    {
                            permisos = false;
                    }

                    if (permisos) 
                    {
                        listaReturn.add(puertaCalle);
                    }
                }
            }

            return listaReturn;
	}
	
	private List<PuertaCalle> getPuertaCalleBD() throws SQLException {
            List<PuertaCalle> listaReturn = new ArrayList<>();
		
            final String query = "select * from SENSOR_PUERTA_CALLE";
            Statement statement;
            ResultSet rs;
            conn = DriverManager.getConnection(myUrl, userBD, passBD);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) 
            {
                PuertaCalle temporal = new PuertaCalle(rs.getString("DNI_PACIENTE"), rs.getDate("FECHA"), rs.getBoolean("ABIERTA"));
                listaReturn.add(temporal);
            }
            conn.close();
            statement.close();
            rs.close();
            
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
	private List<PuertaCalle> getPuertaCalleJSON() 
        {
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
	private List<?> JSONtoObject(String fileJson) 
        {
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
        
        public void guardarMensajeBD(Mensaje mensaje) throws SQLException{
            final String principal = "insert into MENSAJERIA "
                    + "(DNI_EMISOR,DNI_RECEPTORES,ID_ETIQUETA,LEIDO,ASUNTO,MENSAJE)";
            Statement statement;
            ResultSet rs;
            conn = DriverManager.getConnection(myUrl, userBD, passBD);
            statement = conn.createStatement();
            String query = principal + " values ("+ "'"+mensaje.getDni_Emisor()+"'," + "'"+mensaje.getDni_Emisor()+"',"
                    + "'"+mensaje.getDni_Receptor()+"'," + "'"+mensaje.getEtiqueta()+"'," + "'FALSE'" + "'"+mensaje.getAsunto()+"',"
                    + "'"+mensaje.getMensaje()+"'" + ")";
            rs = statement.executeQuery(query);
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
                        statement.close();
                        rs.close();
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
	public Usuario login(String dni, String pass) {
		if(isModeDB()) {
			try {
				return loginBD(dni, pass);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return loginJSON(dni, pass);
		}
		
		return null;
	}
	
	public Usuario loginBD(String dni, String pass) throws SQLException{
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
		
		return null;
	}
	
	private Usuario loginJSON(String dni, String pass) {
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
		
		return null;
	}
	
	/**
	 * Metodo para comprobar si el dni existe en la base de datos
	 *
	 * @param user recibe el usuario del cual comprobaremos dni
	 * @return true si esta repetido, false si no lo esta
	 * @throws SQLException
	 */
	public boolean DNIRepetido(String dni) {
		if(isModeDB()) {
			try {
				return DNIRepetidoDB(dni);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return DNIRepetidoJSON(dni);
		}
		
		return false;
	}
	
	private boolean DNIRepetidoDB(String dni) throws SQLException {
		final String query = "SELECT * FROM USUARIO WHERE DNI = ?";
		conn = DriverManager.getConnection(myUrl, userBD, passBD);
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, dni);
		ResultSet resultSet = pstmt.executeQuery();
		if (resultSet.next()) {
			conn.close();
			return true;
		}
		conn.close();
		pstmt.close();
                resultSet.close();
		return false;
	}
	
	public boolean DNIRepetidoJSON(String dni) {
		List<Usuario> listaUsuario = getUsuarios(null, null, false, false);

		for (Usuario usuarioLista : listaUsuario) {
			if (usuarioLista.getDni().equalsIgnoreCase(dni)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean registroUsuario(boolean editar, boolean passCambiada, Usuario user, String pass, Usuario aEditar)
        {
            if (!DNIRepetido(user.getDni()) || editar) 
            {
                if (passCambiada)
                {
                    byte[] salt = null;

                    try 
                    {
                        salt = getSalt();
                    } 
                    catch (NoSuchAlgorithmException e) 
                    {
                        //Si salta una excepcion al generar el SALT no guardaremos el usuario
                        e.printStackTrace();
                        return false;
                    }

                    String contrasenaHashed = getHash(pass, salt);

                    user.setSalt(salt);
                    user.setPass_Hashed(contrasenaHashed);
                }

                if(isModeDB()) {
                    try 
                    {
                        return registroUsuarioBD(editar, user, aEditar, passCambiada);
                    } 
                    catch (SQLException e) 
                    {
                        e.printStackTrace();
                    }
                } 
                else 
                {
                    return registroUsuarioJSON(editar, user, aEditar);
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
	public boolean registroUsuarioBD(boolean editar, Usuario user, Usuario aEditar, boolean passCambiada) throws SQLException
        {
            //SI ESTA EN MODO EDITAR ES UN UPDATE NO UN INSERT
            //esto lo hare mas adelante ***
            String query;
            conn = DriverManager.getConnection(myUrl, userBD, passBD);
            PreparedStatement preparedStmt;
            if (editar) 
            {
                query = "DELETE FROM usuario WHERE dni = ?";
                preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, aEditar.getDni());
                preparedStmt.execute();
                
                
                // UPDATE DE POSIBLES USUARIOS ASIGNADOS A ESTE USUARIO
                query = "UPDATE ASIGNACION SET DNI_ASIGNADO = ? WHERE DNI_ASIGNADO = ?";
                preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, user.getDni());
                preparedStmt.setString(2, aEditar.getDni());
                preparedStmt.execute();
            }
            
            query = "insert into USUARIO(DNI, PASS_HASHED, ID_ROL, NOMBRE, APELLIDOS, EMAIL, TLF_MOVIL, TLF_FIJO, ID_MUNICIPIO, DIRECCION, SALT)"
                            + " values (?,?,?,?,?,?,?,?,?,?,?);";

            


            // Preparar el statement
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, user.getDni());
            if (passCambiada)
                preparedStmt.setString(2, user.getPass_Hashed());
            else
                preparedStmt.setString(2, aEditar.getPass_Hashed());
            preparedStmt.setInt(3, user.getId_Rol());
            preparedStmt.setString(4, user.getNombre());
            preparedStmt.setString(5, user.getApellidos());
            preparedStmt.setString(6, user.getEmail());
            preparedStmt.setString(7, user.getTlf_Movil());
            preparedStmt.setString(8, user.getTlf_Fijo());
            if (user.getId_Muncipio() == -1)
                preparedStmt.setNull(9, java.sql.Types.INTEGER);
            else
                preparedStmt.setInt(9, user.getId_Muncipio());
            preparedStmt.setString(10, user.getDireccion());
            if (passCambiada)
                preparedStmt.setBytes(11, user.getSalt());
            else
                preparedStmt.setBytes(11, aEditar.getSalt());
            preparedStmt.execute();

            conn.close();
            preparedStmt.close();
            return true;            
	}
	
	public boolean registroUsuarioJSON(boolean editar, Usuario user, Usuario aEditar) 
        {
            List<Usuario> listaUsuario = getUsuarios(null, null, false, false);
                
            if (editar)
            {
                for(int i = 0; i < listaUsuario.size(); i++) 
                {        
                    Usuario u = listaUsuario.get(i);

                    if (u.getDni() == null ? aEditar.getDni() == null : u.getDni().equals(aEditar.getDni()))
                    {
                        System.out.println("test");
                        listaUsuario.set(i, user);
                    }
                }
            }
            else 
            {
                listaUsuario.add(user);
            }
            ObjecttoJson(listaUsuario, USUARIO_JSON);
            return true;
	}
    
	/**
    * Metodo registro de asignacion, trae una asignacion ya creada y la guarda en BD o JSON
    * @param editar parametro que nos dice que es un nuevo registro o una update a uno ya existente
    * @param pListAsignacion asignaciones que deberemos actualizar o insertar
    * @return
    */
	public boolean registroAsignacion(boolean editar, List<Asignacion> pListAsignacion)
        {
            if(isModeDB()) 
            {
                try 
                {
                    return registroAsignacionBD(editar, pListAsignacion);
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                }
            } 
            else 
            {
                return registroAsignacionJSON(editar, pListAsignacion);
            }

            return false;
	}
	
	public boolean registroAsignacionBD(boolean editar, List<Asignacion> pListAsignacion) throws SQLException 
        {
            //tengo que meter inserts de cada objeto de la lista recorriendo la lista
            // para editar tengo que recorrer la lista de parametro y borrar las asignaciones que existan en base al getDni_asociado para luego meterlas como estan arriba
            String query;
            conn = DriverManager.getConnection(myUrl, userBD, passBD);
            PreparedStatement preparedStmt = null;
            if (editar) 
            {
                query = "DELETE FROM ASIGNACION WHERE DNI_ASOCIADO = ?";
                preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, pListAsignacion.get(0).getDni_Asociado());
                preparedStmt.execute();
            }
            
            for (Asignacion a : pListAsignacion)
            {
                query = "insert into ASIGNACION(DNI_ASOCIADO, DNI_ASIGNADO, ID_TIPO)" 
                    + " values (?,?,?)";
                preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, a.getDni_Asociado());
                preparedStmt.setString(2, a.getDni_Asignado());
                preparedStmt.setInt(3, a.getId_Tipo());
                preparedStmt.execute();
            }
            conn.close();
            preparedStmt.close();
            
            return true;
	}

	public boolean registroAsignacionJSON(boolean editar, List<Asignacion> pListAsignacion) 
        {
            List<Asignacion> listaAsignacion = getAsignaciones(null, null, false);
        
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
        
        public List<Alerta> getAlertas(List<String> dniLista) {
            List<Alerta> listaReturn = new ArrayList<>();
            try {	
                
                String query = "select * from alerta where DNI_PACIENTE in (";
                for(int x = 0; x < dniLista.size(); x++){
                    query += "'" + dniLista.get(x) + "'";
                    if(x != dniLista.size() - 1){
                        query += ", ";
                    }
                }
                query += ")";
                
		conn = DriverManager.getConnection(myUrl, userBD, passBD);
		
		Statement statement;
		ResultSet rs;
		conn = DriverManager.getConnection(myUrl, userBD, passBD);
		statement = conn.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next()) {
                    Alerta temporal = new Alerta(rs.getInt("ID"), rs.getString("DNI_PACIENTE"), rs.getString("SENSOR"), rs.getInt("VALOR"), rs.getDate("FECHA"));
                    listaReturn.add(temporal);
		}
		conn.close();
		statement.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return listaReturn;
	}
        
        public boolean borrarNota(int id){
            try {
                conn = DriverManager.getConnection(myUrl, userBD, passBD);
            
                PreparedStatement preparedStmt;
                String query = "DELETE FROM NOTA WHERE ID = ?";
                preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, id);
                preparedStmt.execute();

                preparedStmt.close();  

                conn.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(GestorDatos.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        
        public boolean borrarAlerta(int id){
            try {
                conn = DriverManager.getConnection(myUrl, userBD, passBD);
            
                PreparedStatement preparedStmt;
                String query = "DELETE FROM ALERTA WHERE ID = ?";
                preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, id);
                preparedStmt.execute();

                preparedStmt.close();  

                conn.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(GestorDatos.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        
        
        
        public boolean crearNota(String dni, String texto){
            try{
                String query;
                conn = DriverManager.getConnection(myUrl, userBD, passBD);
                PreparedStatement preparedStmt = null;

                query = "insert into NOTA(DNI, FECHA, NOTA)" 
                    + " values (?,?,?)";

                preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, dni);
                preparedStmt.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
                preparedStmt.setString(3, texto);
                preparedStmt.execute();

                conn.close();
                preparedStmt.close();
            }catch (SQLException ex) {
                Logger.getLogger(GestorDatos.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            return true;
        }
}
