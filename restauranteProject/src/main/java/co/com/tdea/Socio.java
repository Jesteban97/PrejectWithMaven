/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.tdea;

import com.tdea.FabricaDeConsumos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author TdeA
 */
public class Socio {

    //Atributos
    private int cedula;
    private String tipoDeSuscripcion;
    private double fondos;
    private String nombre;
    private PersonaAutorizada[] personasAutorizadas;
    private Factura[] facturas;

 
    public Socio(int cedula, String tipoDeSuscripcion, double fondos, String nombre) {
        this.cedula = cedula;
        this.tipoDeSuscripcion = tipoDeSuscripcion;
        this.fondos = fondos;
        this.nombre = nombre;
        this.personasAutorizadas = new PersonaAutorizada[3];
        this.facturas = new Factura[20];
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getTipoDeSuscripcion() {
        return tipoDeSuscripcion;
    }

    public void setTipoDeSuscripcion(String tipoDeSuscripcion) {
        this.tipoDeSuscripcion = tipoDeSuscripcion;
    }

    public double getFondos() {
        return fondos;
    }

    public void setFondos(double fondos) {
        this.fondos = fondos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PersonaAutorizada[] getPersonasAutorizadas() {
        return personasAutorizadas;
    }

    public void setPersonasAutorizadas(PersonaAutorizada[] personasAutorizadas) {
        this.personasAutorizadas = personasAutorizadas;
    }

    public Factura[] getFacturas() {
        return facturas;
    }

    public void setFacturas(Factura[] facturas) {
        this.facturas = facturas;
    }
    
    public void eliminarPersonaAutorizada(int cedulaAutorizada) {
        //boolean registro = false;
        if (buscarCedula(cedulaAutorizada) != null) {
                for (int i = 0; i < personasAutorizadas.length; i++) {
                    if (personasAutorizadas[i]!=null &&personasAutorizadas[i].getCedula() == cedulaAutorizada ) {
                        //String nombre = JOptionPane.showInputDialog(null, "ingrese el nombre de la persona");
                        //PersonaAutorizada persona = new PersonaAutorizada(nombre, this, cedula);
                        personasAutorizadas[i] = null;
                        JOptionPane.showMessageDialog(null, "Se elimino la persona Autorizada");
                        return;
                        //registro = true;
                    }
                }
        } else {
            JOptionPane.showMessageDialog(null, "el socio no tiene personas Autorizadas");
        }
    }

    public boolean cuposDisponibles() {
        for (int i = 0; i < personasAutorizadas.length; i++) {
            if (personasAutorizadas[i] == null) {
                return true;
            }
        }
        return false;
    }

    public PersonaAutorizada buscarCedula(int cedula) {

        PersonaAutorizada encontrado = null;
        for (int i = 0; i < personasAutorizadas.length; i++) {
            if (personasAutorizadas[i] != null && personasAutorizadas[i].getCedula() == cedula) {
                encontrado = personasAutorizadas[i];
            }
        }
        return encontrado;
    }
    
     public int contarAsociados() {
        int cantidadAsociados = 0;
        for (int i = 0; i < personasAutorizadas.length; i++) {
            if (personasAutorizadas[i] != null ) {
                cantidadAsociados++;
            }
        }
        return cantidadAsociados;
    }
     
     public int traerAsociado() {

        PersonaAutorizada encontrado = null;
        
        for (int i = 0; i < personasAutorizadas.length; i++) {
            if (personasAutorizadas[i] != null ) {
                encontrado = personasAutorizadas[i];
            }
        }
        return encontrado.getCedula();
    }
     

    public void registrarConsumo(int opMenu, int dato) {
        if (this.fondos >= 50000) {
            if (facturasDisponibles()) {
                boolean registro = false;
                for (int i = 0; i < facturas.length; i++) {
                    if (facturas[i] == null && !registro) {
                        FabricaDeConsumos fabrica = new FabricaDeConsumos();
                        
                        Consumo consumo = fabrica.creadorDePlatos(opMenu,dato);
                        String concepto = consumo.concepto();
                        double valor = consumo.valor();
                        
                        if(this.getFondos()>= valor){
                            Factura factura = new Factura(concepto, valor, this.nombre);
                            facturas[i] = factura;
                            registro = true;
                            JOptionPane.showMessageDialog(null, "se registro con exito ");
                        }else{
                            JOptionPane.showMessageDialog(null, "No tiene fondos suficientes");
                        }
                        
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "el socio no puede tener mas facturas sin pagar");
            }
        } else {
            JOptionPane.showMessageDialog(null, "el socio no tiene fondos suficientes");
        }
    }

    public boolean facturasDisponibles() {
        for (int i = 0; i < facturas.length; i++) {
            if (facturas[i] == null) {
                return true;
            }
        }
        return false;
    }

    
   
    
      public String buscarFacturas() {
        String factura = "";
        int message=0;
        for (int i = 0; i < facturas.length; i++) {
            if (facturas[i] != null) {
                factura += (" Tiene Factutras Pendientes");
                message++;
            }
            
        }
        return factura;
    }
      
       public String mostrarFacturas() {
        String factura = "";
        int message=0;
        for (int i = 0; i < facturas.length; i++) {
            if (facturas[i] != null) {
                
                factura += "\n===============================" 
                        
                        + ("\nNombre: " + facturas[i].getNombre()
                        + "\nN° Documento: "+this.cedula
                        +"\nN° Factura: " + i 
                        + "\nconcepto: " + facturas[i].getConcepto() 
                        + "\nvalor: " + facturas[i].getValor());
                //JOptionPane.showMessageDialog(null,factura);
                //System.out.print(factura);
                message++;
            }
            
        }
        if(message<1){
                JOptionPane.showMessageDialog(null," no hay facturas para esta cedula");
            }
        return factura;
    }
      
      

     public void pagarFacturaSocio(int cedula,int posicion) {
        String factura = mostrarFacturas();
        if (factura.equals("")) {
            JOptionPane.showMessageDialog(null, "el socio no tiene facturas pendientes de pago");
        } else {
            //int posicion = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese el numero de factura a pagar \n" + factura));
            if (posicion >= 0 && posicion <= facturas.length && facturas[posicion] != null) {
                if (facturas[posicion].getValor() <= fondos) {
                    fondos -= facturas[posicion].getValor();
                    
                    facturas[posicion] = null;
                    JOptionPane.showMessageDialog(null, "Pagada con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "el socio no cuenta con fondos suficientes");
                }
            } else {
                JOptionPane.showMessageDialog(null, "ingrese una posicion valida");
            }
        }

    }
     public void eliminarFacturaSocio(int cedula,int posicion) {
        String factura = mostrarFacturas();
        if (factura.equals("")) {
            JOptionPane.showMessageDialog(null, "el socio no tiene facturas pendientes de pago");
        } else {
           
            if (posicion >= 0 && posicion <= facturas.length && facturas[posicion] != null) {
                if (facturas[posicion].getValor() <= fondos) {
                    
                    facturas[posicion] = null;
                    JOptionPane.showMessageDialog(null, "Eliminada con exito");
                
                }
            } else {
                JOptionPane.showMessageDialog(null, "ingrese una posicion valida");
            }
        }

    }


    @Override
    public String toString() {
        return "Socio " + "\nCedula: " + cedula + "\nTipo de Suscripcion: " + tipoDeSuscripcion + "\nFondos disponibles: " + fondos + "\nNombre Socio: " + nombre ;
    }
    
    
      public void registrarPersonaAutorizada(String nombre,int cedulaAutorizada, int cedula) {
        boolean registro = false;
        if (buscarCedula(cedulaAutorizada) == null) {
            if (cuposDisponibles() && this.fondos >= 50000) {
                for (int i = 0; i < personasAutorizadas.length; i++) {
                    if (personasAutorizadas[i] == null && !registro) {                        
                        PersonaAutorizada persona = new PersonaAutorizada(nombre,cedulaAutorizada,this);
                        personasAutorizadas[i] = persona;
                        registro = true;
                        JOptionPane.showMessageDialog(null,"Se creo la persona autorizada");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "el socio no puede registrar mas personas");
            }
        } else {
            JOptionPane.showMessageDialog(null, "ya existe una persona con esa cedula afiliada por este socio");
        }
    }
    
    
    //******************************************************************************************
    
        public String EncontrarFactura(int cedula,int posicion) {
          String encontrado = "";
        String factura = mostrarFacturas();
        if (factura.equals("")) {
            JOptionPane.showMessageDialog(null, "el socio no tiene facturas pendientes de pago");
        } else {
            //int posicion = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese el numero de factura a pagar \n" + factura));
            if (posicion >= 0 && posicion <= facturas.length && facturas[posicion] != null) {
                encontrado += "\n===============================" 
                        
                        + ("\nNombre: " + facturas[posicion].getNombre()
                        + "\nN° Documento: "+this.cedula
                        +"\nN° Factura: " + posicion 
                        + "\nconcepto: " + facturas[posicion].getConcepto() 
                        + "\nvalor: " + facturas[posicion].getValor());
            } else {
                JOptionPane.showMessageDialog(null, "ingrese una posicion valida");
            }
        }
        return encontrado;

    }
        
      public void CargarPersonaAutorizada(String nombre, int cedulaAutorizada, int cedulaSocio)throws IllegalAccessException{
       boolean registro = false;
        if (buscarCedula(cedulaAutorizada) == null) {
            if (cuposDisponibles() && this.fondos >= 50000) {
                for (int i = 0; i < personasAutorizadas.length; i++) {
                    if (personasAutorizadas[i] == null && !registro) {                        
                        PersonaAutorizada persona = new PersonaAutorizada(nombre,cedulaAutorizada,this);
                        personasAutorizadas[i] = persona;
                        registro = true;
                        
                    }
                }
            } else {
                throw new IllegalAccessException( "el socio no puede registrar mas personas");
            }
        } else {
            throw new IllegalAccessException("ya existe el Asociado con cedula " + cedulaAutorizada);
        }
    }
        
   
    
    
}
