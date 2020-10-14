/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_safeway;

/**
 *
 * @author JOSED
 */
public class EDD_SafeWay {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println("Hola Mundo!!");
        
        Usuario user1 = new Usuario(1,"Daniel","kbytegt","email","1234","555",0.23,-23.34);
        Conductor con1 = new Conductor(1,"Jose","kbytegt","1234","555",0.23,-23.34,false);
        
        System.out.println(user1.getNombre());
        System.out.println(con1.getNombre());
    }
    
}
