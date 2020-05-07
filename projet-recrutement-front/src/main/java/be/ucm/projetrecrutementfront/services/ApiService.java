package be.ucm.projetrecrutementfront.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiService {

    public static String contacterApiSansBody(String lien, String methode){
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
            System.out.println("Output from Server ....");
            System.out.println(output + "\n");
            conn.disconnect();
            return output;
        } catch (Exception e) {

            e.printStackTrace();

        }
        return null;
    }
}