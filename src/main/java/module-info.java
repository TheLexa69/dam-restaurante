module edu.badpals.damrestaurante {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    requires net.synedra.validatorfx;
    requires google.cloud.firestore;
    requires firebase.admin;
    requires com.google.auth.oauth2;
    requires com.google.auth;

    opens edu.badpals.damrestaurante to javafx.fxml;
    opens edu.badpals.damrestaurante.controllers to javafx.fxml;
    opens edu.badpals.damrestaurante.entities to org.hibernate.orm.core; // Add this line
    exports edu.badpals.damrestaurante;

    requires java.sql;
    requires javafx.media;
    requires jdk.compiler;

    exports edu.badpals.damrestaurante.entities;
}