package be.ucm.projetrecrutementfront.services;

import be.ucm.projetrecrutementfront.models.Participation_Projet;
import be.ucm.projetrecrutementfront.models.Projet;
import be.ucm.projetrecrutementfront.models.Utilisateur;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjetService {

    private static ProjetService instance;

    private ProjetService(){}

    public static ProjetService getInstance(){
        if(instance == null){
            instance = new ProjetService();
        }
        return instance;
    }


    public Projet getProjet(int idProjet){

        String output = ApiService.getInstance().contacterApiSansBody("projet/" + idProjet, "GET");

        JSONObject jo = new JSONObject(output);

        Projet p = remplirProjet(jo);
        p.setMaitrises(MaitriseService.remplirPlusieursMaitrises(jo));

        Long adminId = UtilisateurService.getUtilisateursParProjet(idProjet, true).fst;
        Set<Utilisateur> participantsActifs = UtilisateurService.getUtilisateursParProjet(idProjet, true).snd;
        Set<Utilisateur> participantsNonActifs = UtilisateurService.getUtilisateursParProjet(idProjet, false).snd;
        p.setUtilActifs(participantsActifs);
        p.setUtilNonActifs(participantsNonActifs);
        p.setAdminId(adminId);

        return p;

    }

    public Set<Projet> getProjets(){

        Set<Projet> projets = new HashSet<>();
        String output = ApiService.getInstance().contacterApiSansBody("projet/", "GET");

        JSONArray ja = new JSONArray(output);

        projets = jsonToProjets(ja);

        return projets;
    }

    public Projet jsonToProjet(JSONObject output){
        Projet par = new Projet();
        if (output != null) {
            try {
                par.setId(output.getLong("id"));
                par.setNom(output.getString("nom"));
                par.setDescription(output.getString("description"));
                par.setDateDebut(LocalDate.parse(output.getString("dateDebut")));
                par.setDateFin(LocalDate.parse(output.getString("dateFin")));
                par.setTypeProjet(output.getString("typeProjet"));
                par.setMaxParticipants(output.getInt("maxParticipants"));
                par.setStatut(output.getString("statut"));
                par.setTpsTravailHebdo(output.getInt("tpsTravailHebdo"));
                //par.setMaitrises(MaitriseService.remplirPlusieursMaitrises(output));
                //par.setNbParticipants(output.getInt("nbParticipants"));
                //L'attribut NbParticipats a l'air d'être nulle part dans l'API :o)
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return par;
    }

    public Set<Projet> jsonToProjets(JSONArray output){
        Set<Projet> projets = new HashSet<>();

        for (int i = 0; i < output.length() ; i++) {
            projets.add(jsonToProjet(output.getJSONObject(i)));
        }

        return projets;
    }



}
