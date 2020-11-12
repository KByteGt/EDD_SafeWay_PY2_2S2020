/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_safeway;

import arbol_b.Key;
import arbol_b.Tree;
import block_chain.Log;
import block_chain.Logger;

/**
 *
 * @author JOSED
 */
public class TravelController extends Controller{
    
    private Tree<Integer,Travel> viajes;
    private int indexTravel;
    
    private static TravelController travelController;
    
    private TravelController(){
        viajes = new Tree(5);
        indexTravel = 0;
    }
    
    public static TravelController getInstance(){
        if(travelController == null){
            travelController = new TravelController();
        }
        
        return travelController;
    }
    
    public void addTravel(String place1, String place2, String date){
        Travel newTravel = new Travel(indexTravel, place1, place2, date);
        
        viajes.insertar(indexTravel, newTravel, date);
        
        indexTravel++;
        
        //Insertar en el Log
        Log log = new Log();
        log.setAccion(Log.Accion.INSERTAR);
        log.setTipo(Log.Tipo.TRAVEL);
        log.setObjeto(newTravel);
        SecurityController.getInstance().addLog(log);
    }
    
    public int addTravelMap(String place1, String place2){
        String date = Logger.getInstance().getTimeStamp();
        Travel newTravel = new Travel(indexTravel, place1, place2, date);
        
        viajes.insertar(indexTravel, newTravel, date);
        
        System.out.println(" --> Viaje añadido: ["+indexTravel+"] {"+place1+" -> "+place2+", "+date+"}");
        indexTravel++;
        
         //Insertar en el Log
        Log log = new Log();
        log.setAccion(Log.Accion.INSERTAR);
        log.setTipo(Log.Tipo.TRAVEL);
        log.setObjeto(newTravel);
        SecurityController.getInstance().addLog(log);
        
        return indexTravel-1;
    }
    
    public Travel getTravel(int id){
        
        Key key = viajes.buscar(id);
        
        if(key != null){
            return (Travel) key.getValor();
        } else {
            return  null;
        }
    }

    public boolean viewTravelsTree() {
        String dot = viajes.getGraphviz("Viajes");
        
        if(!dot.isEmpty()){
            this.writeTxt("BTreeTravels",dot);
            this.writePDF("BTreeTravels");
            this.openPDF();
            return true;
        } else {
            return false;
        }
    }
}
