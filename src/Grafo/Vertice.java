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
public class Vertice {
    
    private ArrayList<Arista> aristas;
    private String etiqueta;
    
    public Vertice (String etiqueta){
        this.etiqueta = etiqueta;
        this.aristas = new ArrayList<Arista>();
    }
    
    //Insertar un vecino
    public void insertarVecino(Arista arista){
        if(!this.aristas.contains(arista)){
            this.aristas.add(arista);
        }
    }
    
    public boolean haveVecino(Arista arista){
        return this.aristas.contains(arista);
    }
    
    public Arista getArista(int index){
        return this.aristas.get(index);
    }
    
    public int getGrado(){
        return this.aristas.size();
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
    
    public ArrayList<Arista> getAristas(){
        return new ArrayList<Arista>(this.aristas);
    }
}
