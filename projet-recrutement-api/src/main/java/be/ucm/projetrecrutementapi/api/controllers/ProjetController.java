package be.ucm.projetrecrutementapi.api.controllers;

import be.ucm.projetrecrutementapi.api.dto.*;
import be.ucm.projetrecrutementapi.dal.entities.Projet;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import be.ucm.projetrecrutementapi.dal.repositories.ProjetDAO;
import be.ucm.projetrecrutementapi.dal.repositories.UtilisateurDAO;
import be.ucm.projetrecrutementapi.services.ProjetService;
import be.ucm.projetrecrutementapi.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.rmi.CORBA.Util;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value={ "/api/projet" })
public class ProjetController {

    @Autowired
    private ProjetService projetService;

    @Autowired
    private UtilisateurDAO utilisateurDAO;

    @Autowired
    private ProjetDAO projetDAO;

    @GetMapping(value={"/{id}"})
    public ResponseEntity<AfficheProjetDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(
                new AfficheProjetDTO(
                    this.projetService.findById(id).orElse(new Projet())
        ));
    }

    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<List<AfficheProjetDTO>> getByUser(@PathVariable Long id){
        List<Projet> projets = projetDAO.findByUserId(id);

        return ResponseEntity.ok(projets.stream().map(AfficheProjetDTO::new).collect(Collectors.toList()));
    }

    @GetMapping({"/"})
    public ResponseEntity<List<AfficheProjetDTO>> getAll(ProjetFiltreDTO filters){
        List<Projet> projetsFiltres = this.projetService.findAllFiltered(filters);
        return ResponseEntity.ok(projetsFiltres.stream().map(AfficheProjetDTO::new).collect(Collectors.toList()));
    }

    @PostMapping("/createByUser{id}")
    public ResponseEntity createUser (@PathVariable Long id, @RequestBody ProjetDTO dataProjetDTO){
        Projet nouveauprojet = dataProjetDTO.toEntity();
        Utilisateur utilisateurActif = utilisateurDAO.findById(id).orElse(null);

        if(projetService.testerValiditeProjet(utilisateurActif, nouveauprojet) != null){
            return ResponseEntity.ok(projetDAO.save(nouveauprojet));
        }

        return null;

    }

}


