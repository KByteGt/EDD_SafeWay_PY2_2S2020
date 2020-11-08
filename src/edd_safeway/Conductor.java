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
public class Conductor extends Usuario {
    private boolean disponibilidad;

    public Conductor(int id, String nombre, String usuario, String correo, String contraseña, String telefono, double latitud, double longitud, boolean disponibilidad) {
        this.setId(id);
        this.setNombre(nombre);
        this.setUsuario(usuario);
        this.setCorreo(correo);
        this.setContraseña(contraseña);
        this.setTelefono(telefono);
        this.setLatitud(latitud);
        this.setLongitud(longitud);
        this.disponibilidad = disponibilidad;
    }
    
    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
    
}
