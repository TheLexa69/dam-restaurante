package edu.badpals.damrestaurante.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class IndexController {

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
    private VBox vboxIndex;

    private String[] images;
    private int currentIndex = 0; // Índice para rastrear la imagen actual

    class SliderThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(5000); // Espera 5 segundos antes de cambiar la imagen
                    Platform.runLater(() -> {
                        imgCarrousel.setImage(new Image(images[currentIndex]));
                        currentIndex = (currentIndex + 1) % images.length; // Avanza y reinicia si es necesario
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void initialize() {
        System.out.println("Inicializando...");
        images = new String[]{
                getClass().getResource("/edu/badpals/damrestaurante/images/comida/arroz_marisco.JPG").toExternalForm(),
                getClass().getResource("/edu/badpals/damrestaurante/images/comida/brownie.JPG").toExternalForm(),
                getClass().getResource("/edu/badpals/damrestaurante/images/comida/cachopo.png").toExternalForm(),
                getClass().getResource("/edu/badpals/damrestaurante/images/comida/fabada.JPG").toExternalForm()
        };

        // Mostrar la primera imagen inmediatamente
        imgCarrousel.setImage(new Image(images[currentIndex]));
        currentIndex++; // Avanzar al siguiente índice

        // Iniciar el hilo del carrusel
        SliderThread sliderThread = new SliderThread();
        sliderThread.setDaemon(true); // Asegura que el hilo se detenga al cerrar la aplicación
        sliderThread.start();
    }

    @FXML
    void onBtnClickCarta(ActionEvent event) {
        System.out.println("Botón Carta pulsado");
    }

    @FXML
    void onBtnClickInicio(ActionEvent event) {
        System.out.println("Botón Inicio pulsado");
    }

    @FXML
    void onBtnClickReservas(ActionEvent event) {
        System.out.println("Botón Reservas pulsado");
    }
}
