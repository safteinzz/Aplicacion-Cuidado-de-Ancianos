/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.data.time.TimePeriodValues;
import org.jfree.data.time.TimePeriodValuesCollection;
import org.jfree.data.xy.XYDataset;

import pinfui.dto.ConstantesAplicacion;
import pinfui.dto.TipoRango;
import pinfui.entidades.RitmoCardiaco;
import pinfui.interfaz.PInfUI;

/**
 * Clase especializada en el control de datos de Ritmo Cardiaco y devuelver una grafica con los datos
 * @author ITCOM
 */
public class RitmoCardiacoController extends SensorController{

    int media = 0;
    int maxima = 0;
    int minima = 1000;

    
    /**
     * Metodo encargado de devolver la imagen con la grafica de valores
     * @param dniPaciente DNI del paciente a buscar
     * @param nombrePaciente Nombre del paciente a buscar
     * @param tipoRango Tipo de rango para la diferente de puntos de la grafica
     * @param rango Cantidad de separacion entre un punto y otro de la grafica
     * @param fechaDesde Fecha desde donde empieza a coger valores para la grafica
     * @param fechaHasta Fecha hasta donde tiene que coger valores para la grafica
     * @return Devuelve un objeto de Image con la imagen de la grafica creada
     */
    @SuppressWarnings("deprecation")
	public Image crearGrafica(String dniPaciente, String nombrePaciente, TipoRango tipoRango, int rango, Date fechaDesde, Date fechaHasta) {
    	System.out.println(new Date());
        BufferedImage image = null;

        //Según el rango seleccionado la barra de fecha cambia
        //Linea en el eje X
        final DateAxis domainAxis = new DateAxis(PInfUI.getBundle().getString("tiempo"));
        domainAxis.setVerticalTickLabels(true);
        if (tipoRango.equals(TipoRango.ANIO)) {
            domainAxis.setTickUnit(new DateTickUnit(DateTickUnit.YEAR, rango));
            domainAxis.setDateFormatOverride(new SimpleDateFormat("yyyy"));
        } else if (tipoRango.equals(TipoRango.MES)) {
            domainAxis.setTickUnit(new DateTickUnit(DateTickUnit.MONTH, rango));
            domainAxis.setDateFormatOverride(new SimpleDateFormat("MM/yyyy"));
        } else if (tipoRango.equals(TipoRango.DIA)) {
            domainAxis.setTickUnit(new DateTickUnit(DateTickUnit.DAY, rango));
            domainAxis.setDateFormatOverride(new SimpleDateFormat("dd/MM"));
        } else if (tipoRango.equals(TipoRango.HORA)) {
            domainAxis.setTickUnit(new DateTickUnit(DateTickUnit.HOUR, rango));
            domainAxis.setDateFormatOverride(new SimpleDateFormat("HH:mm"));
        } else if (tipoRango.equals(TipoRango.MINUTO)) {
            domainAxis.setTickUnit(new DateTickUnit(DateTickUnit.MINUTE, rango));
            domainAxis.setDateFormatOverride(new SimpleDateFormat("HH:mm"));
        }

        domainAxis.setLowerMargin(0.01);
        domainAxis.setUpperMargin(0.01);
        //Linea en el eje Y
        final ValueAxis rangeAxis = new NumberAxis(PInfUI.getBundle().getString("pulsacion"));

        try {
            //Se transforman los datos en parametros para la grafica
            XYDataset data = createDataset(dniPaciente, tipoRango, rango, fechaDesde, fechaHasta);
            if(data != null) {
	            final StandardXYItemRenderer render = new StandardXYItemRenderer(StandardXYItemRenderer.SHAPES_AND_LINES);
	            render.setShapesFilled(true);
	
	            final XYPlot plot = new XYPlot(data, domainAxis, rangeAxis, render);
	            //Los rangos para visualizar las pulsaciones dependen de la minima y la maxima
	            plot.getRangeAxis().setRange(minima - 10, maxima + 10);
	            final JFreeChart chart = new JFreeChart(PInfUI.getBundle().getString("tituloGrafica") + " " + nombrePaciente, plot);
	            chart.setBackgroundPaint(Color.white);
	            //Se transforma la grafica en imagen
	            image = chart.createBufferedImage(1000, 600);
            }
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        System.out.println(new Date());
        return image;
    }

    /**
     * Metodo encargado de transformas los valores de ritmo cardiaco en un objeto para ser utilizado por la grafica JFreeChart
     * @param dniPaciente DNI del paciente a buscar
     * @param tipoRango Tipo de rango para la diferente de puntos de la grafica
     * @param rango Cantidad de separacion entre un punto y otro de la grafica
     * @param fechaDesde Fecha desde donde empieza a coger valores para la grafica
     * @param fechaHasta Fecha hasta donde tiene que coger valores para la grafica
     * @return
     * @throws ParseException
     */
    private XYDataset createDataset(String dniPaciente, TipoRango tipoRango, int rango, Date fechaDesde, Date fechaHasta) throws ParseException {

        //Nombres de las diferentes lineas de la grafica
        final TimePeriodValues s1 = new TimePeriodValues(PInfUI.getBundle().getString("pulsaciones"));
        final TimePeriodValues s2 = new TimePeriodValues(PInfUI.getBundle().getString("media"));

        List<RitmoCardiaco> puntosFinales = new ArrayList<RitmoCardiaco>();
        List<RitmoCardiaco> puntos = PInfUI.gestorDatos.getRitmoCardiaco(dniPaciente, tipoRango, rango, fechaDesde, fechaHasta);

        final TimePeriodValuesCollection dataset = new TimePeriodValuesCollection();
        if(!puntos.isEmpty()) {
		    //De la lista obtenida calculamos la media segun los rangos
		    puntosFinales = mediasLista(puntos, tipoRango, rango, fechaDesde);
		    System.out.println("Tamaño: " + puntos.size());
		    //Recorremos la lista para calcular el punto maximo, minimo y la media total 
		    int contador = 0;
		    for (RitmoCardiaco punto : puntosFinales) {
		        if (punto.getValor() != 0) {
		            media += punto.getValor();
		            if (punto.getValor() > maxima) {
		                maxima = punto.getValor();
		            }
		
		            if (punto.getValor() < minima) {
		                minima = punto.getValor();
		            }
		            contador++;
		        }
		    }
		
		    media = media / contador;
		
		    Minute m0 = null;
		    Minute m1 = null;
		
		    Calendar calendarPunto = Calendar.getInstance();
		    calendarPunto.setTime(puntosFinales.get(0).getFecha());
		
		    // Calculos para obtener el primer punto 
		    m0 = new Minute(calendarPunto.get(Calendar.MINUTE) - 1, new Hour(calendarPunto.get(Calendar.HOUR_OF_DAY), calendarPunto.get(Calendar.DAY_OF_MONTH), calendarPunto.get(Calendar.MONTH) + 1, calendarPunto.get(Calendar.YEAR)));
		
		    // Recorremos la lista de puntos para ir pintando los puntos
		    for (RitmoCardiaco punto : puntosFinales) {
		        calendarPunto.setTime(punto.getFecha());
		        m1 = new Minute(calendarPunto.get(Calendar.MINUTE), new Hour(calendarPunto.get(Calendar.HOUR_OF_DAY), calendarPunto.get(Calendar.DAY_OF_MONTH), calendarPunto.get(Calendar.MONTH) + 1, calendarPunto.get(Calendar.YEAR)));
		        s1.add(new SimpleTimePeriod(m0.getStart(), m1.getStart()), punto.getValor());
		        s2.add(new SimpleTimePeriod(m0.getStart(), m1.getStart()), media);
		        m0 = new Minute(calendarPunto.get(Calendar.MINUTE), new Hour(calendarPunto.get(Calendar.HOUR_OF_DAY), calendarPunto.get(Calendar.DAY_OF_MONTH), calendarPunto.get(Calendar.MONTH) + 1, calendarPunto.get(Calendar.YEAR)));
		    }
		
		    
		    dataset.addSeries(s1);
		    dataset.addSeries(s2);
		    
		    return dataset;
        } else {
        	return null;
        }
    }

    /**
     * Metodo que se encarga de calcular un punto medio entre los valores de un
     * rango seleccionado por el usuario, este rango se pasa por parametros al
     * metodo.
     *
     * @param puntos Lista con los puntos de los que se quiere calcular la media
     * @param tipoRango Tipo de rango para la diferente de puntos de la grafica
     * @param rango Cantidad de separacion entre un punto y otro de la grafica
     * @return List<RitmoCardiaco> lista con los puntos medios obtenidos entre
     * todos los valores.
     */
    private List<RitmoCardiaco> mediasLista(List<RitmoCardiaco> puntos, TipoRango tipoRango, int rango, java.util.Date fechaDesde) {
        List<RitmoCardiaco> puntosFinales = new ArrayList<RitmoCardiaco>();

        Calendar fechaPunto = Calendar.getInstance();
        fechaPunto.setTime(fechaDesde);
        Calendar fechaGuardar = Calendar.getInstance();

        // Recorremos la lista de los datos cargados
        for (int x = 0; x < puntos.size(); x++) {
            int media = 0;

            calcularFechaProxPunto(fechaPunto, fechaGuardar, tipoRango, rango);

            int contador = 0;
            int i = 0;

            // Cuando tenemos la fecha hasta donde tenemos que llegar, volveremos a recorrer todos los puntos para calcular la media del tramo de fechas
            for (i = x; i < puntos.size(); i++) {
                if (fechaPunto.getTime().compareTo(puntos.get(i).getFecha()) > 0) {
                    media += puntos.get(i).getValor();
                    contador++;
                } else {
                    //Si no ha encontrado ninguna fecha en el rango de tiempo, ponemos un valor a 0
                    // y cargamos la proxima fecha punto y volvemos quitamos un valor al for para no perder ningun valor
                    if (contador == 0) {
                        puntosFinales.add(new RitmoCardiaco(puntos.get(x).getDniPaciente(), fechaGuardar.getTime(), 0));
                        calcularFechaProxPunto(fechaPunto, fechaGuardar, tipoRango, rango);
                        i--;
                    } else {
                        //Si se encontro alguna fechan en el rango y ya no se encuentran mas salimos del for para optimizar
                        break;
                    }
                }
            }

            // Seteamos la X con el ultimo punto calculado para no repetir valores
            x = i - 1;

            // Una vez tengamos el resultado, este lo agregamos a la lista de puntos finales
            puntosFinales.add(new RitmoCardiaco(puntos.get(x).getDniPaciente(), fechaGuardar.getTime(), media / contador));
        }
        return puntosFinales;
    }
    
    /**
     * Metodo encargado de calcular la proxima fecha hasta donde se debe
     * calcular la media
     *
     * @param fechaPunto
     * @param fechaGuardar
     * @param tipoRango
     * @param rango
     */
    private void calcularFechaProxPunto(Calendar fechaPunto, Calendar fechaGuardar, final TipoRango tipoRango, final int rango) {
        //Segun el tipo de rango seleccionado ajustamos la fecha hasta donde tiene que coger los puntos para calcular la media de ese tramo
    	resetearFecha(fechaPunto, tipoRango);
    	
    	fechaGuardar.setTime(fechaPunto.getTime());
    	
    	if (tipoRango.equals(TipoRango.ANIO)) {
            fechaPunto.add(Calendar.YEAR, rango);
        } else if (tipoRango.equals(TipoRango.MES)) {
            fechaPunto.add(Calendar.MONTH, rango);
        } else if (tipoRango.equals(TipoRango.DIA)) {
            fechaPunto.add(Calendar.DAY_OF_MONTH, rango);
        } else if (tipoRango.equals(TipoRango.HORA)) {
            fechaPunto.add(Calendar.HOUR_OF_DAY, rango);
        } else if (tipoRango.equals(TipoRango.MINUTO)) {
            fechaPunto.add(Calendar.MINUTE, rango);
        }
    }
    
    /**
     * Metodo para la generacion de un excel con los valores de ritmo cardiaco segun un excel
     * @param dniPaciente DNI del paciente a buscar
     * @param nombrePaciente Nombre del paciente a buscar
     * @param tipoRango Tipo de rango para la diferente de puntos de la grafica
     * @param rango Cantidad de separacion entre un punto y otro de la grafica
     * @param fechaDesde Fecha desde donde empieza a coger valores para la grafica
     * @param fechaHasta Fecha hasta donde tiene que coger valores para la grafica
     * @return
     */
    public boolean createExcel(String dniPaciente, String nombrePaciente, TipoRango tipoRango, int rango, Date fechaDesde, Date fechaHasta) {
    	List<RitmoCardiaco> puntos = PInfUI.gestorDatos.getRitmoCardiaco(dniPaciente, tipoRango, rango, fechaDesde, fechaHasta);
		if(puntos != null && !puntos.isEmpty()) {
			String[] titulo = {"Valor", "Fecha"};
			super.createExcel(puntos, titulo, ConstantesAplicacion.NOMBRE_EXCEL_RITMO_CARDIACO, nombrePaciente);
			return true;
		} else {
			return false;
		}
	}
}
