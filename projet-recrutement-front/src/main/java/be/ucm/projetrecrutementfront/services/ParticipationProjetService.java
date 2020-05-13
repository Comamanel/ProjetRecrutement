package be.ucm.projetrecrutementfront.services;

import be.ucm.projetrecrutementfront.models.Maitrise;
import be.ucm.projetrecrutementfront.models.Participation_Projet;
import be.ucm.projetrecrutementfront.models.Projet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class ParticipationProjetService {

    private static ParticipationProjetService instance;

    private ParticipationProjetService(){
    }

    public static ParticipationProjetService getInstance(){
        if(instance == null){
            instance = new ParticipationProjetService();
        }
        return instance;
    }

    public Set<Participation_Projet> jsonToParticipationProjets(JSONObject json){
        Set<Participation_Projet> participationProjets = new HashSet<>();
        JSONArray participationsJson = json.getJSONArray("projetsParticipes");


        for (int i = 0; i < participationsJson.length(); i++) {
            Participation_Projet participationProjet = new Participation_Projet();
            JSONObject maitriseJson = participationsJson.getJSONObject(i);
            participationProjet.setId(maitriseJson.getLong("id"));
            participationProjet.setActif(maitriseJson.getBoolean("actif"));
            participationProjet.setProprio(maitriseJson.getBoolean("proprio"));
            participationProjet.setProjet(ProjetService.getInstance().jsonToProjet(maitriseJson.getJSONObject("projet")));
            participationProjets.add(participationProjet);
        }
        return participationProjets;
    }

    public Participation_Projet remplirUnProjet(Participation_Projet p, JSONObject json){
        return p;
    }
}
