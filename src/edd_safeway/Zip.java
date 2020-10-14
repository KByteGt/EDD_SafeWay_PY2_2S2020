/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_safeway;

import java.util.zip.*;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author JOSED
 */
public class Zip {
    private Deflater d;
    private Inflater i;
    private static Zip zip;
    private byte data[];
    private int size;
    
    private Zip(){}
    
    public static Zip getInstance(){
        if(zip == null){
            zip = new Zip();
        }
        return zip;
    }
    
    //Comprimir
    public byte[] zip(String txt) throws UnsupportedEncodingException{
        d = new Deflater();
        data = new byte[1024];
        
        //Convertir entrada
        d.setInput(txt.getBytes("UTF-8"));
        d.finish();
        
        //Comprimir la data
        size = d.deflate(data);
        
        return data;
    }
    
    public String unzip(byte input[]) throws DataFormatException, UnsupportedEncodingException{
        i = new Inflater();
        data = new byte[1024];
        
        i.setInput(input);
        
        //Desconprimir data
        size = i.inflate(data);
        i.end();
        
        return new String(data, "UTF-8");
    }
}
