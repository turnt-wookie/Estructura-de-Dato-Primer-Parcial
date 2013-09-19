package Model;


import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 56185
 */
public class PilaArrayList implements Pila{
    private ArrayList pila;
    
    /**
     * Metodo que crea un arraylist
     */
    public PilaArrayList(){
        pila = new ArrayList();
    }

    /**
     * Metodo que regresa una pila
     * @return
     */
    @Override
    public int pop() {
        if(!isEmpty())
            return (Integer) pila.remove(pila.size()-1);
        else
            return 0;        
    }

    /**
     * Metodo que le agrega datos a la pila
     * @param pos
     */
    @Override
    public void push(int pos) {
        pila.add(pos);
    }

    /**
     * Metodo que limpia la pila
     * @return
     */
    @Override
    public boolean isEmpty() {
        return pila.isEmpty();
    }

    /**
     * Metodo que devuelve una pila
     * @return
     */
    @Override
    public int top() {
        
            return (Integer) pila.get(pila.size()-1);
    }

    /**
     * Metodo que devuelve el tamaño de la pila
     * @return
     */
    @Override
    public int size() {
        return pila.size();
    }
    
    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
            PilaArrayList pila = new PilaArrayList();
            for(int i=0;i<10;i++){
                pila.push(i);
            }
            
            for(int i=0;i<10;i++){
                System.out.println(pila.top());
            }
    }
}
