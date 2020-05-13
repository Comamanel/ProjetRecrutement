package be.ucm.projetrecrutementfront.services;

import be.ucm.projetrecrutementfront.models.Technologie;
import org.json.JSONObject;

public class TechnologieService {

    private static TechnologieService instance;

    public static TechnologieService getInstance(){
        if(instance == null)
            instance = new TechnologieService();
        return instance;
    }

    private TechnologieService(){}


    public Technologie jsonToTechnologie(JSONObject json){
        Technologie technologie = new Technologie();
        technologie.setId(json.getLong("id"));
        technologie.setNom(json.getString("nom"));
        technologie.setCreateur(json.getString("createur"));
        return technologie;
    }
}
