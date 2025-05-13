package foc.departamento.view;

import foc.departamento.model.Departamento;
import foc.departamento.view.components.FormPanel;
import foc.departamento.view.components.InfoPanel;
import foc.departamento.controller.DepartamentoController;
import util.AlertUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

/**
 * Vista principal de la aplicación.
 */
public class DepartamentoView extends Application {
    private DepartamentoController controller;
    private FormPanel formPanel;
    private InfoPanel infoPanel;

    @Override
    public void start(Stage primaryStage) {
        try {

            controller = new DepartamentoController();

            primaryStage.setTitle("Gestión de Departamentos");

            BorderPane root = new BorderPane();
            root.setStyle("-fx-background-color: #f5f5f5;");

            // Crear paneles
            formPanel = new FormPanel();
            infoPanel = new InfoPanel();

            // Cargar datos iniciales
            cargarDatosIniciales();

            // Configurar eventos
            configurarEventos();

            // Organizar layout
            SplitPane splitPane = new SplitPane(formPanel, infoPanel);
            splitPane.setDividerPositions(0.45);
            root.setCenter(splitPane);

            Scene scene = new Scene(root, 963, 500);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            AlertUtils.mostrarError("Error", "No se pudo iniciar la aplicación: " + e.getMessage());
        }
    }

    /**
     * Carga los datos iniciales en la tabla
     */
    private void cargarDatosIniciales() {
        List<Departamento> departamentos = controller.listarTodosDepartamentos();
        if (departamentos != null) {
            infoPanel.actualizarTabla(departamentos);
        }
    }

    private void configurarEventos() {
        // Configurar eventos de los botones del formulario
        formPanel.getBtnInsertar().setOnAction(e -> insertarDepartamento());
        formPanel.getBtnModificar().setOnAction(e -> modificarDepartamento());
        formPanel.getBtnBorrar().setOnAction(e -> borrarDepartamento());
        formPanel.getBtnLimpiar().setOnAction(e -> limpiarCampos());
        // formPanel.getBtnMostrarDepartamento().setOnAction(e -> buscarYMostrarDepartamento());

        // Configurar eventos de los botones del panel de información
        infoPanel.getBtnBuscar().setOnAction(e -> buscarEnTabla());
        infoPanel.getBtnMostrarTodo().setOnAction(e -> mostrarTodosDepartamentos());
        infoPanel.getBtnLimpiar().setOnAction(e -> infoPanel.limpiar());

        // Configurar evento de doble clic en la tabla para cargar datos en el formulario
        infoPanel.getTablaDepartamentos().setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                cargarDepartamentoEnFormulario(infoPanel.getDepartamentoSeleccionado());
            }
        });
    }

    /**
     * Busca un departamento en la tabla por código
     */
    private void buscarEnTabla() {
        String codigo = formPanel.getTxtCodigo().getText();
        if (codigo.isEmpty()) {
            AlertUtils.mostrarError("Error", "Debe especificar un código de departamento para buscar.");
            return;
        }

        boolean encontrado = infoPanel.seleccionarDepartamentoPorCodigo(codigo);
        if (!encontrado) {
            AlertUtils.mostrarError("No encontrado", "No se encontró ningún departamento con el código: " + codigo);
        }
    }

    /**
     * Carga los datos de un departamento en el formulario
     * @param departamento El departamento a cargar
     */
    private void cargarDepartamentoEnFormulario(Departamento departamento) {
        if (departamento != null) {
            formPanel.getTxtCodigo().setText(departamento.getCodigo());
            formPanel.getTxtNombre().setText(departamento.getNombre());
            formPanel.getTxtLocalizacion().setText(String.valueOf(departamento.getIdLocalizacion()));
            formPanel.getTxtManager().setText(String.valueOf(departamento.getIdManager()));
        }
    }

    private void insertarDepartamento() {
        try {
            Departamento dept = obtenerDepartamentoDesdeFormulario();
            if (dept == null) return;

            boolean exito = controller.insertarDepartamento(dept);
            if (exito) {
                AlertUtils.mostrarInformacion("Éxito", "Departamento insertado correctamente.");
                // Actualizar la información mostrada en el InfoPanel
                actualizarInfoPanel();
                limpiarCampos();
            }
        } catch (NumberFormatException e) {
            AlertUtils.mostrarError("Error de formato", "Los campos de localización y manager deben ser números.");
        }
    }

    private void modificarDepartamento() {
        try {
            Departamento dept = obtenerDepartamentoDesdeFormulario();
            if (dept == null) return;

            boolean exito = controller.modificarDepartamento(dept);
            if (exito) {
                // Actualizar la información mostrada en el InfoPanel
                actualizarInfoPanel();
            }
        } catch (NumberFormatException e) {
            AlertUtils.mostrarError("Error de formato", "Los campos de localización y manager deben ser números.");
        }
    }

    /**
     * Obtiene un objeto Departamento con los datos del formulario
     * @return El departamento creado o null si hay errores de validación
     */
    private Departamento obtenerDepartamentoDesdeFormulario() {
        String codigo = formPanel.getTxtCodigo().getText();
        String nombre = formPanel.getTxtNombre().getText();
        String localizacionStr = formPanel.getTxtLocalizacion().getText();
        String managerStr = formPanel.getTxtManager().getText();

        // Validación básica
        if (codigo.isEmpty() || nombre.isEmpty() || localizacionStr.isEmpty() || managerStr.isEmpty()) {
            AlertUtils.mostrarError("Error", "Todos los campos son obligatorios.");
            return null;
        }

        try {
            int localizacion = Integer.parseInt(localizacionStr);
            int manager = Integer.parseInt(managerStr);
            return new Departamento(codigo, nombre, localizacion, manager);
        } catch (NumberFormatException e) {
            AlertUtils.mostrarError("Error de formato", "Los campos de localización y manager deben ser números.");
            return null;
        }
    }

    private void borrarDepartamento() {
        String codigo = formPanel.getTxtCodigo().getText();
        if (codigo.isEmpty()) {
            AlertUtils.mostrarError("Error", "Debe especificar un código de departamento.");
            return;
        }

        boolean exito = controller.eliminarDepartamento(codigo);
        if (exito) {
            limpiarCampos();
            // Actualizar la información mostrada en el InfoPanel
            actualizarInfoPanel();
        }
    }

    private void limpiarCampos() {
        formPanel.getTxtCodigo().clear();
        formPanel.getTxtNombre().clear();
        formPanel.getTxtLocalizacion().clear();
        formPanel.getTxtManager().clear();
    }

    private void actualizarInfoPanel() {
        List<Departamento> departamentos = controller.actualizarTabla();
        if (departamentos != null) {
            infoPanel.actualizarTabla(departamentos);
        }
    }

    /**
     * Busca un departamento por su código y muestra su información
     */
    private void buscarYMostrarDepartamento() {
        String codigo = formPanel.getTxtCodigo().getText();
        if (codigo.isEmpty()) {
            AlertUtils.mostrarError("Error", "Debe especificar un código de departamento para buscar.");
            return;
        }

        Departamento departamento = controller.buscarDepartamento(codigo);
        if (departamento != null) {
            // Mostrar la información en el formulario
            cargarDepartamentoEnFormulario(departamento);

            // Seleccionar el departamento en la tabla si existe
            infoPanel.seleccionarDepartamentoPorCodigo(codigo);
        } else {
            AlertUtils.mostrarError("No encontrado", "No se encontró ningún departamento con el código: " + codigo);
        }
    }

    /**
     * Muestra la información de todos los departamentos
     */
    private void mostrarTodosDepartamentos() {
        List<Departamento> departamentos = controller.listarTodosDepartamentos();
        if (departamentos != null && !departamentos.isEmpty()) {
            infoPanel.actualizarTabla(departamentos);

            StringBuilder info = new StringBuilder();
            info.append("LISTADO DE DEPARTAMENTOS\n");
            info.append("==========================\n");
            info.append("Se han cargado ").append(departamentos.size()).append(" departamentos.\n");
            info.append("Haga doble clic en un departamento para cargar sus datos en el formulario.");

            infoPanel.mostrarInformacion(info.toString());
        } else {
            infoPanel.mostrarInformacion("No hay departamentos para mostrar.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}