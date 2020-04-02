package be.ucm.projetrecrutementapi.services;

import be.ucm.projetrecrutementapi.Exceptions.CandidatureNonValideException;
import be.ucm.projetrecrutementapi.api.dto.CandidatureFormulaireDTO;
import be.ucm.projetrecrutementapi.dal.entities.*;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatCandidature;
import be.ucm.projetrecrutementapi.dal.entities.enums.TypeProjet;
import be.ucm.projetrecrutementapi.dal.repositories.CandidatureDAO;
import be.ucm.projetrecrutementapi.dal.repositories.ProjetDAO;
import be.ucm.projetrecrutementapi.dal.repositories.TechnologieDAO;
import be.ucm.projetrecrutementapi.dal.repositories.UtilisateurDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
}
