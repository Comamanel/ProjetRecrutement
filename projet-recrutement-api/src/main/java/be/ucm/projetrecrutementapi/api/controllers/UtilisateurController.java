package be.ucm.projetrecrutementapi.api.controllers;

import be.ucm.projetrecrutementapi.api.dto.UtilisateurDTO;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import be.ucm.projetrecrutementapi.dal.repositories.UtilisateurDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
@RequestMapping("/api/utilisateur")
public class UtilisateurController {

    private final UtilisateurDAO utilisateurDAO;
    public UtilisateurController(UtilisateurDAO utilisateurDAO) { this.utilisateurDAO = utilisateurDAO;}

    @GetMapping("")
    public ResponseEntity<List<UtilisateurDTO>> getAll(){
        List<Utilisateur> utilisateurs = utilisateurDAO.findAll();

        return ResponseEntity.ok(utilisateurs.stream().map(UtilisateurDTO::new).collect(Collectors.toList()));
    }

}
