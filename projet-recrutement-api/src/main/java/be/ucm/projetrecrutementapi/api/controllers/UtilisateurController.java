package be.ucm.projetrecrutementapi.api.controllers;

import be.ucm.projetrecrutementapi.api.dto.AfficheUtilisateurDTO;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import be.ucm.projetrecrutementapi.dal.repositories.UtilisateurDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
@RequestMapping("/api/utilisateur")
public class UtilisateurController {

    @Autowired
    private UtilisateurDAO utilisateurDAO;

    @GetMapping("")
    public ResponseEntity<List<AfficheUtilisateurDTO>> getAll(){
        List<Utilisateur> utilisateurs = utilisateurDAO.findAll();

        return ResponseEntity.ok(utilisateurs.stream().map(AfficheUtilisateurDTO::new).collect(Collectors.toList()));
    }

//    Pour tester si il renvoie bien quelque chose (pas indispensable)
//    @GetMapping("/email={email}")
//    public ResponseEntity<Utilisateur> getByEmail(@PathVariable String email){
//        Utilisateur utilisateur = utilisateurDAO.findByEmail(email).orElse(null);
//
//        return ResponseEntity.ok(utilisateur);
//    }

//    @GetMapping("/pseudo={pseudo}")
//    public ResponseEntity<Utilisateur> getByPseudo(@PathVariable String pseudo){
//        Utilisateur utilisateur = utilisateurDAO.findByPseudo(pseudo).orElse(null);
//
//        return ResponseEntity.ok(utilisateur);
//    }

}
