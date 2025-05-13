package foc.departamento.view.util;

import javafx.scene.paint.Color;

/**
 * Clase de utilidad que centraliza constantes de estilo para la interfaz gráfica.
 * Mejora la consistencia y facilita el mantenimiento del diseño visual.
 */
public class StyleConstants {
    // Colores principales
    public static final String COLOR_PRIMARY = "#3498db";
    public static final String COLOR_SECONDARY = "#2980b9";
    public static final String COLOR_SUCCESS = "#2ecc71";
    public static final String COLOR_DANGER = "#e74c3c";
    public static final String COLOR_WARNING = "#f39c12";
    public static final String COLOR_INFO = "#3498db";
    public static final String COLOR_LIGHT = "#f5f5f5";
    public static final String COLOR_DARK = "#34495e";
    public static final String COLOR_GRAY = "#95a5a6";

    // Estilos para componentes
    public static final String BUTTON_STYLE = "-fx-background-color: " + COLOR_PRIMARY +
            "; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;";
    public static final String BUTTON_STYLE_HOVER = "-fx-background-color: " + COLOR_SECONDARY +
            "; -fx-text-fill: white; -fx-font-weight: bold;";
    public static final String BUTTON_DANGER_STYLE = "-fx-background-color: " + COLOR_DANGER +
            "; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;";
    public static final String BUTTON_DANGER_HOVER = "-fx-background-color: #c0392b; -fx-text-fill: white; -fx-font-weight: bold;";
    public static final String BUTTON_NEUTRAL_STYLE = "-fx-background-color: " + COLOR_GRAY +
            "; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;";
    public static final String BUTTON_NEUTRAL_HOVER = "-fx-background-color: #7f8c8d; -fx-text-fill: white; -fx-font-weight: bold;";

    // Estilos para textos y títulos
    public static final String HEADER_STYLE = "-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: " + COLOR_PRIMARY + ";";
    public static final String TITLE_STYLE = "-fx-font-weight: bold; -fx-font-size: 22px; -fx-text-fill: " + COLOR_PRIMARY + ";";
    public static final String LABEL_BOLD_STYLE = "-fx-font-weight: bold;";

    // Estilos para paneles
    public static final String PANEL_STYLE = "-fx-background-color: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);";

    // Estilos para campos con error
    public static final String FIELD_ERROR_STYLE = "-fx-border-color: " + COLOR_DANGER + "; -fx-background-radius: 4;";
    public static final String FIELD_NORMAL_STYLE = "-fx-border-color: transparent; -fx-background-radius: 4;";

    // Constructor privado para evitar instanciación
    private StyleConstants() {
        // Esta clase no debe ser instanciada
    }

    /**
     * Obtiene el Color JavaFX equivalente a partir de un código hexadecimal.
     *
     * @param colorHex Código de color en formato hexadecimal
     * @return Objeto Color de JavaFX
     */
    public static Color getColor(String colorHex) {
        return Color.web(colorHex);
    }
}