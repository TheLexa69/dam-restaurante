package edu.badpals.damrestaurante.controllers;

import edu.badpals.damrestaurante.entities.UsuarioActual;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditarPerfilController {

    private UsuarioActual user;

    @FXML
    private TextField txtDni;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtSegundoApellido;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtCP;

    @FXML
    public void initialize() {
        System.out.println("Inicializando...");
    }

    public void cargarDatosUser() {
        if (user != null) {
            txtDni.setText(user.getUsuarioByIdUsuario().getNif());
            txtNombre.setText(user.getUsuarioByIdUsuario().getNombre());
            txtApellido.setText(user.getUsuarioByIdUsuario().getApellido1());
            txtSegundoApellido.setText(user.getUsuarioByIdUsuario().getApellido2());
            txtDireccion.setText(user.getUsuarioByIdUsuario().getDireccion());
            txtCP.setText(user.getUsuarioByIdUsuario().getCp());
        }
    }

    public void setUser(UsuarioActual user) {
        this.user = user;
    }


}