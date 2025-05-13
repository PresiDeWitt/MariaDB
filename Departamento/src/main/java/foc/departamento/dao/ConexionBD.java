package foc.departamento.dao;

import foc.departamento.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase singleton que gestiona la conexión a la base de datos.
 * Implementa el patrón Singleton para asegurar una única instancia de conexión.
 */
public class ConexionBD {
    private static final Logger LOGGER = Logger.getLogger(ConexionBD.class.getName());
    private static ConexionBD instancia;
    private Connection conexion;

    /**
     * Constructor privado para implementar el patrón Singleton.
     * Carga el driver JDBC al inicializar.
     */
    private ConexionBD() {
        try {
            // Cargar el driver
            Class.forName(DatabaseConfig.DRIVER);
            LOGGER.info("Driver de base de datos cargado correctamente");
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al cargar el driver: {0}", e.getMessage());
        }
    }

    /**
     * Obtiene la única instancia de la clase (Singleton).
     *
     * @return Instancia única de ConexionBD
     */
    public static synchronized ConexionBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    /**
     * Obtiene una conexión a la base de datos.
     * Si la conexión está cerrada o es nula, crea una nueva.
     *
     * @return Conexión a la base de datos
     * @throws SQLException Si ocurre un error al establecer la conexión
     */
    public Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            try {
                // Reconectar si es necesario
                conexion = DriverManager.getConnection(
                        DatabaseConfig.URL,
                        DatabaseConfig.USUARIO,
                        DatabaseConfig.PASSWORD
                );
                LOGGER.info("Conexión establecida con éxito");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error al conectar con la base de datos: {0}", e.getMessage());
                throw e;
            }
        }
        return conexion;
    }

    /**
     * Cierra la conexión a la base de datos.
     */
    public void cerrarConexion() {
        if (conexion != null) {
            try {
                if (!conexion.isClosed()) {
                    conexion.close();
                    LOGGER.info("Conexión cerrada correctamente");
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error al cerrar la conexión: {0}", e.getMessage());
            } finally {
                conexion = null;
            }
        }
    }
}