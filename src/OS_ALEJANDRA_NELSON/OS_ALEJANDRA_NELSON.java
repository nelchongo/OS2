/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OS_ALEJANDRA_NELSON;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author NELSON CANDIA Y ALEJANDRA SAAVEDRA
 */
public class OS_ALEJANDRA_NELSON {

    /**
     * @param args the command line arguments
     */
    
    //****Variables ****
    Scanner sc = new Scanner(System.in);
    private int Creados = 0;
    private int existe = 0;
    private int ppale;
    private int virtuale;
    private int fe;
    private Queue listos = new Queue();
    private Queue sec = new Queue();
    private int r = 0;
    private int b = 0;
    private int procesos = 1;
    
    //memorias
    private int[] principal;
    private int[] virtual;
    
    //lista de bloqueados
    private int Bloqueados[] = new int[30];
    
    public static void main(String[] args) throws IOException {
        OS_ALEJANDRA_NELSON app = new OS_ALEJANDRA_NELSON();
        app.Define();
        app.Menu();
    }

    //**************DEFINICION DE LA DISTRIBUCION DE MEMORIA Y MENU**************************
    public void Menu() throws IOException {
        Mostrar();
        //System.out.println("¡METRO OS!");
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
                    + "|__________________________________|"
        );

        System.out.print("Seleccione una opción:");
        int opcion = sc.nextInt();
       
        switch (opcion){
            case 1: 
            Create();
            Menu();
            break;

            case 2:
            Block();
            Menu();
            break;


            case 3:
            Unblock();
            Menu();
            break;


            case 4: 
            Delete();
            Menu();
            break;


            case 5:
            Pause();
            Menu();
            break;


            case 6:
            ProActive();
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
    
    public void Define() {
        
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
        
        op();
    }
    
    public void op(){
       int result1, result2;
       //System.out.println(ppale / fe);
       //System.out.println(virtuale/ fe);
       result1 = ppale / fe;
       result2= virtuale/ fe;
               
       principal = new int[result1];
       virtual = new int[result2];
        
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
    
    //******MUESTRA LAS MEMORIAS*********************
    public void Mostrar() {
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
        listos.ShowQ();

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

        for (int i = 0; i < Bloqueados.length; i++) {
            if (Bloqueados[i] != 0) {
            System.out.println("-->Proceso " + Bloqueados[i]);
            }
        }
        
        System.out.println("");
        System.out.println("_______________________________________");
    }
    
    
    public void Create() {
        System.out.println("Se le asignará en orden creciente, un número al proceso!");
        int id = procesos;
        System.out.println("RECORDAR: Favor introducir números que sean potencias de 2 (32,64,128,....))\n");
        System.out.println("Especifique tamaño del proceso: (MB) ");
        int tam = sc.nextInt();
        //int tame = (int) tam;
        Pro process = new Pro (tam, id, fe);
        procesos++;
        Creados++;
        int z = process.getPag().length;
        RAM(process);  
    }
    
    //////****************INSERCION EN MEMORIA PRINCIPAL*************************
    public void RAM(Pro process) {
        int mp = 0;
        //Numero de paginas del proceso
        int nump = process.getPag().length;       
        

        for (int i = 0; i < principal.length; i++) {
            if (principal[i] == 000000) {
                mp++;     //MP libres en RAM
            }
        }
        
        //VERIFICA QUE MAS DE LA MITAD DEL PROCESO QUEPA EN RAM
        if (!(mp == nump / 2 && nump % 2 != 0)) {
            if (mp >= nump / 2) {           
                //System.out.println("aaaaaaa");
                for (int i = 0; i < principal.length; i++) {
                    if (principal[i] == 0 && nump > 0) {
                        principal[i] = process.getID();
                        nump--;
                    }
                }
                if (nump > 0) {
                    int a = nump * fe;     //fe es el tamano de cada pagina
                    Pro j = new Pro(a, process.getID(), fe);
                    Secondary(nump, j);
                    VIRTUAL(nump, j);
                }
            } else {
                if (!Check(process.getID())) {
                    
                    Ready(nump, process);
                }
                VIRTUAL(nump, process);
            }
        } else {
            if (!Check(process.getID())) {
                Ready(nump, process);
            }
            VIRTUAL(nump, process);
        }
    }
    
    //********************LLENA MEMORIA VIRTUAL***************
    public void VIRTUAL(int nump, Pro process) {
        int ctte = 0;
        for (int i = 0; i < virtual.length; i++) {
            if (virtual[i] == 0) {
                ctte++;
            }
        }
        if (ctte >= nump) {

            for (int i = 0; i < virtual.length; i++) {
                if (virtual[i] == 000000 && nump > 0) {
                    virtual[i] = process.getID();
                    nump--;

                }
            }

        } else {
            System.out.println("");
            System.out.println("**************!!!!!!!!!!!!!!!!NO HAY ESPACIO DISPONIBLE!!!!!!!!!!!!!!**************");
            System.out.println("");
        }
    }
    
       //**************************METODO PARA ELIMINAR PROCESOS************************************
    public void Delete() {
        boolean existe = false;
        System.out.println("Introduzca el número del proceso a eliminar");
        int numpro = sc.nextInt();
        r++;

        for (int i = 0; i < Bloqueados.length; i++) {
            if (Bloqueados[i] == numpro) {
                Bloqueados[i] = 0;
            }
        }

        for (int i = 0; i < principal.length; i++) {
            if (principal[i] == numpro) {
                existe = true;
                principal[i] = 0;
            }
        }
        for (int i = 0; i < virtual.length; i++) {
            if (virtual[i] == numpro) {
                virtual[i] = 0;
            }
        }
        if (existe == false) {
            listos.Search(numpro);
        }
        SecondaryActive();
    }
    
     //**************************METODO PARA SUSPENDER PROCESOS************************************
    public void Pause() {
        System.out.println("Introduzca el número del proceso a suspender");
        int numpro = sc.nextInt();
        int cont1 = 0;
        int cont2 = 0;
        
        for (int i = 0; i < principal.length; i++) {
                if (principal[i] == numpro) {
                    principal[i] = 0;
                    cont1++;
                }
            }
            for (int i = 0; i < virtual.length; i++) {
                if (virtual[i] == numpro) {
                    cont2++;
                }
            }
            if (!Check(numpro)) {
                listos.Add(new Container(new Pro((cont2 + cont1) * fe, numpro, fe)));
            }
            for (int i = 0; i < virtual.length; i++) {
                if (virtual[i] == 0 && cont1 > 0) {
                    virtual[i] = numpro;
                    cont1--;
                }
            }
            SecondaryActive();
    }

    //**************************METODOS PARA ACTIVAR PROCESOS************************************
    public void ProActive() {
        if (listos.getHead() == null) {
        } else {
            int nump = listos.getHead().getProceso().getPag().length;
            int id = listos.getHead().getProceso().getID();
            //System.out.println("aaaaaaa "+id);
            int ctte = 0;
            for (int i = 0; i < principal.length; i++) {
                if (principal[i] == 0) {
                    ctte++;
                }
            }
            if (!(ctte == nump / 2 && nump % 2 != 0)) {
                if (ctte >= nump / 2) {
                    for (int i = 0; i < virtual.length; i++) {
                        if (virtual[i] == id) {
                            virtual[i] = 0;
                            nump--;
                        }
                    }
                    if (nump > 0) {
                        int a = nump * fe;
                        Pro j = new Pro(a, id, fe);
                        Secondary(nump, j);
                    }
                    RAM(listos.Remove().getProceso());
                }
            }
        }
    }

    public void SecondaryActive() {
        if (sec.getHead() == null) {
        } else {
            int nump = sec.getHead().getProceso().getPag().length;
            int id = sec.getHead().getProceso().getID();

            int ctte = 0;
            for (int i = 0; i < principal.length; i++) {
                if (principal[i] == 000000) {
                    ctte++;
                }
            }
            if (!(ctte == nump / 2 && nump % 2 != 0)) {
                if (ctte >= nump / 2) {
                    for (int i = 0; i < virtual.length; i++) {
                        if (virtual[i] == id) {
                            virtual[i] = 000000;
                        }
                    }
                    RAM(sec.Remove().getProceso());
                }
            }
        }
    }
    
    //////**************METODOS PARA INSERTAR EN LAS COLAS*******************
    public void Secondary(int z, Pro process) {
        int ctte = 0;
        for (int i = 0; i < virtual.length; i++) {
            if (virtual[i] == 000000) {
                ctte++;
            }
        }
        if (ctte >= z) {
            Container np = new Container(process);
            sec.Add(np);
        }
    }

    public void Ready(int z, Pro process) {
        int ctte = 0;
        for (int i = 0; i < virtual.length; i++) {
            if (virtual[i] == 000000) {
                ctte++;
            }
        }
        if (ctte >= z) {
            Container np = new Container(process);
            listos.Add(np);
        }
    }
    
    
    //**************************METODOS PARA BLOQUEAR Y DESBLOQUEAR PROCESOS************************************

    public void Block() {
        boolean aux = false;
        System.out.println("Indique numero del proceso a bloquear: ");
        int numpro = sc.nextInt();
        b++;
        int c = 0;
        int a = 0;
        for (int i = 0; i < principal.length; i++) {
            if (principal[i] == numpro) {
                a++;
            }
        }
        for (int i = 0; i < virtual.length; i++) {
            if (virtual[i] == numpro) {
                c++;
            }
        }
        
        if (a > 0 && c==0) {
            for (int i = 0; i < Bloqueados.length; i++) {
                if (Bloqueados[i] == 0 ) {
                    Bloqueados[i] = numpro;
                    break;
                }
            }

            for (int i = 0; i < principal.length; i++) {
                if (principal[i] == numpro) {
                    aux = true;
                }
            }
            if (aux != true) {
                listos.Search(numpro);
            }
        }else{
            
        }
    }
    
     ///***************REVISA SI EL PROCESO ESTA BLOQUEADO. SE USA PARA INSERTAR EN LISTOS********************
    public boolean Check(int id) {
        boolean aux = false;
        for (int i = 0; i < Bloqueados.length; i++) {
            if (Bloqueados[i] == id) {
                aux = true;
            }
        }
        return aux;
    }

    public void Unblock() {
        boolean aux = false;
        System.out.println("Indique número del proceso a desbloquear: ");
        int id = sc.nextInt();

        for (int i = 0; i < Bloqueados.length; i++) {
            if (Bloqueados[i] == id) {
                Bloqueados[i] = 000000;
            }
        }
        for (int i = 0; i < principal.length; i++) {
            if (principal[i] == id) {
                aux = true;
            }
        }

        if (aux != true) {
            int ctte = 0;
            for (int i = 0; i < virtual.length; i++) {
                if (virtual[i] == id) {
                    ctte++;
                }
            }
            Container np = new Container(new Pro(fe * ctte, id, ctte));
            listos.Add(np);
        }
    }
    
    ////************PARA LOS REGISTROS******************
    public int MP() {
        int ctte = 0;
        for (int i = 0; i < principal.length; i++) {
            if (principal[i] != 000000) {
                ctte++;
            }
        }
        return ctte;
    }
    
    public int AvailableRAM() {
        int ctte;
        ctte = ppale - (fe * MP());
        return ctte;
    }

    public int AvailableVirtual() {
        int ctte, z = 0;
        for (int i = 0; i < virtual.length; i++) {
            if (virtual[i] != 000000) {
                z++;
            }
        }
        ctte = virtuale - (fe * z);
        return ctte;
    }
    
    //**************************METODO PARA LLEVAR LOS REGISTROS***********************************
    public void Registro() throws IOException {
        System.out.println("+ Número de procesos que se han creados: " + Creados);
        System.out.println("+ Número de procesos que se han eliminado: " + r);
        System.out.println("+ Número de procesos que se han bloqueado: " + b);
        System.out.println("+ Marcos de página que están en uso: " + MP());
        System.out.println("+ Marcos de página que siguen disponibles: " + (principal.length - MP()));
        System.out.println("+ Memoria Principal Disponible (Espacio): " + AvailableRAM() + " MB");
        System.out.println("+ Memoria Secundaria Disponible (Espacio): " + AvailableVirtual() + " MB");
        System.out.println("+ Cantidad de procesos que se han eliminado: " + r);
        System.out.println("+ Cantidad de procesos que se han bloqueado: " + b);
        Menu();
    }   
}
