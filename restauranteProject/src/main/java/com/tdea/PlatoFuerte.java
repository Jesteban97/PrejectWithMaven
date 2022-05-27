package com.tdea;

public abstract class PlatoFuerte extends Plato {
    private NoAlcoholica bebida;

    public PlatoFuerte(NoAlcoholica bebida, String ingredientes, String nombre, double precio) {
        super(ingredientes, nombre, precio);
        this.bebida = bebida;
    }

    public NoAlcoholica getBebida() {
        return bebida;
    }

    public void setBebida(NoAlcoholica bebida) {
        this.bebida = bebida;
    }
    
}
