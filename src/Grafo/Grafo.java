/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author JOSED
 */
public class Grafo {
    
    private HashMap<String, Vertice> vertices;
    private HashMap<Integer, Arista> aristas;
    
    public Grafo(){
        this.vertices = new HashMap<String, Vertice>();
        this.aristas = new HashMap<Integer, Arista>();
    }
    
    public Grafo(ArrayList<Vertice> vertices){
         this.vertices = new HashMap<String, Vertice>();
         this.aristas = new HashMap<Integer, Arista>();

         for (Vertice v : vertices) {
            this.vertices.put(v.getEtiqueta(), v);
        }
    }

    public boolean insertarArista(String A, String B){
        return insertarArista(A,B, 1, 1);
    }
   
    public boolean insertarArista(String puntoA, String puntoB, int peso, double costo){
        if(puntoA.equals(puntoB))
           return false;
        
        //Insertar posibles nuevos veritices
        insertarVertice(new Vertice(puntoA));
        insertarVertice(new Vertice(puntoB));
           
        //Obtener vertices de la lista
        Vertice A = vertices.get(puntoA);
        Vertice B = vertices.get(puntoB);

        Arista arista = new Arista(A,B,peso,costo);
       
        if(aristas.containsKey(arista.hashCode())){
            //Ya existe la arista
            return false;
        } else if(A.haveVecino(arista) || B.haveVecino(arista)){
            //Ya existe la arista
            return false;
        }else {
           
            //Insertar arista
            aristas.put(arista.hashCode(), arista);
            A.insertarVecino(arista);
            B.insertarVecino(arista);

            return true;
        }
    }
   
    public boolean haveArista(Arista arista){
        if(arista.getPuntoA() == null || arista.getPuntoB() == null){
            return false;
        } else {
            return this.aristas.containsKey(arista.hashCode());
        }
    }

    public boolean haveVertice(Vertice vertice){
        return (this.vertices.get(vertice.getEtiqueta()) != null);
    }

    public Vertice getVertice(String etiqueta){
        return this.vertices.get(etiqueta);
    }

    public boolean insertarVertice(Vertice v){
        Vertice vertice = this.vertices.get(v.getEtiqueta());

        if(vertice == null){
             //Insertar el vertice
             vertices.put(v.getEtiqueta(), v);
             return true;

        } else {
            return false;
        }
    }

    public Set<String> verticeKeys(){
        return this.vertices.keySet();
    }

    public Set<Arista> getAristas(){
        return new HashSet<Arista>(this.aristas.values());
    }

    public int getVertices(){
        return vertices.size();
    }
}
