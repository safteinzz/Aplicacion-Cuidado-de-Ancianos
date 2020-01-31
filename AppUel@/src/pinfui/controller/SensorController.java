package pinfui.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pinfui.dto.TipoRango;
import pinfui.entidades.Presencia;
import pinfui.entidades.RitmoCardiaco;
import pinfui.interfaz.PInfUI;

public class SensorController {

	 /**
     * Devuelve un dia menos a la fecha pasada por parametros
     * @param fecha Fecha a cual se le restara un dia
     * @return Devuelve la fecha pasada por parametros con un dia menos
     */
    public Calendar getDiaAnterior(final Calendar fecha) {
    	Calendar fechaReturn = Calendar.getInstance();
    	fechaReturn.setTime(fecha.getTime());
    	fechaReturn.add(Calendar.DAY_OF_YEAR, -1);
        
        return fechaReturn;
    }
    
    /**
     * Recupera la fecha actual del equipo
     * @return Devuelve la fecha actual del equipo
     */
    public Calendar getFechaActual() {
    	Calendar fechaActual = Calendar.getInstance();
        fechaActual.setTime(new Date());
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
    
    public void createExcel(List<?> listaObject, String[] titulo, String nombreHoja, String nombrePaciente) {
    	XSSFWorkbook workbook = new XSSFWorkbook();
         
        Map<String, XSSFCellStyle> styles = createStyles(workbook);

        Sheet sheet = workbook.createSheet("Business Plan");

        //Configuracion de la hoja
        sheet.setDisplayGridlines(false);
        sheet.setPrintGridlines(false);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        sheet.setAutobreaks(true);
        printSetup.setFitHeight((short)1);
        printSetup.setFitWidth((short)1);

        //the header row: centered text in 48pt font
        Row nombreRow = sheet.createRow(0);
        nombreRow.setHeightInPoints(80);
        Cell titleCell = nombreRow.createCell(0);
        titleCell.setCellValue(nombrePaciente);
        titleCell.setCellStyle(styles.get("title"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$B$1"));
        
        //Cabecera del excel
        Row headerRow = sheet.createRow(1);
        headerRow.setHeightInPoints(12.75f);
        for (int i = 0; i < titulo.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(titulo[i]);
            cell.setCellStyle(styles.get("header"));
        }
 
        int rowCount = 1;
        int contadorColumna = 0;
        boolean par = true;
        int valorMaximo = 0;
        int rowMaximo = 0;
        int valorMinimo = 999;
        int rowMinimo = 0;
        
        //Agregar el contenido de la tabla
        for (Object data : listaObject) {
            Row row = sheet.createRow(++rowCount);
            
            //Comprobar si una fila es par o impar
            if (rowCount % 2 == 0) {
            	par = true;
    		} else {
    			par = false;
    		}
            
            //Se agregar las diferentes celdas segun el tipo de objeto
            if(data instanceof Presencia) {
            	contadorColumna = 2;
            	//Creacion de la primera celda
            	Cell cell = row.createCell(0);
            	cell.setCellValue(PInfUI.getBundle().getString("tipoPresencia" + ((Presencia) data).getId_tipo_presencia()));
            	cell.setCellStyle(styles.get("cell_normal" + (par ? "" : "_impar")));
            	
            	//Creacion de la segunda celda
            	Cell cell1 = row.createCell(1);
            	cell1.setCellValue(((Presencia) data).getFecha());
            	cell1.setCellStyle(styles.get("cell_normal_date" + (par ? "" : "_impar")));
            } else if(data instanceof RitmoCardiaco) {
            	contadorColumna = 2;
            	//Creacion de la primera celda
            	Cell cell = row.createCell(0);
            	cell.setCellValue(((RitmoCardiaco) data).getValor());
            	cell.setCellStyle(styles.get("cell_normal" + (par ? "" : "_impar")));
            	
            	//Creacion de la segunda celda
            	Cell cell1 = row.createCell(1);
            	cell1.setCellValue(((RitmoCardiaco) data).getFecha());
            	cell1.setCellStyle(styles.get("cell_normal_date" + (par ? "" : "_impar")));
            	
            	//Averiguar que los valores maximos y minimos
            	if (((RitmoCardiaco) data).getValor() != 0) {
	            	if (((RitmoCardiaco) data).getValor() > valorMaximo) {
	            		valorMaximo = ((RitmoCardiaco) data).getValor();
	            		rowMaximo = rowCount;
		            }
		
		            if (((RitmoCardiaco) data).getValor() < valorMinimo) {
		            	valorMinimo = ((RitmoCardiaco) data).getValor();
		            	rowMinimo = rowCount;
		            }
            	}
            } 
            
        }
        
        //Si se encontro algun valor maximo este ya no sera 0 y se pondra su caja a rojo
        if(valorMaximo != 0) {
        	Cell cell = sheet.getRow(rowMaximo).getCell(0);
        	cell.setCellStyle(styles.get("cell_valor_maximo"));
        }
        
      //Si se encontro algun valor minimo este ya no sera 999 y se pondra su caja a azul
        if(valorMinimo != 999) {
        	Cell cell = sheet.getRow(rowMinimo).getCell(0);
        	cell.setCellStyle(styles.get("cell_valor_minimo"));
        }
        
        //Auto ajustar el ancho de las celdas segun su contenido
        for(int x = 0; x < contadorColumna; x++) {
    		sheet.autoSizeColumn(x);
    	}
         
        try (FileOutputStream outputStream = new FileOutputStream(nombreHoja + ".xlsx")) {
            workbook.write(outputStream);
            
            //Abrir el excel
            File file = new File(nombreHoja + ".xlsx");
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Metodo para crear los diferentes estilos de las celdas
     * @param wb
     * @return
     */
    private static Map<String, XSSFCellStyle> createStyles(XSSFWorkbook wb){
        Map<String, XSSFCellStyle> styles = new HashMap<>();
        DataFormat df = wb.createDataFormat();
        

        XSSFCellStyle style;
        Font headerFont = wb.createFont();
        headerFont.setBold(true);
        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(0, 153, 255)));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(headerFont);
        styles.put("header", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setWrapText(true);
        styles.put("cell_normal", style);
        
        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(232, 232, 232)));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        styles.put("cell_normal_impar", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setWrapText(true);
        style.setDataFormat(df.getFormat("dd/MM/yyyy HH:mm"));
        styles.put("cell_normal_date", style);
        
        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setWrapText(true);
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(232, 232, 232)));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setDataFormat(df.getFormat("dd/MM/yyyy HH:mm"));
        styles.put("cell_normal_date_impar", style);
        
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short)18);
        titleFont.setColor(IndexedColors.DARK_BLUE.getIndex());
        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(titleFont);
        style.setWrapText(true);
        styles.put("title", style);
        
        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setFillForegroundColor(new XSSFColor(java.awt.Color.RED));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        styles.put("cell_valor_maximo", style);
        
        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setFillForegroundColor(new XSSFColor(java.awt.Color.BLUE));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        styles.put("cell_valor_minimo", style);
        
        return styles;
    }

    /**
     * Metodo para crear el estilo de los bordes de las celdas
     * @param wb
     * @return
     */
    private static XSSFCellStyle createBorderedStyle(Workbook wb){
        BorderStyle thin = BorderStyle.THIN;
        short black = IndexedColors.BLACK.getIndex();

        XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
        style.setBorderRight(thin);
        style.setRightBorderColor(black);
        style.setBorderBottom(thin);
        style.setBottomBorderColor(black);
        style.setBorderLeft(thin);
        style.setLeftBorderColor(black);
        style.setBorderTop(thin);
        style.setTopBorderColor(black);
        return style;
    }
}
