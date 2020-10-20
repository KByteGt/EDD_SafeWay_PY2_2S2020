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
public class Tree<T extends Comparable,V> {
    private final int grado;
    private Page raiz;
    
    //Constructor
    public Tree(int k){
        this.grado = k;
        this.raiz = new Page(k);
    }

    //Otros
    
    public void insertar(T llave, V valor){
        Key tempKey = new Key(llave, valor);
        
        if (this.raiz.isNull()) {
            //Insertar al inicio
            this.raiz.putKey(0, tempKey);
        } else if(this.raiz.isHoja()){
            //Insertar en la hoja
            Page temp = this.raiz;
            int indice = temp.insertar(tempKey);
            
            //Ordenar
            if(indice != -1){
                if(indice == grado -1){
                    //Dividir la pagina en dos
                    
                    
                    
                    int mitad = grado / 2;
                    Key middleKey = temp.getKey(mitad); //Indice central de la pagina
                    
                    //Crar nuevas paginas
                    Page derecha = new Page(grado);
                    Page izquierda = new Page(grado);
                    
                    //int leftIndex = 0;
                    //int rightIndex = 0;
                    
                    for (int i = 0; i < grado; i++) {
                        if(temp.getKey(i).compareTo(middleKey.getKey()) < 0){
                            //Insertar llaves menores en la pagina izquierda
                            izquierda.insertar(temp.getKey(i));
                            //izquierda.putKey(leftIndex, temp.getKey(i));
                            //leftIndex++;
                            temp.putKey(i, null);
                            
                        } else if(temp.getKey(i).compareTo(middleKey.getKey()) > 0){
                            //Insertar llaves mayores en la página derecha
                            derecha.insertar(temp.getKey(i));
                            //rightIndex++;
                            temp.putKey(i, null);
                        }
                    }
                }
            }
        }
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
    
    
}
