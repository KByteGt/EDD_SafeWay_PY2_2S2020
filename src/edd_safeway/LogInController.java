/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_safeway;

import edd_safeway.UserController.UserKind;

/**
 *
 * @author JOSED
 * MVC (Modelo, Vista, Controlador)
 * Controlador del LogIn
 */

public class LogInController extends Controller{
    
    //String userKey;
    private UserController userController;
    
    public LogInController(){
        userController = UserController.getInstance();
        //userKey = "";
    }
    
    /*
    Pasos:
    1- Buscar el usuario en el árbol de usuarios
    2- encriptar contraseña [pass]
    3- comparar contraseña
    4- retornar respuesta
    */
    
    public Usuario logInUser(String user, String pass){
        
        Usuario temp = userController.searchUser(user);
        String password = cryp.sha256(pass);
        
        if(temp != null && password.equals(temp.getContraseña())){
            //Existe el conductor
            System.out.println("Usuario: " + temp.getUsuario() + " ("+ temp.getContraseña() +")");
            return temp;
        }

        return null;
    }
    
    public Conductor logInDriver(String user, String pass){
        
        Conductor temp = userController.searchDriver(user);
        String password = cryp.sha256(pass);
        
        if(temp != null && password.equals(temp.getContraseña())){
            //Existe el conductor
            System.out.println("Usuario: " + temp.getUsuario() + " ("+ temp.getContraseña() +")");
            return temp; 
        }

        return null;

    }
    
    public Usuario logInAdmin(String user, String pass){
        Usuario temp = userController.getAdmin();
        
        String password = cryp.sha256(pass);
        
        if(password.equals(temp.getContraseña())){
            System.out.println("Usuario: " + temp.getUsuario() + " ("+ temp.getContraseña() +")");
            return temp;
        }
        return null;
    }
    
    
}
