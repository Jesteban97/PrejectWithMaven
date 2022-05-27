/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tdea;

import java.util.Map;

public class Bebida_Calientes extends Bebida  {
    private Map<String, Object> adiciones;

    public Bebida_Calientes(String nombre, double precio, double tamanio,Map<String, Object> adiciones) {
        super(nombre, precio, tamanio);
        this.adiciones = adiciones;
    }

  

  

    public Map<String, Object> getAdiciones() {
        return adiciones;
    }

    public void setAdiciones(Map<String, Object> adiciones) {
        this.adiciones = adiciones;
    }

    @Override
    public String concepto() {
        String concepto = getNombre() + " : " + getPrecio() + "\n";
        if (!adiciones.isEmpty()) {
            concepto += "Adicionales ==> ";
            for (Map.Entry<String, Object> adicion : adiciones.entrySet()) {
                concepto += (adicion.getKey() + ":" + adicion.getValue().toString() + "\n");
            }
        }
        return concepto;
    }

    @Override
    public double valor() {
        double valor = getPrecio();
        for (Map.Entry<String, Object> adicion : adiciones.entrySet()) {
            valor += Double.parseDouble(adicion.getValue().toString());
        }
        return valor;
    }
}