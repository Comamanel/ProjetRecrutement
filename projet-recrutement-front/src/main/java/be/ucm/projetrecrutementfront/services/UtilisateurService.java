package be.ucm.projetrecrutementfront.services;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.HashSet;

import be.ucm.projetrecrutementfront.models.Group;
import be.ucm.projetrecrutementfront.models.Utilisateur;
import com.sun.tools.javac.util.Pair;
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

    public static Pair<Long, Set<Utilisateur>> getUtilisateursParProjet(Long idProjet, Boolean active) {

        Long idAdmin = null;
        Set<Utilisateur> utilisateurs = new HashSet<>();

        String output = ApiService.contacterApiSansBody("utilisateur/", "GET");
        JSONArray ja = new JSONArray(output);

        for (int i = 0; i<ja.length(); i++){
            JSONObject utilJo = ja.getJSONObject(i);
            JSONArray ppjo = utilJo.getJSONArray("projetsParticipes");
            for(int j = 0; j<ppjo.length(); j++){
                JSONObject pjo = ppjo.getJSONObject(j).getJSONObject("projet");
                if(pjo.getLong("id") == idProjet){
                    Utilisateur nu = new Utilisateur();
                    remplirUnUtilisateur(nu, utilJo);
                    if(ppjo.getJSONObject(j).getBoolean("actif") == active) {
                        utilisateurs.add(nu);
                        if(ppjo.getJSONObject(j).getBoolean("proprio")){
                            idAdmin = nu.getId();
                        }
                    }
                }
            }
        }

        return new Pair<>(idAdmin, utilisateurs);
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
