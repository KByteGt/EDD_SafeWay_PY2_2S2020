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
        
        //Si la pagina es una pagina hoja
        if(this.isHoja()){
            //Encontrar una posición para la key
            this.insetarKeyPadre(key);

        } else {
            //Inicializar el indice con el elemento más a la derecha
            int index = size-1;
            
            //Si la pagina no es una pagina hoja
            
            //Encontrar el hijo que almacenara la nueva key
            while(index >= 0 && keys[index].compareTo(key.getKey()) > 0){
                index--;
            }
            
            //Verificar si el hijo encontrado está lleno
            if(index == -1){
                //Verificar pagina izquierda del primer elemento
                if(keys[0].getIzquierda().isFull()){
                    //El hijo está lleno
                    //Dividir hijo
                    Page tempPage = new Page(grado);
                    tempPage.dividirPagina(keys[0].getIzquierda());

                    this.insetarKeyPadre(tempPage.getKey(0));
                  
                }
                //Buscar en donde insertar la nueva Key
                if(this.getKey(0).compareTo(key.getKey()) < 0 ){
                    //Insertar en el sub-arbol derecho
                    keys[0].getDerecha().insertarNoLleno(key);
//                     keys[0].getIzquierda().insertarNoLleno(key);
                } else {
                     //Insertar en el sub-arbol izquierdo
                    keys[0].getIzquierda().insertarNoLleno(key);
                }

            } else {
                //Verificar pagina derecha
                if(keys[index].getDerecha().isFull()){
                    //El hijo está lleno
                    //Dividir hijo
                    Page tempPage = new Page(grado);
                    tempPage.dividirPagina(keys[index].getDerecha());

                    this.insetarKeyPadre(tempPage.getKey(0));
                }
                
                int i = size -1;
                while(i >= 0 && keys[i].compareTo(key.getKey()) > 0){
                    i--;
                }

                if(keys[i].compareTo(key.getKey()) < 0 ){
                    //Insertar en el sub-arbol derecho
                    this.getKey(i).getDerecha().insertarNoLleno(key);
                } else {
                     //Insertar en el sub-arbol izquierdo
                    keys[i].getIzquierda().insertarNoLleno(key);
//                     keys[i].getDerecha().insertarNoLleno(key);
                }
            }
        }
    }
        
    private void insetarKeyPadre(Key key){
        int index = size - 1;
        
        //Encontrar una posición para la key
        while(index >= 0 && keys[index].compareTo(key.getKey()) > 0){
            keys[index + 1] = keys[index];
            index--;
        }

        //Insertar la nueva Key en la ubicación encontrada
        keys[index + 1] = key;
        
        //Re-organizar punteros
        if(index + 1 == 0){
            //Se inserto al inicio
            keys[index +2].setIzquierda(key.getDerecha());
        } else if(index + 1 == size){
            //Se inserto al final
            keys[index].setDerecha(key.getIzquierda());
        } else {
            keys[index].setDerecha(key.getIzquierda());
            keys[index +2].setIzquierda(key.getDerecha());
        }  
        
        size++;
    }
    
    public void dividirPagina(Page page){
        //Dividir la pagina en dos
        
        int middleIndex = grado / 2;
        Key middleKey = page.getKey(middleIndex); //Indice central de la pagina

        //Crear nuevas paginas
        Page rigth = new Page(grado);
        Page left = new Page(grado);

        //Llenar paginas
        for (int i = 0; i < grado -1; i++) {
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

        //Apuntar enlaces de hijos a padre
        left.setPadre(this);
        left.setHoja(true);
        rigth.setPadre(this);
        rigth.setHoja(true);
        
        
        //Validar paginas hijas
        if(left.keys[0] != null ){
            if(left.keys[0].getIzquierda() != null){
                left.setHoja(false);
            }
        }
        
        if(rigth.keys[0] != null ){
            if(rigth.keys[0].getIzquierda() != null){
                rigth.setHoja(false);
            }
        }

        //Apuntar enlaces de padre a hijos
        middleKey.setIzquierda(left);
        middleKey.setDerecha(rigth);
        
        //Insertar la Key central
        this.putKey(0, middleKey);
        this.setHoja(false);

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
    
    /*
     * Método para recorrer
     */
    
    void recorrer() {
        int i;
        
        for(i = 0; i < size; i++){
            if(!isHoja()){
                keys[i].getIzquierda().recorrer();
            }
            
            keys[i].imprimir();
        }
        
        if(!isHoja()){
            keys[size - 1].getDerecha().recorrer();
        }
    }
    
    public Key buscar(Key k){
        
        int i = 0;
        
        while(i < size && keys[i].compareTo(k.getKey()) < 0){
            i++;
        }
        
        if(i != size){
            if(keys[i].compareTo(k.getKey()) == 0){
                return keys[i];
            }
        } else {
            if(keys[i-1].compareTo(k.getKey()) == 0){
                return keys[i-1];
            }
        }
        
        
        if(isHoja()){
            return null;
        } else {
            if(i == size){
                return keys[i-1].getDerecha().buscar(k);
            } else {
                return keys[i].getIzquierda().buscar(k);
            }
        }
        
        
    }
    
    /*
     * Método para obtener el dot
     * @return dot
     */
    
    public String getGraphviz(){
        String g = "";
        int i;
        int j;
        
        g += "\nnode"+keys[0].hashCode()+" [label=\"";
        for (j = 0; j < this.size; j++) {
            g += "<f"+j+"> |["+this.keys[j].getStringKey()+"]|";
        }
        g += "<f"+j+">\"];\n";
        
        for(i = 0; i < this.size; i++){
            if(!this.isHoja()){
                g += "\n\"node"+keys[0].hashCode()+"\": f"+i+" -> \"node"+keys[i].getIzquierda().getKey(0).hashCode()+"\"";
                g += keys[i].getIzquierda().getGraphviz();
            }
        }
        if(!isHoja()){
            g += "\n\"node"+keys[0].hashCode()+"\": f"+i+" -> \"node"+keys[i-1].getDerecha().getKey(0).hashCode()+"\"";
            g += keys[i-1].getDerecha().getGraphviz();
        }
        
        return g;
    }
}
