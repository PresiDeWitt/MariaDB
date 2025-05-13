package foc.departamento.view.util;

import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase de utilidad para validación de campos de entrada.
 * Centraliza la lógica de validación de formularios.
 */
public class ValidatorUtils {

    /**
     * Valida un formulario de departamento.
     *
     * @param codigo Campo de texto para el código
     * @param nombre Campo de texto para el nombre
     * @param localizacion Campo de texto para la localización
     * @param manager Campo de texto para el manager
     * @return Un mapa con los errores encontrados o null si no hay errores
     */
    @Nullable
    public static Map<String, String> validarFormularioDepartamento(
            TextField codigo, TextField nombre, TextField localizacion, TextField manager) {

        Map<String, String> errores = new HashMap<>();

        // Validar campo código
        if (estaVacio(codigo)) {
            errores.put("codigo", "El código no puede estar vacío");
            marcarCampoInvalido(codigo);
        } else {
            marcarCampoValido(codigo);
        }

        // Validar campo nombre
        if (estaVacio(nombre)) {
            errores.put("nombre", "El nombre no puede estar vacío");
            marcarCampoInvalido(nombre);
        } else {
            marcarCampoValido(nombre);
        }

        // Validar campo localización
        if (estaVacio(localizacion)) {
            errores.put("localizacion", "ID Localización no puede estar vacío");
            marcarCampoInvalido(localizacion);
        } else if (!esNumeroEntero(localizacion.getText().trim())) {
            errores.put("localizacion", "ID Localización debe ser un número entero");
            marcarCampoInvalido(localizacion);
        } else {
            marcarCampoValido(localizacion);
        }

        // Validar campo manager
        if (estaVacio(manager)) {
            errores.put("manager", "ID Manager no puede estar vacío");
            marcarCampoInvalido(manager);
        } else if (!esNumeroEntero(manager.getText().trim())) {
            errores.put("manager", "ID Manager debe ser un número entero");
            marcarCampoInvalido(manager);
        } else {
            marcarCampoValido(manager);
        }

        return errores.isEmpty() ? null : errores;
    }

    /**
     * Verifica si un campo de texto está vacío.
     *
     * @param campo Campo de texto a verificar
     * @return true si está vacío, false en caso contrario
     */
    public static boolean estaVacio(@NotNull TextField campo) {
        return campo.getText().trim().isEmpty();
    }

    /**
     * Verifica si un texto es un número entero válido.
     *
     * @param texto Texto a verificar
     * @return true si es un número entero, false en caso contrario
     */
    public static boolean esNumeroEntero(String texto) {
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Marca visualmente un campo como inválido.
     *
     * @param campo Campo a marcar
     */
    public static void marcarCampoInvalido(@NotNull TextField campo) {
        campo.setStyle(StyleConstants.FIELD_ERROR_STYLE);
    }

    /**
     * Marca visualmente un campo como válido.
     *
     * @param campo Campo a marcar
     */
    public static void marcarCampoValido(@NotNull TextField campo) {
        campo.setStyle(StyleConstants.FIELD_NORMAL_STYLE);
    }

    /**
     * Formatea los errores de validación en un mensaje legible.
     *
     * @param errores Mapa de errores
     * @return Mensaje de error formateado
     */
    public static String formatearErrores(Map<String, String> errores) {
        if (errores == null || errores.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder("Por favor corrija los siguientes errores:\n");
        errores.values().forEach(error -> sb.append("- ").append(error).append("\n"));
        return sb.toString();
    }

    // Constructor privado para evitar instanciación
    private ValidatorUtils() {
        // Esta clase no debe ser instanciada
    }
}