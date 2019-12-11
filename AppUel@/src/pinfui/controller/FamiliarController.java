/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.xml.bind.annotation.XmlElement;
import pinfui.dto.TipoRango;
import pinfui.entidades.RitmoCardiaco;

//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.axis.DateAxis;
//import org.jfree.chart.axis.DateTickUnit;
//import org.jfree.chart.axis.NumberAxis;
//import org.jfree.chart.axis.ValueAxis;
//import org.jfree.chart.plot.XYPlot;
//import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
//import org.jfree.data.time.Hour;
//import org.jfree.data.time.Minute;
//import org.jfree.data.time.SimpleTimePeriod;
//import org.jfree.data.time.TimePeriodValues;
//import org.jfree.data.time.TimePeriodValuesCollection;
//import org.jfree.data.xy.XYDataset;

/**
 *
 * @author sergi
 */
public class FamiliarController {
//    static int media = 0;
//	static int maxima = 0;
//	static int minima = 1000;
//	public static ResourceBundle mybundle = ResourceBundle.getBundle("resources.mensajes.Mensajes", new Locale("es","ES"));
//
//	public static void main(String[] args) throws Exception {
//		System.out.println(mybundle.getString("saludo"));
//		java.util.Date fechaPrograma = new java.util.Date();
//        System.out.println(fechaPrograma.getHours() + ":" + fechaPrograma.getMinutes() + ":" + fechaPrograma.getSeconds());
//
//        //Variables que se cargarán de la pantalla - El usuario introduce los datos en pantalla
//        TipoRango tipoRango = TipoRango.MINUTO;
//        int rango = 30;
//        String fechaDesde = "26/11/2019 00:00:00";
//        String fechaHasta = "28/11/2019 00:00:00";
//        
//        //Según el rango seleccionado la barra de fecha cambia
//		final DateAxis domainAxis = new DateAxis("Hora");
//		domainAxis.setVerticalTickLabels(true);
//		if(tipoRango.equals(TipoRango.ANIO)) {
//			domainAxis.setTickUnit(new DateTickUnit(DateTickUnit.YEAR, rango));
//			domainAxis.setDateFormatOverride(new SimpleDateFormat("yyyy"));
//		} else if(tipoRango.equals(TipoRango.MES)) {
//			domainAxis.setTickUnit(new DateTickUnit(DateTickUnit.MONTH, rango));
//			domainAxis.setDateFormatOverride(new SimpleDateFormat("MM/yyyy"));
//		} else if(tipoRango.equals(TipoRango.DIA)) {
//			domainAxis.setTickUnit(new DateTickUnit(DateTickUnit.DAY, rango));
//			domainAxis.setDateFormatOverride(new SimpleDateFormat("dd/MM"));
//		} else if(tipoRango.equals(TipoRango.HORA)) {
//			domainAxis.setTickUnit(new DateTickUnit(DateTickUnit.HOUR, rango));
//			domainAxis.setDateFormatOverride(new SimpleDateFormat("HH:mm"));
//		} else if(tipoRango.equals(TipoRango.MINUTO)) {
//			domainAxis.setTickUnit(new DateTickUnit(DateTickUnit.MINUTE, rango));
//			domainAxis.setDateFormatOverride(new SimpleDateFormat("HH:mm"));
//		}
//		
//		domainAxis.setLowerMargin(0.01);
//		domainAxis.setUpperMargin(0.01);
//		final ValueAxis rangeAxis = new NumberAxis("Pulsación");
//
//		//Se transforman los datos en parametros para la grafica
//		final XYDataset data2 = createDataset2(tipoRango, rango, fechaDesde, fechaHasta);
//		final StandardXYItemRenderer renderer2 = new StandardXYItemRenderer(StandardXYItemRenderer.SHAPES_AND_LINES);
//		renderer2.setShapesFilled(true);
//
//		final XYPlot plot = new XYPlot(data2, domainAxis, rangeAxis, renderer2);
//		//Los rangos para visualizar las pulsaciones dependen de la minima y la maxima
//		plot.getRangeAxis().setRange(minima - 10, maxima + 10);
//		final JFreeChart chart = new JFreeChart("Ritmo cardiaco del paciente1", plot);
//		
//		//Se transforma la grafica en imagen
//		BufferedImage image = chart.createBufferedImage(600, 400);
//		ImageIO.write(image, "png", new File("D:/xy-chart.png"));
//		
//		fechaPrograma = new java.util.Date();
//        System.out.println(fechaPrograma.getHours() + ":" + fechaPrograma.getMinutes() + ":" + fechaPrograma.getSeconds());
//	}
//
//	public static XYDataset createDataset2(TipoRango tipoRango, int rango, String fechaDesde, String fechaHasta) throws ParseException {
//
//		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//
//		//Nombres de las diferentes lineas de la grafica
//		final TimePeriodValues s1 = new TimePeriodValues("Ritmo cardiaco");
//		final TimePeriodValues s2 = new TimePeriodValues("Media");
//
//		List<RitmoCardiaco> puntosFinales = new ArrayList<RitmoCardiaco>();
//		List<RitmoCardiaco> puntos = GestorDatos.getRitmoCardiaco(tipoRango, rango,sdf.parse(fechaDesde),sdf.parse(fechaHasta));
//		
//		
//		/**
//		//Carga de datos de prueba - BORRAR
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 11:00:00"), 150));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 11:30:00"), 100));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 12:00:00"), 80));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 12:30:00"), 70));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 13:00:00"), 90));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 13:30:00"), 70));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 14:00:00"), 80));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 14:30:00"), 90));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 15:00:00"), 90));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 15:30:00"), 100));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 16:00:00"), 105));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 16:30:00"), 0));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 17:00:00"), 140));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 17:30:00"), 150));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 18:00:00"), 160));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 18:30:00"), 180));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 19:00:00"), 170));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 19:30:00"), 160));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 20:00:00"), 180));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 20:30:00"), 100));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 21:00:00"), 90));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 21:30:00"), 90));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 22:00:00"), 80));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 22:30:00"), 90));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 23:00:00"), 80));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("20/11/2019 23:30:00"), 100)); // 23:30
//
//		// NUEVO DIA
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 00:00:00"), 90)); // 00:00
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 00:30:00"), 70));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 01:00:00"), 80));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 01:30:00"), 70));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 02:00:00"), 80));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 02:30:00"), 90));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 03:00:00"), 100));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 03:30:00"), 110));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 04:00:00"), 90));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 04:30:00"), 110));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 05:00:00"), 80));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 05:30:00"), 90));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 06:00:00"), 80));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 06:30:00"), 70));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 07:00:00"), 60));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 07:30:00"), 55));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 08:00:00"), 57));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 08:30:00"), 58));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 09:00:00"), 80));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 09:30:00"), 85));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 10:00:00"), 90));
//		puntos.add(new RitmoCardiaco(1, sdf.parse("21/11/2019 10:30:00"), 100));
//		*/
//
//		//De la lista obtenida calculamos la media segun los rangos
//		puntosFinales = mediasLista(puntos, tipoRango, rango, sdf.parse(fechaDesde));
//
//		//Recorremos la lista para calcular el punto maximo, minimo y la media total 
//		int contador = 0;
//		for (RitmoCardiaco punto : puntosFinales) {
//			if(punto.getValor() != 0) {
//				media += punto.getValor();
//				if (punto.getValor() > maxima) {
//					maxima = punto.getValor();
//				}
//	
//				if (punto.getValor() < minima) {
//					minima = punto.getValor();
//				}
//				contador ++;
//			}
//		}
//
//		media = media / contador;
//
//		Minute m0 = null;
//		Minute m1 = null;
//
//		Calendar calendarPunto = Calendar.getInstance();
//		calendarPunto.setTime(puntosFinales.get(0).getFecha());
//		
//		// Calculos para obtener el primer punto 
//		m0 = new Minute(calendarPunto.get(Calendar.MINUTE) - 1, new Hour(calendarPunto.get(Calendar.HOUR_OF_DAY), calendarPunto.get(Calendar.DAY_OF_MONTH), calendarPunto.get(Calendar.MONTH) + 1, calendarPunto.get(Calendar.YEAR)));
//		
//		// Recorremos la lista de puntos para ir pintando los puntos
//		for (RitmoCardiaco punto : puntosFinales) {
//			calendarPunto.setTime(punto.getFecha());
//			m1 = new Minute(calendarPunto.get(Calendar.MINUTE), new Hour(calendarPunto.get(Calendar.HOUR_OF_DAY), calendarPunto.get(Calendar.DAY_OF_MONTH), calendarPunto.get(Calendar.MONTH) + 1, calendarPunto.get(Calendar.YEAR)));
//			s1.add(new SimpleTimePeriod(m0.getStart(), m1.getStart()), punto.getValor());
//			s2.add(new SimpleTimePeriod(m0.getStart(), m1.getStart()), media);
//			m0 = new Minute(calendarPunto.get(Calendar.MINUTE), new Hour(calendarPunto.get(Calendar.HOUR_OF_DAY), calendarPunto.get(Calendar.DAY_OF_MONTH), calendarPunto.get(Calendar.MONTH) + 1, calendarPunto.get(Calendar.YEAR)));
//		}
//
//		final TimePeriodValuesCollection dataset = new TimePeriodValuesCollection();
//		dataset.addSeries(s1);
//		dataset.addSeries(s2);
//
//		return dataset;
//	}
//
//	/**
//	 * Metodo que se encarga de calcular un punto medio entre los valores de un rango seleccionado por el usuario,
//	 * este rango se pasa por parametros al metodo.
//	 * @param puntos
//	 * @param tipoRango
//	 * @param rango
//	 * @return List<RitmoCardiaco> lista con los puntos medios obtenidos entre todos los valores.
//	 */
//	private static List<RitmoCardiaco> mediasLista(List<RitmoCardiaco> puntos, TipoRango tipoRango, int rango, java.util.Date fechaDesde){
//		List<RitmoCardiaco> puntosFinales = new ArrayList<RitmoCardiaco>();
//		
//		Calendar fechaPunto = Calendar.getInstance();
//		fechaPunto.setTime(fechaDesde);
//		Calendar fechaGuardar = Calendar.getInstance();
//		
//		// Recorremos la lista de los datos cargados
//		for(int x = 0; x < puntos.size(); x++) {
//			int media = 0 ;
//			
//			calcularFechaProxPunto(fechaPunto, fechaGuardar, tipoRango, rango);
//			
//			int contador = 0;
//			int i = 0;
//			
//			// Cuando tenemos la fecha hasta donde tenemos que llegar, volveremos a recorrer todos los puntos para calcular la media del tramo de fechas
//			for(i = x; i < puntos.size(); i++) {
//				if(fechaPunto.getTime().compareTo(puntos.get(i).getFecha()) > 0) {
//					media += puntos.get(i).getValor();
//					contador ++;
//				} else {
//					//Si no ha encontrado ninguna fecha en el rango de tiempo, ponemos un valor a 0
//					// y cargamos la proxima fecha punto y volvemos quitamos un valor al for para no perder ningun valor
//					if(contador == 0) {
//						puntosFinales.add(new RitmoCardiaco(puntos.get(x).getIdPaciente(), fechaGuardar.getTime(), 0));
//						calcularFechaProxPunto(fechaPunto, fechaGuardar, tipoRango, rango);
//						i --;
//					} else {
//						//Si se encontro alguna fechan en el rango y ya no se encuentran mas salimos del for para optimizar
//						break;
//					}
//				}
//			}
//			
//			// Seteamos la X con el ultimo punto calculado para no repetir valores
//			x = i - 1;
//			
//			System.out.println("Punto: " + x + ", Fecha: " + fechaGuardar.getTime());
//			// Una vez tengamos el resultado, este lo agregamos a la lista de puntos finales
//			puntosFinales.add(new RitmoCardiaco(puntos.get(x).getIdPaciente(), fechaGuardar.getTime(), media / contador));
//		}
//		return puntosFinales;
//	}
//	
//	/**
//	 * Metodo encargado de calcular la proxima fecha hasta donde se debe calcular la media
//	 * @param fechaPunto
//	 * @param fechaGuardar
//	 * @param tipoRango
//	 * @param rango
//	 */
//	private static void calcularFechaProxPunto(Calendar fechaPunto, Calendar fechaGuardar, final TipoRango tipoRango, final int rango) {
//		//Segun el tipo de rango seleccionado ajustamos la fecha hasta donde tiene que coger los puntos para calcular la media de ese tramo
//		if(tipoRango.equals(TipoRango.ANIO)) {
//			fechaPunto.set(Calendar.MONTH, 0);
//			fechaPunto.set(Calendar.DAY_OF_YEAR, 0);
//			fechaPunto.set(Calendar.HOUR_OF_DAY, 0);
//			fechaPunto.set(Calendar.MINUTE, 0);
//			fechaPunto.set(Calendar.SECOND, 0);
//			
//			fechaGuardar.setTime(fechaPunto.getTime());
//			
//			fechaPunto.add(Calendar.YEAR, rango);
//		} else if(tipoRango.equals(TipoRango.MES)) {
//			fechaPunto.set(Calendar.DAY_OF_MONTH, 0);
//			fechaPunto.set(Calendar.HOUR_OF_DAY, 0);
//			fechaPunto.set(Calendar.MINUTE, 0);
//			fechaPunto.set(Calendar.SECOND, 0);
//			
//			fechaGuardar.setTime(fechaPunto.getTime());
//			
//			fechaPunto.add(Calendar.MONTH, rango);
//		} else if(tipoRango.equals(TipoRango.DIA)) {
//			fechaPunto.set(Calendar.HOUR_OF_DAY, 0);
//			fechaPunto.set(Calendar.MINUTE, 0);
//			fechaPunto.set(Calendar.SECOND, 0);
//			
//			fechaGuardar.setTime(fechaPunto.getTime());
//			
//			fechaPunto.add(Calendar.DAY_OF_MONTH, rango);
//		} else if(tipoRango.equals(TipoRango.HORA)) {
//			fechaPunto.set(Calendar.MINUTE, 0);
//			fechaPunto.set(Calendar.SECOND, 0);
//			
//			fechaGuardar.setTime(fechaPunto.getTime());
//			
//			fechaPunto.add(Calendar.HOUR_OF_DAY, rango);
//		} else if(tipoRango.equals(TipoRango.MINUTO)) {
//			fechaPunto.set(Calendar.SECOND, 0);
//			
//			fechaGuardar.setTime(fechaPunto.getTime());
//			
//			fechaPunto.add(Calendar.MINUTE, rango);
//		}
//	}
	
}

