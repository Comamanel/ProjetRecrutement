package be.ucm.projetrecrutementapi.services;

import be.ucm.projetrecrutementapi.api.dto.CandidatureFormulaireDTO;
import be.ucm.projetrecrutementapi.api.dto.TraitementCandidatureFormulaireDTO;
import be.ucm.projetrecrutementapi.dal.entities.Candidature;
import be.ucm.projetrecrutementapi.dal.entities.Participation_Projet;

import java.util.List;

public interface CandidatureService {
    List<Candidature> findByProjet(Long ProjetId);
    Candidature save(CandidatureFormulaireDTO candidature) throws Exception;
    Participation_Projet validerCandidature(TraitementCandidatureFormulaireDTO candidature);
}
