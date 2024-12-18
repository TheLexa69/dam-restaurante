module edu.badpals.damrestaurante {
    requires javafx.controls;
    requires javafx.fxml;

    requires net.synedra.validatorfx;
    requires google.cloud.firestore;
    requires firebase.admin;
    requires com.google.auth.oauth2;
    requires com.google.auth;

    opens edu.badpals.damrestaurante to javafx.fxml;
    exports edu.badpals.damrestaurante;
}