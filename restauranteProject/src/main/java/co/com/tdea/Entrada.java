package co.com.tdea;

import com.tdea.Plato;

public class Entrada extends Plato {

    private boolean compartir;
    private String salsa;

    public Entrada(boolean compartir, String salsa, String ingredientes, String nombre, double precio) {
        super(ingredientes, nombre, precio);
        this.compartir = compartir;
        this.salsa = salsa;
    }

    public boolean isCompartir() {
        return compartir;
    }

    public void setCompartir(boolean compartir) {
        this.compartir = compartir;
    }

    public String getSalsa() {
        return salsa;
    }

    public void setSalsa(String salsa) {
        this.salsa = salsa;
    }

    public String concepto() {
        String concepto = getNombre() + " : " + getPrecio() + "\n";
        if (compartir) {
            concepto += "para compartir \n";
        } else {
            concepto += "porcion unica \n";
        }
        concepto += ("con salsa " + salsa );
        return concepto;
    }

    public double valor() {
        return getPrecio();
    }

}
