package be.ucm.projetrecrutementapi.services;

import be.ucm.projetrecrutementapi.api.dto.ProjetFiltreDTO;
import be.ucm.projetrecrutementapi.dal.entities.Projet;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatProjet;
import be.ucm.projetrecrutementapi.dal.entities.enums.TypeProjet;
import be.ucm.projetrecrutementapi.dal.repositories.ProjetDAO;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetServiceImpl implements ProjetService {
    @Autowired
    private ProjetDAO projetDAO;

    @Override
    public Optional<Projet> findById(Long id) {
        return this.projetDAO.findById(id);
    }

    @Override
    public List<Projet> findByUserId(Long id) { return this.projetDAO.findByUserId(id); }

    @Override
    public List<Projet> findAll() {
        return this.projetDAO.findAll();
    }

    @Override
    public List<Projet> findAllFiltered(ProjetFiltreDTO filtres) {
        if(filtres.getDateDebutEgal() != null){
            filtres.setDateDebutEgalConverti(filtres.getDateDebutEgal().toLocalDate());
        }

        if(filtres.getDateDebutAvant() != null){
            filtres.setDateDebutAvantConverti(filtres.getDateDebutAvant().toLocalDate());
        }

        if(filtres.getDateDebutApres() != null){
            filtres.setDateDebutApresConverti(filtres.getDateDebutApres().toLocalDate());
        }

        if(filtres.getDateFinEgal() != null){
            filtres.setDateFinEgalConverti(filtres.getDateFinEgal().toLocalDate());
        }

        if(filtres.getDateFinAvant() != null){
            filtres.setDateFinAvantConverti(filtres.getDateFinAvant().toLocalDate());
        }

        if(filtres.getDateFinApres() != null){
            filtres.setDateFinApresConverti(filtres.getDateFinApres().toLocalDate());
        }
        return this.projetDAO.getFiltered(filtres);
    }

    @Override
    public Projet testerValiditeProjet(Utilisateur utilisateurActif, Projet nouveauProjet) {

        if(utilisateurActif == null || utilisateurActif.getId() == null){
            return null;
        }

        String titre = nouveauProjet.getNom();
        if(titre == null || titre.trim().equals("")){
            return null;
        }

        LocalDate dateDebut = nouveauProjet.getDateDebut();
        LocalDate dateFin = nouveauProjet.getDateFin();
        if((dateFin.compareTo(dateDebut) < 0) || (LocalDate.now().compareTo(dateDebut) > 0)){
            return null;
        }

        int longueurDescription = nouveauProjet.getDescription().length();
        if(longueurDescription > 1000){
            return null;
        }

        int projetsUtilisateur = utilisateurActif.getProjetsParticipes().size();
        if(projetsUtilisateur >= 5){
            return null;
        }

        int projetsUtilisateurAdmin = utilisateurActif.getProjetsCrees().size();
        if(projetsUtilisateurAdmin >= 3){
            return null;
        }

        int maxParticipants = nouveauProjet.getMaxParticipants();
        if(maxParticipants < 0){
            return null;
        }

        int tpsTravailHebdo = nouveauProjet.getTpsTravailHebdo();
        if(tpsTravailHebdo < 0){
            return null;
        }

        TypeProjet typeProjet = nouveauProjet.getTypeProjet();
        if(typeProjet == null){
            return null;
        }

        nouveauProjet.setStatut(EtatProjet.ACT);
        return nouveauProjet;

    }
}
