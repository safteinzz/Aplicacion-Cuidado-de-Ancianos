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
public class Paciente extends Usuario {
    
    protected String medicosAsociadosDNI;
    protected String familiaresAsociadosDNI;
    
    public Paciente(String usuario, String nombre, String apellidos, String email, String telMovil, String telFijo, String localidad, String pais, String direccion) {
        super(usuario, nombre, apellidos, email, telMovil, telFijo, localidad, pais, direccion);
        
    }

    public String getMedicosAsociadosDNI() {
        return medicosAsociadosDNI;
    }

    public void setMedicosAsociadosDNI(String medicosAsociadosDNI) {
        this.medicosAsociadosDNI = medicosAsociadosDNI;
    }

    public String getFamiliaresAsociadosDNI() {
        return familiaresAsociadosDNI;
    }

    public void setFamiliaresAsociadosDNI(String familiaresAsociadosDNI) {
        this.familiaresAsociadosDNI = familiaresAsociadosDNI;
    }
}
