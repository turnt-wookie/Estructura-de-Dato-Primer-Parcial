package Model;

import jxl.Cell;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Juan Pablo
 * Date: 18/09/13
 * Time: 08:05 PM
 */
public class MergeMagicSort {

    /**
     * Declaracion de arreglo de tipo celda.
     */
    private String x[];
    /**
     * Declaracion de arreglo de tipo celda.
     */
    private String y[];
    /**
     * Declaracion de arreglo de tipo celda.
     */
    private double z[];

    /**
     * Metodo que recibe 3 datos tipo celda y uno entero, es el constructor de la clase
     * @param a
     * @param b
     * @param c
     * @param opcion
     */
    public MergeMagicSort(Cell[] a, Cell[] b, Cell[] c, int opcion) {
        ArrayList<String[]> W = new ArrayList();
        String aux[];
        for (int i = 1; i < a.length; i++) {
            aux = new String[3];
            aux[0] = a[i].getContents();
            W.add(i - 1, aux);

        }
        for (int i = 1; i < b.length; i++) {
            aux = W.get(i - 1);
            aux[1] = b[i].getContents();
            W.set(i - 1, aux);

        }
        for (int i = 1; i < c.length; i++) {
            aux = W.get(i - 1);
            aux[2] = c[i].getContents();
            W.set(i - 1, aux);

        }
        ArrayList <String[]> Q;

        switch (opcion){
            case 1:
                Q = ordenaMerge(W);
                break;
            case 2:
                Q = ordenaMerge1(W);
                break;
            default:
                Q = ordenaMerge(W);
                break;
        }
        x = new String[Q.size()];
        y = new String[Q.size()];
        z = new double[Q.size()];
        for (int i = 0; i < Q.size(); i++) {
            x[i] = Q.get(i)[0];
        }
        for (int i = 0; i < Q.size(); i++) {
            y[i] = Q.get(i)[1];
        }
        for (int i = 0; i < Q.size(); i++) {
            z[i] =  Double.parseDouble(Q.get(i)[2].replace(",", "."));
        }

    }



    /**
     * Metodo que recibe un dato de tipo ArrayList de tipo String Arreglo, inicia el ordenamiento
     * @param L
     * @return
     */
    public ArrayList<String[]> ordenaMerge(ArrayList<String[]> L) {
        int n = L.size(), i, m;
        ArrayList<String[]> L1 = new ArrayList(),
                L2 = new ArrayList();
        if (n > 1) {
            m = n / 2;
            for (i = 0; i < m; i++) {
                L1.add(L.get(i));
            }
            for (i = m; i < n; i++) {
                L2.add(L.get(i));
            }
            L = merge(ordenaMerge(L1), ordenaMerge(L2));
        }
        return L;
    }
    /**
     * Metodo que recibe 2 datos tipo ArrayList tipo String Arreglo, realiza el ordenamiento de los arreglos
     * @param L1
     * @param L2
     * @return
     */
    public ArrayList<String[]> merge(ArrayList<String[]> L1,ArrayList<String[]> L2){

        ArrayList lista=new ArrayList();
        while(!L1.isEmpty() && !L2.isEmpty()){
            if (Double.parseDouble(L1.get(0)[2].replace(",", ".")) < Double.parseDouble(L2.get(0)[2].replace(",", "."))){
                lista.add(L1.get(0));
                L1.remove(0);
                if(L1.isEmpty()){
                    lista.addAll(L2);
                    L2.clear();
                }
            }else{
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
    /*
     * Descendente
     */
    /**
     * Metodo que recibe un dato de tipo ArrayList de tipo String Arreglo, inicia el ordenamiento
     * @param L
     * @return
     */
    public ArrayList<String[]> ordenaMerge1(ArrayList<String[]> L) {
        int n = L.size(), i, m;
        ArrayList<String[]> L1 = new ArrayList(),
                L2 = new ArrayList();
        if (n > 1) {
            m = n / 2;
            for (i = 0; i < m; i++) {
                L1.add(L.get(i));
            }
            for (i = m; i < n; i++) {
                L2.add(L.get(i));
            }
            L = merge1(ordenaMerge1(L1), ordenaMerge1(L2));
        }
        return L;
    }
    /**
     * Metodo que recibe 2 datos tipo ArrayList tipo String Arreglo, realiza el ordenamiento de los arreglos, pero de manera descendente
     * @param L1
     * @param L2
     * @return
     */
    public ArrayList<String[]> merge1(ArrayList<String[]> L1,ArrayList<String[]> L2){

        ArrayList lista=new ArrayList();
        while(!L1.isEmpty() && !L2.isEmpty()){
            if (Double.parseDouble(L1.get(0)[2].replace(",", ".")) > Double.parseDouble(L2.get(0)[2].replace(",", "."))){
                lista.add(L1.get(0));
                L1.remove(0);
                if(L1.isEmpty()){
                    lista.addAll(L2);
                    L2.clear();
                }
            }else{
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

    /**
     * Metodo que devuelve un dato tipo double arreglo
     * @return
     */
    public double[] getZ() {
        return z;
    }

    /**
     * Metodo que devuelve un dato tipo String Arreglo
     * @return
     */
    public String[] getY() {
        return y;
    }

    /**
     * Metodo que devuelve un dato tipo String Arreglo
     * @return
     */
    public String[] getX() {
        return x;
    }

    /**
     *
     * @param args
     */
    /*public static void main(String[] args) {
        Reading dr = new Reading(1, "C:\\Users\\Juan Pablo\\Downloads\\BaseDatosMexico.xls");
        MergeMagicSort mq = new MergeMagicSort(dr.getA(), dr.getB(), dr.getC(),1);
        for(int i=0; i<mq.getZ().length; i++){
            System.out.println(mq.getZ()[i]);

        }
    }*/

}