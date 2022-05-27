package com.tdea;

import java.util.Map;

public class Vegano extends PlatoFuerte {

    private Map<String, Object> adiciones;

    public Vegano(Map<String, Object> adiciones, NoAlcoholica bebida, String ingredientes, String nombre, double precio) {
        super(bebida, ingredientes, nombre, precio);
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
        String concepto = getNombre() + " : " + getPrecio()
                + "\nBebida" + this.getBebida().getNombre() + "\n" ;
        if (!adiciones.isEmpty()) {
            concepto += "con adiciones de : \n";
            for (Map.Entry<String, Object> adicion : adiciones.entrySet()) {
                concepto += (adicion.getKey() + ":" + adicion.getValue().toString() + "\n");
            }
        }
        return concepto;
    }

    @Override
    public double valor() {
        double valor = getPrecio();
        if (!adiciones.isEmpty()) {
            for (Map.Entry<String, Object> adicion : adiciones.entrySet()) {
                valor += Double.parseDouble(adicion.getValue().toString());
            }
        }
        return valor;
    }

}
