/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_safeway;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher; //Libreria para encriptar y desencriptar
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author JOSED
 */
public class EDD_SafeWay {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, DataFormatException {
        // TODO code application logic here
            
        System.out.println("Hola Mundo!!");

        Usuario user1 = new Usuario(1,"Daniel","kbytegt","email","1234","555",0.23,-23.34);
        Conductor con1 = new Conductor(1,"Jose","kbytegt","1234","555",0.23,-23.34,false);

        System.out.println(user1.getNombre());
        System.out.println(con1.getNombre());

            
        Zip z = Zip.getInstance();
        
        byte data[];
        
        data = z.zip("Hola mundo!!");
        System.out.println( new String(data));
        System.out.println(z.unzip(data));
        
        data = z.zip("Daniel");
        System.out.println( new String(data));
        System.out.println(z.unzip(data));
       
    }
    
}
