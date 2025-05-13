package foc.departamento.dao;

import foc.departamento.model.Departamento;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import util.AlertUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static util.AlertUtils.mostrarAdvertencia;
import static util.AlertUtils.mostrarConfirmacion;

/**
 * Clase que implementa las operaciones CRUD (Create, Read, Update, Delete)
 * para la entidad Departamento utilizando JDBC.
 */
public class DepartamentoDAO {
    private static final Logger LOGGER = Logger.getLogger(DepartamentoDAO.class.getName());
    private final ConexionBD conexionBD;

    /**
     * Constructor que inicializa el DAO con la instancia de conexión.
     */
    public DepartamentoDAO() {
        this.conexionBD = ConexionBD.getInstancia();
    }

    /**
     * Inserta un nuevo departamento en la base de datos.
     *
     * @param dep Departamento a insertar
     * @return true si se insertó correctamente, false en caso contrario
     * @throws SQLException Si ocurre un error en la base de datos
     */
    public boolean insertar(Departamento dep) throws SQLException {
        String sql = "INSERT INTO departamentos (codigo, nombre, id_localizacion, id_manager) VALUES (?, ?, ?, ?)";

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dep.getCodigo());
            stmt.setString(2, dep.getNombre());
            stmt.setInt(3, dep.getIdLocalizacion());
            stmt.setInt(4, dep.getIdManager());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al insertar departamento: {0}", e.getMessage());
            throw e;

        }
    }

    /**
     * Modifica un departamento existente en la base de datos.
     *
     * @param dep Departamento con los datos actualizados
     * @return true si se modificó correctamente, false en caso contrario
     * @throws SQLException Si ocurre un error en la base de datos
     */
    public boolean modificar(Departamento dep) throws SQLException {
        String sql = "UPDATE departamentos SET nombre = ?, id_localizacion = ?, id_manager = ? WHERE codigo = ?";

        if (!AlertUtils.mostrarAdvertencia("Confirmar acción", "¿Estás seguro de modificar el departamento con código: " + dep.getCodigo() + "?")) {
            return false;
        }

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dep.getNombre());
            stmt.setInt(2, dep.getIdLocalizacion());
            stmt.setInt(3, dep.getIdManager());
            stmt.setString(4, dep.getCodigo());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                mostrarConfirmacion("Confirmación", "Departamento modificado con éxito.");
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al modificar departamento: {0}", e.getMessage());
            throw e;
        }
    }


    /**
     * Elimina un departamento de la base de datos.
     *
     * @param codigo Código del departamento a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     * @throws SQLException Si ocurre un error en la base de datos
     */
    public boolean eliminar(String codigo) throws SQLException {
        String sql = "DELETE FROM departamentos WHERE codigo = ?";

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);

            if (AlertUtils.mostrarAdvertencia("Confirmar acción", "¿Estás seguro de eliminar el departamento con código: " + codigo + "?")) {
                int filasAfectadas = stmt.executeUpdate();

                if (filasAfectadas > 0) {
                    mostrarConfirmacion("Confirmación", "Departamento eliminado con éxito.");
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar departamento: {0}", e.getMessage());
            throw e;
        }
    }

    /**
     * Busca un departamento por su código.
     *
     * @param codigo Código del departamento a buscar
     * @return Departamento encontrado o null si no existe
     * @throws SQLException Si ocurre un error en la base de datos
     */
    public Departamento buscarPorCodigo(String codigo) throws SQLException {
        String sql = "SELECT * FROM departamentos WHERE codigo = ?";

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearDepartamento(rs);
                }
                return null;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al buscar departamento: {0}", e.getMessage());
            throw e;
        }
    }

    /**
     * Lista todos los departamentos de la base de datos.
     *
     * @return Lista de departamentos
     * @throws SQLException Si ocurre un error en la base de datos
     */
    public List<Departamento> listarTodos() throws SQLException {
        List<Departamento> departamentos = new ArrayList<>();
        String sql = "SELECT * FROM departamentos";

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                departamentos.add(mapearDepartamento(rs));
            }
            return departamentos;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al listar departamentos: {0}", e.getMessage());
            throw e;
        }
    }

    /**
     * Mapea un ResultSet a un objeto Departamento.
     *
     * @param rs ResultSet con los datos del departamento
     * @return Objeto Departamento con los datos mapeados
     * @throws SQLException Si ocurre un error en el mapeo
     */
    @NotNull
    @Contract("_ -> new")
    private Departamento mapearDepartamento(ResultSet rs) throws SQLException {
        return new Departamento(
                rs.getString("codigo"),
                rs.getString("nombre"),
                rs.getInt("id_localizacion"),
                rs.getInt("id_manager")
        );
    }
}