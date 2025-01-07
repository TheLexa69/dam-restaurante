package edu.badpals.damrestaurante.controllers;

import edu.badpals.damrestaurante.entities.CartaComida;
import edu.badpals.damrestaurante.entities.UsuarioActual;
import edu.badpals.damrestaurante.models.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.net.URL;
import javafx.scene.image.Image;

import java.io.File;
import java.net.URL;

public class ItemController {
    @FXML
    private Button btnAddComida;

    @FXML
    private ImageView img;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    private CartaComida cartaComida;
    private UsuarioActual user;

    @FXML
    void onBtnClickAddComida(ActionEvent event) {
        DatabaseConnection.addToCarrito(MainController.em,cartaComida,user);
    }

    public void setData(CartaComida cartaComida) {
        try {
            this.cartaComida = cartaComida;
            nameLabel.setText(cartaComida.getNombre());
            priceLabel.setText(cartaComida.getPrecio() + "€");

            String imagePath = "src/main/resources/edu/badpals/damrestaurante/images/comida/" + cartaComida.getImg();
            System.out.println("Ruta de la imagen: " + imagePath);

            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                URL imageUrl = imageFile.toURI().toURL();
                System.out.println("URL de la imagen: " + imageUrl);
                Image image = new Image(imageUrl.openStream());
                img.setImage(image);
            } else {
                System.out.println("Image not found, using default image.");
                img.setImage(new Image("/edu/badpals/damrestaurante/images/comida/fabada.jpg"));
            }
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public UsuarioActual getUser() {
        return user;
    }

    public void setUser(UsuarioActual user) {
        this.user = user;
    }
}