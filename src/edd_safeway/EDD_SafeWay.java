/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_safeway;

import UI.LogIn;

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
        bockchain.start();
        
        LogIn log = new LogIn();
        log.setVisible(true);
        
    }
}
