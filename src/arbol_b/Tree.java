/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol_b;

/**
 *
 * @author JOSED
 * @param <T>
 * @param <V>
 */
public class Tree<T extends Comparable<T>,V> {
    private final int grado;
    private Page raiz;
    
    //Constructor
    public Tree(int k){
        this.grado = k;
        this.raiz = new Page(k);
    }

    //Otros
    
    /*
     * Método de insertar
     */
    
    public void insertar(T llave, V valor){
        Key newKey = new Key(llave, valor);
        
        //Si el árbol está vacio
        if(isEmpty()){
            this.raiz.putKey(0, newKey);
            this.raiz.setHoja(true);
            
        } else {
            //El árbol no está vacio
            
            //Si la raiz está llena, el árbol crece en altura
            if(this.raiz.isFull()){
                //Dividir la raiz y generar dos nuevos sub-arboles
                Page tempPage = new Page(grado);
                tempPage.dividirPagina(this.raiz);
                //Cambiar la raiz
                this.raiz = tempPage;
                
                //Buscar en donde insertar la nueva Key
                if(this.raiz.getKey(0).compareTo(newKey.getKey()) > 0 ){
                    //Insertar en el sub-arbol izquierdo
                    this.raiz.getKey(0).getIzquierda().insertarNoLleno(newKey);
                } else {
                    //Insertar en el sub-arbol derecho
                    this.raiz.getKey(0).getDerecha().insertarNoLleno(newKey);
                }
            } else {
                //La raiz no está llena, insertar en la pagina
                //Llamar al método insertarNotFull
                this.raiz.insertarNoLleno(newKey);
            }
            
        }
    }
    
    /*
     * Método para eliminar
     */
    
    /*
     * Método para recorrer
     */
    
    public void recorrer(){
        if(!isEmpty()){
            this.raiz.recorrer();
        }
    }
    
    public Key buscar(T llave){
        Key k = new Key(llave, null);
        
        return (isEmpty()) ? null : this.raiz.buscar(k);
    }
    
    /*
     * Método para obtener el dot
     * @param String nombre
     * @return dot
     */
    
    public String getGraphviz(String nombre){
        String g = "";
        if(!isEmpty()){
            g += "digraph g{\nnode [shape = record, height = .1];\nlabel = \".: Árbol B - "+nombre+" :.\";";
            g += this.raiz.getGraphviz();
            g += "\n}";
        }
        return g;
    }
    
    //Get & Set
    
    public int getGrado() {
        return grado;
    }

    public Page getRaiz() {
        return raiz;
    }

    public void setRaiz(Page raiz) {
        this.raiz = raiz;
    }  

    private boolean isEmpty() {
        return this.raiz.isNull();
    }
    
}
