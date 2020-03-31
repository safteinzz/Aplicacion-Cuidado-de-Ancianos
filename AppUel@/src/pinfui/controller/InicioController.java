package pinfui.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import pinfui.dto.ConstantesAplicacion;
import pinfui.entidades.Alerta;
import pinfui.entidades.Asignacion;
import pinfui.entidades.Nota;
import pinfui.entidades.Usuario;
import pinfui.interfaz.PInfUI;

public class InicioController {

	public boolean cargarTablaAlertas(DefaultTableModel model, Usuario usuario) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		List<String> dniLista = new ArrayList<String>();
    	if(usuario.getId_Rol() == ConstantesAplicacion.ROL_PACIENTE) {
    		dniLista.add(usuario.getDni());
    	} else {
    		for(Asignacion asignacion : usuario.getListaAsignacion()) {
    			dniLista.add(asignacion.getDni_Asignado());
    		}
    	}
		
    	List<Alerta> listaAlertas = PInfUI.gestorDatos.getAlertas(dniLista);
        
        //BORRAR
        listaAlertas = new ArrayList<Alerta>();
        listaAlertas.add(new Alerta("12536", "SENSOR", 1, new Date()));
        listaAlertas.add(new Alerta("12536", "SENSOR", 50, new Date()));
        
    	if(listaAlertas != null && !listaAlertas.isEmpty()) {
    		for(Alerta alerta : listaAlertas) {
	    		Object[] obj = { sdf.format(alerta.getFecha()), alerta.getSensor(), alerta.getValor()};
	    		model.addRow(obj);
	    	}
    		return true;
    	}
    	
    	return false;
	}
	
	public boolean cargarTablaMensajeria(DefaultTableModel model, Usuario usuario) {

		if(usuario.getListaAsignacion() != null && !usuario.getListaAsignacion().isEmpty()) {
    		for(Asignacion asignacion : usuario.getListaAsignacion()) {
    			String nombre = "";
    			
    			if(usuario.getId_Rol() == ConstantesAplicacion.ROL_PACIENTE) {
    	    		nombre = asignacion.getUsuarioAsignado().getNombre() + " " + asignacion.getUsuarioAsignado().getApellidos();
    	    	} else {
    	    		nombre = asignacion.getUsuarioAsociado().getNombre() + " " + asignacion.getUsuarioAsociado().getApellidos();
    	    	}
    			
    			nombre += "(" + asignacion.getTipoAsignacion().getDescripcion() + ")";
    			
    			Object[] obj = { nombre };
	    		model.addRow(obj);
    		}
			
    		return true;
    	}
    	
    	return false;
	}
	
	public boolean cargarTablaNotas(DefaultTableModel model, Usuario usuario) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		usuario.setListaNotas(PInfUI.gestorDatos.getNotas(usuario.getDni()));
		
                //BORRAR
                usuario.setListaNotas(new ArrayList<Nota>());
                usuario.getListaNotas().add(new Nota(1, "1255", new Date(), "NOTA"));
                usuario.getListaNotas().add(new Nota(2, "1255", new Date(), "NOTA2"));
                
		if(usuario.getListaNotas() != null && !usuario.getListaNotas().isEmpty()) {
			for(Nota nota : usuario.getListaNotas()) {
				Object[] obj = { sdf.format(nota.getFecha()), nota.getNota() };
	    		model.addRow(obj);
			}
			
			return true;
		} else {
			return false;
		}
		
	}
        
        
        public boolean borrarNotas(int[] filasSeleccionadas, Usuario usuario){
            for(int filaSeleccionada : filasSeleccionadas){
                if(!PInfUI.gestorDatos.borrarNota(usuario.getListaNotas().get(filaSeleccionada).getId())){
                    return false;
                }
            }
            
            return true;
        }
}
