package be.ucm.projetrecrutementapi.services;

import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import be.ucm.projetrecrutementapi.dal.repositories.UtilisateurDAO;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@CrossOrigin
public class UtilisateurServices {

    @Autowired
    private UtilisateurDAO utilisateurDAO;

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

}
