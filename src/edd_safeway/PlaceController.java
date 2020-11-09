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
import java.util.ArrayList;
import tabla_hash.TablaHash;

/**
 *
 * @author JOSED
 */
public class PlaceController extends Controller{
    
    private TablaHash lugares;
    
    private static PlaceController placeController;
    private UserController userController;
    
    private int indexPlace = 0;
    
    private PlaceController(){
        //Constructor
        lugares = new TablaHash(11);
        userController = UserController.getInstance();
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
        
        return lugares.insertar(name, newPlace);
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
                        lugares.insertar(name, newPlace);
                        
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
    
    public int loadUsersLocation(File file){
        //Cargar las ubicaciones de los usuarios
        String gson = getJson(file);
        int i = -1;
        
        if(!gson.isEmpty()){
            i = 0;
            
            JsonElement element = json.fromJson(gson, JsonElement.class);
            JsonObject obj = element.getAsJsonObject();
            
            JsonArray locations = obj.getAsJsonArray("localidades");
            if(locations != null){
                System.out.println(" T - Cargando localidades...");
                for(JsonElement location_obj : locations){
                    JsonObject location = location_obj.getAsJsonObject();
                    
                    if(location != null){
                        int id_user = location.get("id_usuario").getAsInt();
                        String name = location.get("nombre").getAsString();
                        
                        //Relacionar lugar con usuario
                        Lugar tempPlace = (Lugar) lugares.buscar(name).getValor();
                        
                        userController.updateUserLocation(id_user, tempPlace.getLatitud(), tempPlace.getLongitud(), name);
                        i++;
                    }
                }
            }
        }
        
        return i;
    }
    
    public int loadDriversLocation(File file){
        //Cargar las ubicaciones de los conductores
        String gson = getJson(file);
        int i = -1;
        
        if(!gson.isEmpty()){
            i = 0;
            
            JsonElement element = json.fromJson(gson, JsonElement.class);
            JsonObject obj = element.getAsJsonObject();
            
            JsonArray locations = obj.getAsJsonArray("localidades");
            if(locations != null){
                System.out.println(" T - Cargando localidades...");
                for(JsonElement location_obj : locations){
                    JsonObject location = location_obj.getAsJsonObject();
                    
                    if(location != null){
                        int id_user = location.get("id_conductor").getAsInt();
                        String name = location.get("lugar").getAsString();
                        boolean available = location.get("disponibilidad").getAsBoolean();
                        
                        //Relacionar lugar con Conductor
                        Lugar tempPlace = (Lugar) lugares.buscar(name).getValor();
                        
                        userController.updateDriverLocation(id_user, tempPlace.getLatitud(), tempPlace.getLongitud(), available, name);
                        i++;
                    }
                }
            }
        }
        
        return i;
    }
    
    public ArrayList getPlaces(){
        
        if(lugares.getPlaceList().isEmpty()){
            return null;
        } else {
            return lugares.getPlaceList();
        }
        
    }
}
