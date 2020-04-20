package be.ucm.projetrecrutementapi.services;

import be.ucm.projetrecrutementapi.api.dto.CandidatureFormulaireDTO;
import be.ucm.projetrecrutementapi.dal.entities.Candidature;

import java.util.List;

public interface CandidatureService {
    List<Candidature> findByProjet(Long ProjetId);
    Candidature save(CandidatureFormulaireDTO candidature) throws Exception;
}
