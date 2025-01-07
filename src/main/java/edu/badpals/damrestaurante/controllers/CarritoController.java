package edu.badpals.damrestaurante.controllers;

import edu.badpals.damrestaurante.entities.CarritoComida;
import edu.badpals.damrestaurante.entities.CartaComida;
import edu.badpals.damrestaurante.entities.Empresa;
import edu.badpals.damrestaurante.entities.UsuarioActual;
import edu.badpals.damrestaurante.models.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class CarritoController {
    private UsuarioActual user;
    private Empresa empresa;
    private List<CarritoComida> comidas = new ArrayList<>();
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
    }

    public void cargarCarrito() {
        comidas = DatabaseConnection.getCarritoComida(MainController.em,user);
        System.out.println(comidas);
        int column = 0;
        int row = 0;
        try {
            for (int i = 0; i < comidas.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(Main.class.getResource("item.fxml"));
                fxmlLoader.setLocation(getClass().getResource("/edu/badpals/damrestaurante/carrito.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                // Obtén el controlador del fxmlLoader
                ItemCardPaymentController itemController = fxmlLoader.getController();
                System.out.println(comidas.get(i));
                itemController.setData(comidas.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
                itemController.setUser(user);
                itemController.setEmpresa(empresa);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar la carta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public UsuarioActual getUser() {
        return user;
    }

    public void setUser(UsuarioActual user) {
        this.user = user;
    }
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
