/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication12;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author AleSaavedra
 */
public class JavaApplication12 {

    /**
     * @param args the command line arguments
     */
    
     //****Variables ****
    Scanner sc = new Scanner(System.in);
    private int[] principal;
    private int[] virtual;
    private int Creados = 0;
    private int existe = 0;
    private int Bloqueados[] = new int[30];
    private int ppale;
    private int virtuale;
    private int fe;
    private Cola listos = new Cola();
    private Cola sec = new Cola();
    private int r = 0;
    private int b = 0;
    private int procesos = 1;

    //**************DEFINICION DE LA DISTRIBUCION DE MEMORIA Y MENU**************************
    public void Definicion() {
        
//        System.out.println("RECORDAR: Favor introducir números que sean potencias de 2 (32,64,128,....)\n");
        System.out.println("Especifique tamaño de memoria principal (MB):\n");
        ppale = sc.nextInt();
        System.out.println("Especifique tamaño de memoria virtual (MB):\n");
        virtuale = sc.nextInt();
        System.out.println("Especifique tamaño de fragmentaciones (MB):\n");
        fe =  sc.nextInt();

//        ppale = (int) ppal;
//        virtuale = (int) virt;
//        fe = (int) f;   
        
        principal = new int[ppale / fe];
        virtual = new int[virtuale/ fe];
        
            for (int i = 0; i < principal.length; i++) {
               principal[i] = 000000;
            }
            for (int i = 0; i < virtual.length; i++) {
               virtual[i] = 000000;
            }
            for (int i = 0; i < Bloqueados.length; i++) {
               Bloqueados[i] = 000000;
            }
    }

    public void Menu() throws IOException {
        Mostrar();
        System.out.println("¡METRO OS!");
        System.out.println( 
                
                     " _________________________________ \n"
                    + "|                                  |\n"
                    + "|       M E N U-M E T R O O S      |\n"
                    + "|   ____________________________   |\n"
                    + "|  | 1- Crear proceso           |  |\n"
                    + "|  | 2- Bloquear proceso        |  |\n"
                    + "|  | 3- Desbloquear proceso     |  |\n" 
                    + "|  | 4- Eliminar proceso        |  |\n"
                    + "|  | 5- Suspender proceso       |  |\n"
                    + "|  | 6- Activar proceso         |  |\n"
                    + "|  | 7- Registro                |  |\n"
                    + "|  | 0- Salir                   |  |\n"
                    + "|  |____________________________|  |\n"
                    + "|                                  |\n"
                    + "|__________________________________|");

            System.out.print("Seleccione una opción:");
            int opcion = sc.nextInt();
       
            switch (opcion)
            {
                case 1: 
                CrearP();
                Menu();
                break;
                    
                case 2:
                Bloquear();
                Menu();
                break;
                      
                    
                case 3:
                Desbloquear();
                Menu();
                break;
                     
                
                case 4: 
                Eliminar();
                Menu();
                break;
                    
                    
                case 5:
                Suspender();
                Menu();
                break;
                      
                    
                case 6:
                ActivarP();
                Menu();
                break;   
                    
                case 7:
                Registro();
                Menu();
                break;
                        
                default:
                System.out.println("¡Hasta luego!");
                break; 
               
            }
    }
    
    //******MUESTRA LAS MEMORIAS*********************
    public void Mostrar() {
        int k = 0;
        System.out.println("_____________________________________");
        System.out.println("|MEMORIA PRINCIPAL|");
        System.out.println("_____________________________________");
        for (int i = 0; i < principal.length; i++) {
            System.out.print("~|" + principal[i] + "|");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("_____________________________________");
        System.out.println("|SUSPENDIDOS|");
        System.out.println("_____________________________________");
        System.out.println("********************************************");
        listos.mostrarC();
        System.out.println("********************************************");

        System.out.println("");
        System.out.println("_____________________________________");
        System.out.println("|MEMORIA VIRTUAL|");
        System.out.println("_____________________________________");
        for (int i = 0; i < virtual.length; i++) {

            System.out.print("~|" + virtual[i] + "|");
        }
        System.out.println("");
        System.out.println("");

        System.out.println("_____________________________________");
        System.out.println("|BLOQUEADOS|");
        System.out.println("_____________________________________");
//        for (int i = 0; i < Bloqueados.length; i++) {
//            System.out.print("[" + Bloqueados[i] + "]");
//        }
            for (int i = 0; i < Bloqueados.length; i++) {
                if (Bloqueados[i] != 0) {
                System.out.println("      *Proceso " + Bloqueados[i]);
                k++;
                }
            }
        System.out.println("");
        System.out.println("_______________________________________");
    }
    
    
    public void CrearP() {
        System.out.println("Se le asignará en orden creciente, un número al proceso!");
        int datos = procesos;
        System.out.println("RECORDAR: Favor introducir números que sean potencias de 2 (32,64,128,....))\n");
        System.out.println("Especifique tamaño del proceso: (MB) ");
        int tam = sc.nextInt();
//        int tame = (int) tam;
        Pro p = new Pro (tam, datos, fe);
        procesos++;
        Creados++;
        int k = 0;
        int z = p.getPag().length;
        RAM(p);
      
}

     ///***************REVISA SI EL PROCESO ESTA BLOQUEADO. SE USA PARA INSERTAR EN LISTOS********************
    public boolean Revision(int j) {
    boolean aux = false;
        for (int i = 0; i < Bloqueados.length; i++) {
            if (Bloqueados[i] == j) {
                aux = true;
            }
        }
        return aux;
    }
    
    //////****************INSERCION EN MEMORIA PRINCIPAL*************************
    public void RAM(Pro p) {
        int k = 0;
        int z = p.getPag().length;       //z es el numero de paginas que tiene el proceso
        

        for (int i = 0; i < principal.length; i++) {
            if (principal[i] == 000000) {
                k++;     //MP libres en RAM
            }
        }
        
        //VERIFICA QUE MAS DE LA MITAD DEL PROCESO QUEPA EN RAM
        if (!(k == z / 2 && z % 2 != 0)) {
            if (k >= z / 2) {           

                for (int i = 0; i < principal.length; i++) {
                    if (principal[i] == 0 && z > 0) {
                        principal[i] = p.getDatos();
                        z--;
                    }
                }
                if (z > 0) {
                    int a = z * fe;     //fe es el tamano de cada pagina
                    Pro j = new Pro(a, p.getDatos(), fe);
                    sec(z, j);
                    VIRTUAL(z, j);
                }
            } else {
                if (!Revision(p.getDatos())) {
                    
                    ColaReady(z, p);
                }
                VIRTUAL(z, p);
            }
        } else {
            if (!Revision(p.getDatos())) {
                ColaReady(z, p);
            }
            VIRTUAL(z, p);
        }
    }
    
       //**************************METODO PARA ELIMINAR PROCESOS************************************
    public void Eliminar() {
        boolean existe = false;
        System.out.println("Introduzca el número del proceso a eliminar");
        int n = sc.nextInt();
        r++;

        for (int i = 0; i < Bloqueados.length; i++) {
            if (Bloqueados[i] == n) {
                Bloqueados[i] = 0;
            }
        }

        for (int i = 0; i < principal.length; i++) {
            if (principal[i] == n) {
                existe = true;
                principal[i] = 0;
            }
        }
        for (int i = 0; i < virtual.length; i++) {
            if (virtual[i] == n) {
                virtual[i] = 0;
            }
        }
        if (existe == false) {
            listos.buscarCola(n);
        }
        ActSec();
    }

    public void VIRTUAL(int z, Pro p) {
        int k = 0;
        for (int i = 0; i < virtual.length; i++) {
            if (virtual[i] == 0) {
                k++;
            }
        }
        if (k >= z) {

            for (int i = 0; i < virtual.length; i++) {
                if (virtual[i] == 000000 && z > 0) {
                    virtual[i] = p.getDatos();
                    z--;

                }
            }

        } else {
            System.out.println("");
            System.out.println("**************NO HAY ESPACIO DISPONIBLE!!!!!!!!!!!!!!**************");
            System.out.println("");
        }
    }

    
    //////**************METODOS PARA INSERTAR EN LAS COLAS*******************
    public void sec(int z, Pro p) {
        int k = 0;
        for (int i = 0; i < virtual.length; i++) {
            if (virtual[i] == 000000) {
                k++;
            }
        }
        if (k >= z) {
            Nodo np = new Nodo(p);
            sec.add(np);
        }
    }

    public void ColaReady(int z, Pro p) {
        int k = 0;
        for (int i = 0; i < virtual.length; i++) {
            if (virtual[i] == 000000) {
                k++;
            }
        }
        if (k >= z) {
            Nodo np = new Nodo(p);
            listos.add(np);
        }
    }
    
     //**************************METODO PARA SUSPENDER PROCESOS************************************
    public void Suspender() {
        System.out.println("Introduzca el número del proceso a suspender");
        int p = sc.nextInt();
        int k = 0;
        int u = 0;
        int a = 0;

        for (int i = 0; i < Bloqueados.length; i++) {
            if (Bloqueados[i] == p) {
                a = 1;
            }
        }
        for (int i = 0; i < principal.length; i++) {
            if (principal[i] == p) {
                b = 2;
            }
        }
        if (a != 1 && b!=2) {
            //**************************VALIDACION DE BLOQUEO************************************
            System.out.println("EL PROCESO ESTA BLOQUEADO!!!!!!!!!");
            System.out.println("");
        } else {
            for (int i = 0; i < principal.length; i++) {
                if (principal[i] == p) {
                    principal[i] = 0;
                    k++;
                }
            }
            for (int i = 0; i < virtual.length; i++) {
                if (virtual[i] == p) {
                    u++;
                }
            }
            if (!Revision(p)) {
                listos.add(new Nodo(new Pro((u + k) * fe, p, fe)));
            }
            for (int i = 0; i < virtual.length; i++) {
                if (virtual[i] == 0 && k > 0) {
                    virtual[i] = p;
                    k--;
                }
            }
            ActSec();
        }
    }

    //**************************METODOS PARA ACTIVAR PROCESOS************************************
    public void ActivarP() {
        if (listos.getCabeza() == null) {
        } else {
            int z = listos.getCabeza().getProceso().getPag().length;
            int datos = listos.getCabeza().getProceso().getDatos();

            int k = 0;
            for (int i = 0; i < principal.length; i++) {
                if (principal[i] == 000000) {
                    k++;
                }
            }
            if (!(k == z / 2 && z % 2 != 0)) {
                if (k >= z / 2) {
                    for (int i = 0; i < virtual.length; i++) {
                        if (virtual[i] == datos) {
                            virtual[i] = 000000;
                            z--;
                        }
                    }
                    if (z > 0) {
                        int a = z * fe;
                        Pro j = new Pro(a, datos, fe);
                        sec(z, j);
                    }
                    RAM(listos.remove().getProceso());
                }
            }
        }
    }

    public void ActSec() {
        if (sec.getCabeza() == null) {
        } else {
            int z = sec.getCabeza().getProceso().getPag().length;
            int datos = sec.getCabeza().getProceso().getDatos();

            int k = 0;
            for (int i = 0; i < principal.length; i++) {
                if (principal[i] == 000000) {
                    k++;
                }
            }
            if (!(k == z / 2 && z % 2 != 0)) {
                if (k >= z / 2) {
                    for (int i = 0; i < virtual.length; i++) {
                        if (virtual[i] == datos) {
                            virtual[i] = 000000;
                        }
                    }
                    RAM(sec.remove().getProceso());
                }
            }
        }
    }
    
    
    //**************************METODOS PARA BLOQUEAR Y DESBLOQUEAR PROCESOS************************************

    public void Bloquear() {
        boolean aux = false;
        System.out.println("Indique numero del proceso a bloquear: ");
        int p = sc.nextInt();
        b++;
        int c = 0;
        int a = 0;
        for (int k = 0; k < principal.length; k++) {
            if (principal[k] == p) {
                a++;
            }
        }
        for (int k = 0; k < Bloqueados.length; k++) {
            if (Bloqueados[k] == p) {
                c++;
            }
        }
        if (a > 0 && c==0) {
            for (int j = 0; j < Bloqueados.length; j++) {
                if (Bloqueados[j] == 0 ) {
                    Bloqueados[j] = p;
                    break;
                }
            }

            for (int i = 0; i < principal.length; i++) {
                if (principal[i] == p) {
                    aux = true;
                }
            }
            if (aux != true) {
                listos.buscarCola(p);
            }
        }
    }

    public void Desbloquear() {
        boolean aux = false;
        System.out.println("Indique número del proceso a desbloquear: ");
        int datos = sc.nextInt();

        for (int i = 0; i < Bloqueados.length; i++) {
            if (Bloqueados[i] == datos) {
                Bloqueados[i] = 000000;
            }
        }
        for (int i = 0; i < principal.length; i++) {
            if (principal[i] == datos) {
                aux = true;
            }
        }

        if (aux != true) {
            int k = 0;
            for (int i = 0; i < virtual.length; i++) {
                if (virtual[i] == datos) {
                    k++;
                }
            }
            Nodo np = new Nodo(new Pro(fe * k, datos, k));
            listos.add(np);
        }
    }
    ////************PARA LOS REGISTROS******************
    public int MP() {
        int k = 0;
        for (int i = 0; i < principal.length; i++) {
            if (principal[i] != 000000) {
                k++;
            }
        }
        return k;
    }
    
    public int AvailableRAM() {
        int k;
        k = ppale - (fe * MP());
        return k;
    }

    public int AvailableS() {
        int k, z = 0;
        for (int i = 0; i < virtual.length; i++) {
            if (virtual[i] != 000000) {
                z++;
            }
        }
        k = virtuale - (fe * z);
        return k;
    }
    
    //**************************METODO PARA LLEVAR LOS REGISTROS 3***********************************
    public void Registro() throws IOException {
        int k = 0;
        System.out.println("+ Número de procesos que se han creados: " + Creados);
        System.out.println("+ Número de procesos que se han eliminado: " + r);
        System.out.println("+ Número de procesos que se han bloqueado: " + b);
        System.out.println("+ Marcos de página que han sido utilizados: " + MP());
        System.out.println("+ Marcos de página que siguen disponibles: " + (principal.length - MP()));
        System.out.println("+ Memoria Principal Disponible (Espacio): " + AvailableRAM() + " MB");
        System.out.println("+ Memoria Secundaria Disponible (Espacio): " + AvailableS() + " MB");
        System.out.println("+ Cantidad de procesos que se han eliminado: " + r);
        System.out.println("+ Cantidad de procesos que se han bloqueado: " + b);
        Menu();
    }

    

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        //System.out.println("Epaleeeeee");
        JavaApplication12 app = new JavaApplication12();
        app.Definicion();
        app.Menu();
    }
    
}
