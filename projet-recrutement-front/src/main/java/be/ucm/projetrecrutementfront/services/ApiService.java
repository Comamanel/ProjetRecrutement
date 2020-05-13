package be.ucm.projetrecrutementfront.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiService {

    private static ApiService instance;

    public static ApiService getInstance(){
        if(instance == null)
            instance = new ApiService();
        return instance;
    }

    private ApiService(){}

    public  String contacterApiSansBody(String lien, String methode){
        try {

            URL url = new URL("http://localhost:8081/api/" + lien);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(methode);
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            output = br.readLine();
            System.out.println("Output from Server - Without body ....");
            System.out.println(output + "\n");
            conn.disconnect();
            return output;
        } catch (Exception e) {

            e.printStackTrace();

        }
        return null;
    }

    public String contacterApiAvecBody(String lien, String methode, String body){
        try {

            URL url = new URL("http://localhost:8081/api/" + lien);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(methode);
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            try(OutputStream os = conn.getOutputStream()){
                byte[] input = body.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            output = br.readLine();
            System.out.println("Output from Server - With body ....");
            System.out.println(output + "\n");
            conn.disconnect();
            return output;
        } catch (Exception e) {

            e.printStackTrace();

        }
        return null;
    }
}