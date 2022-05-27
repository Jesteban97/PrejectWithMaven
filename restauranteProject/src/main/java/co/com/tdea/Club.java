/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.tdea;

import com.tdea.Conexion_BD;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TdeA
 */
public class Club {

    private String nombre;
    private Socio[] sociosVip;
    private Socio[] sociosRegulares;

    public Club(String nombre) {
        this.nombre = nombre;
        this.sociosVip = new Socio[3];
        this.sociosRegulares = new Socio[10];
        System.out.println("se a creado el club");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Socio[] getSociosVip() {
        return sociosVip;
    }

    public void setSociosVip(Socio[] sociosVip) {
        this.sociosVip = sociosVip;
    }

    public Socio[] getSociosRegulares() {
        return sociosRegulares;
    }

    public void setSociosRegulares(Socio[] sociosRegulares) {
        this.sociosRegulares = sociosRegulares;
    }

    public Socio buscarCedula(int cedula) {

        /*Socio encontrado = null;
        for (int i = 0; i < sociosVip.length; i++) {
            if (sociosVip[i] != null && sociosVip[i].getCedula() == cedula) {
                encontrado = sociosVip[i];
            }
        }
        if (encontrado == null) {
            for (int i = 0; i < sociosRegulares.length; i++) {
                if (sociosRegulares[i] != null && sociosRegulares[i].getCedula() == cedula) {
                    encontrado = sociosRegulares[i];
                }
            }
        }*/
        ClubDao clubDao = new ClubDao();
        try {
            Map<String,Object> resultado = clubDao.buscarSocio(cedula);
            if(!resultado.isEmpty()){
                return new Socio(Integer.parseInt(resultado.get("cedula").toString()),resultado.get("tipoDeSuscripcion").toString(),Double.parseDouble(resultado.get("fondos").toString()),resultado.get("nombre").toString());
            }
        } catch (SQLException ex) {
           
        }
        
        return null;
    }

    public boolean sociosVipNulo() {
        for (int i = 0; i < sociosVip.length; i++) {
            if (sociosVip[i] == null) {
                return true;
            }
        }
        return false;
    }

    public boolean sociosRegularNulo() {
        for (int i = 0; i < sociosRegulares.length; i++) {
            if (sociosRegulares[i] == null) {
                return true;
            }
        }
        return false;
    }

    public void inscribirSocio(int cedula, String tipoDeSuscripcion, String nombre) {
        if (buscarCedula(cedula) == null) {
            boolean registro = false;
            //int tipo = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese: \n 1. para Vip \n 2. para regular"));
            if (tipoDeSuscripcion.equals("VIP") && sociosVipNulo()) {
                //String nombre = JOptionPane.showInputDialog(null, "ingrese su nombre");
                Socio socio = new Socio(cedula, "Vip", 100000, nombre);
                for (int i = 0; i < sociosVip.length; i++) {
                    if (sociosVip[i] == null && !registro) {
                        sociosVip[i] = socio;

                        registro = true;
                        JOptionPane.showMessageDialog(null, "Se Registro el Socio correctamente");
                    }
                }
            }
            if (tipoDeSuscripcion.equals("REGULAR") && sociosRegularNulo() && !registro) {
                //String nombre = JOptionPane.showInputDialog(null, "ingrese su nombre");
                Socio socio = new Socio(cedula, "Regular", 50000, nombre);
                for (int i = 0; i < sociosRegulares.length; i++) {
                    if (sociosRegulares[i] == null && !registro) {
                        sociosRegulares[i] = socio;
                        registro = true;
                        JOptionPane.showMessageDialog(null, "Se Registro el Socio correctamente");
                    }
                }
            }
            if (tipoDeSuscripcion.equals("VIP") && !sociosVipNulo() && sociosRegularNulo() && !registro) {
                JOptionPane.showMessageDialog(null, "lastimosamente no hay cupo VIP, por favor hacer el registro regular");
            }
            if (tipoDeSuscripcion.equals("REGULAR") && !sociosRegularNulo() && sociosVipNulo() && !registro) {
                JOptionPane.showMessageDialog(null, "lastimosamente no hay cupo Regular \n ingrese 1 para hacer el registro Vip");
            }
            if (!sociosRegularNulo() && !sociosVipNulo()) {
                JOptionPane.showMessageDialog(null, "no hay cupos disponibles");
            }
        } else {
            JOptionPane.showMessageDialog(null, "ya existe alguien con esa cedula");
        }
    }

    public void registrarConsumoSocio(int cedula, int opMenu, int dato) {
        //int cedula = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese su cedula"));
        Socio socio = buscarCedula(cedula);

        if (socio == null) {
            JOptionPane.showMessageDialog(null, "no hay ningun socio registrado con la cedula");
        } else {
            socio.registrarConsumo(opMenu, dato);
        }
    }

    public void registrarConsumoAsociado(int cedulaSocio, int cedulaAsociado, int opMenu, int dato) {

        Socio socio = buscarCedula(cedulaSocio);
        if (socio != null) {
            PersonaAutorizada asociado = socio.buscarCedula(cedulaAsociado);
            if (asociado != null) {
                asociado.registrarConsumoAsociado(opMenu, dato, socio);
                //JOptionPane.showInputDialog(null,"Se a registrado consumo");
                //System.out.println(asociado.mostrarFacturasAsociado().toString());
            } else {
                JOptionPane.showInputDialog(null, "no existe un Asociado registrado con esa Cedula");
            }
        } else {
            JOptionPane.showInputDialog(null, "no Existe un socio registrado con esa Cedula");
        }

    }

    public String BuscarfacturaSocio(int cedula) {

        Socio socio = buscarCedula(cedula);

        if (socio == null) {
            JOptionPane.showMessageDialog(null, "no hay ningun socio registrado con la cedula");
            return "no hay ningun socio registrado con la cedula";
        } else {
            return socio.mostrarFacturas();

        }

    }

    public String buscarfacturaAsociado(int cedulaSocio, int cedulaAsociado) {
        String encontrado = "no existe el socio";
        Socio socio = buscarCedula(cedulaSocio);
        PersonaAutorizada personaAutorizada = socio.buscarCedula(cedulaAsociado);

        if (socio != null) {
            PersonaAutorizada asociado = socio.buscarCedula(cedulaAsociado);
            if (asociado != null) {
                encontrado = asociado.mostrarFacturasAsociado();
                //JOptionPane.showInputDialog(null,"Se a registrado consumo");
            } else {
                JOptionPane.showInputDialog(null, "no existe un Asociado registrado con esa Cedula");
            }
        } else {
            JOptionPane.showInputDialog(null, "no Existe un socio registrado con esa Cedula");
        }

        return encontrado;

    }

    public void pagarFactura(int cedula, int posicion) {
        //int cedula = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese su cedula"));
        Socio socio = buscarCedula(cedula);
        if (socio == null) {
            JOptionPane.showMessageDialog(null, "no hay ningun socio registrado con la cedula");
        } else {
            this.imprimirFactura(cedula, posicion);
            socio.pagarFacturaSocio(cedula, posicion);

        }
    }

    public void pagarFacturaAsociado(int cedulaSocio, int cedulaAsociado, int posicion) {

        Socio socio = buscarCedula(cedulaSocio);
        if (socio != null) {
            PersonaAutorizada asociado = socio.buscarCedula(cedulaAsociado);
            if (asociado != null) {
                asociado.pagarFacturaAsociado(socio, cedulaAsociado, posicion);

            } else {
                JOptionPane.showMessageDialog(null, "no existe un Asociado registrado con esa Cedula");
            }
        } else {
            JOptionPane.showMessageDialog(null, "no Existe un socio registrado con esa Cedula");
        }
    }

    public void eliminarFacturaSocio(int cedula, int posicion) {
        //int cedula = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese su cedula"));
        Socio socio = buscarCedula(cedula);
        if (socio == null) {
            JOptionPane.showMessageDialog(null, "no hay ningun socio registrado con la cedula");
        } else {
            socio.eliminarFacturaSocio(cedula, posicion);

        }
    }

    public void eliminarFacturaAsociado(int cedulaSocio, int cedulaAsociado, int posicion) {

        Socio socio = buscarCedula(cedulaSocio);
        if (socio != null) {
            PersonaAutorizada asociado = socio.buscarCedula(cedulaAsociado);
            if (asociado != null) {
                asociado.eliminarFacturaAsociado(socio, cedulaAsociado, posicion);

            } else {
                JOptionPane.showMessageDialog(null, "no existe un Asociado registrado con esa Cedula");
            }
        } else {
            JOptionPane.showMessageDialog(null, "no Existe un socio registrado con esa Cedula");
        }
    }

    @Override
    public String toString() {
        return "Club: " + nombre;
    }

    public String mostrarCedula(int cedula) {

        String encontrado = "no existe el socio";
        Socio socio = buscarCedula(cedula);
        if (socio != null) {
            encontrado = socio.toString();
        }
        return encontrado;
    }

    public void aumentarRecursos(int cedula, double CantidadRecursos) {
        // Variable para idenditicar el Socio
        Socio socio = buscarCedula(cedula);
        //Variable donde se almacenara los nuevos fondos a asignar al Socio
        double fondosNew = 0;

        // condicion para validar si el socio si existe
        if (socio != null) {
            // Variable para traer los Fondos Actuales del Socio    
            double fondosActuales = socio.getFondos();
            // Variable para traer el tipo de suscripcion actual del socio 
            String suscripcion = socio.getTipoDeSuscripcion();

            //Operacion para Calcular en cuanto quedarian los fondos, teniendo encuenta los actuales mas los que se quieren ingresar 
            fondosNew = fondosActuales + CantidadRecursos;

            switch (suscripcion) {
                case "Vip":
                    if (fondosNew <= 5000000) {
                        socio.setFondos(fondosNew);
                        JOptionPane.showMessageDialog(null, "Se a cargado correctamente los Fondos al Socio " + socio.getNombre().toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "no se puede cargar por que excedería el tope autorizado para el Socio ");
                    }
                    break;
                case "Regular":
                    if (fondosNew <= 1000000) {
                        socio.setFondos(fondosNew);
                        JOptionPane.showMessageDialog(null, "Se a cargado correctamente los Fondos al Socio " + socio.getNombre().toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "no se puede cargar por que excedería el tope autorizado por Socio ");

                    }
                    break;
            }

        } else {
            JOptionPane.showMessageDialog(null, "no hay ningun socio registrado con la cedula");
        }

    }

    public String mostrarCedulaAutorizada(int cedulaPersonaAutorizada, int cedulaSocio) {

        String encontrado = "no existe el socio";

        Socio socio = buscarCedula(cedulaSocio);
        PersonaAutorizada personaAutorizada = socio.buscarCedula(cedulaPersonaAutorizada);
        if (personaAutorizada != null) {
            encontrado = personaAutorizada.toString();
        }
        return encontrado;
    }

    public void EliminarSocio(int cedula) {

        String encontrado = null;
        Socio socio = buscarCedula(cedula);
        int finCiclo = 0;

        for (int i = 0; i < sociosVip.length; i++) {
            if (sociosVip[i] != null && sociosVip[i].getCedula() == cedula) {

                encontrado = "encontrado";
                JOptionPane.showMessageDialog(null, "Los Socios VIP no se pueden Eliminar");
            }
        }

        if (encontrado == null) {

            for (int i = 0; i < sociosRegulares.length; i++) {
                if (sociosRegulares[i] != null && sociosRegulares[i].getCedula() == cedula) {
                    if (sociosRegulares[i].buscarFacturas().equals("") && sociosRegulares[i].contarAsociados() <= 1) {
                        if (sociosRegulares[i].contarAsociados() == 1) {
                            PersonaAutorizada personaAutorizada = socio.buscarCedula(socio.traerAsociado());
                            //int cAutorizada = personaAutorizada.getCedula();
                            if (personaAutorizada.contarFacturasAsociado() == 0) {
                                sociosRegulares[i] = null;
                                JOptionPane.showMessageDialog(null, "Se ha Eliminado el Socio  y el Asociado");
                                return;
                                //finCiclo = 1;

                            } else {
                                JOptionPane.showMessageDialog(null, "El asociado aun tiene facturas pendientes");
                            }
                        } else {
                            sociosRegulares[i] = null;
                            JOptionPane.showMessageDialog(null, "Se ha Eliminado el Socio");
                            return;
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "No se puede eliminar");
                    }
                }

            }

        }

    }

    public void eliminarAsociado(int socio, int cedulaAsociado) {

        Socio socioP = buscarCedula(socio);

        if (socioP != null) {
            PersonaAutorizada personaAutorizada = socioP.buscarCedula(cedulaAsociado);
            if (personaAutorizada != null && personaAutorizada.mostrarFacturasAsociado().equals("")) {
                socioP.eliminarPersonaAutorizada(cedulaAsociado);

            } else {
                JOptionPane.showMessageDialog(null, "No se puede eliminar ");
            }
        } else {
            JOptionPane.showMessageDialog(null, "no Existe un socio registrado con esa Cedula");
        }

    }

    public void inscribirPersonaAutorizada(String nombreAut, int cedulaAutorizada, int cedulaSocio) {

        if (buscarCedula(cedulaAutorizada) == null) {
            Socio socio = buscarCedula(cedulaSocio);
            if (socio == null) {
                JOptionPane.showMessageDialog(null, "no existe un socio con esa cedula");
            } else {
                socio.registrarPersonaAutorizada(nombreAut, cedulaAutorizada, cedulaSocio);

                System.out.println(socio.traerAsociado());

            }
        } else {
            JOptionPane.showMessageDialog(null, "esa cedula pertenece al socio " + buscarCedula(cedulaAutorizada).getNombre());
        }
    }

    //**************************************************************************************************
    public void CargarPersonaAutorizada(String nombreAut, int cedulaAutorizada, int cedulaSocio) throws IllegalAccessException {

        if (buscarCedula(cedulaAutorizada) == null) {
            Socio socio = buscarCedula(cedulaSocio);
            if (socio == null) {
                throw new IllegalAccessException("no existe un socio con esa cedula");
            } else {
                //socio.registrarPersonaAutorizada(nombreAut,cedulaAutorizada,cedulaSocio );
                socio.CargarPersonaAutorizada(nombreAut, cedulaAutorizada, cedulaSocio);
                System.out.println(socio.traerAsociado());
            }
        } else {
            throw new IllegalAccessException("esa cedula pertenece al socio " + buscarCedula(cedulaAutorizada).getNombre());
        }
    }

    public String EncontrarFacturaAsociado(int cedulaSocio, int cedulaAsociado, int posicion) {
        String Encontrado = "";
        Socio socio = buscarCedula(cedulaSocio);
        if (socio != null) {
            PersonaAutorizada asociado = socio.buscarCedula(cedulaAsociado);
            if (asociado != null) {
                Encontrado = asociado.ImprimirFacturaAsociado(socio, cedulaAsociado, posicion);

            } else {
                Encontrado = "no existe un Asociado r"
                        + "egistrado con esa Cedula";
            }
        } else {
            Encontrado = "no Existe un socio registrado con esa Cedula";
        }

        return Encontrado;
    }

    public String EncontrarfacturaSocio(int cedula, int posicion) {

        Socio socio = buscarCedula(cedula);
        if (socio == null) {
            return "no hay ningun socio registrado con la cedula";
        } else {

            return socio.EncontrarFactura(cedula, posicion);

        }

    }

    public void imprimirFactura(int cedula, int fac) {
        try {
            String rutaArchivo = "./Facturas";
            String contenido = this.EncontrarfacturaSocio(cedula, fac);
            File factura = new File(rutaArchivo);
            if (factura.mkdir()) {
                System.out.println("Carpeta Creada");
            } else {
                System.out.println("La carpeta ya existe");
            }
            rutaArchivo += "/nombreArchivo.txt";
            factura = new File(rutaArchivo);
            if (!factura.exists()) {
                factura.createNewFile();
            }
            FileWriter fw = new FileWriter(factura);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void imprimirFacturaAsociado(int cedulaSocio, int cedulaAsociado, int posicion) {
        try {
            String rutaArchivo = "./FacturasAsociados";
            String contenido = this.EncontrarFacturaAsociado(cedulaSocio, cedulaAsociado, posicion);
            File factura = new File(rutaArchivo);
            if (factura.mkdir()) {
                System.out.println("Carpeta Creada");
            } else {
                System.out.println("La carpeta ya existe");
            }
            rutaArchivo += "/Factura_Asociado.txt";
            factura = new File(rutaArchivo);
            if (!factura.exists()) {
                factura.createNewFile();
            }
            FileWriter fw = new FileWriter(factura);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //**************************************Base de Datos*****************************************
    public boolean DispoSociosVIP() {
        boolean cuposDisponibles = false;
        int cantidad = 0;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conexion = Conexion_BD.getConexion();
            ps = conexion.prepareStatement("SELECT COUNT(cedula) AS CANTIDAD FROM Socio WHERE tipoDeSuscripcion = 'VIP'");
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("CANTIDAD"));
                cantidad = Integer.parseInt(rs.getString("CANTIDAD"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        if (cantidad < 3) {
            cuposDisponibles = true;
        }

        return cuposDisponibles;
    }

    public boolean DispoSociosReg() {
        boolean cuposDisponibles = false;
        int cantidad = 0;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conexion = Conexion_BD.getConexion();
            ps = conexion.prepareStatement("SELECT COUNT(cedula) AS CANTIDAD FROM Socio WHERE tipoDeSuscripcion = 'REGULAR'");
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("CANTIDAD"));
                cantidad = Integer.parseInt(rs.getString("CANTIDAD"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        if (cantidad < 10) {
            cuposDisponibles = true;
        }
        return cuposDisponibles;
    }

    public boolean BuscarSocio(int cedula) {
        boolean encontrado = false;
        String cc = String.valueOf(cedula);
//        String socio = "";
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection conexion = Conexion_BD.getConexion();
            ps = conexion.prepareStatement("SELECT cedula FROM Socio WHERE cedula = ?");
            ps.setString(1, cc);
            rs = ps.executeQuery();
            while (rs.next()) {
//                socio = rs.getString("cedula");
                encontrado = true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

        System.out.println(encontrado);

        return encontrado;
    }

    public void inscribirSocioBD(int cedula, String tipoDeSuscripcion, String nombre) {
        String cc = String.valueOf(cedula);
        if (!BuscarSocio(cedula)) {
            boolean registro = false;
            if (tipoDeSuscripcion.equals("VIP") && DispoSociosVIP()) {
                //Socio socio = new Socio(cedula, "VIP", 100000, nombre);
                try {
                    Connection con = Conexion_BD.getConexion();
                    PreparedStatement ps = con.prepareStatement("INSERT INTO Socio (cedula, tipoDeSuscripcion, fondos, nombre) VALUES (?,?,?,?)");
                    int i = 1;
                    ps.setString(i++, cc);
                    ps.setString(i++, "VIP");
                    ps.setString(i++, "100000");
                    ps.setString(i++, nombre);
                    //ps.executeQuery();
                    ps.executeLargeUpdate();
                    JOptionPane.showMessageDialog(null, "Registro guardado");
                    registro = true;
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
               
            }
            if (tipoDeSuscripcion.equals("REGULAR") && DispoSociosReg() && !registro) {
                try {
                    Connection con = Conexion_BD.getConexion();
                    PreparedStatement ps = con.prepareStatement("INSERT INTO Socio (cedula, tipoDeSuscripcion, fondos, nombre) VALUES (?,?,?,?)");
                    int i = 1;
                    ps.setString(i++, cc);
                    ps.setString(i++, "REGULAR");
                    ps.setString(i++, "50000");
                    ps.setString(i++, nombre);
                    ps.executeLargeUpdate();
                    JOptionPane.showMessageDialog(null, "Registro guardado");
                    registro = true;
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
              
            }

            if (tipoDeSuscripcion.equals("VIP") && !registro && !DispoSociosVIP()) {
                JOptionPane.showMessageDialog(null, "lastimosamente no hay cupo VIP");
            }
            if (tipoDeSuscripcion.equals("REGULAR") && !DispoSociosReg() && !registro) {
                JOptionPane.showMessageDialog(null, "lastimosamente no hay cupo Regular");
            }
            if (!DispoSociosVIP() && !DispoSociosReg()) {
                JOptionPane.showMessageDialog(null, "no hay cupos disponibles");
            }
        } else {
            JOptionPane.showMessageDialog(null, "ya existe alguien con esa cedula");
        }
    }

    public void EliminarSocioBD(int cedula) {
        String cc = String.valueOf(cedula);
        if (BuscarSocio(cedula)) {
            try {
                Connection con = Conexion_BD.getConexion();
                PreparedStatement ps = con.prepareStatement("DELETE FROM Socio  WHERE cedula=? ");
                ps.setString(1, cc);
                ps.executeLargeUpdate();
                JOptionPane.showMessageDialog(null, "Registro Eliminado");

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay un socio registrado con esa celuda");
        }

    }

    public void aumentarRecursosSocioBD(int cedula, double CantidadRecursos) {
        String cc = String.valueOf(cedula);

        //Variable donde se almacenara los nuevos fondos a asignar al Socio
        double fondosNew = 0;
        double fondosActuales = 0;
        String suscripcion = "";
        // condicion para validar si el socio si existe
        if (BuscarSocio(cedula)) {
            PreparedStatement ps;
            ResultSet rs;
            /*
            Primero busco el los fondos actuales y el tipo de suscripcion 
            para poder validar si lo actual mas lo que va a ingresar
            */
            try {
                Connection conexion = Conexion_BD.getConexion();
                ps = conexion.prepareStatement("SELECT fondos,tipoDeSuscripcion FROM Socio WHERE cedula = ?");
                ps.setString(1, cc);
                rs = ps.executeQuery();
                while (rs.next()) {
                    fondosActuales = Double.parseDouble(rs.getString("fondos"));
                    suscripcion = rs.getString("tipoDeSuscripcion");
                }

            } catch (SQLException e) {
                System.out.println("Error al traer los datos del socio");
                JOptionPane.showMessageDialog(null, e.toString());
            }
            
            /*
           lo agregue en otro try para validar por aparte el select y el Update 
            */

            try {

                Connection conexion = Conexion_BD.getConexion();
                ps = conexion.prepareStatement("update Socio set fondos=? where cedula=?");
                fondosNew = fondosActuales + CantidadRecursos;
                int i = 1;
                ps.setString(i++, String.valueOf(fondosNew));
                ps.setString(i++, cc);

                if (suscripcion.equals("VIP") && fondosNew <= 5000000) {
                    ps.executeLargeUpdate();
                    JOptionPane.showMessageDialog(null, "Recursos Aumentados a Socio vip");

                } else if (suscripcion.equals("REGULAR") && fondosNew <= 1000000) {                    
                    ps.executeLargeUpdate();
                    JOptionPane.showMessageDialog(null, "Recursos Aumentados a Socio Regular");

                }else{
                    JOptionPane.showMessageDialog(null, "Supera tope autorizado para el tipo de suscripcion");
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
                System.out.println("Error al actualizar el los fondos");
            }

        } else {
            JOptionPane.showMessageDialog(null, "no hay ningun socio registrado con la cedula");
        }

    }

}
