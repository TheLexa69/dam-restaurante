package edu.badpals.damrestaurante.controllers;

import edu.badpals.damrestaurante.entities.CartaComida;
import edu.badpals.damrestaurante.entities.Empresa;
import edu.badpals.damrestaurante.entities.UsuarioActual;
import edu.badpals.damrestaurante.models.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class CartaController {

    @FXML
    private Button btnCarrito;

    @FXML
    private Button btnCarta;

    @FXML
    private Button btnInicio;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnReservas;


    @FXML
    private Button concello_pontevedra;

    @FXML
    private Button concello_vigo;

    @FXML
    private GridPane grid;

    @FXML
    private ImageView imgCarrito;

    @FXML
    private ImageView imgCarta;

    @FXML
    private ImageView imgIndexHome;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private ImageView imgReservas;

    @FXML
    private ScrollPane scroll;

    @FXML
    private ChoiceBox<String> selectRestaurant;

    @FXML
    private Separator sepCarrito;

    @FXML
    private Separator sepComedor;

    @FXML
    private Separator sepHome;

    @FXML
    private Separator sepPerfil;

    @FXML
    private Separator sepReservas;

    @FXML
    private VBox vboxIndex;

    private List<CartaComida> comidas = new ArrayList<>();

    private UsuarioActual user;

    private Empresa empresa;


    @FXML
    void onBtnClickCarrito(ActionEvent event) {
        MainController.redirectToCarrito(btnCarrito, user);
    }

    @FXML
    void onBtnClickCarta(ActionEvent event) {

    }

    @FXML
    void onBtnClickInicio(ActionEvent event) {
        MainController.redirectToIndexChangeUser(btnInicio, user);
    }

    @FXML
    void onBtnClickPerfil(ActionEvent event) {
        MainController.redirectToChagePerfil(btnInicio, user);
    }

    @FXML
    void onBtnClickPontevedra(ActionEvent event) {
        System.out.println("Has hecho clic en Pontevedra");
    }

    @FXML
    void onBtnClickReservas(ActionEvent event) {

    }

    @FXML
    void onBtnClickVigo(ActionEvent event) {
        System.out.println("Has hecho clic en Vigo");
    }

    @FXML
    public void initialize() {
        System.out.println("CartaController inicializado");
        controlSeparadores(false, true, false, false, false);
    }

    public void cargarCarta() {
        comidas = DatabaseConnection.getCarta(MainController.em,empresa);
        System.out.println(comidas);

        int column = 0;
        int row = 0;
        try {
            for (int i = 0; i < comidas.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(Main.class.getResource("item.fxml"));
                fxmlLoader.setLocation(getClass().getResource("/edu/badpals/damrestaurante/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                // Obtén el controlador del fxmlLoader
                ItemController itemController = fxmlLoader.getController();
                System.out.println(comidas.get(i));
                itemController.setData(comidas.get(i));

                if (column == 3) {
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

    public void controlSeparadores(Boolean estadoSepHome, Boolean estadoSepComedor, Boolean estadoSepReservas, Boolean estadoSepCarrito, Boolean estadoSepPerfil) {
        sepHome.setVisible(estadoSepHome);
        sepComedor.setVisible(estadoSepComedor);
        sepReservas.setVisible(estadoSepReservas);
        sepCarrito.setVisible(estadoSepCarrito);
        sepPerfil.setVisible(estadoSepPerfil);
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