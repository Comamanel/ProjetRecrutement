package be.ucm.projetrecrutementapi.services;

import be.ucm.projetrecrutementapi.api.dto.CandidatureFormulaireDTO;
import be.ucm.projetrecrutementapi.dal.entities.Candidature;
import be.ucm.projetrecrutementapi.dal.entities.Technologie;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatCandidature;
import be.ucm.projetrecrutementapi.dal.repositories.CandidatureDAO;
import be.ucm.projetrecrutementapi.dal.repositories.ProjetDAO;
import be.ucm.projetrecrutementapi.dal.repositories.TechnologieDAO;
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

    @Autowired
    private TechnologieDAO technologieDAO;

    @Override
    public Candidature save(CandidatureFormulaireDTO candidature) {
        Candidature candid = new Candidature();
        candid.setNbHeuresSemaine(candidature.getNbHeuresSemaine());
        candid.setStatut(EtatCandidature.ATT);
        candid.setProjet(this.projetDAO.getOne(candidature.getProjetId()));
        candid.setUtilisateur(this.utilisateurDAO.getOne(candidature.getUtilisateurId()));

        candidature
                .getTechnologiesSouhaitees()
                .forEach(id -> candid.getTechnologieSouhaitee().add(this.technologieDAO.getOne(id)));

        return this.candidatureDAO.save(candid);
    }
}
