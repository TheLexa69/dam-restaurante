package edu.badpals.damrestaurante.controllers;

import edu.badpals.damrestaurante.Main;
import edu.badpals.damrestaurante.entities.*;
import edu.badpals.damrestaurante.models.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;

public class ItemCardPaymentController {
    private CarritoComida carritoComida;
    private UsuarioActual user;
    private Empresa empresa;

    @FXML
    private Button cntdComidaRestar;

    @FXML
    private Button cntdComidaSumar;

    @FXML
    private Label comidaAlergenos;

    @FXML
    private Label comidaCantidad;

    @FXML
    private Label comidaDescripcion;

    @FXML
    private ImageView comidaImagen;

    @FXML
    private Label comidaNombre;

    @FXML
    private Label comidaPrecio;

    private double total;


    public void setData(CarritoComida carritoComida) {
        try {
            this.carritoComida = carritoComida;
            CartaComida comida = DatabaseConnection.getInfoComida(MainController.em, carritoComida.getIdComida());
            CartaAlergenos cartaalergenos = DatabaseConnection.getInfoComidaAlergenos(MainController.em, carritoComida.getIdComida()); //NOS DEVUELVE EL ID DE LA COMIDA Y DEL ALERGENO
            Alergenos alergenos = DatabaseConnection.getInfoAlergenos(MainController.em, cartaalergenos.getIdAlergeno()); //NOS DEVUELVE EL ID DEL ALERGENO Y EL NOMBRE DEL ALERGENO

            System.out.println(cartaalergenos.getIdAlergeno());
            System.out.println(comida.getIdComida());
            System.out.println(comida.getNombre());
            System.out.println(comida.getPrecio());

            comidaNombre.setText(comida.getNombre());
            comidaCantidad.setText(String.valueOf(carritoComida.getCantidad()));
            total = comida.getPrecio() * carritoComida.getCantidad();
            comidaPrecio.setText("Subtotal: " + total + "€"); //SUBTOTAL
            comidaDescripcion.setText(comida.getDescripcion());
            comidaAlergenos.setText(alergenos.getNombreAlergeno());


            String imagePath = "src/main/resources/edu/badpals/damrestaurante/images/comida/" + comida.getImg();
            System.out.println("Ruta de la imagen: " + imagePath);

            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                URL imageUrl = imageFile.toURI().toURL();
                System.out.println("URL de la imagen: " + imageUrl);
                Image image = new Image(imageUrl.openStream());
                comidaImagen.setImage(image);
            } else {
                System.out.println("Image not found, using default image.");
                comidaImagen.setImage(new Image("/edu/badpals/damrestaurante/images/comida/fabada.jpg"));
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }



    public void restarComida(ActionEvent actionEvent) {
        CarritoComida carritoComidaNuevo = DatabaseConnection.setCantidadCarritoComida(MainController.em,carritoComida.getId_carritoComida(),carritoComida.getCantidad()-1);
        if (carritoComidaNuevo != null){
            this.setData(carritoComidaNuevo);
        } else {
            MainController.showAlert("Error al añadir","No se pudo añadir al carrito");
        }
    }

    public void sumarComida(ActionEvent actionEvent) {
        CarritoComida carritoComidaNuevo = DatabaseConnection.setCantidadCarritoComida(MainController.em,carritoComida.getId_carritoComida(),carritoComida.getCantidad()+1);
        if (carritoComidaNuevo != null){
            this.setData(carritoComidaNuevo);
        } else {
            MainController.showAlert("Error al añadir","No se pudo añadir al carrito");
        }
    }
}