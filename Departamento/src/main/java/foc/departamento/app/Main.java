package foc.departamento.app;

import foc.departamento.view.DepartamentoView;
import javafx.application.Application;

/**
 * Punto de entrada principal de la aplicación.
 * Lanza la interfaz gráfica de usuario.
 */
public class Main {
    public static void main(String[] args) {
        Application.launch(DepartamentoView.class, args);
    }
}