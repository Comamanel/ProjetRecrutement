package be.ucm.projetrecrutementapi.api.controllers;

import be.ucm.projetrecrutementapi.api.dto.AfficheProjetDTO;
import be.ucm.projetrecrutementapi.dal.entities.Projet;
import be.ucm.projetrecrutementapi.services.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value={ "/api/projet" })
public class ProjetController {

    @Autowired
    private ProjetService projetService;


    @GetMapping(value={"/{id}"})
    public ResponseEntity<AfficheProjetDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(
                new AfficheProjetDTO(
                    this.projetService.findById(id).orElse(new Projet())
        ));
    }

}
