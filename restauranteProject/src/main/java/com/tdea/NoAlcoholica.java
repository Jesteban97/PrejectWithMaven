package com.tdea;

public class NoAlcoholica extends Bebida_Frias {
    private double gramajeAzucar;

    public NoAlcoholica(String nombre, double precio, double tamanio, String marca,double gramajeAzucar) {
        super(nombre, precio, tamanio, marca);
        this.gramajeAzucar = gramajeAzucar;
    }

    public double getGramajeAzucar() {
        return gramajeAzucar;
    }

    public void setGramajeAzucar(double gramajeAzucar) {
        this.gramajeAzucar = gramajeAzucar;
    }
    
     public String concepto() {
        String concepto = getNombre() + " : " + getPrecio() +"\n";
       
        return concepto;
    }

    public double valor() {
        return getPrecio();
    }
    
    
}
