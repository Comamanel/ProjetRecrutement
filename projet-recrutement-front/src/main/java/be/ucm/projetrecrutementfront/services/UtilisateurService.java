package be.ucm.projetrecrutementfront.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import be.ucm.projetrecrutementfront.models.Maitrise;
import be.ucm.projetrecrutementfront.models.Utilisateur;
import org.json.*;

public class UtilisateurService {
    private static String contacterAPI(String lien, String methode){
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

    public static Utilisateur getUtilisateur(Long id){
        Utilisateur u;
        String output = contacterAPI("utilisateur/" + id, "GET");
        u = remplirUtilisateur(output);

        u.setMaitrises(remplirMaitrises(output));

        return u;
    }

    private static Utilisateur remplirUtilisateur(String output){
        Utilisateur u = new Utilisateur();
        if(output != null){
            JSONObject json = new JSONObject(output);
            u.setId(json.getLong("id"));
            u.setPseudo(json.getString("pseudo"));
            u.setPrenom(json.getString("prenom"));
            u.setNom(json.getString("nom"));
            u.setEmail(json.getString("email"));
            u.setDateDeNaissance(LocalDate.parse(json.getString("dateDeNaissance")));
            u.setNumTel(json.getString("numTel"));
            u.setInfosSupp(json.getString("infoSupp"));
            u.setPays(json.getString("pays"));
            u.setLienGit(json.getString("lienGit"));
            u.setPhotoProfil(json.getString("photoProfil"));
            u.setCvDoc(json.getString("cvDoc"));

            System.out.println(json.get("maitrises"));
        }
        return u;
    }

    private static Set<Maitrise> remplirMaitrises(String output){
        Set<Maitrise> maitrises = new HashSet<>();
        //récupérer JsonArray contenant les maîtrises

        //Récupérer la taille du JsonArray avec la méthode size

        //Faire une boucle pour parcourir tout l'array

        //Dans la boucle, créer un objet de type Maitrise

        return null;
    }
}
