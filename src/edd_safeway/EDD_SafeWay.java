/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_safeway;
import block_chain.Cryptography;
import block_chain.Zip;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.zip.DataFormatException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import tabla_hash.TablaHash;

/**
 *
 * @author JOSED
 */
public class EDD_SafeWay {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedEncodingException, DataFormatException {
        // TODO code application logic here
            
//        Usuario admin = new Usuario();
//        admin.setId(1);
//        admin.setNombre("Marvin");
//        admin.setCorreo("admin@safeway.com");
//        
        Cryptography cryp = Cryptography.getInstance();
//        
//        admin.setContraseña(cryp.sha256("admin"));
//        System.out.println(cryp.sha256("admin"));
        
    
        hashNode();
        

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
    
}
