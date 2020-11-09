/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_safeway;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import tabla_hash.TablaHash;

/**
 *
 * @author JOSED
 */
public class PlaceController extends Controller{
    
    private TablaHash lugares;
    
    private static PlaceController placeController;
    
    private int indexPlace = 0;
    
    private PlaceController(){
        //Constructor
        lugares = new TablaHash(11);
    }
    
    public static PlaceController getInstance(){
        if(placeController == null){
            placeController = new PlaceController();
        }
        
        return placeController;
    }
    
    //Insertar Lugar
    public boolean creatPlace(String category, String name, double latitud, double longitud){
        
        indexPlace++;
        
        Lugar newPlace = new Lugar(indexPlace, category, name, latitud, longitud);
        
        return lugares.insertar(indexPlace, newPlace);
    }
    
    public boolean viewPlacesHashTable(){
        String dot = lugares.getGraphviz("Conductores");
        
        if(!dot.isEmpty()){
            this.writeTxt("HashTablePlaces",dot);
            this.writePDF("HashTablePlaces");
            this.openPDF();
            return true;
        } else {
            return false;
        }
    }
    
    public int loadPlaces(File file){
        //Cargar lugares desde JSON
        String gson = getJson(file);
        int i = -1;
        
        if(!gson.isEmpty()){
            i = 0;
            
            JsonElement element = json.fromJson(gson, JsonElement.class);
            JsonObject obj = element.getAsJsonObject();
            
            JsonArray places = obj.getAsJsonArray("Lugares");
            if(places != null){
                for(JsonElement places_obj : places){
                    JsonObject place = places_obj.getAsJsonObject();
                    
                    if(place != null){
                        int id = place.get("id").getAsInt();
                        String category = place.get("Categoria").getAsString();
                        String name = place.get("Nombre").getAsString();
                        double latitud = place.get("Lat").getAsDouble();
                        double longitud = place.get("Lon").getAsDouble();
                        
                        Lugar newPlace = new Lugar(id, category, name, latitud,longitud);
                        lugares.insertar(id, newPlace);
                        
                        if(id > indexPlace){
                            indexPlace = id;
                        }
                        i++;
                    }
                }
            }
        }

        return i;
    }
    
}
