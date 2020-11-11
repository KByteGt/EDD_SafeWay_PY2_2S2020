/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_safeway;

import Grafo.Arista;
import Grafo.Grafo;
import Grafo.Vertice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author JOSED
 */
public class GrafoController {
    //Algoritmo Dijkstra
//    private final int MAX = 10005;
//    private final int INF = 1 << 30;
//    private int distancias[] = new int[MAX];
//    private PriorityQueue<Vertice> Q = new PriorityQueue<Vertice>();
//    private int V; //número de vertices
//    private int previo[] = new int [MAX];
//    private boolean ocupado;
    
    private boolean dijkstra = false;
    private double costo = 0.0;
    private double peso = 0.0;
    
    private ArrayList<String> caminoMasCorto;
    
    //Otros
    private static GrafoController controller;
    private Grafo grafo;
    
    private GrafoController(){
        grafo = new Grafo();
    }
    
    public static GrafoController getInstance(){
        if(controller == null){
            controller = new GrafoController();
        }
        
        return controller;
    }
    
    public double getCosto(){
        return this.costo;
    }
    
    public double getPeso(){
        return this.peso;
    }
    
    public void insertarArista(String puntoA, String puntoB, int peso, double precio){
        
        boolean flag = grafo.insertarArista(puntoA, puntoB, peso, precio);
        
        if(flag){
            System.out.println(" | - Insertando: ({"+puntoA +" -> "+puntoB+"}, "+peso+" - Q"+precio+")");
        }
    }
    
    void printAristas(String punto) {
        Vertice vertice = grafo.getVertice(punto);
        System.out.print(vertice);
        if(vertice != null){
            System.out.println(" Grado: " + vertice.getGrado());
            for (int i = 0; i < vertice.getGrado(); i++) {
                System.out.println(vertice.getArista(i));
            }
        }
        
    }
    
    //Algoritmo Dijkstra
    public ArrayList<String> getCamino(String puntoA, String puntoB){
//        ArrayList<String> lista = new ArrayList();
//        
        Vertice A = grafo.getVertice(puntoA);
        Vertice B = grafo.getVertice(puntoB);
        
        if(A != null && B != null){
            this.dijkstra = false;
            this.costo = 0.0;
            this.peso = 0.0;
            System.out.println(" - Calcular camino de: "+puntoA+" -> "+puntoB);
            return dijkstra(puntoA,puntoB,grafo.getVertices());
        }else{
            return null;
        }
    }
    
    private ArrayList<String> dijkstra(String inicio, String fin, int N){
        Stack<String> pila = new Stack<>();
        ArrayList<String> camino = new ArrayList();
        HashMap<String, Double> distancias = new HashMap<>();
        HashMap<String, Boolean> visto = new HashMap<>();
        String tempVertice = "";
        String menorVertice = "";
        double tempPeso = 0.0;
        
        double peso = 0.0;
        double costo = 0.0;
        
        boolean flag = true;
        
        
//        int[] distancias = new int[N];
//        int[] padre = new int[N];
//        boolean[] visto = new boolean[N];
        
        //1- Inicializar distanicias[i] con un valor infinito relativo
        
        for(String v : grafo.verticeKeys()){
            distancias.put(v, Double.MAX_VALUE);
            visto.put(v, false);
        }

        System.out.println(" -> Comenzando algoritmo Dijkstra...");
        
        distancias.put(inicio, 0.0);
        pila.push(inicio);
        camino.add(inicio);
        
        while (!pila.isEmpty() && flag){
            tempPeso = Double.MAX_VALUE;
            String vertice = pila.pop(); //Sacamos el vertice de la pila
            visto.put(vertice, true); //Lo marcamos como visitado
            
            System.out.println(" | vertice: " + vertice);
            
            //Parar iteración
            if(vertice.equals(fin)){
                flag = false;
                break;
            }
                    
            //Obtengo todos los caminos del vertice
            for(Arista vecino : grafo.getVertice(vertice).getAristas()){
                
                if(vertice.compareTo(vecino.getNameA()) == 0){
                    //Usar etiqueta B
                    tempVertice = vecino.getNameB();
                } else {
                    //Usar etiqueta A
                    tempVertice = vecino.getNameA();
                }
                
                System.out.println(" |- sub vertice: " + tempVertice);
                
                if( !visto.get(tempVertice)){
                    //Añadimos el Vertice a la pila
                    pila.add(tempVertice);

                    //Obtener la distancia  tentativa
                    double dt = distancias.get(vertice) + vecino.getPeso();
                    //Comparamos distancia tentativa
                    if(dt < distancias.get(tempVertice)){
                        distancias.put(tempVertice, dt);
                    }
                    
                    //Calcular el valor minimo
                    if(dt > 0 && dt < tempPeso){
                        menorVertice = tempVertice;
                        tempPeso = dt;
                        peso = dt;
                        costo += vecino.getCosto();
                    }

                    visto.put(tempVertice, true);
                }
            }
            
            //6 - Tomar el proximo punto actual el del menor valor en distancias[] 0 < n < max
            pila.push(menorVertice);
            camino.add(menorVertice);
        }
        
        //Sea PuntoInicial = x (Nodo actual
        
        //3- Recorrer todos los nodos adyacentes a PuntoInicial
        
        //4- Para el PuntoActual,calcular la distancia tentativa desde dicho punto
        //hasta sus vecinos, formula: dt(Vi) = Da + d(a,Vi)
        //La distancia tentatica dt(Vi) del punto Vi es
        //   La distancia que actualmente tiene el punto en el vector de distancias
        //   + la distancia del nodo actual hasta el punto Vi.
        //   Si la distancia tentatica es menor que la distancia almacenada en el vector
        //   entonces se actualiza el vector con la distancia tentativa
        
        //5- Se marca como visitado el punto a
        
        //6- Se toma como proximo punto acutla el de menor valor en el vector de distancias
        //Se almacena el valor en una cola de prioridad y se regresa al paso 3
        
        System.out.println(" | > Peso: " + peso);
        System.out.println(" | > Costo: " + costo);
        this.peso = peso;
        this.costo = costo;
        this.dijkstra = true;
        return camino;
    }
}


