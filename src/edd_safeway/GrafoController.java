/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_safeway;

import Grafo.Arista;
import Grafo.Grafo;
import Grafo.Vertice;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author JOSED
 */
public class GrafoController extends Controller{
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
    
    HashMap<String, Double> t_distancias;
    HashMap<String, Double> t_costos;
    
    //Otros
    private static GrafoController controller;
    private Grafo grafo;
    
    private GrafoController(){
        grafo = new Grafo();
        t_distancias = null;
        t_costos = null;
    }
    
    public static GrafoController getInstance(){
        if(controller == null){
            controller = new GrafoController();
        }
        
        return controller;
    }
    
    public Arista getArista(String A, String B){
        Arista arista = new Arista(new Vertice(A), new Vertice(B));
        
        return grafo.getArista(arista);
    }
    
    public double getCosto(){
        return this.costo;
    }
    
    public double getPeso(){
        return this.peso;
    }
    
    public void insertarArista(String puntoA, String puntoB, double peso, double precio){
        
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
    
    //
    public int loadConections(File file){
        String gson = getJson(file);
        int i = -1;
        
        if(!gson.isEmpty()){
            i = 0;
            
            JsonElement element = json.fromJson(gson, JsonElement.class);
            JsonObject obj = element.getAsJsonObject();
            
            JsonArray places = obj.getAsJsonArray("Grafo");
            if(places != null){
                for(JsonElement places_obj : places){
                    JsonObject place = places_obj.getAsJsonObject();
                    
                    if(place != null){
                        String puntoA= place.get("inicio").getAsString();
                        String puntoB = place.get("final").getAsString();
                        double peso = place.get("peso").getAsDouble();
                        double precio = place.get("precio").getAsDouble();
                        
                        this.insertarArista(puntoA, puntoB, peso, precio);
                        i++;
                    }
                }
            }
        }

        return i;
    }
    
    //Algoritmo Dijkstra
    public ArrayList<String> getCamino(String puntoA, String puntoB){
     
        Vertice A = grafo.getVertice(puntoA);
        Vertice B = grafo.getVertice(puntoB);
        
        if(A != null && B != null){
            
            System.out.println(" - Calcular camino de: "+puntoA+" -> "+puntoB);

            return dijkstraA(puntoA,puntoB,grafo.getVertices());
        }else{
            return null;
        }
    }
    
    public boolean getPesoCosto(String inicio, String destino){
        
        if(dijkstra){
            //Está activo antes de su cálculo
            
            if(this.t_distancias.get(inicio) == 0){
                //La matriz ya está generada
                
                this.costo = this.t_costos.get(destino);
                this.peso = this.t_distancias.get(destino);
                
                return true;
            } 
        }
        
        //No se ha generado la matriz
        Vertice vertice = new Vertice(inicio);

        if(grafo.haveVertice(vertice) ){

            //Limpiar data
            this.dijkstra = false;
            //Obtener matriz Dijkstra
            dijkstraB(inicio, grafo.getVertices());

            if(dijkstra){
                //Poner los costos y valores disponibles
                this.costo = this.t_costos.get(destino);
                this.peso = this.t_distancias.get(destino);

                return true;
            }
        }
        return false;
    }
    
    private ArrayList<String> dijkstraA(String inicio, String fin, int N){
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

        System.out.println(" -> Comenzando algoritmo Dijkstra... obtener camino");
        
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

//                    visto.put(tempVertice, true);
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
        
//        System.out.println(" * Peso: " + peso+", Costo: " + costo);
        return camino;
    }
    
    private void dijkstraB(String inicio, int N){
        HashMap<String, Double> distancias = new HashMap<>();
        HashMap<String, Double> costos = new HashMap<>();
        HashMap<String, Boolean> visto = new HashMap<>();

        int vistos = 0;
        System.out.println(" -> Comenzando algoritmo Dijkstra... obtener precio y costo");
        
        //1- Inicializar distanicias[i] con un valor infinito relativo
        grafo.verticeKeys().stream().map((w) -> {
            Arista arista = this.getArista(inicio, w);
            //Si no existe la arista entre inicio y w
            if(arista != null){
                distancias.put(w, arista.getPeso());
                costos.put(w, arista.getCosto());
            } else {
                distancias.put(w, Double.MAX_VALUE); // 0 -1
                costos.put(w, 0.0);
            }
            return w;
        }).forEachOrdered((w) -> {
            visto.put(w, false);
        });

        distancias.put(inicio, 0.0);
        visto.put(inicio, true);
        vistos++;
        
//        pila.push(inicio);
//        camino.add(inicio);
        
        //Mintras no estén vistos todos los nodos
        while(vistos < N){
            //Tomar el mínimo del vector distancia y que no esté visto
            double minimo = Double.MAX_VALUE;
            String vertice = "";
            double distancia = 0.0;
            
            //Obtener el minimo
            for(String k : distancias.keySet()){
                if(!visto.get(k)){
                    //No está visto
                    distancia = distancias.get(k);
                    if(distancia < minimo){
                        minimo = distancia;
                        vertice = k;
                    }
                }
            }
            
            //El minimo se guarda en vertice
            visto.put(vertice, true);
            vistos++;
            
            //Tomar sucesores
            for(Arista w : grafo.getVertice(vertice).getAristas()){
                
                Arista arista = null;
                String temp = "";
                
                if(vertice.compareTo(w.getNameA()) == 0){
                    //Usar etiqueta B
                    temp = w.getNameB();
                    arista = this.getArista(vertice, w.getNameB());
                } else {
                    //Usar etiqueta A
                    temp = w.getNameA();
                    arista = this.getArista(vertice, w.getNameA());
                }
                
                double dt = distancias.get(vertice) + arista.getPeso();
                double total = costos.get(vertice) + arista.getCosto();
                
                if(distancias.get(temp) > dt){
                    distancias.put(temp, dt);
                    costos.put(temp, total);
                }
            }
        }
        
        System.out.println("Distancias[]");
        System.out.println(" [key][valor]");
        
        distancias.keySet().forEach((k) -> {
            System.out.println(" ["+k+"]["+distancias.get(k)+"] - Q"+costos.get(k));
        });

        this.dijkstra = true;
        this.t_costos = costos;
        this.t_distancias = distancias;
    }

}


