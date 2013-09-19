/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Interfaz que sirve como almacenamiento del metodo quicksort
 * @author 56185
 */
public interface Pila {
    
    /**
     * 
     * @return
     */
    public int pop();
    /**
     *
     * @param pos
     */
    public void push(int pos);
    /**
     *
     * @return
     */
    public boolean isEmpty();
    /**
     *
     * @return
     */
    public int top();
    /**
     *
     * @return
     */
    public int size();
}

