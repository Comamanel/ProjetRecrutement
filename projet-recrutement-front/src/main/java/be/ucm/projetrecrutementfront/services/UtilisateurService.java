package be.ucm.projetrecrutementfront.services;

import java.time.LocalDate;
import java.util.HashSet;

import be.ucm.projetrecrutementfront.models.Group;
import be.ucm.projetrecrutementfront.models.Utilisateur;
import be.ucm.projetrecrutementfront.models.forms.InscriptionUtilisateur;
import org.json.*;

public class UtilisateurService {

    private static UtilisateurService instance;

    private UtilisateurService(){
    }

    public static UtilisateurService getInstance(){
        if(instance == null){
            instance = new UtilisateurService();
        }
        return instance;
    }
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

    public Utilisateur getUtilisateur(Long id) {
        Utilisateur u = new Utilisateur();
        String output = ApiService.getInstance().contacterApiSansBody("utilisateur/" + id, "GET");
        if (output != null) {
            JSONObject json = new JSONObject(output);
            getInstance().jsonToUtilisateur(u, json);
            u.setMaitrises(MaitriseService.getInstance().jsonToMaitrises(json));
            u.setProjetsParticipes(ParticipationProjetService.getInstance().jsonToParticipationProjets(json));
            u.setProjetsCrees(ProjetService.getInstance().jsonToProjets(json.getJSONArray("projetsCrees")));
        }
        return u;
    }

    private void jsonToUtilisateur(Utilisateur u, JSONObject json) {
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

    public Utilisateur inscriptionUtilisateur(InscriptionUtilisateur utilisateur){
        JSONObject jsonUtilisateur = new JSONObject(utilisateur);
        String stringUtilisateur = jsonUtilisateur.toString();
        stringUtilisateur = ApiService.getInstance().contacterApiAvecBody("utilisateur/create", "POST", stringUtilisateur);
        if(stringUtilisateur != null){
            Utilisateur u = new Utilisateur();
            jsonToUtilisateur(u, new JSONObject(stringUtilisateur));

            System.out.println(u);
            return u;
        }
        return null;
    }


}
