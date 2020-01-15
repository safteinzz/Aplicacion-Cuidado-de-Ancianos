package pinfui.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import pinfui.dto.ConstantesAplicacion;
import pinfui.entidades.Presencia;
import pinfui.entidades.TipoPresencia;
import pinfui.interfaz.PInfUI;

public class PresenciaController extends SensorController{

	public boolean convertListPresencia(DefaultTableModel model, String dniPaciente, Calendar fechaDesde, Calendar fechaHasta, TipoPresencia tipoPresencia) {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    	List<Presencia> listaPresencias = PInfUI.gestorDatos.getPresencias(dniPaciente, fechaDesde.getTime(), fechaHasta.getTime(), tipoPresencia);
    	if(listaPresencias != null && !listaPresencias.isEmpty()) {
	    	for(Presencia presencia : listaPresencias) {
	    		Object[] obj = { PInfUI.getBundle().getString("tipoPresencia" + presencia.getId_tipo_presencia()), sdf.format(presencia.getFecha())};
	    		
	    		model.addRow(obj);
	    	}
	    	return true;
    	} else {
    		return false;
    	}
    }
	
	public boolean createExcel(String dniPaciente, Calendar fechaDesde, Calendar fechaHasta, TipoPresencia tipoPresencia, String[] titulo, String nombrePaciente) {
		List<Presencia> listaPresencias = PInfUI.gestorDatos.getPresencias(dniPaciente, fechaDesde.getTime(), fechaHasta.getTime(), tipoPresencia);
		if(listaPresencias != null && !listaPresencias.isEmpty()) {
			super.createExcel(listaPresencias, titulo, ConstantesAplicacion.NOMBRE_EXCEL_PRESENCIA, nombrePaciente);
			return true;
		} else {
			return false;
		}
	}
}
