package domain;

import datos.Conexion;
import static datos.Conexion.*;
import domain.Persona;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonaDAO {

    private static final String SELECT_SQL = "SELECT * FROM persona";
    private static final String INSERT_SQL = "INSERT INTO persona(nombre, apellido,email,telefono) VALUES(?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE persona SET nombre = ?, apellido=?, email=?,telefono=? WHERE (idpersona = ?)";
    private static final String DELETE_SQL = "DELETE FROM persona WHERE (`idpersona` = ?)";

    public List<Persona> seleccionar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SELECT_SQL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idPersona = rs.getInt("idpersona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                int telefono = rs.getInt("telefono");
                persona = new Persona(idPersona, nombre, apellido, email, telefono);
                personas.add(persona);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        } finally {
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return personas;
    }

    public int insertar(Persona persona) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int registros = 0;

        try {
            conn = getConnection();
            registros = stmt.executeUpdate();
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setInt(4, persona.getTelefono());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }
        return registros;
    }

    public int editar(Persona persona) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(UPDATE_SQL);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setInt(4, persona.getTelefono());
            stmt.setInt(5, persona.getIdpersona());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            return registros;
        }

    }

    public int eliminar(Persona persona) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(DELETE_SQL);
            stmt.setInt(1, persona.getIdpersona());
            registros = stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }
        return registros;
    }
}
