package be.ucm.projetrecrutementfront.services;

import be.ucm.projetrecrutementfront.models.Projet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class ProjetService {

    public static Set<Projet> remplirDesProjets(JSONArray projetsjson){
        Set<Projet> projets = new HashSet<>();

        for (int i = 0; i < projetsjson.length(); i++) {
            JSONObject projetJson = projetsjson.getJSONObject(i);
            projets.add(remplirUnProjet(projetJson));
        }
        return projets;
    }

    public static Projet remplirUnProjet(JSONObject json){
        Projet p = new Projet();
        try{
            p.setId(json.getLong("id"));
            p.setNom(json.getString("nom"));
            p.setDescription(json.getString("description"));
            p.setDateDebutString(json.getString("dateDebut"));
            p.setDateFinString(json.getString("dateFin"));
            p.setStatut(json.getString("statut"));
            p.setTypeProjet(json.getString("typeProjet"));
            p.setTpsTravailHebdo(json.getInt("tpsTravailHebdo"));
            p.setMaxParticipants(json.getInt("maxParticipants"));
            p.setMaitrises(MaitriseService.remplirPlusieursMaitrises(json));
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        return p;
    }
}
