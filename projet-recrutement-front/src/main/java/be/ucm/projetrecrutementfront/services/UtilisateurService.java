package be.ucm.projetrecrutementfront.services;

import java.time.LocalDate;

import be.ucm.projetrecrutementfront.models.Utilisateur;
import org.json.*;

public class UtilisateurService {


    public static Utilisateur getUtilisateur(Long id) {
        Utilisateur u = new Utilisateur();
        String output = ContactAPIService.contactSansBody("utilisateur/" + id, "GET");
        if (output != null) {
            JSONObject json = new JSONObject(output);
            remplirUnUtilisateur(u, json);

            u.setMaitrises(MaitriseService.remplirPlusieursMaitrises(json));
        }
        return u;
    }

    private static void remplirUnUtilisateur(Utilisateur u, JSONObject json) {
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
    }




}
