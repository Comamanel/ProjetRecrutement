package be.ucm.projetrecrutementapi.services;

import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;

public interface UtilisateurService {

    public Utilisateur testerNouvelUtilisateur(Utilisateur nouvelUtilisateur);

    public Utilisateur modifierInfosUtilisateur(Utilisateur utilisateurActif, Utilisateur utilisateurInfoModif);

    public boolean checkUtilisateurNEstPasProprietaireDePlusDeDeuxProjets(Utilisateur utilisateur);

}
