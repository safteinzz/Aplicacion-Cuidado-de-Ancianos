package pinfui.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.table.DefaultTableModel;

import pinfui.entidades.Presencia;
import pinfui.entidades.TipoPresencia;
import pinfui.interfaz.PInfUI;

public class PresenciaController extends SensorController{

	public void convertListPresencia(DefaultTableModel model, String dniPaciente, Calendar fechaDesde, Calendar fechaHasta, TipoPresencia tipoPresencia) {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    	
    	for(Presencia presencia : PInfUI.gestorDatos.getPresencias(dniPaciente, fechaDesde.getTime(), fechaHasta.getTime(), tipoPresencia.getId())) {
    		Object[] obj = { presencia.getTipoPresencia().getId(), sdf.format(presencia.getFecha())};
    		
    		model.addRow(obj);
    	}
    	
//    	return model;
    }
}
