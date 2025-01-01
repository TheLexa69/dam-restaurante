package edu.badpals.damrestaurante.models;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import edu.badpals.damrestaurante.entities.Usuario;
import edu.badpals.damrestaurante.entities.CartaComida;
import edu.badpals.damrestaurante.entities.Pedidos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class DatabaseConnection {

    public static Firestore db;

    //Firebase
    public static void connect() {
        try {
            FileInputStream refreshToken = new FileInputStream("dam-restaurante.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .build();

            FirebaseApp.initializeApp(options);

            db = FirestoreClient.getFirestore();

            System.out.println("Conectados a Firestore");
        } catch (IOException e) {
            System.out.println("Error al conectar a Firestore" + e.getMessage());
//            e.printStackTrace();
        }

    }

    public static EntityManager connectEm() {
        return Persistence.createEntityManagerFactory("default").createEntityManager();
    }

    public static List<CartaComida> getCarta(EntityManager em) {

        Query query = em.createQuery("select cc from CartaComida cc");
        List<CartaComida> carta = query.getResultList();

        return carta;
    }

    public static List<Usuario> getUsers(EntityManager em) {

        Query query = null;
        try {
            query = em.createQuery("select cc from Usuario cc");
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Usuario> carta = query.getResultList();

        return carta;
    }
}