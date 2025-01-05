package edu.badpals.damrestaurante.controllers;

import edu.badpals.damrestaurante.Main;
import edu.badpals.damrestaurante.entities.UsuarioActual;
import edu.badpals.damrestaurante.models.DatabaseConnection;
import jakarta.persistence.EntityManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    static EntityManager em = DatabaseConnection.connectEm();


    static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    static void redirectToIndex(Node btn) {
        try {
            // Cargar el archivo FXML de la vista del índice
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("inicio.fxml"));
            Parent root = loader.load();

            // Obtener el Stage actual desde cualquier componente
            Stage currentStage = (Stage) btn.getScene().getWindow();

            // Cambiar la escena del Stage actual
            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Pantalla Principal");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "No se pudo cargar la pantalla principal.");
        }
    }

    static void redirectToIndexChangeUser(Node btn, UsuarioActual user) {
        try {
            // Cargar el archivo FXML de la vista del índice
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("inicio.fxml"));
            Parent root = loader.load();

            // Obtener el Stage actual desde cualquier componente
            Stage currentStage = (Stage) btn.getScene().getWindow();

            // Cambiar la escena del Stage actual
            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Pantalla Principal");

            IndexController controller = loader.getController();
            controller.setUser(user);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "No se pudo cargar la pantalla principal.");
        }
    }
}