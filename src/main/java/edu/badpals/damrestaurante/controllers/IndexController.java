package edu.badpals.damrestaurante.controllers;

import edu.badpals.damrestaurante.Main;
import edu.badpals.damrestaurante.entities.UsuarioActual;
import edu.badpals.damrestaurante.models.DatabaseConnection;
import jakarta.persistence.EntityManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import static edu.badpals.damrestaurante.controllers.MainController.showAlert;

public class IndexController {

    private UsuarioActual user;

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
    private ImageView imgCarrito;

    @FXML
    private ImageView imgCarrousel;

    @FXML
    private ImageView imgCarta;

    @FXML
    private ImageView imgIndexHome;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private ImageView imgReservas;

    @FXML
    private Label lblContacto;

    @FXML
    private Label lblDireccion;

    @FXML
    private Label lblDireccionCompleta;

    @FXML
    private Label lblTfno;

    @FXML
    private Label lblTfnoNumber;

    @FXML
    private Separator sepCarrito;

    @FXML
    private Separator sepComedor;

    @FXML
    private Separator sepDir;

    @FXML
    private Separator sepHome;

    @FXML
    private Separator sepPerfil;

    @FXML
    private Separator sepReservas;

    @FXML
    private Separator sepTfno;

    @FXML
    private Label txtCarrousel;

    @FXML
    private Label txtInfoCarrousel;

    @FXML
    private VBox vboxIndex;

    private String[] images;
    private String[] textoComida;
    private String[] comidaCarta;

    private int currentIndex = 0;

    class SliderThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(5000); // Contador de 5 segundos para cambiar la imagen
                    Platform.runLater(() -> {
                        imgCarrousel.setImage(new Image(images[currentIndex]));
                        txtCarrousel.setText(comidaCarta[currentIndex]);
                        txtInfoCarrousel.setText(textoComida[currentIndex]);
                        currentIndex = (currentIndex + 1) % images.length; // Avanzamos y reiniciamos si es necesario
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void initialize() {
        setUser(DatabaseConnection.getUsers(MainController.em).get(0));



        System.out.println("Inicializando...");
        controlSeparadores(true, false, false, false, false);
        images = new String[]{
                getClass().getResource("/edu/badpals/damrestaurante/images/comida/arroz_marisco.JPG").toExternalForm(),
                getClass().getResource("/edu/badpals/damrestaurante/images/comida/brownie.JPG").toExternalForm(),
                getClass().getResource("/edu/badpals/damrestaurante/images/comida/cachopo.png").toExternalForm(),
                getClass().getResource("/edu/badpals/damrestaurante/images/comida/fabada.JPG").toExternalForm()
        };

        comidaCarta = new String[]{
                "Arroz con marisco",
                "Brownie",
                "Cachopo",
                "Fabada"
        };

        textoComida = new String[]{
                "Arroz con Calamares, Mejillones, Langostinos y Almejas",
                "Brownie Rock Slide con Helado de Vainilla",
                "Cachopo de Ternera, Jamón Serrano, Setas y Queso Oveja Trufado",
                "Fabada"
        };

        // Aqui enseñamos la primera imagen inmediatamente sin tener que esperar los 5 segundos
        imgCarrousel.setImage(new Image(images[currentIndex]));
        txtCarrousel.setText(comidaCarta[currentIndex]);
        txtInfoCarrousel.setText(textoComida[currentIndex]);
        currentIndex++;

        // Iniciamos el hilo del carrusel
        SliderThread sliderThread = new SliderThread();
        sliderThread.setDaemon(true); // Al cerrar la aplicacion el hilo demonio se detiene
        sliderThread.start();
    }

    @FXML
    void onBtnClickCarta(ActionEvent event) {
        System.out.println("Botón Carta pulsado");
        MainController.redirectToCarta(btnCarta, user);
    }

    @FXML
    void onBtnClickInicio(ActionEvent event) {
        System.out.println("Botón Inicio pulsado");
        showHideHome(true);
        controlSeparadores(true, false, false, false, false);
    }

    @FXML
    void onBtnClickPerfil(ActionEvent event) {
//        System.out.println("Botón Perfil pulsado");
//        controlSeparadores(false, false, false, false, true);
        try {
            // Cargar el archivo FXML de la vista del índice
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("editarPerfil.fxml"));
            Parent root = loader.load();
            EditarPerfilController controller = loader.getController();
            controller.setUser(user);
            controller.cargarDatosUser();

            // Obtener el Stage actual desde cualquier componente
            Stage currentStage = (Stage) btnPerfil.getScene().getWindow();

            // Cambiar la escena del Stage actual
            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Editar Perfil");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "No se pudo cargar la pantalla principal.");
        }
    }

    @FXML
    void onBtnClickReservas(ActionEvent event) {
        System.out.println("Botón Reservas pulsado");
        controlSeparadores(false, false, true, false, false);
    }

    @FXML
    void onBtnClickCarrito(ActionEvent event) {
        System.out.println("Botón Carrito pulsado");
        MainController.redirectToCarrito(btnCarrito, user);
        controlSeparadores(false, false, false, true, false);
    }


    //ESCONDEMOS O ENSEÑAMOS LOS ELEMENTOS DEL INICIO
    public void showHideHome(Boolean estado) {
        imgCarrousel.setVisible(estado);
        txtCarrousel.setVisible(estado);
        txtInfoCarrousel.setVisible(estado);
        lblDireccion.setVisible(estado);
        lblContacto.setVisible(estado);
        sepDir.setVisible(estado);
        lblDireccion.setVisible(estado);
        lblDireccionCompleta.setVisible(estado);
        lblTfno.setVisible(estado);
        sepTfno.setVisible(estado);
        lblTfnoNumber.setVisible(estado);
    }

    //CONTROL SEPARADORES
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
}