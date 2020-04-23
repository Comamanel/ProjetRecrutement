package be.ucm.projetrecrutementapi.services;

import be.ucm.projetrecrutementapi.dal.entities.Maitrise;
import be.ucm.projetrecrutementapi.dal.entities.Participation_Projet;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatProjet;
import be.ucm.projetrecrutementapi.dal.repositories.MaitriseDAO;
import be.ucm.projetrecrutementapi.dal.repositories.UtilisateurDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurDAO utilisateurDAO;

    @Autowired
    private MaitriseDAO maitriseDAO;

    @Override
    public Utilisateur testerNouvelUtilisateur(Utilisateur nouvelUtilisateur){

        if (nouvelUtilisateur.getEmail() == null
        || nouvelUtilisateur.getMotDePasse() == null
        || nouvelUtilisateur.getPseudo() == null
        || nouvelUtilisateur.getDateDeNaissance() == null
        ){
            return null;
        }

        if (!(nouvelUtilisateur.getPseudo().matches("^[^<>%$#=*/\\\\]*$"))) {
            return null;
        }

        if (!(nouvelUtilisateur.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))){
            return null;
        }

        if (nouvelUtilisateur.getDateDeNaissance().compareTo(LocalDate.now()) > 0) {
            return null;
        }

        if (!nouvelUtilisateur.getMotDePasse().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&éè]{8,}$")){
            return null;
        }

        if (utilisateurDAO.findByPseudo(nouvelUtilisateur.getPseudo()).orElse(null) != null){
            return null;
        }

        if (utilisateurDAO.findByEmail(nouvelUtilisateur.getEmail()).orElse(null) != null){
            return null;
        }

        return nouvelUtilisateur;

    }

    @Override
    public Utilisateur modifierInfosUtilisateur(Utilisateur utilisateurActif, Utilisateur utilisateurInfoModif){

        String newPseudo = utilisateurInfoModif.getPseudo();
        if ((newPseudo != null && !newPseudo.trim().equals(""))
        && (utilisateurDAO.findByPseudo(newPseudo).orElse(null) == null)
        && (newPseudo.matches("^[^<>%$#=*/\\\\]*$"))
        ){
            utilisateurActif.setPseudo(newPseudo);
        }

        String newPrenom = utilisateurInfoModif.getPrenom();
        if (newPrenom != null && !newPrenom.trim().equals("")
        ){
            utilisateurActif.setPrenom(newPrenom);
        }

        String newNom = utilisateurInfoModif.getNom();
        if (newNom != null && !newNom.trim().equals("")
        ){
            utilisateurActif.setNom(newNom);
        }

        String newPays = utilisateurInfoModif.getPays();
        if (newPays != null && !newPays.trim().equals("")
        ){
            utilisateurActif.setPays(newPays);
        }

        String newMail = utilisateurInfoModif.getEmail();
        if ((newMail != null && !newMail.trim().equals(""))
        && (utilisateurDAO.findByEmail(newMail).orElse(null) == null
        && (newMail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")))
        ){
            utilisateurActif.setEmail(newMail);
        }

        String newNumTel = utilisateurInfoModif.getNumTel();
        if (newNumTel != null && !newNumTel.trim().equals("")
        ){
            utilisateurActif.setNumTel(newNumTel);
        }

        String newProfilPhot = utilisateurInfoModif.getPhotoProfil();
        if (newProfilPhot != null && !newProfilPhot.trim().equals("")
        ){
            utilisateurActif.setPhotoProfil(newProfilPhot);
        }

        String newInfoSupp = utilisateurInfoModif.getInfoSupp();
        if (newInfoSupp != null && !newInfoSupp.trim().equals("")
        ){
            utilisateurActif.setInfoSupp(newInfoSupp);
        }

        String newLienGit = utilisateurInfoModif.getLienGit();
        if (newLienGit != null && !newLienGit.trim().equals("")
        ){
            utilisateurActif.setLienGit(newLienGit);
        }

        String newCvDoc = utilisateurInfoModif.getCvDoc();
        if (newCvDoc != null && !newCvDoc.trim().equals("")
        ){
            utilisateurActif.setCvDoc(newCvDoc);
        }

        return utilisateurActif;

    }

    @Override
    public boolean checkUtilisateurNEstPasProprietaireDePlusDeDeuxProjets(Utilisateur utilisateur) {
        long nbProjetsProprio = utilisateur
                .getProjetsParticipes()
                .stream()
                .filter(Participation_Projet::isProprio)
                .filter(pp -> pp.getProjet().getStatut().equals(EtatProjet.ACT))
                .count();
        return nbProjetsProprio < 3;
    }

    @Override
    public Utilisateur ajouterMaitrise(Utilisateur utilisateurCourant, Maitrise nouvelleMaitrise){

        Maitrise presenceMaitrise = utilisateurCourant.getMaitrises().stream().filter(mu -> mu.getTechnologie().equals(nouvelleMaitrise.getTechnologie())).findFirst().orElse(null);

        if(presenceMaitrise != null){
            if(nouvelleMaitrise.getNiveauMaitrise() != presenceMaitrise.getNiveauMaitrise()){
                presenceMaitrise.setNiveauMaitrise(nouvelleMaitrise.getNiveauMaitrise());
            }
        } else {
            utilisateurCourant.getMaitrises().add(nouvelleMaitrise);
        }

        return utilisateurCourant;

    }

    @Override
    public Utilisateur retirerMaitrise(Utilisateur utilisateurCourant, Maitrise maitriseASupprimer){

        utilisateurCourant.getMaitrises().remove(maitriseASupprimer);
        return utilisateurCourant;

    }

}
