/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabla_hash;

import edd_safeway.Lugar;

/**
 *
 * @author JOSED
 */
public class TablaHash {
    
    private Key[] tabla;
    private int size; //Tamaño de la tabla hash
    private int carga; //Tamaño de elementos en la tabla
    
    public TablaHash(int m){
        this.size = m;
        this.carga = 0;
        this.tabla = new Key[m];
        
        llenarTabla();
    }
    
    private void llenarTabla(){
        for (int i = 0; i < size; i++) {
            tabla[i] = null;
        }
    }
    
    private int getPosicion(int k){
        //Método de División H(n) = n % M
        int iteracion = 0, hash = 0;
        
        hash = (int) k % size;
        
        //Ver colisión
        while(tabla[hash] != null && tabla[hash].getHash() != k){
            iteracion++;
            hash = (int) (hash + (iteracion * iteracion)) % size;
        }
       
        return hash;
    }
    
    private boolean needResize(){
        //Se analiza el factor de carga menor al 75%
        return (carga * 100 / size) > 75;
    }
    
    private int getASCII(String g){
        int ascii = 0;
        
        for (int i = 0; i < g.length(); i++) {
            ascii += (int) g.charAt(i);
        }
        
        return ascii;
    }
    
    public void insertar(Lugar l){
        Key node = new Key(getASCII(l.getNombre()), l);
        
        int pos = getPosicion(node.getHash());
        
        if(this.tabla[pos] == null){
            System.out.println("Insertando: "+pos);
            tabla[pos] = node;
            carga++;
            
             //Factor de carga
            if(needResize()){
                double newSize = size * 2.7;
                //Validar si es primo
                newSize = ((newSize % 2) == 0 )? newSize +1 : newSize;
                //Redimensionar tabla
                resize((int) newSize);
            }
            
        } else {
            System.out.println(" >> Ya existe el elemento a insertar: "+pos);
        }
    }
    
    private void resize(int m){
        Key newTabla[] = new Key[m];
        Key tempTabla[] = this.tabla;
        this.tabla = newTabla;
        this.size = m;
        
        llenarTabla();

        int i;
        for(Key n : tempTabla){
            if(n != null){
                i = getPosicion(n.getHash());
                newTabla[i] = n;
            }
        }
        
        
    }
    
    public void buscar(String nombre){
        
        
        int pos = getPosicion(getASCII(nombre));
        
        if(this.tabla[pos] != null){
            //Tenemos el dato buscado
            Lugar n = (Lugar) tabla[pos].getValor();
            System.out.println("["+ pos +"] - ("+tabla[pos].getHash()+") "+n.getNombre());
        } else {
            System.out.println(" >> No existe el elemento: "+pos);
        }
    }
    
    public void imprimir(){
        for (Key n : this.tabla) {
            if(n != null){
                Lugar l = (Lugar) n.getValor();
                System.out.println("("+n.getHash()+") "+l.getNombre());
            }
        }
    }
    
}
