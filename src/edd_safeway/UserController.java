/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_safeway;

import arbol_b.Key;
import arbol_b.Tree;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author JOSED
 */
public class UserController extends Controller {
    
    private class Nodo{
        //Almacena la relación id con username
        private int id;
        private String username;
        
        public Nodo(int id, String username){
            this.id = id;
            this.username = username;
        }
        
        public int getId(){
            return this.id;
        }
        
        public String getUsername(){
            return this.username;
        }
    }
    
    public enum UserKind{
        USER,DRIVER,ADMIN, NULL;
    }
    private ArrayList<Nodo> authUsers;
    private ArrayList<Nodo> authDrivers;
    
    private static Tree<Integer,Usuario> usuarios;
    private static Tree<Integer,Conductor> conductores;
    
    private final Usuario admin = new Usuario();
    private static UserController userController;
    
    private int indexUser;
    private int indexDriver;
    
    private UserController(){
        usuarios = new Tree(5);
        conductores = new Tree(5);
        authUsers = new ArrayList();
        authDrivers = new ArrayList();
        
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

        //Buscar usuario en la tabla de referencia de usuarios por id
        int index = -1;
        for(Nodo n : authUsers){
//            System.out.println(" -> ["+n.getId()+"] "+n.getUsername());
            if(n.getUsername().equals(nombre)){
                index = n.getId();
                break;
            }
        }
        
        if(index > 0){
            //Buscar y retornar el key
            Key key = usuarios.buscar(index);

            if(key != null){
                //Existe usuario
                return (Usuario) key.getValor();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    
    public Conductor searchDriver(String nombre){

        //Buscar usuario en la tabla de referencia de usuarios por id
        int index = -1;
        for(Nodo n : authDrivers){
            if(n.getUsername().equals(nombre)){
                index = n.getId();
                break;
            }
        }
        
        if(index > 0){
            //Buscar y retornar el key
            Key key = conductores.buscar(index);

            if(key != null){
                //Existe usuario
                return (Conductor) key.getValor();
            } else {
                return null;
            }
        } else {
            return null;
        }       
    }
    
    //Eliminar
    
    
    //Obtener
    
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
            usuarios.insertar(indexUser, newUser, user);
            
            //Agregar usuario a authUsers
            addAuthUsers(indexUser, user);

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
            conductores.insertar(indexDriver,newUser, user);
            
            //Agregar usuario a authDrivers
            addAuthDrivers(indexDriver, user);
            
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
        String dot = conductores.getGraphviz("Conductores");
        
        if(!dot.isEmpty()){
            this.writeTxt("BTreeDrivers",dot);
            this.writePDF("BTreeDrivers");
            this.openPDF();
            return true;
        } else {
            return false;
        }
    }
    
    public int loadUsers(File file){
        //Cargar usuarios desde JSON
        String gson = getJson(file);
        int i = -1;
        
        if(!gson.isEmpty()){
            i = 0;
            
            JsonElement element = json.fromJson(gson, JsonElement.class);
            JsonObject obj = element.getAsJsonObject();
            
            JsonArray users = obj.getAsJsonArray("usuarios");
            if(users != null){
                for(JsonElement user_object : users){
                    JsonObject user = user_object.getAsJsonObject();

                    if(user != null){
                        int id = user.get("id").getAsInt();
                        String name = user.get("nombre").getAsString();
                        String username = user.get("usuario").getAsString();
                        String email = user.get("correo").getAsString();
                        String pass = user.get("pass").getAsString();
                        String phone = user.get("telefono").getAsString();
                        String rol = user.get("rol").getAsString();

                        String password = cryp.sha256(pass);

                        System.out.println(" - Insertando usuario: " + username);
                        //Insertar segun rol
                        if(rol.equals("conductor")){
                            //Insertar en conductor

                            //Crear usuario
                            Conductor newUser = new Conductor(id,name,username,email,password,phone,0,0,true);

                            //Insertar el usuario
                            conductores.insertar(id, newUser, username);
                            addAuthDrivers(id,username);

                            if(id > indexDriver){
                                //Ajustar indexDriver
                                indexDriver = id;
                            }

                        } else {
                            //Insertar en usuario

                            //Crear Usuario
                            Usuario newUser = new Usuario(id, name, username,email,password,phone,0,0);

                            //Insertar usuario
                            usuarios.insertar(id, newUser, username);
                            addAuthUsers(id, username);

                            if(id > indexUser){
                                //Ajustamos indexUser
                                indexUser = id;
                            }
                        }

                        i++;
                    }
                }
            }
        } 
        
        return i;
    }
    
    public void addAuthUsers(int id, String name){
        authUsers.add(new Nodo(id,name));
    }
    
    public void addAuthDrivers(int id, String name){
        authDrivers.add(new Nodo(id, name));
    }
    
    public void updateUserLocation(int id, double latitud, double longitud, String place){
        if(id > 0){
            //Buscar y retornar el key
            Key key = usuarios.buscar(id);

            if(key != null){
                //Existe usuario
                Usuario tempUser = (Usuario) key.getValor();
                tempUser.setLatitud(latitud);
                tempUser.setLongitud(longitud);
                tempUser.setPlace(place);
                
                key.setValor(tempUser);
                System.out.println(" | > Se actualizo la ubicación del usuario: "+ tempUser.getNombre());
            } else {
                System.out.println(" | **No se actualizo la ubicación del usuario");
            }
        } 
    }
    
    public void updateDriverLocation(int id, double latitud, double longitud, boolean available, String place){
        if(id > 0){
            //Buscar y retornar el key
            Key key = conductores.buscar(id);

            if(key != null){
                //Existe usuario
                Conductor tempUser = (Conductor) key.getValor();
                tempUser.setLatitud(latitud);
                tempUser.setLongitud(longitud);
                tempUser.setDisponibilidad(available);
                tempUser.setPlace(place);
                
                key.setValor(tempUser);
                System.out.println(" | > Se actualizo la ubicación del conductor: "+ tempUser.getNombre());
            } else {
                System.out.println(" | **No se actualizo la ubicación del conductor");
            }
        }
    }
    
    
    public void deleteUser(int id){
        ArrayList<Key> lista = usuarios.eliminar(id);
        
        if(!lista.isEmpty()){
            System.out.println(" | > Eliminando usuario");
            Tree<Integer,Usuario> newTree = new Tree(5);
            
            for(Key k : lista){
                newTree.insertar((Integer) k.getKey() , (Usuario) k.getValor(), k.getNombre());
            }
            
            usuarios = newTree;
            //newTree.recorrer();
        }
    }
    
    public void deleteDriver(int id){
        ArrayList<Key> lista = conductores.eliminar(id);
        
        if(!lista.isEmpty()){
            System.out.println(" | > Eliminando conductor");
            Tree<Integer,Conductor> newTree = new Tree(5);
            
            for(Key k : lista){
                newTree.insertar((Integer) k.getKey() , (Conductor) k.getValor(), k.getNombre());
            }
            
            conductores = newTree;
            //newTree.recorrer();
        }
    }
    
    public void updateUserInfo(int id, String name, String phone){
        //Buscar usuario
        Key key = usuarios.buscar(id);

        if(key != null){
            //Existe usuario
            Usuario tempUser = (Usuario) key.getValor();
            tempUser.setNombre(name);
            tempUser.setTelefono(phone);
            
            key.setValor(tempUser);
        } 
    }
    
    public void updateDriverInfo(int id, String name, String phone){
        //Buscar usuario
        Key key = conductores.buscar(id);

        if(key != null){
            //Existe usuario
            Conductor tempUser = (Conductor) key.getValor();
            tempUser.setNombre(name);
            tempUser.setTelefono(phone);
            
            key.setValor(tempUser);
        } 
    }
    
    public void updateUserPassword(int id, String password){
        //Buscar usuario
        Key key = usuarios.buscar(id);
        String encryptedPassword = cryp.sha256(password);

        if(key != null){
            //Existe usuario
            Usuario tempUser = (Usuario) key.getValor();
            tempUser.setContraseña(encryptedPassword);
            
            key.setValor(tempUser);
        }
    }
    
    public void updateDriverPassword(int id, String password){
        //Buscar usuario
        Key key = conductores.buscar(id);
        String encryptedPassword = cryp.sha256(password);

        if(key != null){
            //Existe usuario
            Conductor tempUser = (Conductor) key.getValor();
            tempUser.setContraseña(encryptedPassword);
            
            key.setValor(tempUser);
        }
    }
    
    //Travel information
    public ArrayList getUserInvoiceList(int id){
        Key key = usuarios.buscar(id);
        
        if(key != null){
            //Buscamos la lista
            Usuario tempUser = (Usuario) key.getValor();
            ArrayList list = tempUser.getInvoiceList();
            
            if(!list.isEmpty()){
                return list;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    
    public ArrayList getDriverInvoiceList(int id){
        Key key = conductores.buscar(id);
        
        if(key != null){
            //Buscamos la lista
            Conductor tempUser = (Conductor) key.getValor();
            ArrayList list = tempUser.getInvoiceList();
            
            if(!list.isEmpty()){
                return list;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    
    //Agregar invoice
    public void addUserInvoice(int user, int invoice){
        //Buscar y almacenar el invoice
        Key key = usuarios.buscar(user);

        if(key != null){
            //Existe usuario
            Usuario temp = (Usuario) key.getValor();
            temp.addInvoiceId(invoice);
            key.setValor(temp);
        } 
    }
    
    public void addDriverInvoice(int user, int invoice){
        //Buscar y almacenar el invoice
        Key key = conductores.buscar(user);

        if(key != null){
            //Existe usuario
            Conductor temp = (Conductor) key.getValor();
            temp.addInvoiceId(invoice);
            key.setValor(temp);
        } 
    }
    
    public String getUserName(int index){

        //Buscar y retornar el key
        Key key = usuarios.buscar(index);

        if(key != null){
            //Existe usuario
            Usuario temp = (Usuario) key.getValor();
            return temp.getNombre();
        } else {
            return "[Id:"+index+"] Usuario eliminado";
        }
    }
    
    public String getDriverName(int index){
        //Buscar y retornar el key
        Key key = conductores.buscar(index);

        if(key != null){
            //Existe usuario
            Conductor temp = (Conductor) key.getValor();
            return temp.getNombre();
        } else {
            return "[Id:"+index+"] Conductor eliminado";
        }
    }
}
