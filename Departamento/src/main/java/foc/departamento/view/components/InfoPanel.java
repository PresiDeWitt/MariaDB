package foc.departamento.view.components;

import foc.departamento.model.Departamento;
import foc.departamento.view.util.StyleConstants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Panel para mostrar información de departamentos.
 * Encapsula la visualización de datos y botones de búsqueda.
 */
public class InfoPanel extends VBox {
    private TextArea txtAreaDatos;
    private Button btnBuscar;
    private Button btnMostrarTodo;
    private Button btnLimpiar;
    private TableView<Departamento> tablaDepartamentos;

    public InfoPanel() {
        super(15); // Espaciado vertical entre componentes
        setPadding(new Insets(15));
        setStyle(StyleConstants.PANEL_STYLE);

        inicializarComponentes();
        configurarEstilos();
    }

    /**
     * Inicializa los componentes del panel
     */
    private void inicializarComponentes() {
        // Título del panel
        Label lblTitulo = new Label("Información del Departamento");
        lblTitulo.setStyle(StyleConstants.HEADER_STYLE);

        // Inicializar la tabla de departamentos
        inicializarTabla();

        // Área de texto para mostrar información detallada
        txtAreaDatos = new TextArea();
        txtAreaDatos.setEditable(false);
        txtAreaDatos.setWrapText(true);
        txtAreaDatos.setPrefHeight(150);
        txtAreaDatos.setStyle("-fx-font-family: monospace; -fx-font-size: 13px;");

        // Panel de botones
        HBox panelBotones = new HBox(10);
        panelBotones.setAlignment(Pos.CENTER);
        panelBotones.setPadding(new Insets(15, 0, 0, 0));

        // Botón para buscar por código
        btnBuscar = new Button("Buscar por Código");
        btnBuscar.setStyle(StyleConstants.BUTTON_STYLE);
        btnBuscar.setPrefWidth(150);

        // Botón para mostrar todos los departamentos
        btnMostrarTodo = new Button("Mostrar Todo");
        btnMostrarTodo.setStyle(StyleConstants.BUTTON_STYLE);
        btnMostrarTodo.setPrefWidth(150);

        // Botón para limpiar
        btnLimpiar = new Button("Limpiar");
        btnLimpiar.setStyle(StyleConstants.BUTTON_STYLE);
        btnLimpiar.setPrefWidth(150);

        panelBotones.getChildren().addAll(btnBuscar, btnMostrarTodo, btnLimpiar);
        getChildren().addAll(lblTitulo, tablaDepartamentos, txtAreaDatos, panelBotones);

        // Configurar el listener para la selección de la tabla
        configurarSeleccionTabla();
    }

    /**
     * Inicializa la tabla de departamentos y sus columnas
     */
    private void inicializarTabla() {
        tablaDepartamentos = new TableView<>();

        // Configurar columnas
        TableColumn<Departamento, String> colCodigo = new TableColumn<>("Código");
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colCodigo.setPrefWidth(100);

        TableColumn<Departamento, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNombre.setPrefWidth(200);

        TableColumn<Departamento, Integer> colLocalizacion = new TableColumn<>("Localización");
        colLocalizacion.setCellValueFactory(new PropertyValueFactory<>("idLocalizacion"));
        colLocalizacion.setPrefWidth(100);

        TableColumn<Departamento, Integer> colManager = new TableColumn<>("Manager");
        colManager.setCellValueFactory(new PropertyValueFactory<>("idManager"));
        colManager.setPrefWidth(100);

        // Añadir columnas a la tabla
        tablaDepartamentos.getColumns().addAll(colCodigo, colNombre, colLocalizacion, colManager);

        // Configurar la tabla para que ocupe todo el espacio disponible
        VBox.setVgrow(tablaDepartamentos, Priority.ALWAYS);
        tablaDepartamentos.setPrefHeight(250);
    }

    /**
     * Configura el evento de selección de la tabla
     */
    private void configurarSeleccionTabla() {
        tablaDepartamentos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                mostrarDetalleDepartamento(newSelection);
            }
        });
    }

    /**
     * Muestra los detalles de un departamento seleccionado en el área de texto
     * @param departamento El departamento seleccionado
     */
    private void mostrarDetalleDepartamento(Departamento departamento) {
        StringBuilder info = new StringBuilder();
        info.append("DETALLES DEL DEPARTAMENTO\n");
        info.append("==========================\n");
        info.append("Código: ").append(departamento.getCodigo()).append("\n");
        info.append("Nombre: ").append(departamento.getNombre()).append("\n");
        info.append("ID Localización: ").append(departamento.getIdLocalizacion()).append("\n");
        info.append("ID Manager: ").append(departamento.getIdManager()).append("\n");

        txtAreaDatos.setText(info.toString());
    }

    /**
     * Configura los estilos de los componentes
     */
    private void configurarEstilos() {
        // Efectos hover para los botones
        btnBuscar.setOnMouseEntered(e ->
                btnBuscar.setStyle(StyleConstants.BUTTON_STYLE_HOVER));
        btnBuscar.setOnMouseExited(e ->
                btnBuscar.setStyle(StyleConstants.BUTTON_STYLE));

        btnMostrarTodo.setOnMouseEntered(e ->
                btnMostrarTodo.setStyle(StyleConstants.BUTTON_STYLE_HOVER));
        btnMostrarTodo.setOnMouseExited(e ->
                btnMostrarTodo.setStyle(StyleConstants.BUTTON_STYLE));

        btnLimpiar.setOnMouseEntered(e ->
                btnLimpiar.setStyle(StyleConstants.BUTTON_STYLE_HOVER));
        btnLimpiar.setOnMouseExited(e ->
                btnLimpiar.setStyle(StyleConstants.BUTTON_STYLE));
    }

    /**
     * Actualiza los datos de la tabla con una nueva lista de departamentos
     * @param departamentos Lista de departamentos para mostrar en la tabla
     */
    public void actualizarTabla(List<Departamento> departamentos) {
        ObservableList<Departamento> lista = FXCollections.observableArrayList(departamentos);
        tablaDepartamentos.setItems(lista);
    }

    /**
     * Obtiene el departamento seleccionado en la tabla
     * @return El departamento seleccionado o null si no hay selección
     */
    public Departamento getDepartamentoSeleccionado() {
        return tablaDepartamentos.getSelectionModel().getSelectedItem();
    }

    /**
     * Selecciona un departamento en la tabla por su código
     * @param codigo Código del departamento a seleccionar
     * @return true si se encontró y seleccionó, false en caso contrario
     */
    public boolean seleccionarDepartamentoPorCodigo(String codigo) {
        if (codigo == null || codigo.isEmpty()) return false;

        for (Departamento dept : tablaDepartamentos.getItems()) {
            if (dept.getCodigo().equals(codigo)) {
                tablaDepartamentos.getSelectionModel().select(dept);
                tablaDepartamentos.scrollTo(dept);
                return true;
            }
        }
        return false;
    }

    /**
     * Deselecciona cualquier fila seleccionada en la tabla
     */
    public void limpiarSeleccion() {
        tablaDepartamentos.getSelectionModel().clearSelection();
    }

    /**
     * Muestra información en el área de texto
     * @param contenido Texto a mostrar
     */
    public void mostrarInformacion(String contenido) {
        txtAreaDatos.setText(contenido);
    }

    /**
     * Limpia el área de texto y la selección de la tabla
     */
    public void limpiar() {
        txtAreaDatos.clear();
        limpiarSeleccion();
    }

    // Getters para los componentes
    public TextArea getTxtAreaDatos() {
        return txtAreaDatos;
    }

    public Button getBtnBuscar() {
        return btnBuscar;
    }

    public Button getBtnMostrarTodo() {
        return btnMostrarTodo;
    }

    public Button getBtnLimpiar() {
        return btnLimpiar;
    }

    public TableView<Departamento> getTablaDepartamentos() {
        return tablaDepartamentos;
    }
}