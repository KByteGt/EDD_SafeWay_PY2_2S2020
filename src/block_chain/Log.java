/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package block_chain;

/**
 *
 * @author JOSED
 */
public class Log {
    //Tipos de acciones
    public enum Accion{
        ELIMINA,BUSCAR,INSERTAR;
    }
    
    public enum Tipo{
        USUARIO,CONDUCTOR,LUGAR,ASIGNACION;
    }
    
    private Accion accion;
    private Tipo tipo;
    private Object objeto;
    
    //Constructor de registros
    public Log(){
        this.accion = null;
        this.tipo = null;
        this.objeto = null;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }
    
    
}
