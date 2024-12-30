package edu.badpals.damrestaurante;

import edu.badpals.damrestaurante.entities.Usuario;
import edu.badpals.damrestaurante.entities.CartaComida;
import edu.badpals.damrestaurante.entities.Pedidos;
import edu.badpals.damrestaurante.models.DatabaseConnection;
import jakarta.persistence.EntityManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Main extends Application {
    EntityManager em = DatabaseConnection.connectEm();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
    EntityManager em = DatabaseConnection.connectEm();
//        DatabaseConnection db = new DatabaseConnection();
//        db.connect();


//        launch();
        List<CartaComida> comida = DatabaseConnection.getCarta(em);
//        for (CartaComida c : comida){
//            System.out.println(c);
//        }

        List<Usuario> usuarios = DatabaseConnection.getUsers(em);
        for (Usuario c : usuarios){
            System.out.println(c);
        }
        em.close();
    }
}