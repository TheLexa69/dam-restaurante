package edu.badpals.damrestaurante.controllers;

import edu.badpals.damrestaurante.Main;
import edu.badpals.damrestaurante.entities.Empresa;
import edu.badpals.damrestaurante.entities.UsuarioActual;
import edu.badpals.damrestaurante.models.DatabaseConnection;
import jakarta.persistence.EntityManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainController {
    static EntityManager em = DatabaseConnection.connectEm();


    static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    static boolean showConfirmationDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
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

    static void redirectToLogin(Node btn) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SceneLogin.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btn.getScene().getWindow();
            stage.setTitle("T is Restaurant");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error al cargar la ventana principal" + e.getMessage());
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

    static void redirectToCarta(Node btn, UsuarioActual user) {
        try {
            // Cargar el archivo FXML de la vista del índice
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("carta.fxml"));
            Parent root = loader.load();

            // Obtener el Stage actual desde cualquier componente
            Stage currentStage = (Stage) btn.getScene().getWindow();

            // Cambiar la escena del Stage actual
            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Carta");

            CartaController controller = loader.getController();
            controller.setUser(user);
            Empresa empresa = DatabaseConnection.getEmpresa(em,"Restaurante Central");
            controller.setEmpresa(empresa);
            controller.cargarCarta();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "No se pudo cargar la pantalla de carta.");
        }
    }

    static void redirectToCarrito(Node btn, UsuarioActual user){
        try {
            // Cargar el archivo FXML de la vista del índice
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("carrito.fxml"));
            Parent root = loader.load();

            // Obtener el Stage actual desde cualquier componente
            Stage currentStage = (Stage) btn.getScene().getWindow();

            // Cambiar la escena del Stage actual
            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Carrito");

            CarritoController controller = loader.getController();
            controller.setUser(user);
            controller.cargarCarrito();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "No se pudo cargar la pantalla de carta.");
        }
    }



    static void redirectToChagePerfil(Node btn,UsuarioActual user) {
        try {
            // Cargar el archivo FXML de la vista del índice
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("editarPerfil.fxml"));
            Parent root = loader.load();
            EditarPerfilController controller = loader.getController();
            controller.setUser(user);
            controller.cargarDatosUser();

            // Obtener el Stage actual desde cualquier componente
            Stage currentStage = (Stage) btn.getScene().getWindow();

            // Cambiar la escena del Stage actual
            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Editar Perfil");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "No se pudo cargar la pantalla principal.");
        }
    }
}