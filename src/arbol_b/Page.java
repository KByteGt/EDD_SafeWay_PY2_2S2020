/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol_b;

/**
 *
 * @author JOSED
 */
class Page {
    private final int grado;      //Número máximo de llaves(Keys)
    private Page padre;     //Puntero a la página padre
    private Key[] keys;     //Llaves de la página
    private int size;          //Número actual de llaves
    private boolean hoja;   //True = hoja, False != hoja
    
    //Constructor

    public Page(int grado) {
        this.grado = grado;
        this.keys = new Key[grado];
        this.padre = null;
        this.size = 0;
        this.hoja = true;
    }
    
    //Otros
    
    public void putKey(int i, Key key){
        if(existIndice(i)){
            this.keys[i] = key;
        } else {
            System.out.println(" **Error: No se puede insertar la llave en el indice <<" + i +">>, excede los limites del arreglo");
        }
    }
    
    public Key getKey(int i){
        if(existIndice(i)){
            return this.keys[i];
        } else {
            System.out.println(" **Error: indice excede el límite del arreglo");
            return null;
        }
    }
    
    public int insertar(Key key){
        int index = -1;
        for (int i = 0; i < this.grado; i++) {
            if(this.keys[i] == null){
                index = i;
                putKey(i,key);
                break;
            }
        }
        
        return index;
    }
    
    public boolean isNull(){
        //Comprobamos si no está vacio
        return keys[0] == null;
    }
    
    public boolean izquierdaIsNull(int i) {
        if (existIndice(i)) {
            return this.keys[i].getIzquierda() == null;
        } else {
            System.out.println(" **Error: indice excede el límite del arreglo");
            return true;
        }
    }
    
    public boolean derechaIsNull(int i){
        if (existIndice(i)) {
            return this.keys[i].getDerecha() == null;
        } else {
            System.out.println(" Error: indice excede el límite del arreglo");
            return true;
        }
    }
    
    //Validar indice
    
    public boolean existIndice(int i){
        return this.keys.length > i && i >= 0;
    }

    //Get & Set
    public int getGrado() {
        return grado;
    }

    public Page getPadre() {
        return padre;
    }

    public void setPadre(Page padre) {
        this.padre = padre;
    }

    public Key[] getKeys() {
        return keys;
    }

    public void setKeys(Key[] keys) {
        this.keys = keys;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isHoja() {
        return izquierdaIsNull(0);
    }

    public void setHoja(boolean hoja) {
        this.hoja = hoja;
    }  
}
