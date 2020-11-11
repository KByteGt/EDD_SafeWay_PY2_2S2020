/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_safeway;
import Grafo.Grafo;
import Grafo.Vertice;
import arbol_b.Key;
import arbol_b.Tree;
import block_chain.Cryptography;
import block_chain.Log;
import block_chain.Log.Accion;
import block_chain.Log.Tipo;
import block_chain.Logger;
import block_chain.Nodo;
import block_chain.Zip;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.StaticMapsRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Size;
import java.awt.BorderLayout;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tabla_hash.TablaHash;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
/**
 *
 * @author JOSED
 */
public class EDD_SafeWay {
 
    public static UI.LogIn logIn = new UI.LogIn();
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ApiException, InterruptedException, IOException {
        // TODO code application logic here
//        Cryptography cryp = Cryptography.getInstance();
        
        logIn.setVisible(true);
        
        
//        grafo();
//        hashTable();
//        b_tree();
//
//        MapViewOptions options = new MapViewOptions();
//        options.importPlaces();
//        options.setApiKey("AIzaSyCOgY_KsV_Bv1yOhYOdioVgLYGz-kfv654");
//        
//        GoogleMap map = new GoogleMap(options);
//        
//        JFrame frame = new JFrame(" Google Maps - Hello, World!");
//        
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.add(map, BorderLayout.CENTER);
//        frame.setSize(700,500);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);

//        GeoApiContext context = new GeoApiContext.Builder()
//            .apiKey("AIzaSyCOgY_KsV_Bv1yOhYOdioVgLYGz-kfv654")
//            .build();
//        GeocodingResult[] results =  GeocodingApi.geocode(context,
//            "1600 Amphitheatre Parkway Mountain View, CA 94043").await();
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        System.out.println(gson.toJson(results[0].addressComponents));
    }
    
    public static void jsonNodo() throws UnsupportedEncodingException, DataFormatException{
       
        
        Logger logger = Logger.getInstance();
        Gson json = new Gson();
        Zip zip = Zip.getInstance();
        
        Lugar lugar = new Lugar(1,"USAC","T-3",14.5877,-90.5536);
        Log log = new Log();
        log.setAccion(Accion.INSERTAR);
        log.setTipo(Tipo.LUGAR);
        log.setObjeto(lugar);
        
        Lugar lugar2 = new Lugar(2,"Plaza","Plaza Villa nueva",14.533,-90.5852);
        Log log2 = new Log();
        log2.setAccion(Accion.INSERTAR);
        log2.setTipo(Tipo.LUGAR);
        log2.setObjeto(lugar2);

        
        Nodo nodeBC = new Nodo();
        nodeBC.setId(1);
        nodeBC.setTimestamp(logger.getTimeStamp());
        nodeBC.setData(log);
        nodeBC.setData(log2);
        nodeBC.setNonce(0);
        nodeBC.setPreviousHash("0");
        nodeBC.setHash("0000da8618afd5fa8293ec73ad2ef326cb12f5ae50ecedc89d06d05a0a9f7f12");
        
        String respuesta = json.toJson(nodeBC);
        System.out.println(respuesta);
        
        byte[] cripting = zip.zip(respuesta);
        System.out.println(cripting);
        System.out.println(zip.unzip(cripting));
    }
    
    public static void hashNode(){
        ///////////////////////////////////////////////
        Cryptography cryp = Cryptography.getInstance();
        // Hash BlockChain
        String node = "";
        int nonce = 0, n = 4;
        
        String idnode = cryp.toHexadecimal("2");
        String fechanode = cryp.toHexadecimal("29/10/2020");
        String prevhashnode = cryp.toHexadecimal("0000da8618afd5fa8293ec73ad2ef326cb12f5ae50ecedc89d06d05a0a9f7f12");
        String datanode = cryp.toHexadecimal("{\"id\":1,\"Categoria\":\"USAC\",\"Nombre\":\"T-3\",\"Lat\":14.5877,\"Lon\":-90.5536}");
        String tempnonce = "";
//        String idnode = "1";
//        String fechanode = "29/10/2020";
//        String prevhashnode = "0";
//        String datanode = "{\"id\":1,\"Categoria\":\"USAC\",\"Nombre\":\"T-3\",\"Lat\":14.5877,\"Lon\":-90.5536}";
//        String tempnonce = "";
        
        System.out.println("Hash node ");
        boolean flagHashValid = true, flagHash = true;
        char[] hashNode;
        
        while(flagHash){
            flagHashValid = true;
            tempnonce = Integer.toHexString(nonce);
            node = idnode + fechanode + prevhashnode + datanode + tempnonce;
            hashNode = cryp.sha256(node).toCharArray();
            
            for (int i = 0; i < n; i++) {
                if(hashNode[i] != '0'){
                    flagHashValid = false;
                    break;
                } 
            }
            
            if (flagHashValid) {
                System.out.println("Hash Válido, nonce: " + nonce);
                flagHash = false;
                System.out.println(hashNode);
            } else {
                //System.out.println(" nonce: " + nonce); 
                nonce += 1;
            }
        }
        
        // nonce: 15597, incremento en 3
        // 82533 - 3, 38072 - 1
//        char[] hashNode = "00004587bfr48".toCharArray();

        
        ///////////////////////////////////////////////////
    }
    
    public static void b_tree(){
        //Árbol B
//        System.out.println(" Prueba árbol B");
//        Cryptography cryp = Cryptography.getInstance();
//        
//        Usuario user1 = new Usuario();
//        user1.setNombre("Daniel");
//        user1.setUsuario("KByteGt");
//        user1.setId(1);
//        user1.setCorreo("josedan1996@hotmail.com");
//        user1.setContraseña(cryp.sha256("123456"));
//        
//        Tree bt= new Tree(5);
//        
//        bt.insertar(user1.getUsuario(), user1);
//        bt.recorrer();

        Tree<Integer, Integer> arbol = new Tree(5);
        
        
        arbol.insertar(10, 10, "Nodo");
        arbol.insertar(20, 20, "Nodo");
        arbol.insertar(5, 5, "Nodo");
        arbol.insertar(6, 6, "Nodo");
        arbol.insertar(12, 12, "Nodo");
        arbol.insertar(30, 30, "Nodo");
        arbol.insertar(7, 7, "Nodo");
        arbol.insertar(17, 17, "Nodo");
        arbol.insertar(25, 25, "Nodo");
        arbol.insertar(11, 11, "Nodo");
        
        arbol.insertar(1, 1, "Nodo");
        arbol.insertar(2, 2, "Nodo");
        arbol.insertar(3, 3, "Nodo");
        arbol.insertar(40, 40, "Nodo");
        arbol.insertar(31, 31, "Nodo");
        arbol.insertar(13, 13, "Nodo");
        
        arbol.insertar(8, 8, "Nodo");
        arbol.insertar(14, 14, "Nodo");
        arbol.insertar(4, 4, "Nodo");
        
        arbol.recorrer();
        
        System.out.println(" ---");
        
//        System.out.println("Eliminar id 14");
        ArrayList<Key> lista = arbol.eliminar(14);
        
        if(!lista.isEmpty()){
            Tree<Integer,Integer> newTree = new Tree(5);
            
            for(Key k : lista){
                newTree.insertar((Integer) k.getKey() , (Integer) k.getValor(), k.getNombre());
            }
            
            arbol = newTree;
            //newTree.recorrer();
        }
        
//        System.out.println(arbol.getGraphviz("Prueba"));
 arbol.recorrer();
        
    }
    
    public static void hashTable(){
        System.out.println(" |> Tabla Hash");
        
        Lugar l1 = new Lugar(1,"USAC","T-3",14.5877,-90.5536);
        Lugar l2 = new Lugar(2,"Plaza","Plaza Villa nueva",14.533,-90.5852);
        Lugar l3 = new Lugar(3,"Gasolinera","Gasolinera puma quetzal",14.536,-90.5868);
        Lugar l4 = new Lugar(4,"USAC","T6",0,0);
        Lugar l5 = new Lugar(5,"USAC","T7",0,0);
        Lugar l6 = new Lugar(6,"USAC","S12",0,0);
        Lugar l7 = new Lugar(7,"USAC","S11",0,0);
        Lugar l8 = new Lugar(8,"USAC","M5",0,0);
        Lugar l9 = new Lugar(9,"USAC","T1",0,0);
        Lugar l10 = new Lugar(10,"USAC","T2",0,0);
        Lugar l11 = new Lugar(11,"USAC","M6",0,0);
        
        TablaHash hashTable = new TablaHash(11);
        
        hashTable.insertar(l1.getNombre(), l1);
        hashTable.insertar(l2.getNombre(),l2);
        hashTable.insertar(l3.getNombre(),l3);
        hashTable.insertar(l4.getNombre(),l4);
        hashTable.insertar(l5.getNombre(),l5);
        
        
        
        hashTable.insertar(l6.getNombre(),l6);
        hashTable.insertar(l7.getNombre(),l7);
        hashTable.insertar(l8.getNombre(),l8);
        hashTable.insertar(l9.getNombre(),l9);
        hashTable.insertar(l10.getNombre(),l10);
        hashTable.insertar(l11.getNombre(),l11);
        hashTable.insertar(l1.getNombre(),l1);
        
        hashTable.eliminar("T6");
        hashTable.eliminar("M5");
        hashTable.imprimir();
        
        System.out.println(hashTable.getGraphviz("lugares"));
    }
    
    public static void GMap(){
        
    }
    
    public static void grafo(){

        GrafoController grafoController = GrafoController.getInstance();
        
        grafoController.insertarArista("Lugar A", "Lugar B",3,35.6);
        grafoController.insertarArista("Lugar B", "Lugar C",4,30.6);
        grafoController.insertarArista("Lugar C", "Lugar D",3,35.6);
        grafoController.insertarArista("Lugar D", "Lugar E",2,38);
        grafoController.insertarArista("Lugar A", "Lugar E",2,38);
        grafoController.insertarArista("Lugar E", "Lugar B",8,50);
        grafoController.insertarArista("Lugar D", "Lugar B",6,45.6);
        grafoController.insertarArista("Lugar B", "Lugar G",4,20);
        grafoController.insertarArista("Lugar C", "Lugar G",3,20);
        grafoController.insertarArista("Lugar D", "Lugar F",1,20);
        grafoController.insertarArista("Lugar C", "Lugar F",1,20);
        grafoController.insertarArista("Lugar F", "Lugar G",20,20);
        
        
        //Camino
        //Lugar A -> Lugar C
        ArrayList<String> camino = grafoController.getCamino("Lugar A", "Lugar G");
        if(grafoController.getPesoCosto("Lugar A", "Lugar G")){
            System.out.println(" Costo Final: " + grafoController.getCosto());
            System.out.println(" Peso Final: " + grafoController.getPeso());
        }
        
        if(camino != null){
            for (int i = 0; i < camino.size(); i++) {
                System.out.println(" - "+camino.get(i));
            }
        } else {
            System.out.println("No hay camino :(");
        }
     
        
    }
    
    public static StaticMapsRequest newRequest(GeoApiContext context, Size size) {
        return new StaticMapsRequest(context).size(size);
    }
}
