package be.ucm.projetrecrutementfront.services;

import be.ucm.projetrecrutementfront.models.Technologie;
import org.json.JSONObject;

public class TechnologieService {
    public static Technologie remplirUneTechnologie(JSONObject json){
        Technologie technologie = new Technologie();
        technologie.setId(json.getLong("id"));
        technologie.setNom(json.getString("nom"));
        technologie.setCreateur(json.getString("createur"));
        return technologie;
    }
}
