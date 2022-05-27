/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdea;


public class Bebidas_Frias_Alcoholica extends Bebida_Frias {
    private double gradoAlcohol;

    public Bebidas_Frias_Alcoholica( String nombre, double precio, double tamanio, String marca,double gradoAlcohol) {
        super(nombre, precio, tamanio, marca);
        this.gradoAlcohol = gradoAlcohol;
    }

    public double getGramajeAzucar() {
        return gradoAlcohol;
    }

    public void setGramajeAzucar(double gradoAlcohol) {
        this.gradoAlcohol = gradoAlcohol;
    }
    
    
    
    public String concepto() {
        String concepto = getNombre() + " : " + getPrecio() +"\n";
       
        return concepto;
    }

    public double valor() {
        return getPrecio();
    }
    
    
}
