/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.puentedossentidos;

/**
 *
 * @author airam
 */
public class Persona implements Runnable {
    private final String idPersona;
    private final int tiempoPaso;
    private final int pesoPersona;
    private final String sentido;
    private final Puente puente;

    public Persona(String idPersona, int tiempoPaso, int pesoPersona, String sentido, Puente puente) {
        this.idPersona = idPersona;
        this.tiempoPaso = tiempoPaso;
        this.pesoPersona = pesoPersona;
        this.sentido = sentido;
        this.puente = puente;
    }

    @Override
    public void run() {
        try {
            puente.entrar(this);  
            Thread.sleep(tiempoPaso * 100);  
            puente.salir(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getIdPersona() {
        return idPersona;
    }

    public int getPesoPersona() {
        return pesoPersona;
    }

    public String getSentido() {
        return sentido;
    }
}
