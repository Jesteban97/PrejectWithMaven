/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.tdea;

import com.tdea.Conexion_BD;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author NMEJIA
 */
public class ClubDao {

    public Map<String, Object> buscarSocio(int cedula) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String, Object> resultado = new HashMap<String, Object>();
        Connection con = Conexion_BD.getConexion();
        try {
            
            ps = con.prepareStatement("SELECT CEDULA, TIPODESUSCRIPCION,FONDOS,NOMBRE FROM SOCIO WHERE CEDULA = ?");
            ps.setString(1, Integer.toString(cedula));
            rs = ps.executeQuery();
            if (rs.next()) {
                resultado.put("cedula", rs.getObject("CEDULA"));
                resultado.put("fondos", rs.getDouble("FONDOS"));
                resultado.put("nombre", rs.getString("NOMBRE"));
                resultado.put("tipoDeSuscipcion", rs.getString("TIPODESUSCRIPCION"));
            }
        }  finally {
            ps.close();
            rs.close();
            con.close();
        }

        return resultado;
    }

}
