package be.ucm.projetrecrutementapi.api.controllers;

import be.ucm.projetrecrutementapi.api.dto.AfficheProjetDTO;
import be.ucm.projetrecrutementapi.dal.entities.Projet;
import be.ucm.projetrecrutementapi.dal.repositories.ProjetDAO;
import be.ucm.projetrecrutementapi.services.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value={ "/api/projet" })
public class ProjetController {

    @Autowired
    private ProjetService projetService;

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

    @GetMapping({"/", "/list"})
    public ResponseEntity<List<AfficheProjetDTO>> getAll(){
        return ResponseEntity.ok(
                this.projetService.findAll().stream().map(AfficheProjetDTO::new).collect(Collectors.toList())
        );
    }

}
