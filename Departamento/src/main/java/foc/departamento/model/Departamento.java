package foc.departamento.model;

/**
 * Clase que representa la entidad Departamento.
 * Encapsula los datos de un departamento empresarial.
 */
public class Departamento {
    private String codigo;
    private String nombre;
    private int idLocalizacion;
    private int idManager;

    /**
     * Constructor vacío
     */
    public Departamento() {}

    /**
     * Constructor con parámetros para crear un departamento completo
     *
     * @param codigo Código único del departamento
     * @param nombre Nombre del departamento
     * @param idLocalizacion ID de la localización del departamento
     * @param idManager ID del gerente responsable del departamento
     */
    public Departamento(String codigo, String nombre, int idLocalizacion, int idManager) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.idLocalizacion = idLocalizacion;
        this.idManager = idManager;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdLocalizacion() {
        return idLocalizacion;
    }

    public int getIdManager() {
        return idManager;
    }

    // Setters
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdLocalizacion(int idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }

    @Override
    public String toString() {
        return "Código: " + codigo +
                "\nNombre: " + nombre +
                "\nID Localización: " + idLocalizacion +
                "\nID Manager: " + idManager;
    }
}