/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_safeway;
import arbol_b.Tree;
import block_chain.Cryptography;
import block_chain.Log;
import block_chain.Log.Accion;
import block_chain.Log.Tipo;
import block_chain.Logger;
import block_chain.Nodo;
import block_chain.Zip;
import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.zip.DataFormatException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import tabla_hash.TablaHash;
import java.sql.Timestamp;
import java.util.Arrays;
/**
 *
 * @author JOSED
 */
public class EDD_SafeWay {
    Cryptography cryp = Cryptography.getInstance();
    public static final Usuario admin = new Usuario();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cryptography cryp = Cryptography.getInstance();
        admin.setId(1);
        admin.setNombre("Marvin");
        admin.setUsuario("Marvin");
        admin.setCorreo("admin@safeway.com");
        admin.setContraseña(cryp.sha256("admin"));

//        admin.setContraseña(cryp.sha256("admin"));
//        System.out.println(cryp.sha256("admin"));
        
    
//        hashNode();
//        jsonNodo();
        b_tree();

//        Usuario user1 = new Usuario(1,"Daniel","kbytegt","email","1234","555",0.23,-23.34);
//        Conductor con1 = new Conductor(1,"Jose","kbytegt","1234","555",0.23,-23.34,false);
//
//        System.out.println(user1.getNombre());
//        System.out.println(con1.getNombre());
//
//        System.out.println(" |> Encriptación ");
//        Zip z = Zip.getInstance();
//        
//        byte data[];
//        
//        data = z.zip("Hola mundo!!");
//        System.out.println( new String(data));
//        System.out.println(z.unzip(data));
//        
//        data = z.zip("Daniel");
//        System.out.println( new String(data));
//        System.out.println(z.unzip(data));
//        
//        System.out.println(" |> Tabla Hash");
//        
//        Lugar l1 = new Lugar(1,"USAC","T-3",14.5877,-90.5536);
//        Lugar l2 = new Lugar(1,"Plaza","Plaza Villa nueva",14.533,-90.5852);
//        Lugar l3 = new Lugar(1,"Gasolinera","Gasolinera puma quetzal",14.536,-90.5868);
//        Lugar l4 = new Lugar(1,"USAC","T6",0,0);
//        Lugar l5 = new Lugar(1,"USAC","T7",0,0);
//        Lugar l6 = new Lugar(1,"USAC","S12",0,0);
//        Lugar l7 = new Lugar(1,"USAC","S11",0,0);
//        Lugar l8 = new Lugar(1,"USAC","M5",0,0);
//        Lugar l9 = new Lugar(1,"USAC","T1",0,0);
//        Lugar l10 = new Lugar(1,"USAC","T2",0,0);
//        Lugar l11 = new Lugar(1,"USAC","M6",0,0);
//        
//        TablaHash hashTable = new TablaHash(11);
//        
//        hashTable.insertar(l1);
//        
//        hashTable.insertar(l2);
//        hashTable.insertar(l3);
//        hashTable.insertar(l4);
//        hashTable.insertar(l5);
//        hashTable.insertar(l6);
//        hashTable.insertar(l7);
//        hashTable.insertar(l8);
//        hashTable.insertar(l9);
//        hashTable.insertar(l10);
//        hashTable.insertar(l11);
//        hashTable.insertar(l1);
//        
//        hashTable.imprimir();
//        
//        hashTable.buscar("T3");
//        hashTable.buscar("T55");
//        
//        System.out.println(l1.getJSON());
       
    }
    
    public static void jsonNodo() throws UnsupportedEncodingException, DataFormatException{
       
        
        Logger logger = Logger.getInstance();
        Gson json = new Gson();
        Zip zip = Zip.getInstance();
        
        Lugar lugar = new Lugar(1,"USAC","T-3",14.5877,-90.5536);
        Log log = new Log();
        log.setAccion(Accion.INSERTAR);
        log.setTipo(Tipo.LUGAR);
        log.setObjeto(lugar);
        
        Lugar lugar2 = new Lugar(2,"Plaza","Plaza Villa nueva",14.533,-90.5852);
        Log log2 = new Log();
        log2.setAccion(Accion.INSERTAR);
        log2.setTipo(Tipo.LUGAR);
        log2.setObjeto(lugar2);

        
        Nodo nodeBC = new Nodo();
        nodeBC.setId(1);
        nodeBC.setTimestamp(logger.getTimeStamp());
        nodeBC.setData(log);
        nodeBC.setData(log2);
        nodeBC.setNonce(0);
        nodeBC.setPreviousHash("0");
        nodeBC.setHash("0000da8618afd5fa8293ec73ad2ef326cb12f5ae50ecedc89d06d05a0a9f7f12");
        
        String respuesta = json.toJson(nodeBC);
        System.out.println(respuesta);
        
        byte[] cripting = zip.zip(respuesta);
        System.out.println(cripting);
        System.out.println(zip.unzip(cripting));
    }
    
    public static void hashNode(){
        ///////////////////////////////////////////////
        Cryptography cryp = Cryptography.getInstance();
        // Hash BlockChain
        String node = "";
        int nonce = 0, n = 4;
        
        String idnode = cryp.toHexadecimal("2");
        String fechanode = cryp.toHexadecimal("29/10/2020");
        String prevhashnode = cryp.toHexadecimal("0000da8618afd5fa8293ec73ad2ef326cb12f5ae50ecedc89d06d05a0a9f7f12");
        String datanode = cryp.toHexadecimal("{\"id\":1,\"Categoria\":\"USAC\",\"Nombre\":\"T-3\",\"Lat\":14.5877,\"Lon\":-90.5536}");
        String tempnonce = "";
//        String idnode = "1";
//        String fechanode = "29/10/2020";
//        String prevhashnode = "0";
//        String datanode = "{\"id\":1,\"Categoria\":\"USAC\",\"Nombre\":\"T-3\",\"Lat\":14.5877,\"Lon\":-90.5536}";
//        String tempnonce = "";
        
        System.out.println("Hash node ");
        boolean flagHashValid = true, flagHash = true;
        char[] hashNode;
        
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
        
        // nonce: 15597, incremento en 3
        // 82533 - 3, 38072 - 1
//        char[] hashNode = "00004587bfr48".toCharArray();

        
        ///////////////////////////////////////////////////
    }
    
    public static void b_tree(){
        //Árbol B
//        System.out.println(" Prueba árbol B");
//        Cryptography cryp = Cryptography.getInstance();
//        
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

        Tree<Integer, Integer> arbol = new Tree(5);
        
        for (int i = 0; i < 50; i++) {
            arbol.insertar(i, i);
        }
        
//        arbol.insertar(10, 10);
//        arbol.insertar(20, 20);
//        arbol.insertar(5, 5);
//        arbol.insertar(6, 6);
//        arbol.insertar(12, 12);
//        arbol.insertar(30, 30);
//        arbol.insertar(7, 7);
//        arbol.insertar(17, 17);
//        arbol.insertar(25, 25);
//        arbol.insertar(11, 11);
//        
//        arbol.insertar(1, 1);
//        arbol.insertar(2, 2);
//        arbol.insertar(3, 3);
//        arbol.insertar(40, 40);
//        arbol.insertar(31, 31);
//        arbol.insertar(13, 13);
//        
//        arbol.insertar(8, 8);
//        arbol.insertar(14, 14);
//        arbol.insertar(4, 4);
//        
//        arbol.recorrer();
        
        System.out.println(arbol.getGraphviz("Prueba"));
        
    }
}
