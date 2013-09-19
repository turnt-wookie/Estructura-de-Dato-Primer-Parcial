/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.*;

import java.io.IOException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Model.Mezclas.MezclaDirecta;
import jxl.*;
import jxl.write.WriteException;

/**
 *
 * @author Nacho
 */
public class Agrupar {
    
    /**
     * Desclaracion de una variable del tipo Reading, que es una clase del paquete Model.
     */
    public Reading dr;
    /**
     * Declaracion de 3 arreglos de tipo Celda.
     */
    public String[] a,
    /**
     *
     */
    b;
    /**
     *
     */
    public double[] c;
    
    
    /**
     * Metodo del controlador que lee la hoja deseada de excel inicializando la funcion Reading.
     * @param i
     */
    public void leer(int i){
        
       dr = new Reading(i, "./database/BaseDatosMexico.xls");
        
    }
    
    /**
     * Metodo del controlador que recibe un entero que indicara que tipo de ordenacion de realizara.
     * @param i
     */
    public void metodo(int i){

        
        /*
         *Se declara e inicializa una variable del tipo Metodos para poder ejecutar la funcion que 
         * realiza la ordenacion y se envian como parametros los arreglos que se generaron al momento
         * de inicializar la variable del tipo Reading.
         */
         
        Metodos mt = new Metodos(dr.getA(), dr.getB(), dr.getC());
        
        
        switch(i){
            case 1:
                mt.Insercion();
                a = mt.getX();
                b = mt.getY();
                c = mt.getZ();
                break;
            case 2:
                mt.Burbuja();
                a = mt.getX();
                b = mt.getY();
                c = mt.getZ();
                break;
            case 3:
                mt.ShellSort();
                a = mt.getX();
                b = mt.getY();
                c = mt.getZ();
                break;
            case 4:
                MergeMagicSort mg = new MergeMagicSort(dr.getA(), dr.getB(), dr.getC(),1);
                a = mg.getX();
                b = mg.getY();
                c = mg.getZ();
                break;
            case 5:
                mt.Quicksort();
                a = mt.getX();
                b = mt.getY();
                c = mt.getZ();
                break;
            case 6:
                try {
                    MezclaDirecta md = new MezclaDirecta(dr.getA(), dr.getB(), dr.getC()
                            , "./database/" ,"archivoF.xls","archivoF1.xls","archivoF2.xls",1);
                    a = md.getA();
                    b = md.getB();
                    c = md.getC();
                } catch (IOException e) {

                } catch (WriteException e) {

                }

                break;
            case 7:
                break;
            case 8:
                mt.Insercion2();
                a = mt.getX();
                b = mt.getY();
                c = mt.getZ();
                break;
            case 9:
                mt.Burbuja2();
                a = mt.getX();
                b = mt.getY();
                c = mt.getZ();
                break;
            case 10:
                mt.ShellSort2();
                a = mt.getX();
                b = mt.getY();
                c = mt.getZ();
                break;
            case 11:
                MergeMagicSort mq = new MergeMagicSort(dr.getA(), dr.getB(), dr.getC(),2);
                a = mq.getX();
                b = mq.getY();
                c = mq.getZ();
                break;
            case 12:
                mt.Quicksort2();
                a = mt.getX();
                b = mt.getY();
                c = mt.getZ();
                break;
            case 13:
                try {
                    MezclaDirecta md = new MezclaDirecta(dr.getA(), dr.getB(), dr.getC()
                            , "./database/"
                            ,"archivoF.xls","archivoF1.xls","archivoF2.xls",2);
                    a = md.getA();
                    b = md.getB();
                    c = md.getC();
                } catch (IOException e) {

                } catch (WriteException e) {

                }

                break;
            case 14:
                break;
        }
        
        /* Los arreglos que se consiguen despues de la ejecucion del metodo de ordenamiento
         * se almacenan en unos nuevos para poder ser enviados a la vista.
         */



    }

    /**
     * Metodo que devuelve un dato tipo String Arreglo
     * @return
     */
    public String[] getA() {
        return a;
    }

    /**
     * Metodo que devuelve un dato tipo String Arreglo
     * @return
     */
    public String[] getB() {
        return b;
    }

    /**
     * Metodo que devuelve un dato tipo double Arreglo
     * @return
     */
    public double[] getC() {
        return c;
    }
    
    
    
}
