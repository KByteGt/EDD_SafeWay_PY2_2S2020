/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol_b;

/**
 * Key de una página del árbol b
 * @author JOSED
 * @param <T>
 * @param <V>
 */

/*
|=======================|
|         Key (n)       |
|=======================|
| P-izq | valor | P-der |
|=======================|

*/
public class Key <T extends Comparable<T>, V> implements Comparable {

    private T key;              //Llave o identificador
    private V valor;            //Valor a almacenar
    private Page derecha;       //Puntero a página
    private Page izquierda;     //Puntero a página
    
    public Key(T key, V valor){
        this.key = key;
        this.valor = valor;
        this.derecha = null;
        this.izquierda = null;
    }
    
    @Override
    public int compareTo(Object o) {
        T temp = (T) o;
        
        if (this.key.compareTo(temp) < 0) {
            //Menor
            return -1;
        } else if(this.key.compareTo(temp) > 0){ 
            //Mayor
            return 1; 
        }else {
            //Igual
            return 0;
        }
    }

    //Gete & Set
    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }

    public Page getDerecha() {
        return derecha;
    }

    public void setDerecha(Page derecha) {
        this.derecha = derecha;
    }

    public Page getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Page izquierda) {
        this.izquierda = izquierda;
    }
}
