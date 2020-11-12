/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package block_chain;

import com.google.gson.Gson;
import java.util.ArrayList;

/**
 *
 * @author JOSED
 */
public class Nodo {
    private int id;
    private String timestamp;
    private ArrayList<Log> data;
    private int nonce;
    private String previousHash;
    private String hash;

    public Nodo() {
        this.id = 0;
        this.timestamp = "0000-00-00 00:00:00.000";
        this.nonce = 0;
        this.previousHash = "0";
        this.hash = "";
        data = new ArrayList();
    }

    public Nodo(int id, String timestamp, int nonce, String previousHash) {
        this.id = id;
        this.timestamp = timestamp;
        this.nonce = nonce;
        this.previousHash = previousHash;
        this.hash = "";
        data = new ArrayList();
    }

    public ArrayList<Log> getData() {
        return data;
    }

    public void setData(Log data) {
        this.data.add(data);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    
    public String getDataJSONFormat(){
        Gson json= new Gson();
        return json.toJson(data);
    }
    
//    public String getJSON(){
//        String json = "";
//        
//        json += "{";
//        
//        json += "\"id\": " + id;
//        json += ",\"timestamp\": \"" + timestamp + "\"";
//        json += ","
//        json += "}";
//        return json;
//    }
}
