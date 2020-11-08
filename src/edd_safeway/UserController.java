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
    
    private static Tree<String,Usuario> usuarios;
    private static Tree<String,Usuario> conductores;
    
    private final Usuario admin = new Usuario();
    private static UserController userController;
    
    private int indexUser;
    private int indexDriver;
    
    private UserController(){
        usuarios = new Tree(5);
        conductores = new Tree(5);
        
        admin.setId(1);
        admin.setNombre("Marvin");
        admin.setUsuario("Marvin");
        admin.setCorreo("admin@safeway.com");
        admin.setContraseña(cryp.sha256("admin"));
        
        indexUser = 0;
        indexDriver = 0;
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
    
    public Conductor searchDriver(String nombre){

        //Buscar y retornar el key
        Key key = conductores.buscar(nombre);
        
        if(key != null){
            //Existe usuario
            return (Conductor) key.getValor();
        } else {
            return null;
        }
        
    }
    
    public Usuario getAdmin(){
        return admin;
    }
    
//        Usuario user1 = new Usuario();
//        user1.setNombre("Daniel");
//        user1.setUsuario("KByteGt");
//        user1.setId(1);
//        user1.setCorreo("josedan1996@hotmail.com");
//        user1.setContraseña(cryp.sha256("123456"));
//        
//        Tree bt= new Tree(5);
//        
//        bt.insertar(user1.getUsuario(), user1);
//        bt.recorrer();
    
    //Crear nuevos usuarios
    public boolean createUser(String name, String user, String email, String password, String phone){
        try {
            //Encriptar contraseña
            String encryptedPassword = cryp.sha256(password);
            //Incrementar el index
            indexUser++;

            //Crear usuario
            Usuario newUser = new Usuario(indexUser, name, user, email, encryptedPassword, phone, 0,0);

            //Insertar el usuario
            usuarios.insertar(user, newUser);

            return true;
        } catch (Exception e) {
            System.out.println(" | **Error: " + e);
            return false;
        }
        
    }
    
    public boolean createDriver(String name, String user, String email, String password, String phone){
        try {
            //Encriptar contraseña
            String encryptedPassword = cryp.sha256(password);
            //Incrementar el index
            indexDriver++;

            //Crear usuario
            Conductor newUser = new Conductor(indexDriver,name,user,email,encryptedPassword,phone,0,0,true);

            //Insertar el usuario
            conductores.insertar(user, newUser);
            
            return true;
        } catch (Exception e) {
            System.out.println(" | **Error: " + e);
            return false;
        }
    }
    
    public boolean viewUserTree(){
        
        String dot = usuarios.getGraphviz("Usuarios");
        
        if(!dot.isEmpty()){
            this.writeTxt("BTreeUsers",dot);
            this.writePDF("BTreeUsers");
            this.openPDF();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean viewDriverTree(){
        String dot = usuarios.getGraphviz("Conductores");
        
        if(!dot.isEmpty()){
            this.writeTxt("BTreeDrivers",dot);
            this.writePDF("BTreeDrivers");
            this.openPDF();
            return true;
        } else {
            return false;
        }
    }
    
    public void loadUsers(String path){
        //Cargar usuarios desde JSON
    }
}
