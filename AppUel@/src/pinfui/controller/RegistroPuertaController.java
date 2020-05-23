/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import pinfui.dto.ConstantesAplicacion;
import pinfui.entidades.Presencia;
import pinfui.entidades.PuertaCalle;
import pinfui.entidades.TipoPresencia;
import pinfui.interfaz.PInfUI;

/**
 *
 * @author Usuario DAM
 */
public class RegistroPuertaController extends SensorController{
    
    public boolean convertListRegistroPuerta(DefaultTableModel model, String dniPaciente, Calendar fechaDesde, Calendar fechaHasta) {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    	List<PuertaCalle> listaRegisPuerta = PInfUI.gestorDatos.getPuertaCalle(dniPaciente, fechaDesde.getTime(), fechaHasta.getTime());
    	if(listaRegisPuerta != null && !listaRegisPuerta.isEmpty()) {
	    	for(PuertaCalle regisPuerta : listaRegisPuerta) {
	    		Object[] obj = {sdf.format(regisPuerta.getFecha()), (regisPuerta.isAbierta() ? PInfUI.getBundle().getString("abierta") : PInfUI.getBundle().getString("cerrada"))};
	    		
	    		model.addRow(obj);
	    	}
	    	return true;
    	} else {
    		return false;
    	}
    }
    
    public boolean createExcel(String dniPaciente, Calendar fechaDesde, Calendar fechaHasta, String[] titulo, String nombrePaciente) {
            List<PuertaCalle> listaPresencias = PInfUI.gestorDatos.getPuertaCalle(dniPaciente, fechaDesde.getTime(), fechaHasta.getTime());
            if(listaPresencias != null && !listaPresencias.isEmpty()) {
                    super.createExcel(listaPresencias, titulo, ConstantesAplicacion.NOMBRE_EXCEL_PUERTA_CALLE, nombrePaciente);
                    return true;
            } else {
                    return false;
            }
    }
}
