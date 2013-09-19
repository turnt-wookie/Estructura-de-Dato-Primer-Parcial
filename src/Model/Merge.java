/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import jxl.Cell;

/**
 *
 * @author Nacho
 */
public class Merge {
    
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
     *
     * @param a
     * @param b
     * @param c
     */
    public Merge(Cell[] a, Cell[] b, Cell[] c){
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
     */
    public void MergeSort(){
        ArrayList list = new ArrayList();
        ArrayList list2 = new ArrayList();
        
        for(int i=0;i<z.length;i++){
            list.add(z[i]);
        }
        
        list2 = ordenaMerge(list);
        
        for(int i=0;i<list.size();i++){
            z[i]=(Double)list2.get(i);
        }
    }

    /**
     * Metodo que devuelve un dato tipo String Arreglo
     * @return
     */
    public static String[] getX() {
        return x;
    }

    /**
     * Metodo que devuelve un dato tipo String Arreglo
     * @return
     */
    public static String[] getY() {
        return y;
    }

    /**
     * Metodo que devuelve un dato tipo double Arreglo
     * @return
     */
    public static double[] getZ() {
        return z;
    }
    
    
    
    /**
     * 
     * @param L
     * @return
     */
    public ArrayList ordenaMerge(ArrayList L){
        int n = L.size(), i, m;
        ArrayList L1 = new ArrayList();
        ArrayList L2 = new ArrayList();
        
        if(n>1){
            m = n/2;
            for (i=0;i<m;i++) L1.add(L.get(i));
            for (i=m;i<n;i++) L2.add(L.get(i));
            
            L=merge(ordenaMerge(L1), ordenaMerge(L2));
        }
        return L;
    }
    
    /**
     *
     * @param L1
     * @param L2
     * @return
     */
    public ArrayList merge(ArrayList L1, ArrayList L2){
        ArrayList lista = new ArrayList();
       
        while(!L1.isEmpty() && !L2.isEmpty()){
            
            if((Integer)L1.get(0)<(Integer)L2.get(0)){
                lista.add(L1.get(0));
                L1.remove(0);
                if(L1.isEmpty()){
                    lista.addAll(L2);
                    L2.clear();
                }
            }
            else{
                lista.add(L2.get(0));
                L2.remove(0);
                if(L2.isEmpty()){
                    lista.addAll(L1);
                    L1.clear();
                }
            }
        
        }
        return lista;
    }
    
}
