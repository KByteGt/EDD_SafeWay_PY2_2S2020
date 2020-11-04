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
//        this.hoja = true;
    }
    
    //Otros
    
    
    /*
     * Insertar
     * @return posición insertada
     */
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
    
    public void insertarNoLleno(Key key){
        //Inicializar el indice con el elemento más a la derecha
        int index = size-1;
        
        //Si la pagina es una pagina hoja
        if(this.isHoja()){
            //Encontrar una posición para la key
            while(index >= 0 && keys[index].compareTo(key.getKey()) > 0){
                keys[index + 1] = keys[index];
                index--;
            }
            
            //Insertar la nueva Key en la ubicación encontrada
            keys[index + 1] = keys[index];
            size++;
        } else {
            //Si la pagina no es una pagina hoja
            
            //Encontrar el hijo que almacenara la nueva key
            while(index >= 0 && keys[index].compareTo(key.getKey()) > 0)
                index--;
            
            //Verificar si el hijo encontrado está lleno
            if(keys[index].getDerecha().isFull()){
                //El hijo está lleno
                
                Page tempPage = new Page(grado);
                tempPage.dividirPagina(keys[index].getDerecha());
                
            }
            keys[index].getDerecha().insertarNoLleno(key);
        }
        
        
//        //
//        int index = -1;
//        int i = size-1;
//        
//        //Si la pagina es una pagina hoja
//
//        //Encontrar una posición paral la key
//        while(i >= 0 && keys[i].compareTo(key.getKey()) > 0){
//            keys[i + 1] = keys[i];
//            i--;
//        }
//
//        //Insertar la nueva Key en la ubicación encontrada
//        index = i +1;
//        keys[index] = keys[i];
//        size++;
//        
//        return index;
    }
        
        
    public void dividirPagina(Page page){
     //Dividir la pagina en dos
        Page newPage = new Page(grado);
        
        int middleIndex = grado / 2;
        Key middleKey = page.getKey(middleIndex); //Indice central de la pagina

        //Crar nuevas paginas
        Page rigth = new Page(grado);
        Page left = new Page(grado);

        //Llenar paginas
        for (int i = 0; i < grado; i++) {
            if(page.getKey(i).compareTo(middleKey.getKey()) < 0){
                //Insertar llaves menores en la pagina izquierda
                left.insertar(page.getKey(i));
                page.putKey(i, null);

            } else if(page.getKey(i).compareTo(middleKey.getKey()) > 0){
                //Insertar llaves mayores en la página derecha
                rigth.insertar(page.getKey(i));
                page.putKey(i, null);
            }
        }

        //Insertar la Key central
        this.putKey(0, middleKey);
        this.setHoja(false);

        //Apuntar enlaces de hijos a padre
        left.setPadre(this);
        left.setHoja(true);
        rigth.setPadre(this);
        rigth.setHoja(true);

        //Apuntar enlaces de padre a hijos
        middleKey.setIzquierda(left);
        middleKey.setDerecha(rigth);

    }
    
    
    public void putKey(int i, Key key){
        if(existIndice(i)){
            this.keys[i] = key;
            this.size++;
        } else {
            System.out.println(" **Error: No se puede insertar la llave en el indice <<" + i +">>, excede los limites del arreglo");
        }
    }
    
    /*
     * 
     */
    
    public Key getKey(int i){
        if(existIndice(i)){
            return this.keys[i];
        } else {
            System.out.println(" **Error: indice excede el límite del arreglo");
            return null;
        }
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

    public boolean isNull(){
        //Comprobamos si no está vacio
        return keys[0] == null;
    }
    
    public boolean isFull(){
        return this.size >= 4;
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
//        return izquierdaIsNull(0);
        return this.hoja;
    }

    public void setHoja(boolean hoja) {
        this.hoja = hoja;
    }  
}
