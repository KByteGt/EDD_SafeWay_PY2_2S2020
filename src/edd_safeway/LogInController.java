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
    
    String userKey;
    private UserController userController;
    
    public LogInController(){
        userController = UserController.getInstance();
        userKey = "";
    }
    
    public Usuario logIn(String user, String pass, UserKind kind){
        
        /*
        Pasos:
        1- Buscar el usuario en el árbol de usuarios
        2- encriptar contraseña [pass]
        3- comparar contraseña
        4- retornar respuesta
        */
        Usuario temp;
        
        switch(kind){
            case USER:
                temp = userController.searchUser(user);
                break;
            case DRIVER:
                temp = userController.searchDriver(user);
                break;
            case ADMIN:
                temp = userController.searchAdmin(user);
                break;
            default:
                temp = null;
                break;                
        }
        
        if(temp != null){
            //Existe el conductor
            String password = cryp.sha256(pass);
            
            System.out.println("Usuario: " + temp.getUsuario() + " ("+ temp.getContraseña() +")");

            if(!password.equals(temp.getContraseña())){
                //La contraseña es incorrecta
                temp = null;
            }
        }

        return temp;
    }
    
    
}
