package be.ucm.projetrecrutementfront.services;

import be.ucm.projetrecrutementfront.models.Projet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjetService {

    public static List<Projet> getProjets() {

        List<Projet> projets = new ArrayList<>();
        String output = ApiService.contacterApiSansBody("projet/", "GET");

        JSONArray ja = new JSONArray(output);
        System.out.println(ja.get(0));
        System.out.println(ja.get(1));

        for (int i = 0; i < ja.length(); i++) {
            JSONObject p = ja.getJSONObject(i);
            Projet np = remplirProjet(p);
            projets.add(np);
        }

        return projets;
    }

    public static Set<Projet> remplirDesProjets(JSONArray output){
        Set<Projet> projets = new HashSet<>();

        for (int i = 0; i < output.length() ; i++) {
            projets.add(remplirProjet(output.getJSONObject(i)));
        }

        return projets;
    }

    public static Projet remplirProjet(JSONObject output) {
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
                par.setMaitrises(MaitriseService.remplirPlusieursMaitrises(output));
                //par.setNbParticipants(output.getInt("nbParticipants"));
                //L'attribut NbParticipats a l'air d'être nulle part dans l'API :o)
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return par;
    }
}
