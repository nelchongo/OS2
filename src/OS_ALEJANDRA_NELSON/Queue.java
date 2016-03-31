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
public class Queue {
    
    //Cabeza y cola
    private Container head;
    private Container queue;
    
    //constructor
    public Queue(){
        head = null;
        
    }

    //setter and getter
    public Container getHead() {
        return head;
    }

    public Container getQueue() {
        return queue;
    }

    public void setHead(Container cabeza) {
        this.head = cabeza;
    }

    public void setQueue(Container cola) {
        this.queue = cola;
    }
    
    //******************metodos para el manejo de la cola************
    
    
    public Container FIFO(){
        if (head != null){
            Container temp  = head;
            head = head.getProx();
            temp.setProx(null);
            return temp;
        }else{
            return head;
        }
    }
    
        public void Add(Container nuevo){
        if (head == null){
            head = nuevo;
            queue = nuevo;
        }else{
            Container temp = queue;
            temp.setProx(nuevo);
            queue = nuevo;
            queue.setProx(null);
        }
    }
    
    public Container Remove(){
        Container cont;
        cont = FIFO();
        return cont;
    }
    
    public Pro Peek(){
        Container cont;
        Pro id;
        cont = getHead();
        id = cont.getProceso();
        return id;
    }
    
    public boolean isEmpty(){
	return (head==null);
    }
    
    public void ShowQ(){
        Container aux=head;
        while(aux!=null)
        {
            System.out.println("-->Proceso "+ aux.getProceso().getID());
            aux=aux.getProx();
        }
    }
    
    public void Search(int id){
        Container aux=head;
        while(aux!=null){

            if(head.getProceso().getID() == id){
                FIFO();
                
            }else{
                
                if(aux.getProx().getProceso().getID() == id){
                    aux.setProx(aux.getProx().getProx());
                    aux.getProx().setProx(null);
                }
                
                aux=aux.getProx();
            }
        }
    }
    
}
