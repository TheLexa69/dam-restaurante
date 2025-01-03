package edu.badpals.damrestaurante.models;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import edu.badpals.damrestaurante.entities.*;
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
        try {
            return Persistence.createEntityManagerFactory("default").createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<CartaComida> getCarta(EntityManager em) {

        Query query = em.createQuery("select cc from CartaComida cc");
        List<CartaComida> carta = query.getResultList();

        return carta;
    }

    public static List<UsuarioActual> getUsers(EntityManager em) {

        Query query = em.createQuery("select cc from UsuarioActual cc");
        List<UsuarioActual> carta = query.getResultList();

        return carta;
    }

//    Esta funcion se debe usar teniendo en cuenta que si no se encuentra una
//    mesa con la cantidad de comensales adecuada va a devolver null
    public static Mesas getMesaPorComensales(EntityManager em, int comensales){

        Query query = em.createQuery("select cc from Mesas cc where ocupada = false order by cc.cupo ASC");
        List<Mesas> mesas = query.getResultList();
        for (Mesas mesa : mesas){
            if(mesa.getCupo() > comensales){
                return mesa;
            }
        }
        return null;
    }
}