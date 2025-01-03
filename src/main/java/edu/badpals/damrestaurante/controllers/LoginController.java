package edu.badpals.damrestaurante.controllers;

import edu.badpals.damrestaurante.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;

public class LoginController {

    @FXML
    private Button btnUsuarioLogin;

    @FXML
    private Button btnUsuarioRegistrarse;

    @FXML
    private MediaView mediaView;

    @FXML
    private ImageView imgBackgroundLogin;

    @FXML
    private ImageView imgLogoBrawl;

    @FXML
    private TextField txtUsuarioLogin;

    @FXML
    private PasswordField txtUsuarioPwd;

//    private SQLCommands sqlCommands = new SQLCommands();

    private MediaPlayer mediaPlayer;

    @FXML
    public void initialize() {
        try {
            String videoPath = "src/main/resources/edu/badpals/damrestaurante/videos/video_introductorio.mp4";
            File videoFile = new File(videoPath);
            System.out.println("Absolute path: " + videoFile.getAbsolutePath()); // Print the absolute path for debugging
            if (videoFile.exists()) {
                Media media = new Media(videoFile.toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Reproducir en bucle
                mediaPlayer.setMute(true); // Silenciar el audio
                mediaView.setMediaPlayer(mediaPlayer);
                mediaPlayer.play();
            } else {
                showAlert("Error", "El archivo de video no se encuentra en la ruta especificada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "No se pudo cargar el video.");
        }
    }




    @FXML
    void onBtnClickUsuarioLogin(ActionEvent event) {
        String username = txtUsuarioLogin.getText();
        String password = txtUsuarioPwd.getText();
        redirectToIndex();

//        if (sqlCommands.authenticateUser(username, password)) {
//            showAlert("Inicio de sesión exitoso", "Bienvenido, " + username + "!");
//            redirectToIndex();
//        } else {
//            showAlert("Error de inicio de sesión", "Usuario o contraseña incorrectos.");
//        }
    }

    @FXML
    void onBtnClickUsuarioRegistrarse(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("RegisterModal.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Registro de Usuario");
            stage.setScene(new Scene(root));

            // Hacer que la ventana siempre esté encima
            stage.setAlwaysOnTop(true);

            // Hacer que la ventana sea modal
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait(); // Bloquea la interacción con otras ventanas
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "No se pudo abrir el formulario de registro.");
        }
    }

    private void redirectToIndex() {
        try {
            // Cargar el archivo FXML de la vista del índice
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("inicio.fxml"));
            Parent root = loader.load();

            // Obtener el Stage actual desde cualquier componente
            Stage currentStage = (Stage) btnUsuarioLogin.getScene().getWindow();

            // Cambiar la escena del Stage actual
            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Pantalla Principal");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "No se pudo cargar la pantalla principal.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}