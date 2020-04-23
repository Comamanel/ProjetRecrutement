package be.ucm.projetrecrutementapi.services;

import be.ucm.projetrecrutementapi.Exceptions.CandidatureNonValideException;
import be.ucm.projetrecrutementapi.api.dto.CandidatureFormulaireDTO;
import be.ucm.projetrecrutementapi.api.dto.TraitementCandidatureFormulaireDTO;
import be.ucm.projetrecrutementapi.dal.entities.*;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatCandidature;
import be.ucm.projetrecrutementapi.dal.entities.enums.TypeProjet;
import be.ucm.projetrecrutementapi.dal.repositories.*;
import io.cucumber.java.ro.Cand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
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

    @Autowired
    private ParticipationDAO participationDAO;

    @Override
    public List<Candidature> findByProjet(Long projetId) {
        return this.candidatureDAO.findByProjet(projetId);
    }

    @Override
    public Candidature save(CandidatureFormulaireDTO candidature) throws Exception {
        Candidature candid = new Candidature();
        candid.setNbHeuresSemaine(candidature.getNbHeuresSemaine());
        candid.setStatut(EtatCandidature.ATT);
        Projet p = this.projetDAO.getOne(candidature.getProjetId());
        Utilisateur u = this.utilisateurDAO.getOne(candidature.getUtilisateurId());

        if(p.getNom() == null)
            throw new EntityNotFoundException();
        if(u.getPseudo() == null ) {
            throw new EntityNotFoundException();
        }

        candid.setProjet(p);
        candid.setUtilisateur(u);

        candidature
                .getTechnologiesSouhaitees()
                .forEach(id -> candid.getTechnologieSouhaitee().add(this.technologieDAO.getOne(id)));


        if(checkCandidOk(candid))
            return this.candidatureDAO.save(candid);
        throw new CandidatureNonValideException();
    }

    @Override
    public Participation_Projet validerCandidature(TraitementCandidatureFormulaireDTO traitementCandidatureFormulaireDTO) {
        Candidature candidature = this.candidatureDAO.findById(traitementCandidatureFormulaireDTO.getCandidatureId()).orElse(null);

        if(candidature != null){
            Participation_Projet nouvelleParticipation = new Participation_Projet();
            nouvelleParticipation.setActif(true);
            nouvelleParticipation.setProprio(false);
            nouvelleParticipation.setProjet(candidature.getProjet());
            nouvelleParticipation.setUtilisateur(candidature.getUtilisateur());
            nouvelleParticipation = this.participationDAO.save(nouvelleParticipation);

            if(nouvelleParticipation.getId() != null){
                candidature.setStatut(EtatCandidature.ARC);
                candidatureDAO.save(candidature);
                return nouvelleParticipation;
            }
        }
        return new Participation_Projet();
    }

    @Override
    public Candidature refuserCandidature(TraitementCandidatureFormulaireDTO traitementCandidatureFormulaireDTO) {
        Candidature candidature = this.candidatureDAO.findById(traitementCandidatureFormulaireDTO.getCandidatureId()).orElse(null);

        if(candidature != null){
            candidature.setStatut(EtatCandidature.SUS);
            candidature = this.candidatureDAO.save(candidature);
        }
        return candidature;
    }

    //Trucs à check : si le projet est sérieux, l'user doit avoir coché des trucs qu'il connaît, sinon non
    private boolean checkCandidOk(Candidature candidature){
        if(candidature.getProjet().getTypeProjet() == TypeProjet.SER){
            for (Technologie technoSouhaitee: candidature.getTechnologieSouhaitee()) {
                boolean estMaitrise = false;
                Maitrise[] maitriseTab = candidature.getUtilisateur().getMaitrises().toArray(new Maitrise[0]);
                for(int i = 0; i < maitriseTab.length && !estMaitrise; i++){
                    if(maitriseTab[i].getTechnologie() == technoSouhaitee)
                        estMaitrise = true;
                }

                if(!estMaitrise)
                    return false;
            }
        }

        return true;
    }

    public boolean testsBooleanOk(Candidature candidature){
        return this.checkCandidOk(candidature);
    }
}
