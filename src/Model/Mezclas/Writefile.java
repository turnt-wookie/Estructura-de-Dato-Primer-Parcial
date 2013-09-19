package Model.Mezclas;

import jxl.*;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Juan Pablo
 * Date: 16/09/13
 * Time: 04:29 PM
 */
public class Writefile{
    private WritableWorkbook workbook;
    private WritableSheet workingSheet;
    private WritableCellFormat font;
    private String inputFile;
    private int activeRow=0;

    /**
     * Da la ruta del archivo a crear
     *
     * @param  inputFile  ruta del archivo
     */
    public void setOutputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * Genera los encabezados para poder hacer un nuevo
     * archivo de formato excel
     *
     * @throws IOException
     * @throws WriteException  
     */
    public void generate() throws IOException, WriteException {
        File file = new File(inputFile);
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("es", "ES"));
        wbSettings.setEncoding("Cp1252");

        workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Worksheet", 0);
        WritableSheet excelSheet = workbook.getSheet(0);

        WritableFont f10pt = new WritableFont(WritableFont.ARIAL, 10);

        font = new WritableCellFormat(f10pt);
        // Lets automatically wrap the cells
        font.setWrap(true);


        //createLabel(excelSheet);
        workingSheet =    excelSheet;



    }

    /**
     * Escribe y cierra el nuevo archivo creado
     *
     * @throws IOException
     * @throws WriteException  
     */
    public void write()throws IOException, WriteException{

        workbook.write();
    }

    /**
     * Ingresa en el archivo de excel el dato dado
     *
     * @param  column  columna a escribir
     * @param  row fila a escribir
     * @param  integer el valor
     * @throws WriteException 
     * @throws RowsExceededException  
     *
     */
    public void addNumber( int column, int row, Double integer) throws WriteException, RowsExceededException {

        Number number;
        number = new Number(column, row, integer, font);
        workingSheet.addCell(number);
    }

    /**
     * Ingresa en el archivo de excel el dato dado
     *
     * @param  column  columna a escribir
     * @param  row fila a escribir
     * @param  s el valor
     * @throws WriteException  
     *
     */
    public void addText( int column, int row, String s) throws WriteException {

        Label label;
        if (s.isEmpty()){
            s = " ";
        }
        label = new Label(column, row, s, font);

        workingSheet.addCell(label);
    }

    /**
     * Ingresa en el archivo de excel el dato dado
     * a la linea activa utilizando el atributo
     * active row
     *
     * @param  r  arreglo con dos strings y un double en forma de String
     * @throws WriteException  

     *
     */
    public void addNewRow ( String [] r) throws WriteException {



            addText(0, activeRow, r[0]);
            addText(1, activeRow, r[1]);
        if (r[2].contains("S")) {
            addText(2, activeRow, r[2]);
        } else if (r[2].length() == 0){
            addText(2, activeRow, r[2]);
            System.out.println("is empty");
        }else{

            try {
                addNumber(2, activeRow, Double.parseDouble(r[2]));
            }catch(NumberFormatException e){
                addText(2, activeRow,"");
            }

        }


        System.out.println(activeRow++);




    }

    /**
     * Recibe un dato tipo int que sirve como contador y elimina la linea del excel
     * 
     * @param i contador
     */
    public void removeRow(int i){
        workingSheet.removeRow(i);
    }

    /**
     * Escribe y cierra el nuevo archivo creado
     *
     * @throws IOException 
     * @throws WriteException 
     */
    public void close()throws IOException, WriteException{

        write();
        workbook.close();

    }

    /**
     *
     * @param args
     * @throws WriteException
     * @throws IOException
     */
    public static void main(String[] args) throws WriteException, IOException {
        Writefile test = new Writefile();
        test.setOutputFile("c:/test.xls");
        test.generate();
        String [] s = {"hoal", "hola", "1.2", "hola", "hola", "hola"};

        test.addNewRow(s);

        test.write();
        test.close();
        System.out.println("Please check the result file under c:/temp/lars.xls ");
    }
}