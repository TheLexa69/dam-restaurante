module edu.badpals.damrestaurante {
    requires javafx.controls;
    requires javafx.fxml;

    requires net.synedra.validatorfx;

    opens edu.badpals.damrestaurante to javafx.fxml;
    exports edu.badpals.damrestaurante;
}