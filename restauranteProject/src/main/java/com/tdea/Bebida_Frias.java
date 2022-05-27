/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdea;


public abstract class Bebida_Frias extends Bebida {

    private String marca;
    
    
    public Bebida_Frias(String nombre, double precio, double tamanio, String marca) {
        super(nombre, precio, tamanio);
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    

    /* public String concepto() {
        String concepto = getNombre() + " : " + getPrecio() +"\n";
       
        return concepto;
    }

    public double valor() {
        return getPrecio();
    }*/

 
    
}
