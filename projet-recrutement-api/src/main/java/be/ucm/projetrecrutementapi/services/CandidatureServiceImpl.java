package be.ucm.projetrecrutementapi.services;

import be.ucm.projetrecrutementapi.api.dto.CandidatureDemandeFormulaireDTO;
import be.ucm.projetrecrutementapi.dal.entities.Candidature;
import be.ucm.projetrecrutementapi.dal.entities.Technologie;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatCandidature;
import be.ucm.projetrecrutementapi.dal.repositories.CandidatureDAO;
import be.ucm.projetrecrutementapi.dal.repositories.ProjetDAO;
import be.ucm.projetrecrutementapi.dal.repositories.UtilisateurDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CandidatureServiceImpl implements CandidatureService {
    @Autowired
    private ProjetDAO projetDAO;

    @Autowired
    private UtilisateurDAO utilisateurDAO;

    @Autowired
    private CandidatureDAO candidatureDAO;

    @Override
    public Candidature save(CandidatureDemandeFormulaireDTO candidature) {
        Candidature candid = new Candidature();
        candid.setNbHeuresSemaine(candidature.getNbHeuresSemaine());
        candid.setTechnologieSouhaitee(
                candidature
                        .getTechnologiesSouhaitees()
                        .stream()
                        .map(Technologie::new)
                        .collect(Collectors.toSet())
        );
        candid.setStatut(EtatCandidature.ATT);
        candid.setProjet(this.projetDAO.getOne(candidature.getProjetId()));
        candid.setUtilisateur(this.utilisateurDAO.getOne(candidature.getUtilisateurId()));

        return this.candidatureDAO.save(candid);
    }
}
