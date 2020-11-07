/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_safeway;

import arbol_b.Key;
import arbol_b.Tree;

/**
 *
 * @author JOSED
 */
public class UserController extends Controller {
    
    public enum UserKind{
        USER,DRIVER,ADMIN, NULL;
    }
    
    Tree<String,Usuario> usuarios;
    Tree<String,Usuario> conductores;
    
    private final Usuario admin = new Usuario();
    private static UserController userController;
    
    private UserController(){
        usuarios = new Tree(5);
        conductores = new Tree(5);
        
        admin.setId(1);
        admin.setNombre("Marvin");
        admin.setUsuario("Marvin");
        admin.setCorreo("admin@safeway.com");
        admin.setContraseña(cryp.sha256("admin"));
    }
    
    public static UserController getInstance(){
        if(userController == null){
            userController = new UserController();
        }
        
        return userController;
    }
    
    //
    
    //Buscar
    public Usuario searchUser(String nombre){

        //Buscar y retornar el key
        Key key = usuarios.buscar(nombre);
        
        if(key != null){
            //Existe usuario
            return (Usuario) key.getValor();
        } else {
            return null;
        }
        
    }
    
    public Usuario searchDriver(String nombre){

        //Buscar y retornar el key
        Key key = usuarios.buscar(nombre);
        
        if(key != null){
            //Existe usuario
            return (Usuario) key.getValor();
        } else {
            return null;
        }
        
    }
    
    public Usuario searchAdmin(String nombre){

        //Buscar y retornar el key
        if(nombre.equals(admin.getUsuario())){
            return admin;
        } else {
            return null;
        }
        
    }
}
