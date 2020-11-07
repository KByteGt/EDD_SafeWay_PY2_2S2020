/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_safeway;

import block_chain.Cryptography;
import com.google.gson.Gson;

/**
 *
 * @author JOSED
 */
class Controller {
    public enum Request{
        OK,ERROR;
    }
    
    private Request request; 
    
    Cryptography cryp = Cryptography.getInstance();
    Gson json = new Gson();
    
    public Controller(){
        
    }
    
    public String BadReques(String data){
        this.request = Request.ERROR;
        
        return "{\"REQUEST\":"+ this.request +", \"DATA\": "+ data +"}";
    }
    
    public String GoodRequest(String data){
        this.request = Request.OK;
        
        return "{\"REQUEST\":"+ this.request +", \"DATA\": "+ data +"}";
    }
    
    
}
