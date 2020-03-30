package be.ucm.projetrecrutementapi.services;

import be.ucm.projetrecrutementapi.api.dto.CandidatureDemandeFormulaireDTO;
import be.ucm.projetrecrutementapi.dal.entities.Candidature;

public interface CandidatureService {
    Candidature save(CandidatureDemandeFormulaireDTO candidature);
}
