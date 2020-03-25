package be.ucm.projetrecrutementapi.services;

import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import be.ucm.projetrecrutementapi.dal.repositories.UtilisateurDAO;

import java.util.Optional;

public class UtilisateurServices {

    private UtilisateurDAO utilisateurDAO;

    private Utilisateur testerNouvelUtilisateur(Utilisateur nouvelUtilisateur){

        if (nouvelUtilisateur.getDateDeNaissance() == null
        || nouvelUtilisateur.getEmail() == null
        || nouvelUtilisateur.getMotDePasse() == null
        || nouvelUtilisateur.getPseudo() == null) {
            return null;
        }

        if (!nouvelUtilisateur.getPseudo().matches("^\\S*$")) {
            return null;
        }

        if (utilisateurDAO.findByEmail(nouvelUtilisateur.getEmail()).isPresent()){
            return null;
        }

        return nouvelUtilisateur;

    }

}
