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
//                this.raiz = dividirPagina(raiz);
                
                //Buscar en donde insertar la neva Key
                if(this.raiz.getKey(0).compareTo(newKey.getKey()) < 0 ){
                    //Insertar en el sub-arbol izquierdo
                    this.raiz.getKey(0).getIzquierda().insertarNoLleno(newKey);
                } else {
                    //Insertar en el sub-arbol derecho
                    this.raiz.getKey(0).getDerecha().insertar(newKey);
                }
                
                //Cambiar la raiz
                this.raiz = tempPage;
            } else {
                //La raiz no está llena, insertar en la pagina
                //Llamar al método insertarNotFull
                this.raiz.insertarNoLleno(newKey);
            }
            
        }
//        Key tempKey = new Key(llave, valor);
//        
//        if (this.raiz.isNull()) {
//            //Insertar al inicio
//            this.raiz.putKey(0, tempKey);
//        } else if(this.raiz.isHoja()){
//            //Insertar en la hoja
//            Page temp = this.raiz;
//           
//            //Insertar la nueva llave
//            int indice = temp.insertar(tempKey);  //-1 = no se inserto
//
//
//            //Ordenar
//            if(indice != -1){
//                if(indice == grado -1){
//                    //Dividir la pagina en dos
//
//                    int mitad = grado / 2;
//                    Key middleKey = temp.getKey(mitad); //Indice central de la pagina
//
//                    //Crar nuevas paginas
//                    Page derecha = new Page(grado);
//                    Page izquierda = new Page(grado);
//
//                    //int leftIndex = 0;
//                    //int rightIndex = 0;
//
//                    for (int i = 0; i < grado; i++) {
//                        if(temp.getKey(i).compareTo(middleKey.getKey()) < 0){
//                            //Insertar llaves menores en la pagina izquierda
//                            izquierda.insertar(temp.getKey(i));
//                            //izquierda.putKey(leftIndex, temp.getKey(i));
//                            //leftIndex++;
//                            temp.putKey(i, null);
//
//                        } else if(temp.getKey(i).compareTo(middleKey.getKey()) > 0){
//                            //Insertar llaves mayores en la página derecha
//                            derecha.insertar(temp.getKey(i));
//                            //rightIndex++;
//                            temp.putKey(i, null);
//                        }
//                    }
//
//                    //Key central
//                    temp.putKey(mitad, null);
//
////                    this.raiz = temp;
////                    this.raiz.putKey(0, middleKey);
////                    izquierda.setPadre(this.raiz);
////                    derecha.setPadre(this.raiz);
//
//                    temp.putKey(0, middleKey);
//
//                    //Apuntar enlaces de hijos a padre
//                    izquierda.setPadre(temp);
//                    derecha.setPadre(temp);
//
//                    //Apuntar enlaces de padre a hijos
//                    middleKey.setIzquierda(izquierda);
//                    middleKey.setDerecha(derecha);
//
//                } else {
//                    //Aún no se ha llenado la pagina
//                }
//            } else {
//                //No se inserto
//            }
//            ///////// REFACTOR ......................................................................
//        } else if(!this.raiz.isHoja()){
//            //No es un nodo hoja, por lo tanto tiene ramas hijas
//            Page temp = this.raiz;
//            
//            while(!temp.isHoja()){
//                int loop = 0;
//                for (int i = 0; i < grado; i++) {
//                    if(temp.getKey(i) != null){
//                        if(temp.getKey(i).compareTo(tempKey.getKey()) > 0){
//                            temp = temp.getKey(i).getIzquierda();
//                            break;
//                        }
//                    } else {
//                        temp = temp.getKey(i - 1).getDerecha();
//                        break;
//                    }
//                }
//                
//                if(loop == grado){
//                    temp = temp.getKey(loop - 1).getDerecha();
//                }
//            }
//            
//            int indicecolocado = colocarNodo(temp, tempKey);
//            
//            if(indicecolocado == grado-1){
//                while(temp.getPadre() != null){
//                    int indicemedio = grado/2;
//                    Key llavecentral = temp.getKey(indicemedio);
//                    
//                    Page izquierda = new Page(grado);
//                    Page derecha = new Page(grado);
//                    
//                    int indiceizquierda = 0, indicederecho = 0;
//                    
//                    for (int i = 0; i < grado; i++) {
//                        if(temp.getKey(i).compareTo(llavecentral.getKey()) < 0){
//                            izquierda.putKey(indiceizquierda, temp.getKey(i));
//                            indiceizquierda++;
//                            temp.putKey(i, null);
//                        } else if(temp.getKey(i).compareTo(llavecentral.getKey()) > 0){
//                            derecha.putKey(indicederecho, temp.getKey(i));
//                            indicederecho++;
//                            temp.putKey(i, null);
//                        }
//                    }
//                    
//                    temp.putKey(indicemedio, null);
//                    llavecentral.setIzquierda(izquierda);
//                    llavecentral.setDerecha(derecha);
//                    
//                    //Obtener el padre y agregarlo en el padre
//                    
//                    temp = temp.getPadre();
//                    izquierda.setPadre(temp);
//                    derecha.setPadre(temp);
//                    
//                    for (int i = 0; i < grado; i++) {
//                        if(izquierda.getKey(i) != null){
//                            if(izquierda.getKey(i).getIzquierda() != null){
//                                izquierda.getKey(i).getIzquierda().setPadre(izquierda);
//                            }
//                            
//                            if(izquierda.getKey(i).getDerecha() != null){
//                                izquierda.getKey(i).getDerecha().setPadre(izquierda);
//                            }
//                        }
//                    }
//                    for (int i = 0; i < grado; i++) {
//                        if(derecha.getKey(i) != null){
//                            if(derecha.getKey(i).getIzquierda() != null){
//                                derecha.getKey(i).getIzquierda().setPadre(derecha);
//                            }
//                            
//                            if(derecha.getKey(i).getDerecha() != null){
//                                derecha.getKey(i).getDerecha().setPadre(derecha);
//                            }
//                        }
//                    }
//                    
//                    //Colocar el nodo
//                    //Y evaluar el padre
//                    
//                    int lugarcoloado = colocarNodo(temp, llavecentral);
//                    
//                    if(lugarcoloado == grado-1){
//                        if(temp.getPadre() == null){
//                            int indicecentralraiz = grado/2;
//                            
//                            Key llavecentralraiz = temp.getKey(indicecentralraiz);
//                            Page izquierdaraiz = new Page(grado);
//                            Page derecharaiz = new Page(grado);
//                            
//                            int indicederechoraiz = 0, indiceizquierdoraiz = 0;
//                            
//                            for (int i = 0; i < grado; i++) {
//                                if(temp.getKey(i).compareTo(llavecentralraiz.getKey()) < 0){
//                                    izquierdaraiz.putKey(indiceizquierdoraiz, temp.getKey(i));
//                                    indiceizquierdoraiz++;
//                                    temp.putKey(i, null);
//                                }else if(temp.getKey(i).compareTo(llavecentralraiz.getKey()) > 0){
//                                    derecharaiz.putKey(indicederechoraiz, temp.getKey(i));
//                                    indicederechoraiz++;
//                                    temp.putKey(i, null);
//                                }
//                            }
//                            
//                            temp.putKey(indicecentralraiz, null);
//                            temp.putKey(0,llavecentralraiz);
//                            
//                            for (int i = 0; i < grado; i++) {
//                                if(izquierdaraiz.getKey(i) != null){
//                                    izquierdaraiz.getKey(i).getIzquierda().setPadre(izquierdaraiz);
//                                    izquierdaraiz.getKey(i).getDerecha().setPadre(izquierdaraiz);
//                                } 
//                            }
//                            
//                            for (int i = 0; i < grado; i++) {
//                                if(derecharaiz.getKey(i) != null){
//                                    derecharaiz.getKey(i).getIzquierda().setPadre(derecharaiz);
//                                    derecharaiz.getKey(i).getDerecha().setPadre(derecharaiz);
//                                } 
//                            }
//                            
//                            llavecentralraiz.setIzquierda(izquierdaraiz);
//                            llavecentralraiz.setDerecha(derecharaiz);
//                            
//                            izquierdaraiz.setPadre(temp);
//                            derecharaiz.setPadre(temp);
//                            
//                            this.raiz = temp;
//                        }
//                        
//                        continue;
//                    } else {
//                        break;
//                    }
//                }
//            }
//        }
    }
    
    private int colocarNodo(Page nodo, Key newKey) {
        int index = -1;
        for (int i = 0; i < grado; i++) {
            if(nodo.getKey(i) == null){
                boolean placed = false;
                for (int j = i-1; j >= 0; j--) {
                    if(nodo.getKey(j).compareTo(newKey.getKey()) > 0){
                        nodo.putKey(j+1, nodo.getKey(j));
                    } else {
                        nodo.putKey(j+1, newKey);
                        nodo.getKey(j).setDerecha(newKey.getIzquierda());
                        if(j+2 < grado && nodo.getKey(j+2) != null){
                            nodo.getKey(j+2).setIzquierda(newKey.getDerecha());
                        }
                        
                        placed = true;
                        break;
                    }
                }
                if(placed == false){
                  nodo.putKey(0, newKey);
                  nodo.getKey(1).setIzquierda(newKey.getDerecha());
                }
                
                index = i;
                break;
            }
        }
        return index;
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
