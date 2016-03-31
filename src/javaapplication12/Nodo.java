/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication12;

/**
 *
 * @author AleSaavedra
 */
public class Nodo {
   private Pro proceso;
    private Nodo prox;

    public Nodo(Pro proceso) {
        this.proceso = proceso;
    }

   
    public Nodo getProx() {
        return prox;
    }


    public void setProx(Nodo proximo) {
        this.prox = proximo;
    }

    public Pro getProceso() {
        return proceso;
    }

    public void setProceso(Pro proceso) {
        this.proceso = proceso;
    }  
}
