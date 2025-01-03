package edu.badpals.damrestaurante.models;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class ApiConnection {

    private String selectedUrl;

    // Constructor que selecciona una URL aleatoria al iniciar
    public ApiConnection() {
        String[] apiUrls = {
                "https://www.luachea.es/api/carta/vigo",
                "https://www.luachea.es/api/carta/pontevedra",
        };
        // Selección aleatoria de URL
        Random random = new Random();
        selectedUrl = apiUrls[random.nextInt(apiUrls.length)];
    }

    // Método para obtener la respuesta de la API
    public String getApiResponse() {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(selectedUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.err.println("HTTP Error: " + responseCode);
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String line;
                while ((line = errorReader.readLine()) != null) {
                    response.append(line);
                }
                errorReader.close();
                throw new RuntimeException("HTTP error code : " + responseCode + ". Response: " + response.toString());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    // Método para obtener la URL seleccionada
    public String getSelectedUrl() {
        return selectedUrl;
    }
}
