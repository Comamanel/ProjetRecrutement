package be.ucm.projetrecrutementfront.services;

import be.ucm.projetrecrutementfront.models.Maitrise;
import be.ucm.projetrecrutementfront.models.Technologie;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class MaitriseService {

    private static MaitriseService instance;

    public static MaitriseService getInstance(){
        if(instance == null)
            instance = new MaitriseService();
        return instance;
    }

    private MaitriseService(){}


    public Set<Maitrise> jsonToMaitrises(JSONObject json) {
        Set<Maitrise> maitrises = new HashSet<>();
        JSONArray maitrisesJson = json.getJSONArray("maitrises");

        for (int i = 0; i < maitrisesJson.length(); i++) {
            Maitrise maitrise = new Maitrise();
            JSONObject maitriseJson = maitrisesJson.getJSONObject(i);
            maitrise.setId(maitriseJson.getLong("id"));
            maitrise.setNiveauMaitrise(maitriseJson.getString("niveauMaitrise"));
            maitrise.setTechnologie(TechnologieService.getInstance().jsonToTechnologie(maitriseJson.getJSONObject("technologie")));
            maitrises.add(maitrise);
        }

        return maitrises;
    }
}
