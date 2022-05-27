package com.tdea;


import co.com.tdea.Entrada;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class Club {

    private Socio[] _socios;
    private Socio[] _sociosVIP;
    
    
    public Club() {
        this._socios = new Socio[32];
        this._sociosVIP = new Socio[3];
    }

    public Socio[] getSocios() {
        return _socios;
    }

    public void setSocios(Socio[] _socios) {
        this._socios = _socios;
    }

    public Socio[] getSociosVIP() {
        return _sociosVIP;
    }

    public void setSociosVIP(Socio[] _sociosVIP) {
        this._sociosVIP = _sociosVIP;
    }
   
    
    public void registrarSocio()
    {
        boolean salir = false;
        while(!salir)
        {
            String suscripcion = JOptionPane.showInputDialog(null, "ingrese tipo de suscripción \n"
                    + "1. VIP.\n"
                    + "2. Regular\n"
                    + "3. Invitado\n"
                    + "0. Volver","Club",JOptionPane.INFORMATION_MESSAGE);
            switch (suscripcion) 
            {
                case "1": 
                {
                    if(cantidadSociosVip()<3 && cantidadSocios()<32)
                    {
                        String cedula = JOptionPane.showInputDialog(null, "ingrese la cedula o identificación");
                        Socio resultV = buscarSocioPorCedula(_sociosVIP,cedula);
                        Socio resultR = buscarSocioPorCedula(_socios,cedula);
                        
                        if(resultV==null && resultR == null){
                            insertarSocioVip(cedula);
                            salir=true;
                        }
                        else
                        {
                            if(resultR != null){
                                JOptionPane.showMessageDialog(null, "El usuario esta registrado como socio regular","Ya existe ",JOptionPane.INFORMATION_MESSAGE);
                            }else if(resultV != null){
                                JOptionPane.showMessageDialog(null, "El usuario esta registrado como socio VIP","Ya existe ",JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                 JOptionPane.showMessageDialog(null, "El usuario esta registrado como invitado de un socio","Ya existe ",JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No hay mas cupos para insertar usuario VIP");
                    }
                    break;
                }
                case "2": 
                {
                     if(cantidadSocios()<32)
                    {
                        String cedula = JOptionPane.showInputDialog(null, "ingrese la cedula o identificación del socio regular");
                        Socio resultV = buscarSocioPorCedula(_sociosVIP,cedula);
                        Socio resultR = buscarSocioPorCedula(_socios,cedula);//se debe validar que no exista como regular, si existte preguntar si desea convertirlo a VIP
                        //Invitado invitado = buscarInvitadoPorCedula(cedula);
                        
                        if(resultV==null && resultR == null){//fata validar que no exista como invitado
                            insertarSocio(cedula);
                            salir=true;
                        }
                        else
                        {
                             if(resultR != null){
                                JOptionPane.showMessageDialog(null, "El usuario esta registrado como socio regular","Ya existe ",JOptionPane.INFORMATION_MESSAGE);
                            }else if(resultV != null){
                                JOptionPane.showMessageDialog(null, "El usuario esta registrado como socio VIP","Ya existe ",JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                 JOptionPane.showMessageDialog(null, "El usuario esta registrado como invitado de un socio","Ya existe ",JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No hay mas cupos para insertar usuario VIP");
                    }
                    break;
                }
                 case "3": 
                {
                    String cedula = JOptionPane.showInputDialog(null, "ingrese la identificacion del socio");
                    Socio resultV = buscarSocioPorCedula(_sociosVIP,cedula);
                    Socio resultR = buscarSocioPorCedula(_socios,cedula);
                    if(resultV!=null){
                        int cantidad = buscarCantidadInvitadosDelVipPorCedula(cedula); 
                        if(cantidad<10){
                            insertarInvitadoSocio(cedula,1);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "No hay mas cupos para este socio");
                        }
                        salir=true;
                    }
                    else if(resultR!=null){
                        int cantidad = buscarCantidadInvitadosDelSocioCedula(cedula); 
                        if(cantidad<10){
                            insertarInvitadoSocio(cedula,2);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "No hay mas cupos para este socio");
                        }
                        salir=true;
                    }
                     if(resultV == null&& resultR == null){
                         JOptionPane.showMessageDialog(null, "No existe un socio con esa cedula","Advertencia",JOptionPane.WARNING_MESSAGE);
                     }
                    break;
                }
                case "0":{
                    salir = true;
                    break;
                }
            }             
            
        }
        
    }
    
    //#region METODOS PARA  SOCIO VIP
    public void insertarSocioVip(String cedula)
    {
        if(cantidadSociosVip()<3)
        {   
            for(int i =0; i<_sociosVIP.length;i++){
                if(_sociosVIP[i] == null){
                     _sociosVIP[i] = new Socio(cedula,"VIP");
                     JOptionPane.showMessageDialog(null, "Se agregó el socio "+ _sociosVIP[i].getNombre()+", al Club");
                     break;
                }
            }
        }
         else{
            JOptionPane.showMessageDialog(null, "ya no hay mas cupos para socios VIP");
        }
    }
    
    public void insertarInvitadoSocioVip(String cedula)
    {
        if(cantidadSociosVip()<32)
        {
            for(int i =0; i<_sociosVIP.length;i++){
                if(_sociosVIP[i].getCedula().equals(cedula)){
                    Invitado[] invitado = _sociosVIP[i].getInvitados();
                    for(int j =0; j<invitado.length;j++){

                        if(invitado[j] == null){
                            Invitado _invitado = new Invitado();
                            _sociosVIP[i].setInvitados(_invitado);
                            JOptionPane.showMessageDialog(null, "Se agregó el invitado al socio "+ _sociosVIP[i].getNombre());
                            break;
                        } 
                    }
                    break;
                }
            }
        }
         else{
            JOptionPane.showMessageDialog(null, "No contamos con mas cupos disponibles");
        }
    }
    
    public int cantidadSociosVip()
    {
       int cantidad =0;
       for (int j = 0; j < _sociosVIP.length; j++)
        {
           if(_sociosVIP[j] != null )
           {
               if(_sociosVIP[j].getTipoSuscripcion().equals("VIP")){
                    cantidad = cantidad+1; 
               }
           } 
        }
       return cantidad;
    }
    
    public int buscarCantidadInvitadosDelVipPorCedula(String cedula)
    {
       int cantidad =0;
       for (int j = 0; j < _sociosVIP.length; j++)
        {
           if(_sociosVIP[j] != null )
           {
               if(_sociosVIP[j].getCedula().equals(cedula)){
                    cantidad =cantidad+1;
                    break;
               }
           } 
        }
       return cantidad;
    }
    
  
    //#endregion
    
    
    //#region 
      public boolean eliminarSocio()
    {
        boolean eliminado = false;
        String cedula = JOptionPane.showInputDialog(null, "ingrese la identificación del socio a eliminar", "Club",JOptionPane.INFORMATION_MESSAGE);
        Socio resultV = buscarSocioPorCedula(_sociosVIP,cedula);
        Socio resultR = buscarSocioPorCedula(_socios,cedula);
        
        if(resultV==null && resultR == null){//fata validar que no exista como invitado
            JOptionPane.showMessageDialog(null, "El Ususario no existe con esa identificación", "Club",JOptionPane.ERROR_MESSAGE);
        }else{
            if(resultV !=null){
//                for(int i=0;i<_sociosVIP.length;i++)
//                {
//                    if(_sociosVIP[i] !=null)
//                    {
//                        String cedu =_sociosVIP[i].getCedula();
//                        if(cedu.equals(cedula))
//                        {
//                            _sociosVIP[i]=null;
//                            JOptionPane.showMessageDialog(null, "Se Eliminó correctamente el usuario", "Club",JOptionPane.INFORMATION_MESSAGE);
//                            eliminado = true;
//                        }
//                    }
//                   
//                }
                JOptionPane.showMessageDialog(null, "No se puede eliminar un socio de tipo VIP ", "Club",JOptionPane.INFORMATION_MESSAGE);
            }
            if(resultR !=null){
                for(int i=0;i<_socios.length;i++)
                {
                    if(_socios[i] !=null)
                    {
                        String cedu =_socios[i].getCedula();
                        if(cedu.equals(cedula))
                        {
                            Invitado[] invitados = _socios[i].getInvitados();
                            if(invitados !=null){
                                _socios[i]=null;
                                JOptionPane.showMessageDialog(null, "Se Eliminó correctamente el usuario", "Club",JOptionPane.INFORMATION_MESSAGE);
                                eliminado = true;
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "El profesor dijo que no se puede eliminar un socio con personas AUTORIZADAS", "Club",JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                   
                }
            }
        }
        return eliminado;
    }
      
    public void aumentarFondo()
    {
        String cedula = JOptionPane.showInputDialog(null, "ingrese la identificación del socio ", "Club",JOptionPane.INFORMATION_MESSAGE);
        Socio resultV = buscarSocioPorCedula(_sociosVIP,cedula);
        Socio resultR = buscarSocioPorCedula(_socios,cedula);
        if(resultV==null && resultR == null){//fata validar que no exista como invitado
            JOptionPane.showMessageDialog(null, "El Ususario no existe con esa identificación", "Club",JOptionPane.ERROR_MESSAGE);
        }else{
            if(resultV !=null){
                for(int i =0;i<_sociosVIP.length;i++){
                    if(_sociosVIP[i] !=null){
                        if(_sociosVIP[i].getCedula().equals(cedula) ){
                            double resultFondo = Double.parseDouble( JOptionPane.showInputDialog(null,"Cuanto desea ingresar para aumentar su fondo?"));
                            _sociosVIP[i].setFondo(resultFondo);
                            break;
                        }
                    }

                }
            }
             if(resultR !=null){
                for(int i =0;i<_socios.length;i++){
                    if(_socios[i] !=null){
                        if(_socios[i].getCedula().equals(cedula) ){
                            double resultFondo = Double.parseDouble( JOptionPane.showInputDialog(null,"Cuanto desea ingresar para aumentar su fondo?"));
                            _socios[i].setFondo(resultFondo);
                            break;
                        }
                    }

                }
            }
        }
        
      }
    
    public Double verConsumoPorSocio(String cedula){
        double totalConsumo =0;
        Socio resultV = buscarSocioPorCedula(_sociosVIP,cedula);
        Socio resultR = buscarSocioPorCedula(_socios,cedula);
        if(resultV!=null){
         Factura[] factura =  resultV.getFactura();
         for(int i =0;i<factura.length;i++){
            if(factura[i]!=null){
                totalConsumo = totalConsumo +factura[i].getValor();
            }

         }
        }else{
            Factura[] factura =  resultR.getFactura();
            for(int i =0;i<factura.length;i++){
            if(factura[i]!=null){
                totalConsumo = totalConsumo +factura[i].getValor();
            }

         }  
        }
        
        return totalConsumo;
    }
    
     public void registrarConsumo(){
        String cedula = JOptionPane.showInputDialog(null,"ingrese la identificación del socio del que desea registrar el consumo");
        Socio socioVip = buscarSocioPorCedula(_sociosVIP,cedula);
        Socio socio = buscarSocioPorCedula(_socios,cedula);
        if(socio != null){
            creadorDePlatos();
            if(socio.cantidadFacturas()<5){
                Factura factura = new Factura(socio.getFondo());
                socio.setFactura(factura);
                double comprado = 0;
                comprado = comprado+factura.getValor();
               double nuevoFondo =socio.getFondo() - comprado; 
                socio.setFondo(nuevoFondo);
            }else{
                JOptionPane.showInputDialog(null,"No se puede registrar otro consumo ");
            }
        }
        else if( socioVip !=null){
            if(socioVip.cantidadFacturas()<5){
                Factura factura = new Factura(socioVip.getFondo());
                socioVip.setFactura(factura);
                double comprado = 0;
                comprado = comprado+factura.getValor();
               double nuevoFondo =socioVip.getFondo() - comprado; 
                socioVip.setFondo(nuevoFondo);
            
            }else{
                JOptionPane.showInputDialog(null,"No se puede registrar otro consumo ");
            }
        }
     }
     
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
    
    private co.com.tdea.Consumo creadorBebidaCaliente() {
        Map<String, Object> adiciones = new HashMap();
        AgregarAdicionCaliente(adiciones);
        int opc = 0;
        do {
            opc = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese el numero de la adicion que desea \n"
                    + "1. Café Mocca\n"
                    + "2. Matcha\n"
                    + "3. Cappuccino\n"
                    + "0. Ninguna"));
            switch (opc) {
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
        }while(opc != 0);
    }
    
    
    private co.com.tdea.Consumo creadorDePlatosMixtos() {
         ArrayList<String> salsas= new ArrayList();
         
         agregarSalsas(salsas);
        int opc = 0;
        do {
            opc = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese el numero de la adicion que desea \n"
                    + "1. costillas BBQ\n"
                    + "2. Bandeja Paisa\n"
                    + "3. Picada Mixta\n"
                    + "0. Ninguna"));
            switch (opc) {
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
        }while(opc != 0);
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
    
    private co.com.tdea.Consumo creadorBebidasNoAlcoholicas(){
        int opc;
        do{
            opc = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese el numero de la adicion que desea \n"
                    + "1. Jugo\n"
                    + "2. Soda\n"
                    + "3. Gaseosa\n"
                   
                    + "0. para continuar"));
        switch (opc){
            case 1:{
                String nombre = "Jugo";
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
        } while(opc != 0);
     }
    
    private co.com.tdea.Consumo creadorBebidasAlcoholicas(){
        int opc;
        do{
            opc = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese el numero de la adicion que desea \n"
                    + "1. Margarita\n"
                    + "2. Mojito\n"
                    + "3. Piña Colada\n"
                   
                    + "0. para continuar"));
        
        switch (opc){
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
        }while(opc != 0);
    }

    private co.com.tdea.Consumo creadorDePostres() {
        int opc;
        do{
            opc = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese el numero de la adicion que desea \n"
                    + "1. postre tres leches\n"
                    + "2. cheesecake limon\n"
                   
                    + "0. para continuar"));
        
        switch (opc){
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
        }while(opc != 0);
    }

    private co.com.tdea.Consumo creadorDeEntradas() {
        int opc;
        do{
            opc = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese el numero de la adicion que desea \n"
                    + "1. empanadas x10\n"
                    + "2. patacones x6\n"
                   
                    + "0. para continuar"));
        
        switch (opc){
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
        }while(opc != 0);

    }

    private co.com.tdea.Consumo creadorDePlatosVeganos() {
        Map<String, Object> adiciones = new HashMap();
        agregarAdiciones(adiciones);
        int opc;
        do{
            opc = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese el numero de la adicion que desea \n"
                    + "1. Ensalada Especial\n"
                    + "2. Menú Italiano sin carne\n"
                   
                    + "0. para continuar"));
        
        switch (opc){
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
        }while(opc != 0);
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

    public co.com.tdea.Consumo creadorDePlatos() {  
                       int opc1;
        do {
            opc1 = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese el numero de la adicion que desea \n"
                    + "1. para ver postres\n"
                    + "2. para ver entradas\n"
                    + "3. para ver Platos veganos\n"
                    + "4. para ver bebidas alcoholicas\n"
                    + "5. para ver platos mixtos\n"
                    + "6. para ver bebidas frias\n"
                    + "7. para ver bebidas calientes\n"
                    + "0. para continuar"));
        switch (opc1) {
            case 1: {
                return creadorDePostres();
            }
            case 2: {
                return creadorDeEntradas();
            }
            case 3: {
                return creadorDePlatosVeganos();            
            }
            case 4: {                
                return creadorBebidasAlcoholicas();
            }
            case 5:{
                return creadorDePlatosMixtos();
            }
            case 6:{
                return creadorBebidasNoAlcoholicas();
            }
            case 7:{
                return creadorBebidaCaliente();
            }
            default: {
                return null;
            }
        }
        }while(opc1 != 0);

    }
     
    //endregion
    
    
    
    
     //#region METODOS PARA SOCIO REGULAR
     public void insertarSocio(String cedula)
    {
        if(cantidadSocios()<32)
        {   
            for(int i =0; i<_socios.length;i++){
                if(_socios[i] == null){
                     _socios[i] = new Socio(cedula,"REGULAR");
                     JOptionPane.showMessageDialog(null, "Se agregó el socio "+ _socios[i].getNombre()+", al Club");
                     break;
                }
            }
        }
         else{
            JOptionPane.showMessageDialog(null, "ya no hay mas cupos para socios VIP");
        }
    }
    
     public void insertarInvitadoSocio(String cedula, int num)
    {
        if(num==1){
            for(int i =0; i<_sociosVIP.length;i++)
            {
                if(_sociosVIP[i].getCedula() == null ? cedula == null : _sociosVIP[i].getCedula().equals(cedula))
                {
                    Invitado[] invitado = _sociosVIP[i].getInvitados();
                    for(int j =0; j<invitado.length;j++){

                        if(invitado[j] == null){
                            Invitado _invitado = new Invitado();
                            _sociosVIP[j].setInvitados(_invitado);
                            JOptionPane.showMessageDialog(null, "Se agregó el invitado al socio "+ _sociosVIP[i].getNombre());
                            break;
                        } 
                    }
                    break;
                }
            }
        }else
        {
           for(int i =0; i<_socios.length;i++)
            {
                if(_socios[i].getCedula() == null ? cedula == null : _socios[i].getCedula().equals(cedula))
                {
                    Invitado[] invitado = _socios[i].getInvitados();
                    for(int j =0; j<invitado.length;j++){

                        if(invitado[j] == null){
                            Invitado _invitado = new Invitado();
                            _socios[j].setInvitados(_invitado);
                            JOptionPane.showMessageDialog(null, "Se agregó el invitado al socio "+ _socios[i].getNombre());
                            break;
                        } 
                    }
                    break;
                }
            }
        }
    }
     
    public int cantidadSocios()
    {
       int cantidad =0;
       for (int j = 0; j < _socios.length; j++)
        {
           if(_socios[j] != null )
           {
               if(_socios[j].getTipoSuscripcion().equals("REGULAR")){
                    cantidad = cantidad+1; 
               }
           } 
        }
       return cantidad;
   }
    
     public int buscarCantidadInvitadosDelSocioCedula(String cedula)
    {
       int cantidad =0;
       for (int j = 0; j < _socios.length; j++)
        {
           if(_socios[j] != null )
           {
               if(_socios[j].getCedula().equals(cedula)){
                    cantidad = cantidad+1;
               }
           } 
        }
       return cantidad;
    }
    //#endregion
    
    
    //#region METODOS PARA INVITADO
    
    
    public int cantidadInvitados()
    {
       int cantidad =0;
       
       for (int j = 0; j < _sociosVIP.length; j++)
        {
           if(_sociosVIP[j] != null )
           {
               if(_sociosVIP[j].getTipoSuscripcion().equals("VIP")){
                    cantidad = cantidad+1; 
               }
           } 
        }
       return cantidad;
   }
    
    public Invitado[] listaInvitados()
    {
       Invitado[] listInvitado  = new Invitado[350];
       
       for(int i =0;i<listInvitado.length;i++)
       {
           if(listInvitado[i]==null)
           {
                for (int j = 0; j < _sociosVIP.length; j++)
                {
                   if(_sociosVIP[j] != null )
                   {
                      listInvitado =  _sociosVIP[j].getInvitados(); 
                      break;
                   } 
                }
           }
       }
       
       for(int i =0;i<listInvitado.length;i++)
       {
           if(listInvitado[i]==null)
           {
                for (int j = 0; j < _socios.length; j++)
                {
                   if(_socios[j] != null )
                   {
                      listInvitado =  _socios[j].getInvitados();
                      break;
                   } 
                }
           }
       }
       
       return listInvitado;
   }
    //#endregion
    
    
    public Socio buscarSocioPorCedula(Socio[] listaSocio, String cedula)
    {
        Socio encontrado = null;
        
        for (int i = 0; i < listaSocio.length; i++) {
             if (listaSocio[i] != null && listaSocio[i].getCedula().equals(cedula)) {
                 encontrado = listaSocio[i];
             }
        }
        return encontrado;
    }
   
    public String verSocios(){
       String socio = "";
       int count =0;
        for (int i = 0; i < _socios.length; i++) {
            if (_socios[i] != null) {
                count =count+1;
                socio += (count+". "+_socios[i].toString() + "\n");
            }
        }
        for (int i = 0; i < _sociosVIP.length; i++) {
            if (_sociosVIP[i] != null) {
                count =count+1;
                socio += (count+". "+_sociosVIP[i].toString() + "\n");
            }
        }
        return socio;
    }
    
    public void verInvitados(){
        String cedula = JOptionPane.showInputDialog(null, "Ingrese identificación del socio");
        
        for(int i =0;i<_socios.length;i++)
        {
            if(_socios[i]!=null)
            {
                if(_socios[i].getCedula().equals(cedula)){
                    JOptionPane.showMessageDialog(null, _socios[i].verInvitado());
                    
                }
            }
        } 
         for(int i =0;i<_sociosVIP.length;i++)
        {
            if(_sociosVIP[i]!=null)
            {
                if(_sociosVIP[i].getCedula().equals(cedula)){
                    JOptionPane.showMessageDialog(null, _sociosVIP[i].verInvitado());
                    
                }
            }
        } 
    }
}
