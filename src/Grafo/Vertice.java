/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.ArrayList;

/**
 *
 * @author JOSED
 */
class Vertice {
    
    private ArrayList<Arista> vecinos;
    private String etiqueta;
    
    public Vertice (String etiqueta){
        this.etiqueta = etiqueta;
        this.vecinos = new ArrayList<Arista>();
    }
    
    //Insertar un vecino
    public void insertarVecino(Arista arista){
        if(!this.vecinos.contains(arista)){
            this.vecinos.add(arista);
        }
    }
    
    public boolean haveVecino(Arista arista){
        return this.vecinos.contains(arista);
    }
    
    public Arista getVecico(int index){
        return this.vecinos.get(index);
    }
    
    public int getNumVecinos(){
        return this.vecinos.size();
    }

    String getEtiqueta() {
        return this.etiqueta;
    }
    
    @Override
    public boolean equals(Object v){
        if( !(v instanceof Vertice))
            return false;
        
        Vertice vertice = (Vertice) v;
        return this.etiqueta.equals(vertice.getEtiqueta());
    }
    
    @Override
    public String toString(){
        return "Vertice: "+this.etiqueta;
    }
    
    @Override
    public int hashCode(){
        return this.etiqueta.hashCode();
    }
    
    public ArrayList<Arista> getVecinos(){
        return new ArrayList<Arista>(this.vecinos);
    }
}
