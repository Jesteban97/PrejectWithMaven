/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdea;

import co.com.tdea.Consumo;


public abstract class Bebida extends Consumo{
    private double tamanio;

    public Bebida( String nombre, double precio, double tamanio) {
        super(nombre, precio);
        this.tamanio = tamanio;
    }

    public double getTamano() {
        return tamanio;
    }

    public void setTamano(double tamano) {
        this.tamanio = tamano;
    }
        

    
}
