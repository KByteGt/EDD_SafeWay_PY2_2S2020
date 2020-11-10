/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_safeway;

import arbol_b.Key;
import arbol_b.Tree;

/**
 *
 * @author JOSED
 */
public class InvoiceController extends Controller{
    
    private Tree<Integer,Invoice> facturas;
    private int indexInvoice;
    
    private static InvoiceController invoiceController;
    
    private InvoiceController(){
        facturas = new Tree(5);
        indexInvoice = 0;
    }
    
    public static InvoiceController getInstance(){
        if(invoiceController == null){
            invoiceController = new InvoiceController();
        }
        
        return invoiceController;
    }
    
    public void addInvoice(int user, int driver, int travel, String date, double total){
        Invoice newInvoice = new Invoice(indexInvoice, user, driver, travel, date, total);
        
        facturas.insertar(indexInvoice, newInvoice, "Q"+total);
        indexInvoice++;
    }
    
    public Invoice searchInvoice(int id){
        
        Key key = facturas.buscar(id);
        if( key != null){
            return (Invoice) key.getValor();
        } else {
            return null;
        }
       
    }
    
}
