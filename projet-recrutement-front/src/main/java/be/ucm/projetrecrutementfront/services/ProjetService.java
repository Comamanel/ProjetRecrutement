package be.ucm.projetrecrutementfront.services;

import be.ucm.projetrecrutementfront.models.Projet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjetService {

    private static ApiService apiService;

    public static List<Projet> getProjets(){

        ArrayList<Projet> projets = new ArrayList<Projet>();
        String output = apiService.contacterAPI("projet/", "GET");

        JSONArray ja = new JSONArray(output);
        System.out.println(ja.get(0));
        System.out.println(ja.get(1));

        for(int i=0; i<ja.length(); i++){
           JSONObject p = ja.getJSONObject(i);
           Projet np = remplirProjet(p);
           projets.add(np);
        }

        return projets;
    }

    private static Projet remplirProjet(JSONObject output){
        Projet par = new Projet();
        if(output != null){
            par.setId(output.getLong("id"));
            par.setNom(output.getString("name"));
            par.setDescription(output.getString("description"));
            par.setDateDebut(LocalDate.parse(output.getString("dateDebut")));
            par.setDateFin(LocalDate.parse(output.getString("dateFin")));
            par.setTypeProjet(output.getString("typeProjet"));
            par.setMaxParticipants(output.getInt("maxParticipants"));
            par.setNbParticipants(output.getInt("nbParticipants"));
            par.setStatut(output.getString("statut"));
            par.setTpsTravailHebdo(output.getInt("tpsTravailHebdo"));
        }
        return par;
    }



}
