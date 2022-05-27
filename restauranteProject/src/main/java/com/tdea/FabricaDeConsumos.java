package com.tdea;

import co.com.tdea.Consumo;
import co.com.tdea.Entrada;
import com.tdea.Postre;
import com.tdea.Vegano;
import com.tdea.Mixto;
import com.tdea.Bebidas_Frias_Alcoholica;
import com.tdea.NoAlcoholica;
import com.tdea.Bebida_Calientes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class FabricaDeConsumos {
    
    public void AgregarAdicionCaliente(Map<String, Object> adiciones) {
        int opc = 0;
        do {
            opc = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese el numero de la adicion que desea \n"
                    + "1. Endulzante\n"
                    + "2. Instacream\n"
                    + "3. Stevia\n"
                    + "0. Ninguna"));

            switch (opc) {
                case 1: {
                    if (adiciones.containsKey("Endulzante")) {
                        adiciones.put("Endulzante", (Double.parseDouble(adiciones.get("Endulzante").toString())) + 1000);
                    } else {
                        adiciones.put("Endulzante", 1000);
                    }
                    break;
                }
                case 2: {
                    if (adiciones.containsKey("Stevia")) {
                        adiciones.put("Stevia", (Double.parseDouble(adiciones.get("Stevia").toString())) + 2000);
                    } else {
                        adiciones.put("Stevia", 2000);
                    }
                    break;
                }
                case 3: {
                    if (adiciones.containsKey("Instacrem")) {
                        adiciones.put("Instacrem", (Double.parseDouble(adiciones.get("Instacrem").toString())) + 1500);
                    } else {
                        adiciones.put("Instacrem", 1500);
                    }
                    break;
                }
            }
        } while (opc != 0);
    }
    
    private Consumo creadorBebidaCaliente(int dato) {
        Map<String, Object> adiciones = new HashMap();
        AgregarAdicionCaliente(adiciones);
        switch (dato) {
            case 1: {
                String nombre = "Café Mocca";
                double precio = 7500;
                int tamano = 350;
                //String nombre, double precio, double tamanio,Map<String, Object> adiciones
                return new Bebida_Calientes (nombre, precio, tamano,adiciones);
            }
            case 2: {
                String nombre = "Matcha";
                double precio = 8000;
                int tamano = 600;
                return new Bebida_Calientes (nombre, precio, tamano,adiciones);
            }
            case 3: {
                String nombre = "Cappuccino";
                double precio = 7000;
                int tamano = 400;
                return new Bebida_Calientes (nombre, precio, tamano,adiciones);
            }            
            default:
                return null;
        }
    }
    
    
    private Consumo creadorDePlatosMixtos(int dato) {
         ArrayList<String> salsas= new ArrayList();
         
         agregarSalsas(salsas);
        switch (dato) {
            case 1: {
                boolean compartir = true;
                String nombre = "costillas BBQ";
                double precio = 35000;
                String ingredientes = "costillas y Papas";
                NoAlcoholica bebida = new NoAlcoholica("Gaseosa",3000,350,"Club",15.5);
                return new Mixto(salsas, bebida, ingredientes, nombre, precio);
               
            }
            case 2: {
                boolean compartir = true;
                String nombre = "Bandeja Paisa";
                double precio = 25000;
                String ingredientes = "Frijoles,huevo cocido, tajada de maduro, chorizo,aguacate, carne molida, arroz, chicharron, arepa redonda";
                NoAlcoholica bebida = new NoAlcoholica("Guarapo",2000,250,"Club",5.5);
                return new Mixto(salsas, bebida, ingredientes, nombre, precio);
            }
            case 3: {
                boolean compartir = true;
                String nombre = "Picada Mixta";
                double precio = 20000;
                String ingredientes = "De todo";
                NoAlcoholica bebida = new NoAlcoholica("Limonada",4000,550,"Club",20.5);
                return new Mixto(salsas, bebida, ingredientes, nombre, precio);
            }
            default: {
                return null;
            }
        }
    }
    
    private void agregarSalsas(ArrayList<String> salsas) {
        int opc;
        do {
            opc = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese el numero de la adicion que desea \n"
                    + "1. Guacamole\n"
                    + "2. Bolognesa\n"
                    + "3. Bechamel\n"
                   
                    + "0. para continuar"));
            switch (opc) {
                case 1: {
                    salsas.add("Guacamole");
                    break;
                }
                case 2: {
                    salsas.add("Bolognesa");
                    break;
                }
                case 3: {
                     salsas.add("Bechamel");
                    break;
                }
                
            }

        } while (opc != 0);
    }
    
    private Consumo creadorBebidasNoAlcoholicas(int dato){
        switch (dato){
            case 1:{
                String nombre = "Jugo del Día";
                double precio = 18000;
                double tamanio = 150;
                String marca = "Natural";
                double gramajeAzucar = 5.0;
               return new NoAlcoholica(nombre,precio,tamanio,marca,gramajeAzucar);  
            } 
            case 2 :{
                String nombre = "Soda";
                double precio = 8000;
                double tamanio = 150;
                String marca = "Bretaña";
                double gramajeAzucar = 0.0;
               return new NoAlcoholica(nombre,precio,tamanio,marca,gramajeAzucar);  
            }   
            case 3:{
                String nombre = "Gaseosa";
                double precio = 3000;
                double tamanio = 350;
                String marca = "Coca-cola";
                double gramajeAzucar = 12.0;
               return new NoAlcoholica(nombre,precio,tamanio,marca,gramajeAzucar);  
            }   
            
            default: {
                return null;
            }                       
        } 
    }
    
    private Consumo creadorBebidasAlcoholicas(int dato){
        //String nombre, double precio, double tamanio, String marca,double gradoAlcohol
        switch (dato){
            case 1:{
                String nombre = "Margarita";
                double precio = 25000;
                double tamanio = 150;
                String marca = "Jose Cuervo";
                double gradoAlcohol = 25.6;
               return new Bebidas_Frias_Alcoholica(nombre,precio,tamanio,marca,gradoAlcohol);  
            }
            case 2:{
                String nombre = "Mojito";
                double precio = 35000;
                double tamanio = 250;
                String marca = "Bacardi";
                double gradoAlcohol = 45.6;
               return new Bebidas_Frias_Alcoholica(nombre,precio,tamanio,marca,gradoAlcohol);  
            }
            case 3:{
                String nombre = "Piña Colada";
                double precio = 15000;
                double tamanio = 170;
                String marca = "Tahiti";
                double gradoAlcohol = 15.6;
               return new Bebidas_Frias_Alcoholica(nombre,precio,tamanio,marca,gradoAlcohol);  
            }  
            
            default: {
                return null;
            }                       
        } 
    }

    private Consumo creadorDePostres(int dato) {
        switch (dato) {
            case 1: {
                String nombre = "tres leches";
                String ingredientes = "gelatina sin sabor, leche entera, crema de leche, lecherita, azucar, mantequilla";
                String endulzante = "lecherita"; // preguntar al usuario
                double precio = 20000;
                return new Postre(endulzante, ingredientes, nombre, precio);
            }
            case 2: {
                String nombre = "cheesecake limon";
                String ingredientes = "crema de leche, leche entera, limon, galletas, gelatina sin sabor, mantequilla";
                String endulzante = "lecherita"; // preguntar al usuario
                double precio = 20000;
                return new Postre(endulzante, ingredientes, nombre, precio);
            }
            default: {
                return null;
            }
        }

    }

    private Consumo creadorDeEntradas(int dato ) {
        switch (dato) {
            case 1: {
                boolean compartir = true; // preguntar
                String nombre = "empanadas x 10";
                String salsa = "aji";
                double precio = 30000;
                String ingredientes = "masa, papa, carne";
                return new Entrada(compartir, salsa, ingredientes, nombre, precio);
            }
            case 2: {
                boolean compartir = true; // preguntar
                String nombre = "patacones x6";
                String salsa = "guacamole";
                double precio = 6000;
                String ingredientes = "patacón, sal";
                return new Entrada(compartir, salsa, ingredientes, nombre, precio);
            }
            default: {
                return null;
            }
        }

    }

    private Consumo creadorDePlatosVeganos(int dato) {
        Map<String, Object> adiciones = new HashMap();
        agregarAdiciones(adiciones);
        switch (dato) {
            case 1: {
                boolean compartir = true;
                String nombre = "Ensalada Especial";
                double precio = 25000;
                String ingredientes = "lechuga, tomate,zanahoria, rabano, cebolla, pimientos, proteina vegetal, vinagreta, crutones, queso, ";
                NoAlcoholica bebida = new NoAlcoholica("Limonada",4000,550,"Club",20.5);;
                return new Vegano(adiciones, bebida, ingredientes, nombre, precio);
            }
            case 2: {
                boolean compartir = true;
                String nombre = "Menú Italiano sin carne";
                double precio = 20000;
                String ingredientes = "Pastas y salsa";
                NoAlcoholica bebida = new NoAlcoholica("Coca Cola",4000,550,"Club",20.5);;
                return new Vegano(adiciones, bebida, ingredientes, nombre, precio);
            }
            default: {
                return null;
            }
        }
    }
    
    
    

    private void agregarAdiciones(Map<String, Object> adiciones) {
        int opc;
        do {
            opc = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese el numero de la adicion que desea \n"
                    + "1. para yogurt griego\n"
                    + "2. para finas hierbas\n"
                    + "3. para Brocoli\n"
                    + "0. para continuar"));
            switch (opc) {
                case 1: {
                    if (adiciones.containsKey("yogurt griego")) {
                        adiciones.put("yogurt griego", (Double.parseDouble(adiciones.get("yogurt griego").toString())) + 2000);
                    } else {
                        adiciones.put("yogurt griego", 2000);
                    }
                    break;
                }
                case 2: {
                    if (adiciones.containsKey("finas hierbas")) {
                        adiciones.put("finas hierbas", (Double.parseDouble(adiciones.get("finas hierbas").toString())) + 1000);
                    } else {
                        adiciones.put("finas hierbas", 1000);
                    }
                    break;
                }
                case 3: {
                    if (adiciones.containsKey("Brocoli")) {
                        adiciones.put("Brocoli", (Double.parseDouble(adiciones.get("Brocoli").toString())) + 1500);
                    } else {
                        adiciones.put("Brocoli", 1500);
                    }
                    
                    break;
                }
            }

        } while (opc != 0);
    }

    public Consumo creadorDePlatos(int opc,int dato) {  
               
        switch (opc) {
            case 1: {
                return creadorDePostres(dato);
                
            }
            case 2: {
                return creadorDeEntradas(dato);
            }
            case 3: {
                return creadorDePlatosVeganos(dato);            
            }
            case 4: {                
                return creadorBebidasAlcoholicas(dato);
            }
            case 5:{
                return creadorDePlatosMixtos(dato);
            }
            case 6:{
                return creadorBebidasNoAlcoholicas(dato);
            }
            case 7:{
                return creadorBebidaCaliente(dato);
            }
            default: {
                return null;
            }
        }

    }

}
