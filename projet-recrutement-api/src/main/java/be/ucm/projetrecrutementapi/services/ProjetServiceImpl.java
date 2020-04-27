package be.ucm.projetrecrutementapi.services;

import be.ucm.projetrecrutementapi.api.dto.AnnulationParticipationProjetDTO;
import be.ucm.projetrecrutementapi.api.dto.ChangementProprietaireFormulaire;
import be.ucm.projetrecrutementapi.api.dto.ProjetFiltreDTO;
import be.ucm.projetrecrutementapi.dal.entities.*;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatProjet;
import be.ucm.projetrecrutementapi.dal.entities.enums.NiveauMaitrise;
import be.ucm.projetrecrutementapi.dal.entities.enums.TypeProjet;
import be.ucm.projetrecrutementapi.dal.repositories.ParticipationDAO;
import be.ucm.projetrecrutementapi.dal.repositories.ProjetDAO;
import be.ucm.projetrecrutementapi.dal.repositories.UtilisateurDAO;
import lombok.EqualsAndHashCode;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjetServiceImpl implements ProjetService {

    @Autowired
    private UtilisateurDAO utilisateurDAO;

    @Autowired
    private ProjetDAO projetDAO;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ParticipationDAO participationDAO;

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
        } else {
           Set<Participation_Projet> pu = utilisateurActif.getProjetsParticipes();
           Participation_Projet[] projetsUtilisateur = pu.toArray(new Participation_Projet[pu.size()]);
           for(int i=0; i<projetsUtilisateur.length; i++){
               boolean proprietaireProjetBoucle = projetsUtilisateur[i].isProprio();
               EtatProjet statutProjetBoucle = projetsUtilisateur[i].getProjet().getStatut();
              String nomProjetBoucle = projetsUtilisateur[i].getProjet().getNom();
              if(proprietaireProjetBoucle && (nomProjetBoucle.toLowerCase()).equals(titre.toLowerCase()) && statutProjetBoucle.equals(EtatProjet.ACT)){
                  return null;
              }
          }
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

    @Override
    public boolean verifierProprieteProjet(Utilisateur utilisateurActif, Projet projetVise) {

        Participation_Projet pp = participationDAO.findByUserAndProject(utilisateurActif.getId(), projetVise.getId()).orElse(null);

        if(pp != null) {
            return pp.isProprio();
        }

        return false;

    }

    @Override
    public boolean changerProprietaireProjet(ChangementProprietaireFormulaire changementProprietaireFormulaire) {
        if(changementProprietaireFormulaire.getProjet() == null || changementProprietaireFormulaire.getAncienProprietaire() == null || changementProprietaireFormulaire.getNouveauProprietaire() == null)
            return false;
        Utilisateur ancienProprio = this.utilisateurDAO.findById(changementProprietaireFormulaire.getAncienProprietaire()).orElse(null);
        Utilisateur nouveauProprio = this.utilisateurDAO.findById(changementProprietaireFormulaire.getNouveauProprietaire()).orElse(null);

        if(ancienProprio == null || ancienProprio.getProjetsParticipes() == null)
            return false;
        if(nouveauProprio == null || nouveauProprio.getProjetsParticipes() == null)
            return false;

        boolean changeageProprioOk = utilisateurService.checkUtilisateurNEstPasProprietaireDePlusDeDeuxProjets(nouveauProprio);

        if(!changeageProprioOk)
            return changeageProprioOk;

        Participation_Projet participationAncienProprio = ancienProprio.getProjetsParticipes().stream().filter(p -> p.getProjet().getId().equals(changementProprietaireFormulaire.getProjet())).findFirst().orElse(null);
        Participation_Projet participationNouveauProprio = nouveauProprio.getProjetsParticipes().stream().filter(p -> p.getProjet().getId().equals(changementProprietaireFormulaire.getProjet())).findFirst().orElse(null);

        if(participationAncienProprio == null || participationNouveauProprio == null || !participationAncienProprio.isProprio())
            return false;

        ancienProprio.getProjetsParticipes().stream().filter(p -> p.getId().equals(participationAncienProprio.getId())).findFirst().orElse(new Participation_Projet()).setProprio(false);

        nouveauProprio.getProjetsParticipes().stream().filter(p -> p.getProjet().getId().equals(changementProprietaireFormulaire.getProjet())).findFirst().orElse(new Participation_Projet()).setProprio(true);

        try{
            utilisateurDAO.save(nouveauProprio);
            utilisateurDAO.save(ancienProprio);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Projet modifierInfosProjet(Utilisateur utilisateurActif, Projet projetActif, Projet projetModif){

        if (verifierProprieteProjet(utilisateurActif, projetActif)) {

            String newNom = projetModif.getNom();
            List<Projet> projetsUtilisateur = projetDAO.findByUserId(utilisateurActif.getId());

            if (newNom != null && !(newNom.trim().equals(""))) {
                for (Projet projet : projetsUtilisateur) {
                    if (!(projet.getNom().equals(newNom))) {
                        projetActif.setNom(newNom);
                    }
                }
            }

            String newDesc = projetModif.getDescription();
            if (newDesc != null && newDesc.length() < 1000) {
                projetActif.setDescription(newDesc);
            }

            LocalDate newDateFin = projetModif.getDateFin();
            if (newDateFin != null && newDateFin.compareTo(projetActif.getDateDebut()) > 0) {
                projetActif.setDateFin(newDateFin);
            }

            int newMaxParticipants = projetModif.getMaxParticipants();
            if (newMaxParticipants >= 0 & newMaxParticipants != projetActif.getMaxParticipants()) {
                projetActif.setMaxParticipants(newMaxParticipants);
            }

            int newTpsTravailHebdo = projetModif.getTpsTravailHebdo();
            if (newTpsTravailHebdo > 0 && newTpsTravailHebdo < 24) {
                projetActif.setTpsTravailHebdo(newTpsTravailHebdo);
            }
        }

        return projetActif;
    }

    @Override
    public Utilisateur ajouterParticipant (Projet projetActif, Utilisateur candidat){

        Set<Participation_Projet> candidats = participationDAO.findByProjectId(projetActif.getId());

        if(projetActif.getMaxParticipants() == 0 || projetActif.getMaxParticipants() > candidats.size()){

            int projetsActifs = candidat.getProjetsParticipes().size();
            System.out.println(projetsActifs);

            if (projetsActifs < 5) {
                Participation_Projet pp = new Participation_Projet();
                pp.setProjet(projetActif);
                pp.setActif(true);
                pp.setProprio(false);
                pp.setUtilisateur(candidat);

                candidat.getProjetsParticipes().add(pp);
                return candidat;
            }
        }
        return null;
    }

    @Override
    public Projet ajouterMaitrise(Projet projetActif, Maitrise nouvelleMaitrise){

        Maitrise presenceMaitrise =
                projetActif.getMaitrisesDemandees().stream()
                .filter(mp -> mp.getTechnologie().equals(nouvelleMaitrise.getTechnologie()))
                .filter(mp -> mp.getNiveauMaitrise().equals(nouvelleMaitrise.getNiveauMaitrise()))
                        .findFirst().orElse(null);

        if(presenceMaitrise == null){
            projetActif.getMaitrisesDemandees().add(nouvelleMaitrise);
        }

        return projetActif;

    }

    @Override
    public Projet retirerMaitrise(Projet projetActif, Maitrise maitriseARetirer){

        projetActif.getMaitrisesDemandees().remove(maitriseARetirer);
        return projetActif;

    }

    @Override
    public Projet ajouterProfilType(Projet projetActif, Set<ProfilType> profilsType){

        for(ProfilType pf : profilsType){
            projetActif.getProfilsType().add(pf);
        }

        return projetActif;

    }

    @Override
    public Projet retirerProfilType(Projet projetActif, ProfilType profilASupp){

        projetActif.getProfilsType().remove(profilASupp);

        return projetActif;

    }

    @Override
    public AnnulationParticipationProjetDTO annulationParticipationProjet(AnnulationParticipationProjetDTO annulationParticipationProjet) {
        annulationParticipationProjet.setAnnulationActeeEnDB(false);
        annulationParticipationProjet.setNecessitePasserLeRoleDeProprietaire(false);

        Participation_Projet pp = this.participationDAO.findByUserAndProject(annulationParticipationProjet.getUtilisateurId(), annulationParticipationProjet.getProjetId()).orElse(null);

        if(pp != null){

            if(!pp.isProprio()){
                pp.setActif(false);
                this.participationDAO.save(pp);
                annulationParticipationProjet.setAnnulationActeeEnDB(true);
            }
            else{

                if(pp.getProjet() != null && checkLeProjetNAQuUnSeulParticipant(pp.getProjet())){
                     //Projet p = pp.getProjet();
                    pp.getProjet().setStatut(EtatProjet.ARC);
                    pp.setActif(false);
                    this.participationDAO.save(pp);
                    annulationParticipationProjet.setAnnulationActeeEnDB(true);
                }
                else{
                    annulationParticipationProjet.setNecessitePasserLeRoleDeProprietaire(true);
                }
            }


        }


        return annulationParticipationProjet;
    }

    @Override
    public double CalculerMatchingProjet(Projet projetActif, Utilisateur utilisateurActif) {

        double matching = 0;
        List<Maitrise> maitrisesUtilisateur = new ArrayList<>(utilisateurActif.getMaitrises());
        int nbMaitrisesUtilisateur = maitrisesUtilisateur.size();
        double matchingScale = 100.0/nbMaitrisesUtilisateur;

        for (Maitrise mu : maitrisesUtilisateur) {
            //mp = Maitrise présente
            Maitrise mp = projetActif.getMaitrisesDemandees()
                    .stream()
                    .filter(maitrise -> maitrise.getTechnologie().equals(mu.getTechnologie()))
                    .findFirst()
                    .orElse(null);

            if (mp != null) {
                //Niveau Utilisateur
                int nu = mu.getNiveauMaitrise().getValeurNiveau();
                //Niveau Projet
                int np = mp.getNiveauMaitrise().getValeurNiveau();

                if(np <= nu){
                    matching += matchingScale;
                }
            }
        }

        return matching;

    }

    @Override
    public double CalculerMatchingUtilisateur(Projet projetActif, Utilisateur utilisateurActif){

        double matching = 0;
        int nbProfilsTypeProjet = projetActif.getProfilsType().size();
        double matchingScale = 100.0/nbProfilsTypeProjet;

        //Ne conserve que les profils type dont la place est libre
        List<ProfilType> profilsLibres = projetActif.getProfilsType().stream().filter(ProfilType::isOuvert).collect(Collectors.toList());

        //Observe les profils un par un et établit une "checklist" des maitrises qui doivent être confirmées
        for(ProfilType pl : profilsLibres){
            //Maitrises profil = mp
            Set<Maitrise> mp = pl.getMaitrisesDemandees();
            int maitrisesOk = 0;
            int nbMaitrisesProfil = mp.size();

            //Maitrises utilisateur = mu
            Set<Maitrise> mu = utilisateurActif.getMaitrises();

            //Observe chaque maitrise une par une et vérifie que les maitrises de l'utilisateur correspondent
            //Maitrise Profil Itérée = mpi
            for(Maitrise mpi : mp){

                //Récupère un optional de maitrise correspondante dans le profil user
                //Maitrise utilisateur itérée = mui
                Maitrise maitriseTechCheck = mu.stream().filter(mui -> mui.getTechnologie().equals(mpi.getTechnologie())).findFirst().orElse(null);

                if(maitriseTechCheck != null){
                    if(maitriseTechCheck.getNiveauMaitrise().getValeurNiveau() >= mpi.getNiveauMaitrise().getValeurNiveau())
                    maitrisesOk++;
                }

            }

            if(maitrisesOk == nbMaitrisesProfil){

                matching += matchingScale;

            }

        }

        return matching;

    }

    private boolean checkLeProjetNAQuUnSeulParticipant(Projet projet){
        long nbParticipants = participationDAO.findByProjectId(projet.getId())
                .stream()
                .filter(Participation_Projet::isActif)
                .count();

        return nbParticipants == 1;
    }

    public ProjetServiceImpl() {
    }

    public ProjetServiceImpl(ParticipationDAO participationDAO) {
        this.participationDAO = participationDAO;
    }
}
