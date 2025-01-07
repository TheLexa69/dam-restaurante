package edu.badpals.damrestaurante.controllers;

import edu.badpals.damrestaurante.entities.UsuarioActual;
import edu.badpals.damrestaurante.models.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class CarritoController {
    private UsuarioActual user;

    @FXML
    private AnchorPane apCarrito;

    @FXML
    private Button btnClickPagar;

    @FXML
    private Button btnClickVolver;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    void onBtnClickPagar(ActionEvent event) {

    }

    @FXML
    void onBtnClickVolver(ActionEvent event) {
        MainController.redirectToIndex(btnClickVolver);
    }

    @FXML
    public void initialize() {
        setUser(DatabaseConnection.getUsers(MainController.em).get(0));
    }

    public UsuarioActual getUser() {
        return user;
    }

    public void setUser(UsuarioActual user) {
        this.user = user;
    }

}
