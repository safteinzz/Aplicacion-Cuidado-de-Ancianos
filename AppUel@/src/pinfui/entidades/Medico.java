/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ITCOM
 */
public class Medico extends Usuario {
    
    List<String> pacientesAsociadosDNI = new ArrayList<String> ();
    
    public Medico(String usuario, String nombre, String apellidos, String email, String telMovil, String telFijo, String localidad, String pais, String direccion) {
        super(usuario, nombre, apellidos, email, telMovil, telFijo, localidad, pais, direccion);
        
    }

    public List<String> getPacientesAsociadosDNI() {
        return pacientesAsociadosDNI;
    }

    public void setPacientesAsociadosDNI(List<String> pacientesAsociadosDNI) {
        this.pacientesAsociadosDNI = pacientesAsociadosDNI;
    }

  
}
