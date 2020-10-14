/*
 * Clase Usuario, almacena la información básica de un usuario
 * para poder ser utilizado y exportado
 */
package edd_safeway;

/**
 *
 * @author JOSED
 */
public class Usuario {
    private int id;
    private String nombre, usuario, correo, contraseña, telefono;
    private double latitud, longitud;
    
    //Construcotes
    
    public Usuario(){
        this.id = 0;
        this.nombre = "";
        this.usuario = "";
        this.correo = "";
        this.contraseña = "";
        this.telefono = "";
        this.latitud = 0.0;
        this.longitud = 0.0;
    }

    public Usuario(int id, String nombre, String usuario, String correo, String contraseña, String telefono, double latitud, double longitud) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.correo = correo;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.latitud = latitud;
        this.longitud = longitud;
    }
    
    //Get & Set

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    
    
    
}
