/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.tdea.bo;


import java.sql.*;
import org.apache.log4j.Logger;

public class UtilidadesBo {
    private static final String ERROR = "Error";
    static final Logger logger = Logger.getLogger(UtilidadesBo.class);
        
    public static Connection getConnectionApp() {
        try{
          Connection con = DriverManager.getConnection("jdbc:sqlserver://wdb4.my-hosting-panel.com:1433;user=abicomco_users;password=!oj338I6;database=abicomco_users;encrypt=true;trustServerCertificate=true");          
          logger.info("Conexion Exitosa");
          return con;
        }catch(SQLException e){
            logger.error(e.toString());
            return null;
        } catch (Exception e) {
            logger.error(e.toString());
              return null;
            }
	}
    public static String cargarDatos(String strSql) {
		String salida = null;
		CallableStatement cs = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try (Connection con = getConnectionApp()) {
                    cs = con.prepareCall(strSql);
                    cs.execute();
		} catch (Exception e) {
			logger.error(ERROR, e);
			return e.toString();
		} finally {
                        try {
                            if (rs != null)
                                rs.close();
                        } catch (Exception e) {
                                logger.error(ERROR, e);
                        }
                        try {
                            if (ps != null)
                                ps.close();
                        } catch (Exception e) {
                                logger.error(ERROR, e);
                        }
                        try {
                            if (cs != null)
                                cs.close();
                        } catch (Exception e) {
                                logger.error(ERROR, e);
                        }
		}
		return salida;
	}

    
}
