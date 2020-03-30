package be.ucm.projetrecrutementapi.services;

import be.ucm.projetrecrutementapi.api.dto.CandidatureFormulaireDTO;
import be.ucm.projetrecrutementapi.dal.entities.Candidature;

public interface CandidatureService {
    Candidature save(CandidatureFormulaireDTO candidature);
}
