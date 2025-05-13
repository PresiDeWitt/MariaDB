package foc.departamento.controller;

import foc.departamento.dao.DepartamentoDAO;
import foc.departamento.model.Departamento;
import util.AlertUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Controlador que maneja la lógica entre la vista y el modelo.
 */
public class DepartamentoController {
    private final DepartamentoDAO departamentoDAO;

    public DepartamentoController() {
        this.departamentoDAO = new DepartamentoDAO();
    }

    public boolean insertarDepartamento(Departamento departamento) {
        try {
            return departamentoDAO.insertar(departamento);
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error al insertar", e.getMessage());
            return false;
        }
    }

    public boolean modificarDepartamento(Departamento departamento) {
        try {
            return departamentoDAO.modificar(departamento);
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error al modificar", e.getMessage());
            return false;
        }
    }

    public boolean eliminarDepartamento(String codigo) {
        try {
            return departamentoDAO.eliminar(codigo);
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error al eliminar", e.getMessage());
            return false;
        }
    }

    public Departamento buscarDepartamento(String codigo) {
        try {
            return departamentoDAO.buscarPorCodigo(codigo);
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error al buscar", e.getMessage());
            return null;
        }
    }
    public List<Departamento> actualizarTabla() {
        try {
            return departamentoDAO.listarTodos();
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error al actualizar tabla", e.getMessage());
            return null;
        }
    }


    public List<Departamento> listarTodosDepartamentos() {
        try {
            return departamentoDAO.listarTodos();
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error al listar", e.getMessage());
            return null;
        }
    }
}