/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Mezclas;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Juan Pablo
 */
public class Readfile {

    private Workbook workbook;
    private Sheet sheet;
    private Cell a[], b[], c[];
    private int  row=0;

    /**
     * Abre un archivo de excel para leer su contenido
     *
     * @param FilePath Recibe la ruta del archivo con el cual se va a trabajar
     */
    public Readfile(String FilePath){
        try {
            workbook = Workbook.getWorkbook(new File(FilePath));
            sheet = workbook.getSheet(0);
            a = sheet.getColumn(0);
            b = sheet.getColumn(1);
            c = sheet.getColumn(2);
            
            
            
        } catch (BiffException ex) {
            System.out.println("Error 1");
        } catch (IOException ex){
            System.out.println("Error 2");
        }
    }


    /**
     * Revisa si el archivo tiene una siguiente linea
     *
     *
     * @return 
     */
    public boolean hasNextRow(){
        if (row<a.length){
            if (a[row].getContents().isEmpty()){
                return false;
            }else
                return true;



        }

        return false;
    }


    /**
     * Regresa la siguiente linea del archivo
     *
     *
     * @return 
     */
    public String[] nextRow(){
        String cf;
        String af, bf;

        if (a[row].getContents().isEmpty()){
            af = " ";
        }else{
            af = a[row].getContents();
        }


        if (b[row].getContents().isEmpty()){
            bf = " ";
        }else{
            bf = b[row].getContents();
        }
        cf = c[row].getContents();
        if (c[row].getContents().contains("S")){
            cf = c[row].getContents();
        }else {
            if (c[row].getContents().length() == 0) {
                cf = " ";
            } else {
                Double cfD;
                String s = c[row].getContents();
                try{


                    s = s.replace(",", "");
                    cfD = Double.parseDouble(s);
                    cf = cfD.toString();
                }catch(NumberFormatException e){
                    System.out.println(s);
                }

            }
        }

        String[] ob = {af , bf ,cf};

        row++;
        return ob;
    }


    /**
     * Cierra el archivo
     *
     *
     */
    public void close(){
        workbook.close();
    }

    /**
     * Devuelve el contenido de la primer columna
     *
     *
     * @return 
     */
    public Cell[] getA() {

        return sheet.getColumn(0);
    }

    /**
     * Devuelve el contenido de la segunda columna
     *
     *
     * @return 
     */
    public Cell[] getB() {

        return sheet.getColumn(1);
    }

    /**
     * Devuelve el contenido de la tercer columnad
     *
     *
     * @return
     */
    public Cell[] getC() {

        return sheet.getColumn(2);
    }


}
    
    
    
    
    
    
    

