package edu.badpals.damrestaurante.controllers;

import edu.badpals.damrestaurante.Main;
import edu.badpals.damrestaurante.entities.Usuario;
import edu.badpals.damrestaurante.entities.UsuarioActual;
import edu.badpals.damrestaurante.models.DatabaseConnection;
import jakarta.persistence.EntityManager;
import javafx.fxml.FXML;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.sql.Timestamp;
import java.util.Date;

import static edu.badpals.damrestaurante.controllers.MainController.redirectToIndex;
import static edu.badpals.damrestaurante.controllers.MainController.showAlert;

public class EditarPerfilController {

    private UsuarioActual user;

    @FXML
    private TextField txtDni;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtSegundoApellido;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtCP;

    @FXML
    private TextField txtTelef;

    @FXML
    private Separator sepHome;

    @FXML
    private Separator sepPerfil;

    @FXML
    private ImageView imgEditarPerfilUpdate;

    @FXML
    private ImageView imgEditarPerfilHome;


    @FXML
    public void initialize() {
        System.out.println("Inicializando...");
    }

    public void cargarDatosUser() {
        if (user != null) {
            Usuario user = DatabaseConnection.getUserByUserActual(MainController.em,this.user);
            txtDni.setText(user.getNif());
            txtNombre.setText(user.getNombre());
            txtApellido.setText(user.getApellido1());
            txtSegundoApellido.setText(user.getApellido2());
            txtDireccion.setText(user.getDireccion());
            txtCP.setText(user.getCp());
            txtTelef.setText(user.getNumTelef());
        }
    }

    public void setUser(UsuarioActual user) {
        this.user = user;
    }

    public void updateProfile(MouseEvent mouseEvent) {
        String dni = txtDni.getText();
        String cp = txtCP.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String apellido2 = txtSegundoApellido.getText();
        String direccion = txtDireccion.getText();
        String telef = txtTelef.getText();
        Date date = new Date();
        if (dni.isEmpty() || cp.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || apellido2.isEmpty() || direccion.isEmpty()) {
            showAlert("Datos incompletos", "Todos los campos deben estar llenos.");
            return;
        } else if (isValidDni(dni) && isValidPostalCode(cp)) {
            DatabaseConnection.updatePerfil(MainController.em, user, nombre, apellido, apellido2, new Timestamp(date.getTime()),telef,dni,direccion,cp);
        } else {
            showAlert("Datios Invalidos", "El DNI o el Codigo Postal son incorrectos");
        }
    }

    public void goIndex(MouseEvent mouseEvent) {
        redirectToIndex(imgEditarPerfilUpdate);
    }

    public void setVisibilityReload(MouseEvent mouseEvent) {
        sepPerfil.setVisible(true);
    }

    public void setInvisibilityReload(MouseEvent mouseEvent) {
        sepPerfil.setVisible(false);
    }

    public void setVisibilityHouse(MouseEvent mouseEvent) {
        sepHome.setVisible(true);
    }

    public void setInvisibilityHouse(MouseEvent mouseEvent) {
        sepHome.setVisible(false);
    }

    public static boolean isValidDni(String dni) {
        // Verificar que el DNI tenga 9 caracteres
        if (dni == null || dni.length() != 9) {
            return false;
        }

        // Verificar que los primeros 8 caracteres sean dígitos
        String numberPart = dni.substring(0, 8);
        if (!numberPart.matches("\\d{8}")) {
            return false;
        }

        // Verificar que el último carácter sea una letra
        char letter = dni.charAt(8);
        if (!Character.isLetter(letter)) {
            return false;
        }

        // Calcular la letra correspondiente
        String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
        int number = Integer.parseInt(numberPart);
        char expectedLetter = letters.charAt(number % 23);

        // Comparar la letra calculada con la letra proporcionada
        return Character.toUpperCase(letter) == expectedLetter;
    }

    public static boolean isValidPostalCode(String postalCode) {
        // Verify that the postal code has exactly 5 digits
        return postalCode != null && postalCode.matches("\\d{5}");
    }
}