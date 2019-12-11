package pinfui.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import pinfui.dto.TipoRango;
import pinfui.entidades.Presencia;

public class SensorController {

	 /**
     * Devuelve un dia menos a la fecha pasada por parametros
     * @param fecha Fecha a cual se le restara un dia
     * @return Devuelve la fecha pasada por parametros con un dia menos
     */
    public Calendar getDiaAnterior(final Calendar fecha) {
        fecha.add(Calendar.DAY_OF_YEAR, -1);
        
        return fecha;
    }
    
    /**
     * Recupera la fecha actual del equipo
     * @return Devuelve la fecha actual del equipo
     */
    public Calendar getFechaActual() {
    	Calendar fechaActual = Calendar.getInstance();
        fechaActual.setTime(new Date());
        resetearFecha(fechaActual, TipoRango.DIA);
        return fechaActual;
    }
    
    /**
     * Metodo encargado de resetear a 0 los valores por debajo del tipo de rango marcado
     * @param fecha Fecha a la cual se le quieren resetear los valores
     * @param tipoRango El rango por debajo del seleccionado sera reseteado a 0
     */
    public void resetearFecha(Calendar fecha, TipoRango tipoRango) {
    	if (tipoRango.equals(TipoRango.ANIO)) {
            fecha.set(Calendar.MONTH, 0);
            fecha.set(Calendar.DAY_OF_MONTH, 1);
            fecha.set(Calendar.HOUR_OF_DAY, 0);
            fecha.set(Calendar.MINUTE, 0);
            fecha.set(Calendar.SECOND, 0);
        } else if (tipoRango.equals(TipoRango.MES)) {
            fecha.set(Calendar.DAY_OF_MONTH, 1);
            fecha.set(Calendar.HOUR_OF_DAY, 0);
            fecha.set(Calendar.MINUTE, 0);
            fecha.set(Calendar.SECOND, 0);
        } else if (tipoRango.equals(TipoRango.DIA)) {
            fecha.set(Calendar.HOUR_OF_DAY, 0);
            fecha.set(Calendar.MINUTE, 0);
            fecha.set(Calendar.SECOND, 0);
        } else if (tipoRango.equals(TipoRango.HORA)) {
            fecha.set(Calendar.MINUTE, 0);
            fecha.set(Calendar.SECOND, 0);
        } else if (tipoRango.equals(TipoRango.MINUTO)) {
            fecha.set(Calendar.SECOND, 0);
        }
    }
    
}
