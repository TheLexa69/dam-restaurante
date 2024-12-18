package edu.badpals.damrestaurante.models;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;

public class Credentials {

    public static Firestore db;


    public static void connect() {
        try{
            FileInputStream refreshToken = new FileInputStream("dam-restaurante-firebase-adminsdk-4zv3d-3b7b7b7b7b.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .build();

            FirebaseApp.initializeApp(options);

            db = FirestoreClient.getFirestore();

            System.out.println("Conectados a Firestore");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}