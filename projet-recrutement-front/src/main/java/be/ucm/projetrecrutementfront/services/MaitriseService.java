package be.ucm.projetrecrutementfront.services;

import be.ucm.projetrecrutementfront.models.Maitrise;
import be.ucm.projetrecrutementfront.models.Technologie;
import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class MaitriseService {

    public static Set<Maitrise> remplirPlusieursMaitrises(JSONObject json) {
        Set<Maitrise> maitrises = new HashSet<>();
        JSONArray maitrisesJson = json.getJSONArray("maitrises");

        for (int i = 0; i < maitrisesJson.length(); i++) {
            Maitrise maitrise = new Maitrise();
            JSONObject maitriseJson = maitrisesJson.getJSONObject(i);
            maitrise.setId(maitriseJson.getLong("id"));
            maitrise.setNiveauMaitrise(maitriseJson.getString("niveauMaitrise"));
            maitrise.setTechnologie(TechnologieService.remplirUneTechnologie(maitriseJson.getJSONObject("technologie")));
            maitrises.add(maitrise);
        }

        return maitrises;
    }

}
