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
public class TablaHash {
    
    private Key[] tabla;
    private int m; //Tamaño de la tabla hash
    
    public TablaHash(int m){
        this.m = m;
        this.tabla = new Key[m];
    }
    
    private int hash(int k, int i){
        return (k % this.m + (i * i)) % this.m;
    }
    
    
}
