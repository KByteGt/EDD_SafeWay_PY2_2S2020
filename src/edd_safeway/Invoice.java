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
public class Invoice {
    
    private int id;
    private int id_user;
    private int id_driver;
    private int id_travel;
    private String fecha;
    private double monto;

    public Invoice(int id, int id_user, int id_driver, int id_travel, String fecha, double monto) {
        this.id = id;
        this.id_user = id_user;
        this.id_driver = id_driver;
        this.id_travel = id_travel;
        this.fecha = fecha;
        this.monto = monto;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_driver() {
        return id_driver;
    }

    public void setId_driver(int id_driver) {
        this.id_driver = id_driver;
    }

    public int getId_travel() {
        return id_travel;
    }

    public void setId_travel(int id_travel) {
        this.id_travel = id_travel;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    
}
