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
public class Pro {
       
   //****ATRIBUTOS****
    private Page pag[];
    private int tam;
    private int id;
    private boolean bloq;
    private boolean susp;
    private boolean act;
    
    
    //****CONSTRUCTORES****
    public Pro (int tam, int id, int paginas) {
        this.tam = tam;
        this.id = id;
        bloq=false;
        susp= false;
        act=false;
        
        
        /////*****DETERMINA PAGINAS*****
        if((tam % paginas) == 0){
            pag = new Page[tam/paginas];
        }
        else{
            int contar = tam/paginas;
            contar++;
            pag = new Page[contar];
        }
        
        ////******CREA NUEVA PAGINA*******
            for (int i = 0; i < pag.length; i++){
                Page p= new Page(id);
            }
    }
    
    
    //****SETTERS - GETTERS****

    public Page[] getPag() {
        return pag;
    }

    public void setPag(Page[] pag) {
        this.pag = pag;
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public boolean isBloq() {
        return bloq;
    }

    public void setBloq(boolean bloq) {
        this.bloq = bloq;
    }

    public boolean isSusp() {
        return susp;
    }

    public void setSusp(boolean susp) {
        this.susp = susp;
    }

    public boolean isAct() {
        return act;
    }

    public void setAct(boolean act) {
        this.act = act;
    }
 
}
