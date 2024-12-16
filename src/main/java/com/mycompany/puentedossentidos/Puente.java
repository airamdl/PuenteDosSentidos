/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.puentedossentidos;

/**
 *
 * @author airam
 */

public class Puente {

    private int numeroPersonas = 0;
    private int pesoPersonas = 0;
    private String sentidoActual = null;
    private int personasSentido1 = 0;
    private int personasSentido2 = 0;
    private static final int MAXIMO_PERSONAS_SENTIDO = 3;
    private static final int MAXIMO_PERSONAS = 4;
    private static final int MAXIMO_PESO = 300;
   

    
    public synchronized void entrar(Persona persona) throws InterruptedException {
        String sentidoPersona = persona.getSentido();

        
        while ((numeroPersonas >= MAXIMO_PERSONAS) ||(pesoPersonas + persona.getPesoPersona() > MAXIMO_PESO) ||  
               (sentidoPersona.equals("1") && personasSentido1 >= MAXIMO_PERSONAS_SENTIDO) ||  
               (sentidoPersona.equals("2") && personasSentido2 >= MAXIMO_PERSONAS_SENTIDO)) {  

            System.out.printf("*** La %s debe esperar. (Condición: %s)\n", persona.getIdPersona(), 
                              (numeroPersonas >= MAXIMO_PERSONAS) ? "Puente lleno" :
                              (pesoPersonas + persona.getPesoPersona() > MAXIMO_PESO) ? "Peso limite superado" :
                              (sentidoPersona.equals("1") && personasSentido1 >= MAXIMO_PERSONAS_SENTIDO) ? "Demasiadas personas en la dirección 1" :
                              "Demasiadas personas en la direccion 2");
            this.wait();  
        }

        
        numeroPersonas++;
        pesoPersonas += persona.getPesoPersona();

        if (sentidoPersona.equals("1")) {
            personasSentido1++;
        } else {
            personasSentido2++;
        }

        
        if (sentidoActual == null) {
            sentidoActual = sentidoPersona;
        }

        System.out.printf(">>> La %s entra en dirección %s. Estado del puente: %d personas, %d kilos.\n",
                persona.getIdPersona(), sentidoPersona, numeroPersonas, pesoPersonas);
    }


    
    public synchronized void salir(Persona persona) {
        String sentidoPersona = persona.getSentido();
        numeroPersonas--;
        pesoPersonas -= persona.getPesoPersona();

        if (sentidoPersona.equals("NORTE")) {
            personasSentido1--;
        } else {
            personasSentido2--;
        }

        
        if (numeroPersonas == 0) {
            sentidoActual = null;
        }

        System.out.printf(">>> La %s sale. Estado del puente: %d personas, %d kilos.\n", persona.getIdPersona(), numeroPersonas, pesoPersonas);

       
        this.notifyAll();
    }
    
   }


