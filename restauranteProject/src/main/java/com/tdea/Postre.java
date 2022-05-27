package com.tdea;

public class Postre extends Plato{
    private String endulzante;
    

    public Postre(String endulzante, String ingredientes, String nombre, double precio) {
        super(ingredientes, nombre, precio);
        this.endulzante = endulzante;
        
    }
    
    public String getEndulzante() {
        return endulzante;
    }

    public void setEndulzante(String endulzante) {
        this.endulzante = endulzante;
    }
    public String concepto() {
        String concepto = getNombre() + " : " + getPrecio() + "\n";
        concepto+= "endulzado con " + endulzante  ;
        return concepto;
    }

    public double valor() {
        return getPrecio();
    }
}
