/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package block_chain;

import java.util.Stack;

/**
 *
 * @author JOSED
 * @see Clase que controla los registros realizados en la aplicación,
 * se encarga de leer, almacenar y exportar los registros
 * @hidden Patron Singleton
 */
public class Logger {
    //Ruta de la carpeta
    private String path = "C:\\KByteGt\\bloques";
    
    //Instancia tipo Logger
    private static Logger log; 
    
    //Pila de tipo Log
    private Stack<Log> pila;
    
    //Constructor privado
    private Logger(){
        pila = new Stack<Log>();
    }
    
    //Obtener la instancia
    public static Logger getInstance(){
        if(log == null){
            log = new Logger();
        }
        return log;
    }
}
