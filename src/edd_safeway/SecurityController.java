/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_safeway;

import block_chain.Cryptography;
import block_chain.Log;
import block_chain.Nodo;
import block_chain.Zip;
import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.Stack;

/**
 *
 * @author JOSED
 */
public class SecurityController extends Thread{
    
    private static SecurityController controller;
    private Zip zip = Zip.getInstance();
    private Cryptography cryp = Cryptography.getInstance();
    private int time = 5;
    private volatile boolean running = true;
    private volatile boolean paused = false;
    
    private String path = "C:\\SafeWay_EDD\\bloques";
    private int indice = 0;
    private String previousHash = "";
    private int nonce = 0;
//    private final Object lock = new Object();
    
    private Stack<Log> logs;
    
    private SecurityController(){
        //Constructor
        this.time = 3; //Tiempo por defecto 5 min
        this.logs = new Stack();
        this.setPath(path);
        //Cargar data inicial si existe
        rollback();
        
        
    }
    
    public static SecurityController getInstance(){
        if(controller == null){
            controller = new SecurityController();
        }
        
        return controller;
    }
    
    //Run
    
    @Override
    public void run(){
        System.out.println(" Iniciando hilo ...");
        
        while(running){
            
            if(!paused){
                try {
                    System.out.println("Sleep: "+(time * 60000));
                    sleep(time * 60000); //Pausa para ejecutar la sigiente iteración
                } catch (Exception e) {
                    break;
                }
                
                //Ejecución en la iteración
                System.out.println(" Thread - time");
                commit();
            }
            
            
        }
        
    }
//    
//    @Override
//    public void stop(){
//        running = false;
//        resume();
//    }
    
    public void pause(){
        paused = true;
    }
    
//    @Override
//    public void resume(){
//        synchronized (lock){
//            paused = false;
//            lock.notifyAll();
//        }
//    }
    //Get && Set
    
    public int getTime(){
        return this.time;
    }
    
    public void setTime(int t){
        if(t > 0){
            this.time = t;
        } else {
            this.time = 1;
        }
        
    }
    
    public int getIndice(){
        return this.indice;
    }
    
    public void setIndice(int i){
        this.indice = i;
    }
    
    public void addLog(Log log){
        this.logs.push(log);
    }
    
    /*//////////////////////////////////////////////
        Archivos
    //////////////////////////////////////////////*/
    
    private void setPath(String path){
        File dir = new File(path);
        
        if(!dir.exists()){
            if(dir.mkdir()){
                System.out.println(" | > Se creo el directorio");
                this.path = path;
            } else {
                System.out.println(" | **Error: No se pudo crear el directorio: " + path);
            }
        } else {
            this.path = path;
        }
    }
    
    public void writeArchivo(String name, String txt){
        String txtPath = this.path+"\\"+name+".txt";
        
        FileOutputStream os;
        OutputStreamWriter sw;
        BufferedWriter bw;
        
        try {
            os = new FileOutputStream(txtPath);
            sw = new OutputStreamWriter(os, "utf-8");
            bw = new BufferedWriter(sw);
            
            bw.write(txt);
            
            bw.close();
            
        } catch (Exception e) {
            System.out.println(" | **Error: [01] "+e);
            txtPath = "";
        }
    }
    
    /*//////////////////////////////////////////////
        Block Chain Methods
    //////////////////////////////////////////////*/
                
    private void commit(){
        if(!this.logs.empty()){
            System.out.println(" Thread - Commit");
            this.paused = true;
            
            //Crear nuevo bloque
            Nodo bloque = new Nodo();
            bloque.setId(indice);
            bloque.setTimestamp(getTimeStamp());
            bloque.setPreviousHash(previousHash);
            
            while(!this.logs.empty()){
                bloque.setData(logs.pop());
            }
            
            //Hacer la prueba de trabajo
            String hash = getHashNode(bloque.getId(),bloque.getTimestamp(),bloque.getPreviousHash(),bloque.getDataJSONFormat());
            bloque.setHash(hash);
            bloque.setNonce(nonce);
            
            //Actualizar le hash
            this.previousHash = hash;
            
            
            //Procedemos a guardar el bloque
            guardarBloque(bloque);
        }
    }
    
    private void rollback(){
        
        //Si existe algun nodo en la carpeta ejecutar
        
        //Si no arrancar la aplicación
        
        
    }
    
    
    public void guardarBloque(Nodo bloque){
        //Proceder a guardar en la carpeta de bloques
        
        try {
            //se comprime primero y luego se cifra
            Gson json = new Gson();
            String nombre = this.getNombreTimeStamp();
            String data = json.toJson(bloque);
            byte[] archivoZip = zip.zip(data);
            byte[] archivoCript = cryp.cifrar(Arrays.toString(archivoZip));
            
            this.writeArchivo(nombre, Arrays.toString(archivoCript));
            
            //Al terminar despausamos
            this.paused = false;
            indice++;
            
        } catch (Exception e) {
            System.out.println(" **Error al exportar bloque ");
        }

    }
    
    public void obtenerBloque(){
        
        //Se decifra y luego se descomprime
    }
    
    private String getTimeStamp(){
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        
        return ts.toString();
    }
    
    private String getNombreTimeStamp(){
        String temp = getTimeStamp();
        char[] lista = temp.toCharArray();
        
        String nombre = "";
        
        for (int i = 0; i < lista.length; i++) {
            if(lista[i] == '.'){
                break;
            } else {
                if(lista[i] == ':'){
                    nombre += '.';
                } else {
                    nombre += lista[i];
                }
            }
        }
        
        return nombre;
        
    }
    
    /*//////////////////////////////////////////////
        Prueba de trabajo
    //////////////////////////////////////////////*/
    
    private String getHashNode(int indice, String fecha, String previosHash, String data){
         ///////////////////////////////////////////////
//        Cryptography cryp = Cryptography.getInstance();
        // Hash BlockChain
        boolean flagHashValid = true, flagHash = true;
        char[] hashNode = null;
        String node = "";
        this.nonce = 0;
        int n = 4;
        
        String idnode = cryp.toHexadecimal(Integer.toString(indice));
        String fechanode = cryp.toHexadecimal(fecha);
        String prevhashnode = cryp.toHexadecimal(previosHash);
        String datanode = cryp.toHexadecimal(data);
        String tempnonce = "";
//        String idnode = "1";
//        String fechanode = "29/10/2020";
//        String prevhashnode = "0";
//        String datanode = "{\"id\":1,\"Categoria\":\"USAC\",\"Nombre\":\"T-3\",\"Lat\":14.5877,\"Lon\":-90.5536}";
//        String tempnonce = "";
        
        System.out.println("Hash node ");
        
        
        while(flagHash){
            flagHashValid = true;
            tempnonce = Integer.toHexString(nonce);
            node = idnode + fechanode + prevhashnode + datanode + tempnonce;
            hashNode = cryp.sha256(node).toCharArray();
            
            for (int i = 0; i < n; i++) {
                if(hashNode[i] != '0'){
                    flagHashValid = false;
                    break;
                } 
            }
            
            if (flagHashValid) {
                System.out.println("Hash Válido, nonce: " + nonce);
                flagHash = false;
                System.out.println(hashNode);
            } else {
                //System.out.println(" nonce: " + nonce); 
                nonce += 1;
            }
        }
        
        return Arrays.toString(hashNode);
    }
    
}
