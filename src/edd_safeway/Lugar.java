/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_safeway;


/**
 *
 * @author JOSED
 */

public class Lugar {
    
    private final int id;
    private final String categoria,nombre;
    private final double latitud,longitud;
    
    private String g;

    public Lugar(int id, String categoria, String nombre, double latitud, double longitud) {
        this.id = id;
        this.categoria = categoria;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }
    
    public String getJSON(){
        g = "";
        
        System.out.println(" >> Exportadno JSON del lugar: "+id);
        
        g.concat("{");
        g.concat("\"id\":").concat(Integer.toString(id));
        g.concat(",\"Categoria\":").concat(categoria);
        g.concat(",\"Nombre\":").concat(nombre);
        g.concat(",\"Lat\":").concat(Double.toString(latitud));
        g.concat(",\"Lon\":").concat(Double.toString(longitud));
        g.concat("}");
        
        return g;
    }
    
    public void cargarJSON(){
        
    }
}
