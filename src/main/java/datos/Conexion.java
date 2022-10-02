package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    private static Connection conexion = null;
    private static final String url = "jdbc:mysql://localhost:3306/pruebas?useSSL=false";
    private static final String user = "root";
    private static final String password = "yamidheli";

    public static Connection getConnection() throws SQLException {
        conexion = DriverManager.getConnection(url, user, password);

        return conexion;
    }
    
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    public static void close(Statement smtm) throws SQLException{
        smtm.close();
    }
    public static void close(Connection conn) throws SQLException{
        conn.close();
    }
    
}
