/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabla_hash;

import edd_safeway.Lugar;

/**
 *
 * @author JOSED
 */
public class TablaHash {
    
    private Key[] tabla;
    private int size; //Tamaño de la tabla hash
    private int carga; //Tamaño de elementos en la tabla
    
    public TablaHash(int m){
        this.size = m;
        this.carga = 0;
        this.tabla = new Key[m];
        
        llenarTabla();
    }
    
    private void llenarTabla(){
        for (int i = 0; i < size; i++) {
            tabla[i] = null;
        }
    }
    
    private int getPosicion(int k){
        //Método de División H(n) = n % M
        int iteracion = 0, hash = 0;
        
        hash = (int) k % size;
        
        //Ver colisión
        while(tabla[hash] != null && tabla[hash].getHash() != k){
            iteracion++;
            hash = (int) (hash + (iteracion * iteracion)) % size;
        }
       
        return hash;
    }
    
    private boolean needResize(){
        //Se analiza el factor de carga menor al 75%
        return (carga * 100 / size) > 75;
    }
    
    private int getASCII(String g){
        int ascii = 0;
        
        for (int i = 0; i < g.length(); i++) {
            ascii += (int) g.charAt(i);
        }
        
        return ascii;
    }
    
    public boolean insertar(int id, Lugar l){
        Key node = new Key(id, l);
        
        int pos = getPosicion(id);
        
        if(this.tabla[pos] == null){
            tabla[pos] = node;
            carga++;
            
             //Factor de carga
            if(needResize()){
                double newSize = size * 2.7;
                //Validar si es primo
                newSize = ((newSize % 2) == 0 )? newSize +1 : newSize;
                //Redimensionar tabla
                resize((int) newSize);
            }
            
            return  true;
            
        } else {
            return false;
        }
    }
    
    private void resize(int m){
        Key newTabla[] = new Key[m];
        Key tempTabla[] = this.tabla;
        this.tabla = newTabla;
        this.size = m;
        this.carga = 0;
        
        llenarTabla();
        

        int i;
        for(Key n : tempTabla){
            if(n != null && !n.isDelited()){
                this.insertar(n.getHash(), (Lugar)n.getValor());
//                i = getPosicion(n.getHash());
//                newTabla[i] = n;
//                carga++;
            }
        }
        
        
    }
    
    public boolean eliminar(int id){
        
        Key k = buscar(id);
        
        if(k != null){
            k.setDelited();
            carga--;
            return true;
        } else {
            return false;
        }
    }
    
    public Key buscar(int id){
        
        
        int pos = getPosicion(id);
        
        if(this.tabla[pos] != null){
            //Tenemos el dato buscado
            Lugar n = (Lugar) tabla[pos].getValor();
            
            System.out.println("["+ pos +"] - ("+tabla[pos].getHash()+") "+n.getNombre());
            
            return tabla[pos];
        } else {
            System.out.println(" >> No existe el elemento: "+pos);
            return null;
        }
    }
    
    public void imprimir(){
        for (Key n : this.tabla) {
            if(n != null && !n.isDelited()){
                Lugar l = (Lugar) n.getValor();
                System.out.println("("+n.getHash()+") "+l.getNombre()+" id: "+l.getId());
            }
        }
    }
    
    public String getGraphviz(String name){
        String g = "";
        g += "digraph g{\n" +
                "node [shape = record, height = .1];\n" +
                "label = \".: Tabla Hash - "+ name +" :.\\nTamaño: "+ size +", Elementos: "+ carga +"\";\n";
        
        int bloques = (int) size / 5;
        String temp = "";
        
        for (int i = 0; i <= bloques; i++) {
            temp = "node"+i+" [label=\"";

            int j = 0;
            if(((i*5)+j) < size){
                if(tabla[(i*5)+j] != null){
                    if(tabla[(i*5)+j].isDelited()){
                        temp += " [ * ]";
                    } else {
                        Lugar l = (Lugar)tabla[(i*5)+j].getValor();
                        temp += "["+tabla[(i*5)+j].getHash()+"] "+l.getNombre();
                    }
                } else {
                    temp += " [ - ]";
                }
            }
            
            for (j = 1; j < 5; j++) {
                if(((i*5)+j) < size){
                    if(tabla[(i*5)+j] != null){
                        if(tabla[(i*5)+j].isDelited()){
                            temp += "| [ * ] ";
                        } else {
                            Lugar l = (Lugar)tabla[(i*5)+j].getValor();
                            temp += "| ["+tabla[(i*5)+j].getHash()+"] "+l.getNombre();
                        }
                    } else {
                        temp += "| [ - ] ";
                    }
                }
            }

            g += temp + "\"];\n";
            temp = "";
        }
        
        for (int i = 0; i < bloques; i++) {
            g += "node"+i+" -> node"+(i+1)+";\n";
        }
        
        g += "}";
        return g;
    }
    
}
