/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_safeway;
import java.io.UnsupportedEncodingException;
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
        
        Lugar l1 = new Lugar(1,"USAC","T3",0,0);
        Lugar l2 = new Lugar(1,"USAC","T4",0,0);
        Lugar l3 = new Lugar(1,"USAC","T5",0,0);
        Lugar l4 = new Lugar(1,"USAC","T6",0,0);
        Lugar l5 = new Lugar(1,"USAC","T7",0,0);
        Lugar l6 = new Lugar(1,"USAC","S12",0,0);
        Lugar l7 = new Lugar(1,"USAC","S11",0,0);
        Lugar l8 = new Lugar(1,"USAC","M5",0,0);
        Lugar l9 = new Lugar(1,"USAC","T1",0,0);
        Lugar l10 = new Lugar(1,"USAC","T2",0,0);
        Lugar l11 = new Lugar(1,"USAC","M6",0,0);
        
        TablaHash hashTable = new TablaHash(11);
        
        hashTable.insertar(l1);
        
        hashTable.insertar(l2);
        hashTable.insertar(l3);
        hashTable.insertar(l4);
        hashTable.insertar(l5);
        hashTable.insertar(l6);
        hashTable.insertar(l7);
        hashTable.insertar(l8);
        hashTable.insertar(l9);
        hashTable.insertar(l10);
        hashTable.insertar(l11);
        hashTable.insertar(l1);
        
        hashTable.imprimir();
        
        hashTable.buscar("T3");
        hashTable.buscar("T55");
       
    }
    
}
