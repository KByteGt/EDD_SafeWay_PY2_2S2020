/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

/**
 *
 * @author JOSED
 */
class Arista {
    private Vertice puntoA, puntoB;;
    private int peso;
    private double costo;
    
    public Arista(Vertice puntoA, Vertice puntoB){
        this(puntoA,puntoB,1,1);
    }
    
    public Arista(Vertice puntoA, Vertice puntoB, int peso, double costo){
        if(puntoA.getEtiqueta().compareTo(puntoB.getEtiqueta()) <= 0){
            this.puntoA = puntoA;
            this.puntoB = puntoB;
        } else {
            this.puntoA = puntoB;
            this.puntoB = puntoA;
        }
        
        this.peso = peso;
        this.costo = costo;
        
    }
    
    public Vertice getVecino(Vertice punto){
        if(punto.equals(this.puntoA)){
            return this.puntoB;
        } else if(punto.equals(this.puntoB)){
            return this.puntoA;
        } else {
            return null;
        }
    }

    public Vertice getPuntoA() {
        return puntoA;
    }

    public void setPuntoA(Vertice puntoA) {
        this.puntoA = puntoA;
    }

    public Vertice getPuntoB() {
        return puntoB;
    }

    public void setPuntoB(Vertice puntoB) {
        this.puntoB = puntoB;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    
    public int compareTo(Arista arista){
        return this.peso - arista.peso;
    }
    
    public String toString(){
        return "({"+this.puntoA +", "+this.puntoB+"}, "+this.peso+" - Q"+this.costo+")";
    }
    
    public int hashCode(){
        return (puntoA.getEtiqueta() + puntoB.getEtiqueta()).hashCode();
    }
    
    public boolean equals(Arista objeto){
        if( !(objeto instanceof Arista)){
            return false;
        } else {
            Arista arista = (Arista) objeto;
            
            if(arista.puntoA.equals(this.puntoA) && arista.puntoB.equals(this.puntoB)){
                return true;
            } else {
                return false;
            }
        }
    }
}
