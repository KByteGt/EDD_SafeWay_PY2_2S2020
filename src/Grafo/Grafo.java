/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.HashMap;

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
    
   
}
