/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

/**
 *
 * @author JOSED
 * Solo para acceso dentro del paquete grafo
 */
class Adyacente {
    int destino;
    double peso;
    
    Adyacente(int index, double p){
        this.destino = index;
        this.peso = p;
    }
    
    public String toString(){
        return this.destino + "("+this.peso+")";
    }
}
