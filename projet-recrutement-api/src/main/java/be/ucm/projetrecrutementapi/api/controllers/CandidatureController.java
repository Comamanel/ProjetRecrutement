package be.ucm.projetrecrutementapi.api.controllers;

import be.ucm.projetrecrutementapi.api.dto.CandidatureDemandeFormulaireDTO;
import be.ucm.projetrecrutementapi.dal.entities.Candidature;
import be.ucm.projetrecrutementapi.services.CandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/candidature")
public class CandidatureController {

    @Autowired
    private CandidatureService candidatureService;

    @PostMapping("new")
    public ResponseEntity<Candidature> createCandidature(@RequestBody CandidatureDemandeFormulaireDTO candidature){
        return ResponseEntity.ok(this.candidatureService.save(candidature));
    }
}
