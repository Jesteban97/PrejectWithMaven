package com.tdea;

import java.util.ArrayList;

public class Mixto extends PlatoFuerte{
    private ArrayList<String> salsas;

    public Mixto(ArrayList<String> salsas, NoAlcoholica bebida, String ingredientes, String nombre, double precio) {
        super(bebida, ingredientes, nombre, precio);
        this.salsas = salsas;
    }

    public ArrayList<String> getSalsas() {
        return salsas;
    }

    public void setSalsas(ArrayList<String> salsas) {
        this.salsas = salsas;
    }
    
    public String concepto() {
        String concepto = getNombre() + " : " + getPrecio() +"\n";
        concepto+= "salsas adiconales: " + concatenarSalsas() 
                +  "Bebida: " + this.getBebida().getNombre() ;
        return concepto;
    }

    public double valor() {
        return getPrecio();
    }
    
    private String concatenarSalsas(){
        String salsasConcatenadas="\n";
    for(String salsa : salsas){
        salsasConcatenadas+=(salsa + "\n");
    }
    return salsasConcatenadas;
    }
}
