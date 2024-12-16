/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.puentedossentidos;

/**
 *
 * @author airam
 */
import java.util.Random;

public class PuenteDosSentidos {

    public static void main(String[] args) {
          final int MINIMO_TIEMPO_LLEGADA = 1;
        final int MAXIMO_TIEMPO_LLEGADA = 30;
        final int MINIMO_TIEMPO_PASO = 10;
        final int MAXIMO_TIEMPO_PASO = 50;
        final int MINIMO_PESO_PERSONA = 40;
        final int MAXIMO_PESO_PERSONA = 120;

        final Puente puente = new Puente();
        String idPersona = "";
        int tiempoLlegada;
        int tiempoPaso;
        int pesoPersona;

        
        int numeroPersona = 1; 
        while (true) {
            
            idPersona = "Persona " + numeroPersona;
            tiempoLlegada = numeroAleatorio(MINIMO_TIEMPO_LLEGADA, MAXIMO_TIEMPO_LLEGADA);
            tiempoPaso = numeroAleatorio(MINIMO_TIEMPO_PASO, MAXIMO_TIEMPO_PASO);
            pesoPersona = numeroAleatorio(MINIMO_PESO_PERSONA, MAXIMO_PESO_PERSONA);

            
            String sentido = numeroAleatorio(0, 1) == 0 ? "1" : "2";
            System.out.printf("La %s llegará en %d segundos, pesa %d kilos, irá hacia %s y tardará %d segundos en cruzar.\n",
                    idPersona, tiempoLlegada, pesoPersona, sentido, tiempoPaso);

            Thread hiloPersona = new Thread(new Persona(idPersona, tiempoPaso, pesoPersona, sentido, puente));
 
            try {
                Thread.sleep(tiempoLlegada * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
  
            hiloPersona.start();
   
            numeroPersona++;
        }
    }

    public static int numeroAleatorio(int valorMinimo, int valorMaximo) {
        Random r = new Random();
        return valorMinimo + r.nextInt((valorMaximo - valorMinimo) + 1);
    }
}
    

