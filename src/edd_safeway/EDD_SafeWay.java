/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_safeway;
import Grafo.Grafo;
import Grafo.Vertice;
import arbol_b.Key;
import arbol_b.Tree;
import block_chain.Cryptography;
import block_chain.Log;
import block_chain.Log.Accion;
import block_chain.Log.Tipo;
import block_chain.Logger;
import block_chain.Nodo;
import block_chain.Zip;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.StaticMapsRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Size;
import java.awt.BorderLayout;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tabla_hash.TablaHash;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
/**
 *
 * @author JOSED
 */
public class EDD_SafeWay {
 
    public static UI.LogIn logIn = new UI.LogIn();    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
//        Cryptography cryp = Cryptography.getInstance();
        
        //Verificar si no hay un nodo
        SecurityController bockchain = SecurityController.getInstance();
        bockchain.run();
        
    }
}
