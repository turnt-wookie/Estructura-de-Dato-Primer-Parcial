/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.*; 
import jxl.read.biff.BiffException;

/**
 *
 * @author Nacho
 */
public class Reading {
    
    /**
     *
     */
    public Workbook workbook;
    /**
     *
     */
    public Sheet sheet;
    /**
     *
     */
    public Cell a[],
    /**
     *
     */
    b[],
    /**
     *
     */
    c[];
    /**
     *
     */
    public int pag;
    
    
    /**
     * Constructor de la clase. Recibe un dato de tipo entero que sirve para especificar en pagina
     * del archivo de excel se van a leer las columnas.
     * @param i Contador
     * @param F 
     */
    public Reading(int i, String F){
        try {
            
            pag = i;
            workbook = Workbook.getWorkbook(new File(F));
            sheet = workbook.getSheet(pag);
            
            /*Se igualan los arreglos de tipo celda a las columnas correspondientes con los datos deseados. 
             * Esto rellena el arreglo con cada celda en la columna que contenga datos.
             */
            a = sheet.getColumn(4);
            b = sheet.getColumn(5);
            c = sheet.getColumn(6);
            
            
            
        } catch (BiffException ex) {
            System.out.println("Error");
        } catch (IOException ex){
            System.out.println("Error"); 
        }
        workbook.close();
    }

    /**
     * Metodo que regresa un dato de tipo celda.
     * @return
     */
    public Cell[] getA() {
        return a;
    }

    /**
     * Metodo que recibe un dato de tipo celda.
     * @param a
     */
    public void setA(Cell[] a) {
        this.a = a;
    }

    /**
     * Metodo que regresa un dato de tipo celda.
     * @return
     */
    public Cell[] getB() {
        return b;
    }

    /**
     * Metodo que recibe un dato de tipo celda.
     * @param b
     */
    public void setB(Cell[] b) {
        this.b = b;
    }

    /**
     * Metodo que regresa un dato de tipo celda.
     * @return
     */
    public Cell[] getC() {
        return c;
    }

    /**
     * Metodo que recibe un dato de tipo celda.
     * @param c
     */
    public void setC(Cell[] c) {
        this.c = c;
    }

    /**
     * Metodo que regresa un dato tipo entero
     * @return
     */
    public int getPag() {
        return pag;
    }

    /**
     * Metodo que recibe un dato tipo entero
     * @param pag
     */
    public void setPag(int pag) {
        this.pag = pag;
    }
    
    
    
    
    
    
    }
    
    
    
    
    
    
    

