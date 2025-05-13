package util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Clase de utilidad para mostrar diálogos y alertas en la aplicación.
 * Centraliza la creación de diálogos para mantener consistencia en la UI.
 */
public class AlertUtils {

    /**
     * Muestra un mensaje de información.
     *
     * @param titulo Título de la ventana
     * @param mensaje Mensaje a mostrar
     */
    public static void mostrarInformacion(String titulo, String mensaje) {
        mostrarMensaje(titulo, mensaje, Alert.AlertType.INFORMATION);
    }

    /**
     * Muestra un mensaje de error.
     *
     * @param titulo Título de la ventana
     * @param mensaje Mensaje de error a mostrar
     */
    public static void mostrarError(String titulo, String mensaje) {
        mostrarMensaje(titulo, mensaje, Alert.AlertType.ERROR);
    }

    /**
     * Muestra un mensaje de advertencia con botones Aceptar y Cancelar.
     * Corregido para manejar correctamente la cancelación.
     *
     * @param titulo Título de la ventana
     * @param mensaje Mensaje de advertencia a mostrar
     * @return true si el usuario presiona Aceptar, false si presiona Cancelar o cierra el diálogo
     */
    public static boolean mostrarAdvertencia(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        ButtonType btnAceptar = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
        ButtonType btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        alerta.getButtonTypes().setAll(btnAceptar, btnCancelar);

        Optional<ButtonType> resultado = alerta.showAndWait();

        // Retorna true SOLO si explícitamente se presionó el botón Aceptar
        return resultado.isPresent() && resultado.get().getButtonData() == ButtonBar.ButtonData.OK_DONE;
    }

    /**
     * Metodo genérico para mostrar un diálogo con un mensaje.
     *
     * @param titulo Título de la ventana
     * @param mensaje Mensaje a mostrar
     * @param tipo Tipo de alerta (INFORMATION, ERROR, WARNING, CONFIRMATION)
     */
    public static void mostrarMensaje(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Muestra un diálogo de confirmación y devuelve la respuesta del usuario.
     *
     * @param titulo Título de la ventana
     * @param mensaje Mensaje de confirmación
     * @return true si el usuario confirma, false en caso contrario
     */
    public static boolean mostrarConfirmacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    // Constructor privado para evitar instanciación
    private AlertUtils() {
        // Esta clase no debe ser instanciada
    }
}