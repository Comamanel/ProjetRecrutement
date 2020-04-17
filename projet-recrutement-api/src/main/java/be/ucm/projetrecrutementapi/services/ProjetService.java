package be.ucm.projetrecrutementapi.services;

import be.ucm.projetrecrutementapi.api.dto.ChangementProprietaireFormulaire;
import be.ucm.projetrecrutementapi.api.dto.ProjetFiltreDTO;
import be.ucm.projetrecrutementapi.dal.entities.Maitrise;
import be.ucm.projetrecrutementapi.dal.entities.Projet;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ProjetService {
    Optional<Projet> findById(Long id);
    List<Projet> findByUserId(Long id);
    List<Projet> findAll();
    public List<Projet> findAllFiltered(ProjetFiltreDTO filtres);
    public Projet testerValiditeProjet(Utilisateur utilisateurActif, Projet nouveauProjet);
    public boolean verifierProprieteProjet(Utilisateur utilisateurActif, Projet projetVise);
    public boolean changerProprietaireProjet(ChangementProprietaireFormulaire changementProprietaireFormulaire);
    public Projet modifierInfosProjet(Utilisateur utilisateurActif, Projet projetActif, Projet projetModif);
    public Utilisateur ajouterParticipant(Projet projetActif, Utilisateur candidat);
    public Projet ajouterMaitrise(Projet projetActif, Maitrise nouvelleMaitrise);
}
