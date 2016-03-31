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
public class Cola {
        
    private Nodo cabeza;
    private Nodo cola;
    
    public Cola()
    {
        cabeza=null;
        
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public Nodo getCola() {
        return cola;
    }

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }

    public void setCola(Nodo cola) {
        this.cola = cola;
    }
    
    
    
    public void add(Nodo nuevo)
	{
		if (cabeza == null)
		{
			cabeza = nuevo;
			cola = nuevo;
		}
		else
		{
			Nodo temp = cola;
			temp.setProx(nuevo);
			cola = nuevo;
			cola.setProx(null);
		}
	}
    
    public Nodo remove()
	{
		Nodo nodo;
		nodo = EliminarPrimero();
		return nodo;
	}
    
    public Nodo EliminarPrimero()
	{
		if (cabeza != null)
		{
			Nodo temp  = cabeza;
			cabeza = cabeza.getProx();
			temp.setProx(null);
			return temp;
		}
		else
		{
			return cabeza;
		}
	}
    
    public Pro PrimeroCola()
	{
		Nodo nodo;
		Pro dato;
		nodo = getCabeza();
		dato = nodo.getProceso();
		return dato;
	}
    
    public boolean isEmpty()
	{
		return (cabeza==null);
	}
    
    public void mostrarC()
    {
        Nodo aux=cabeza;
        while(aux!=null)
        {
            System.out.println("[Proceso: "+aux.getProceso().getDatos()+"]");
            aux=aux.getProx();
        }
    }
    
    public void buscarCola(int datos){
        Nodo aux=cabeza;
        while(aux!=null)
        {
            
            if(cabeza.getProceso().getDatos() == datos)
            {
                EliminarPrimero();
            }
            else
                if(aux.getProx().getProceso().getDatos() == datos){
                    aux.setProx(aux.getProx().getProx());
                    aux.getProx().setProx(null);

                }
                aux=aux.getProx();
        }
    }
}
