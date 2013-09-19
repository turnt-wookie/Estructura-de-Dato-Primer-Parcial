/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import jxl.*;

/**
 *
 * @author Nacho
 */
public class Metodos {
    
    
    /**
     * Declaracion de arreglo de tipo celda.
     */
    public static String x[];
    /**
     * Declaracion de arreglo de tipo celda.
     */
    public static String y[];
    /**
     * Declaracion de arreglo de tipo celda.
     */
    public static double z[];
    
    
    /**
     * Constructor de la calse Metodos que recibe 3 arreglos de tipo Celda y los iguala a otros 3 arreglos.
     * 
     * @param a
     * @param b
     * @param c
     * 
     */
    public Metodos(Cell[] a, Cell[] b, Cell[] c){
        x = new String[a.length];
        y = new String[b.length];
        z = new double[c.length];
        
        for(int i=1;i<z.length;i++){
            x[i-1] = a[i].getContents();
            y[i-1] = b[i].getContents();
            /*
         * Se rellena el arreglo de tipo String creado con los contenidos de las celdas. La API de Excel
         * transforma los puntos decimales en comas, es por eso que si se encuentra una coma dentro del
         * arreglo se reemplaza por un punto y asi no cause problemas al momento de la transformacion a Double.
         */
            z[i-1] = Double.parseDouble(c[i].getContents().replace(",", "."));
        }
    }
    
    
    /**
     *
     * Metodo que utiliza el metodo de ordenamiento de Insercion segun los valores que se guardan en los 3 arreglos.
     * 
     */
    public void Insercion(){
        
        double aux;
        String aux2, aux3;
        int i, k;
        
        for(i=2;i<z.length;i++){
            aux = z[i];
            aux2= x[i];
            aux3= y[i];
            k=i-1;
            
            //Se convierte el archivo String a Double para poder hacer la comparacion.
            while(k>=0 && aux<z[k]){
                
                /*ya que las demas arreglos dependen del arreglo que es comparado, 
                *se necesitan hacer los mismo cambios para que los datos sean los adecuados
                */
                z[k+1] = z[k];
                y[k+1] = y[k];
                x[k+1] = x[k];
                k--;
            }
            z[k+1] = aux;
            x[k+1] = aux2;
            y[k+1] = aux3;
        }
        
        
    }
    
    /**
     * Metodo que utiliza el metodo de ordenamiento de Insercion segun los valores que se guardan en los 3 arreglos en descendente
     */
    public void Insercion2(){
        
        double aux;
        String aux2, aux3;
        int i, k;
        
        for(i=2;i<z.length;i++){
            aux = z[i];
            aux2= x[i];
            aux3= y[i];
            k=i-1;
            
            //Se convierte el archivo String a Double para poder hacer la comparacion.
            while(k>=0 && aux>z[k]){
                
                /*ya que las demas arreglos dependen del arreglo que es comparado, 
                *se necesitan hacer los mismo cambios para que los datos sean los adecuados
                */
                z[k+1] = z[k];
                y[k+1] = y[k];
                x[k+1] = x[k];
                k--;
            }
            z[k+1] = aux;
            x[k+1] = aux2;
            y[k+1] = aux3;
        }
        
        
    }
    
    /**
     * Metodo que utiliza el metodo de ordenamiento de Burbuja segun los valores que se guardan en los 3 arreglos.
     */
    public void Burbuja(){
        int i, j;
        double aux;
        String aux2, aux3;
        
        for(i=2;i<z.length;i++){
            for (j=z.length-1;j>=i;j--){
                if(z[j-1]>z[j]){
                    aux = z[j-1];
                    aux2= x[j-1];
                    aux3= y[j-1];
                   
                    z[j-1] = z[j];
                    x[j-1] = x[j];
                    y[j-1] = y[j];
                    
                    z[j] = aux;
                    x[j] = aux2;
                    y[j] = aux3;
                }
            }
        }
    }
    
    /**
     * Metodo que utiliza el metodo de ordenamiento de Burbuja segun los valores que se guardan en los 3 arreglo en descendente
     */
    public void Burbuja2(){
        int i, j; 
        double aux;
        String aux2, aux3;
        
        for(i=1; i < z.length; i++){
           for(j=2; j < (z.length-i); j++){
              if(z[j-1] < z[j]){
                                            //swap the elements!
                aux = z[j-1];
                aux2 = x[j-1];
                aux3 = y[j-1];
                
                z[j-1] = z[j];
                x[j-1] = x[j];
                y[j-1] = y[j];
                
                z[j] = aux;
                x[j] = aux2;
                y[j] = aux3;
                
               }

             }
         }
    }
    
    /**
     * Metodo que utiliza el metodo de ordenamiento de Shellsort segun los valores que se guardan en los 3 arreglos
     */
    public void ShellSort(){
        int i, INT;
        double aux;
        String aux2, aux3;
        boolean band;
        INT = z.length +1;
        while(INT > 1){
            INT /=2;
            band = true;
            
            while(band==true){
                band = false;
                i=1;
                while ((i+INT)<z.length){
                    if (z[i]>z[i+INT]){
                       
                        aux = z[i];
                        aux2 = x[i];
                        aux3 = y[i];
                        
                        z[i] = z[i+INT];
                        x[i] = x[i+INT];
                        y[i] = y[i+INT];
                        
                        z[i+INT]= aux;
                        x[i+INT]= aux2;
                        y[i+INT]= aux3;
                        
                        band = true;
                    }               
                    i++;
                }
            }
        }
    }
    
    /**
     * Metodo que utiliza el metodo de ordenamiento de Shellsort segun los valores que se guardan en los 3 arreglos en descendente
     */
    public void ShellSort2(){
        int i, INT;
        double aux;
        String aux2, aux3;
        boolean band;
        INT = z.length +1;
        while(INT > 1){
            INT /=2;
            band = true;
            
            while(band==true){
                band = false;
                i=1;
                while ((i+INT)<z.length){
                    if (z[i]<z[i+INT]){
                       
                        aux = z[i];
                        aux2 = x[i];
                        aux3 = y[i];
                        
                        z[i] = z[i+INT];
                        x[i] = x[i+INT];
                        y[i] = y[i+INT];
                        
                        z[i+INT]= aux;
                        x[i+INT]= aux2;
                        y[i+INT]= aux3;
                        
                        band = true;
                    }               
                    i++;
                }
            }
        }
    }
   
    /**
     * Metodo que inicia el ordenamiento quicksort 
     */
    
    public void Quicksort(){
     int ini, fin, pos;  
     PilaArrayList pilaMenor = new PilaArrayList();
     PilaArrayList pilaMayor = new PilaArrayList();
     pilaMenor.push(0);
     pilaMayor.push(z.length-1);
     while (!pilaMayor.isEmpty()){
         ini = pilaMenor.pop();
         fin = pilaMayor.pop();
         pos = posicionaQuicksort(ini,fin);
         if (ini<pos-1){
             pilaMenor.push(ini);
             pilaMayor.push(pos-1);
             
         }
         if(fin>pos+1){
             pilaMenor.push(pos+1);
             pilaMayor.push(fin);
         }
     }
     
    }
    /**
     * Metodo que recibe dos datos tipo entero y que realiza el ordenamiento
     * @param ini Punta inciial del arreglo
     * @param fin Punta final del arreglo
     * @return
     */
    public int posicionaQuicksort(int ini, int fin){
        int izq, der, pos;
        double AUX;
        String AUX2,AUX3;
        boolean BAND;
        izq = ini;
        der = fin;
        pos = ini;
        BAND = true;
        while (BAND == true){
            while ((z[pos]<=z[der])&& (pos!=der)){
                der = der-1;
            }
            if (pos == der){
                BAND = false;
            }
            else{
                AUX = z[pos];
                AUX2= x[pos];
                AUX3= y[pos];
                
                z[pos] = z[der];
                x[pos] = x[der];
                y[pos] = y[der];
                
                z[der] = AUX;
                x[der] = AUX2;
                y[der] = AUX3;
                
                pos = der;
                while ((z[pos]>=z[izq])&& (pos!=izq)){
                    izq = izq+1;
                }
                if (pos == izq){
                    BAND = false;
                }
                else{
                    AUX = z[pos];
                    AUX2= x[pos];
                    AUX3= y[pos];
                    
                    z[pos] = z[izq];
                    x[pos] = x[izq];
                    y[pos] = y[izq];
                    
                    z[izq] = AUX;
                    x[izq] = AUX2;
                    y[izq] = AUX3;
                    pos = izq;
                }
            }
        }
        return pos;
    }
   /**
    * Metodo que inicia el quicksort pero desendente
    */ 
    public void Quicksort2(){
     int ini, fin, pos;  
     PilaArrayList pilaMenor = new PilaArrayList();
     PilaArrayList pilaMayor = new PilaArrayList();
     pilaMenor.push(0);
     pilaMayor.push(z.length-1);
     while (!pilaMayor.isEmpty()){
         ini = pilaMenor.pop();
         fin = pilaMayor.pop();
         pos = posicionaQuicksort2(ini,fin);
         if (ini<pos-1){
             pilaMenor.push(ini);
             pilaMayor.push(pos-1);
             
         }
         if(fin>pos+1){
             pilaMenor.push(pos+1);
             pilaMayor.push(fin);
         }
     }
     
    }
    /**
     *
     * Metodo que recibe dos datos tipo entero y que realiza el ordenamiento en descendente
     * @param ini Punta inciial del arreglo
     * @param fin Punta final del arreglo
     * @return
     */
    public int posicionaQuicksort2(int ini, int fin){
        int izq, der, pos;
        double AUX;
        String AUX2,AUX3;
        boolean BAND;
        izq = ini;
        der = fin;
        pos = ini;
        BAND = true;
        while (BAND == true){
            while ((z[der]<=z[pos])&& (der!=pos)){
                der = der-1;
            }
            if (pos == der){
                BAND = false;
            }
            else{
                AUX = z[pos];
                AUX2= x[pos];
                AUX3= y[pos];
                
                z[pos] = z[der];
                x[pos] = x[der];
                y[pos] = y[der];
                
                z[der] = AUX;
                x[der] = AUX2;
                y[der] = AUX3;
                
                pos = der;
                while ((z[izq]>=z[pos])&& (izq!=pos)){
                    izq = izq+1;
                }
                if (pos == izq){
                    BAND = false;
                }
                else{
                    AUX = z[pos];
                    AUX2= x[pos];
                    AUX3= y[pos];
                    
                    z[pos] = z[izq];
                    x[pos] = x[izq];
                    y[pos] = y[izq];
                    
                    z[izq] = AUX;
                    x[izq] = AUX2;
                    y[izq] = AUX3;
                    pos = izq;
                }
            }
        }
        return pos;
    }

    
    
    /**
     * Metodo que devuelve un arreglo de tipo celda.
     * @return
     */
    public String[] getX() {
        return x;
    }

    /**
     * Metodo que devuelve un arreglo de tipo celda.
     * @return
     */
    public String[] getY() {
        return y;
    }

    /**
     * Metodo que devuelve un arreglo de tipo celda.
     * @return
     */
    public double[] getZ() {
        return z;
    }
    
    
    
    
    
}
