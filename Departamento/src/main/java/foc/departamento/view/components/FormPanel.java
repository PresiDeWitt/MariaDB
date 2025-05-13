package foc.departamento.view.components;

import foc.departamento.view.util.StyleConstants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Panel de formulario para entrada de datos de departamento.
 * Encapsula la entrada de datos y botones de acción.
 */
public class FormPanel extends VBox {
    private TextField txtCodigo;
    private TextField txtNombre;
    private TextField txtLocalizacion;
    private TextField txtManager;
    private Button btnInsertar;
    private Button btnModificar;
    private Button btnBorrar;
    private Button btnLimpiar;
    private Button btnMostrar;

    public FormPanel() {
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
        Label lblTitulo = new Label("Datos del Departamento");
        lblTitulo.setStyle(StyleConstants.HEADER_STYLE);

        // Crear campos de texto
        txtCodigo = new TextField();
        txtCodigo.setPromptText("Código (Obligatorio)");

        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre del departamento");

        txtLocalizacion = new TextField();
        txtLocalizacion.setPromptText("ID de localización (numérico)");

        txtManager = new TextField();
        txtManager.setPromptText("ID del manager (numérico)");

        // Panel de campos en rejilla
        GridPane gridCampos = new GridPane();
        gridCampos.setHgap(10);
        gridCampos.setVgap(15);
        gridCampos.setPadding(new Insets(10, 0, 10, 0));

        // Añadir etiquetas y campos
        gridCampos.add(new Label("Código:"), 0, 0);
        gridCampos.add(txtCodigo, 1, 0);

        gridCampos.add(new Label("Nombre:"), 0, 1);
        gridCampos.add(txtNombre, 1, 1);

        gridCampos.add(new Label("Localización:"), 0, 2);
        gridCampos.add(txtLocalizacion, 1, 2);

        gridCampos.add(new Label("Manager:"), 0, 3);
        gridCampos.add(txtManager, 1, 3);

        // Crear botones
        btnInsertar = new Button("Insertar");
        btnModificar = new Button("Modificar");
        btnBorrar = new Button("Borrar");
        btnLimpiar = new Button("Limpiar");
        btnMostrar = new Button("Buscar");

        // Organizar botones en contenedores
        HBox panelBotonesCRUD = new HBox(10);
        panelBotonesCRUD.setAlignment(Pos.CENTER);
        panelBotonesCRUD.getChildren().addAll(btnInsertar, btnModificar, btnBorrar);

        HBox panelBotonesUtil = new HBox(10);
        panelBotonesUtil.setAlignment(Pos.CENTER);
        panelBotonesUtil.getChildren().addAll(btnLimpiar);

        // Aplicar estilos a botones
        aplicarEstilosBotones();

        // Añadir todos los componentes al panel
        getChildren().addAll(lblTitulo, gridCampos, panelBotonesCRUD, panelBotonesUtil);

        validarCamposObligatorios();
    }

    /**
     * Aplica estilos CSS a los botones
     */
    private void aplicarEstilosBotones() {
        btnInsertar.setStyle(StyleConstants.BUTTON_STYLE + "-fx-background-color: #4CAF50;");
        btnInsertar.setPrefWidth(90);

        btnModificar.setStyle(StyleConstants.BUTTON_STYLE + "-fx-background-color: #2196F3;");
        btnModificar.setPrefWidth(90);

        btnBorrar.setStyle(StyleConstants.BUTTON_STYLE + "-fx-background-color: #F44336;");
        btnBorrar.setPrefWidth(90);

        btnLimpiar.setStyle(StyleConstants.BUTTON_STYLE);
        btnLimpiar.setPrefWidth(90);

        btnMostrar.setStyle(StyleConstants.BUTTON_STYLE);
        btnMostrar.setPrefWidth(90);
    }

    /**
     * Configura los estilos y eventos hover de los componentes
     */
    private void configurarEstilos() {
        // Efectos hover para los botones
        String greenHover = StyleConstants.BUTTON_STYLE_HOVER + "-fx-background-color: #45a049;";
        String blueHover = StyleConstants.BUTTON_STYLE_HOVER + "-fx-background-color: #0b7dda;";
        String redHover = StyleConstants.BUTTON_STYLE_HOVER + "-fx-background-color: #d32f2f;";

        btnInsertar.setOnMouseEntered(e -> btnInsertar.setStyle(greenHover));
        btnInsertar.setOnMouseExited(e -> btnInsertar.setStyle(StyleConstants.BUTTON_STYLE + "-fx-background-color: #4CAF50;"));

        btnModificar.setOnMouseEntered(e -> btnModificar.setStyle(blueHover));
        btnModificar.setOnMouseExited(e -> btnModificar.setStyle(StyleConstants.BUTTON_STYLE + "-fx-background-color: #2196F3;"));

        btnBorrar.setOnMouseEntered(e -> btnBorrar.setStyle(redHover));
        btnBorrar.setOnMouseExited(e -> btnBorrar.setStyle(StyleConstants.BUTTON_STYLE + "-fx-background-color: #F44336;"));

        btnLimpiar.setOnMouseEntered(e -> btnLimpiar.setStyle(StyleConstants.BUTTON_STYLE_HOVER));
        btnLimpiar.setOnMouseExited(e -> btnLimpiar.setStyle(StyleConstants.BUTTON_STYLE));

        btnMostrar.setOnMouseEntered(e -> btnMostrar.setStyle(StyleConstants.BUTTON_STYLE_HOVER));
        btnMostrar.setOnMouseExited(e -> btnMostrar.setStyle(StyleConstants.BUTTON_STYLE));
    }

    /**
     * Método para habilitar/deshabilitar los campos de entrada
     * @param editable true para habilitar, false para deshabilitar
     */
    public void setEditable(boolean editable) {
        txtCodigo.setEditable(editable);
        txtNombre.setEditable(editable);
        txtLocalizacion.setEditable(editable);
        txtManager.setEditable(editable);
    }

    /**
     * Valida si los campos obligatorios están completados
     */
    public void validarCamposObligatorios() {
        if (!txtCodigo.getText().trim().isEmpty() &&
                !txtNombre.getText().trim().isEmpty() &&
                !txtLocalizacion.getText().trim().isEmpty()) {
            txtManager.getText();
        }
    }

    // Getters para los componentes
    public TextField getTxtCodigo() {
        return txtCodigo;
    }

    public TextField getTxtNombre() {
        return txtNombre;
    }

    public TextField getTxtLocalizacion() {
        return txtLocalizacion;
    }

    public TextField getTxtManager() {
        return txtManager;
    }

    public Button getBtnInsertar() {
        return btnInsertar;
    }

    public Button getBtnModificar() {
        return btnModificar;
    }

    public Button getBtnBorrar() {
        return btnBorrar;
    }

    public Button getBtnLimpiar() {
        return btnLimpiar;
    }

    public Button getBtnMostrar() {
        return btnMostrar;
    }
}