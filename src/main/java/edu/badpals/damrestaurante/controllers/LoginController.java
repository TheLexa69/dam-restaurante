package edu.badpals.damrestaurante.controllers;

import edu.badpals.damrestaurante.Main;
import edu.badpals.damrestaurante.entities.Usuario;
import edu.badpals.damrestaurante.entities.UsuarioActual;
import edu.badpals.damrestaurante.models.DatabaseConnection;
import jakarta.persistence.EntityManager;
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

import static edu.badpals.damrestaurante.controllers.MainController.*;

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

    private EntityManager em = DatabaseConnection.connectEm();

    private MediaPlayer mediaPlayer;

    @FXML
    public void initialize() {
        try {
            String videoPath = "src/main/resources/edu/badpals/damrestaurante/videos/video_introductorio.mp4";
            File videoFile = new File(videoPath);
            System.out.println("Ruta: " + videoFile.getAbsolutePath());
            if (videoFile.exists()) {
                Media media = new Media(videoFile.toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.setMute(true);
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
        UsuarioActual user = DatabaseConnection.authenticateUser(em,username, password);

        if (!username.isBlank() && !password.isBlank() && user != null) {
            showAlert("Inicio de sesión exitoso", "Bienvenido, " + username + "!");
            redirectToIndexChangeUser(txtUsuarioLogin,user);
        } else {
            showAlert("Error de inicio de sesión", "Usuario o contraseña incorrectos.");
        }
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


}