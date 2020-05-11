package be.ucm.projetrecrutementfront.services;

import java.time.LocalDate;
import java.util.HashSet;

import be.ucm.projetrecrutementfront.models.Utilisateur;
import be.ucm.projetrecrutementfront.models.forms.InscriptionUtilisateur;
import org.json.*;

public class UtilisateurService {

    /* Méthode de démo du contact de l'API avec un Body, pour le POST
    public static void testContactAvecBody(){
        InscriptionUtilisateur u = new InscriptionUtilisateur();
        u.setEmail("test-contact-avec-body@api.pr");
        u.setPseudo("TestContactAPIAvecBody");
        u.setMotDePasse("testmDp5!");
        u.setDateDeNaissance(LocalDate.now());
        JSONObject obj = new JSONObject(u);
        String resultat = ApiService.contacterApiAvecBody("utilisateur/create","POST", obj.toString());
        System.out.println("Méthode de test du body. Valeur : ");
        System.out.println(resultat);
    }*/

    public static Utilisateur getUtilisateur(Long id) {
        Utilisateur u = new Utilisateur();
        String output = ApiService.contacterApiSansBody("utilisateur/" + id, "GET");
        if (output != null) {
            JSONObject json = new JSONObject(output);
            remplirUnUtilisateur(u, json);
            u.setMaitrises(MaitriseService.remplirPlusieursMaitrises(json));
            u.setProjetsParticipes(ParticipationProjetService.remplirDesParticipationProjets(json));
            u.setProjetsCrees(ProjetService.remplirListeProjets(json.getJSONArray("projetsCrees")));
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

    public static void inscriptionUtilisateur(InscriptionUtilisateur utilisateur){
        System.out.println(utilisateur);
    }


}
