/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_safeway;

import block_chain.Cryptography;
import com.google.gson.Gson;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author JOSED
 */
class Controller {
    
    private String path;
    public String txtPath;
    public String pdfPath;
    
    public enum Request{
        OK,ERROR;
    }
    
    private Request request; 
    
    Cryptography cryp = Cryptography.getInstance();
    Gson json = new Gson();
    
    public Controller(){
        this.setPath("C:\\SafeWay_EDD");
    }
    
    public String BadReques(String data){
        this.request = Request.ERROR;
        
        return "{\"REQUEST\":"+ this.request +", \"DATA\": "+ data +"}";
    }
    
    public String GoodRequest(String data){
        this.request = Request.OK;
        
        return "{\"REQUEST\":"+ this.request +", \"DATA\": "+ data +"}";
    }
    
    public String getPath(){
        return this.path;
    }
    
    public void setPath(String path){
        File dir = new File(path);
        
        if(!dir.exists()){
            if(dir.mkdir()){
                System.out.println(" | > Se creo el directorio");
                this.path = path;
            } else {
                System.out.println(" | **Error: No se pudo crear el directorio: " + path);
            }
        } else {
            this.path = path;
        }
    }
    
    public void writeTxt(String name, String dot){
        txtPath = this.path+"\\"+name+".txt";
        
        FileOutputStream os;
        OutputStreamWriter sw;
        BufferedWriter bw;
        
        try {
            os = new FileOutputStream(txtPath);
            sw = new OutputStreamWriter(os, "utf-8");
            bw = new BufferedWriter(sw);
            
            bw.write(dot);
            
            bw.close();
            
        } catch (Exception e) {
            System.out.println(" | **Error: [01] "+e);
            txtPath = "";
        }
    }
    
    public void writePDF(String name){
        pdfPath = this.path + "\\" + name + ".pdf";
        if(!txtPath.isEmpty()){
            try {
                String dotPath = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";

                String tParam = "-Tpdf";
                String oParam = "";

                String[] cmd = new String[5];
                cmd[0] = dotPath;
                cmd[1] = "-Tpdf";
                cmd[2] = txtPath;
                cmd[3] = "-o";
                cmd[4] = pdfPath;

                Runtime rt = Runtime.getRuntime();
                rt.exec(cmd);
            } catch (Exception e) {
                System.out.println(" | **Error: [02] "+e);
                pdfPath = "";
            }
        } else {
            pdfPath = "";
        }
    }
    
    public void openPDF(){
        if(!pdfPath.isEmpty()){
            try {
//                String[] cmd = new String[4];
//                cmd[0] = "rundll32";
//                cmd[1] = "url.dll";
//                cmd[2] = "FileProtocolHandler";
//                cmd[3] = pdfPath;
//                
//                Runtime rt = Runtime.getRuntime();
//                rt.exec(cmd);
                
                File file = new File(pdfPath);
                Desktop.getDesktop().open(file);
            } catch (Exception e) {
                System.out.println(" | **Error:[03] ");
                e.printStackTrace();
            }
        }
    }
    
    public String getJson(File file){
        String j = "";
        
        FileInputStream is = null;
        InputStreamReader sr = null;
        BufferedReader br = null;

        try {
            is = new FileInputStream(file);
            sr = new InputStreamReader(is, "UTF-8");
            br = new BufferedReader(sr, 8);
            
            //Lectura del fichero
            String line;
            while((line = br.readLine()) != null)
                j += line;
            
            is.close();
            
        } catch (Exception e) {
            System.out.println(" | **Error [04]: "+e);
        }
        
        return j;
    }
}
