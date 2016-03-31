/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OS_ALEJANDRA_NELSON;

/**
 *
 * @author NELSON CANDIA Y ALEJANDRA SAAVEDRA
 */
public class Container {
   private Pro proceso;
   private Container prox;
    
    //constructor por defecto
    public Container(Pro proceso) {
        this.proceso = proceso;
    }

    //setter and getter
    public Container getProx() {
        return prox;
    }

    public void setProx(Container proximo) {
        this.prox = proximo;
    }

    public Pro getProceso() {
        return proceso;
    }

    public void setProceso(Pro proceso) {
        this.proceso = proceso;
    }  
}
