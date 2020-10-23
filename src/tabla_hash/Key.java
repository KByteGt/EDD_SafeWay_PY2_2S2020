/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabla_hash;

/**
 *
 * @author JOSED
 */
public class Key <T extends Comparable<T>, V> implements Comparable {

    private T key;
    private V valor;
    
    public Key(T key, V valor){
        this.key = key;
        this.valor = valor;
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
    
    //Get & Set

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
    
}
