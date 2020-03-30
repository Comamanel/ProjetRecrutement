package be.ucm.projetrecrutementapi.api.controllers;

import be.ucm.projetrecrutementapi.api.dto.AfficheCandidatureDTO;
import be.ucm.projetrecrutementapi.api.dto.CandidatureFormulaireDTO;
import be.ucm.projetrecrutementapi.services.CandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/candidature")
public class CandidatureController {

    @Autowired
    private CandidatureService candidatureService;

    @PostMapping("new")
    public ResponseEntity<AfficheCandidatureDTO> createCandidature(@RequestBody @Valid CandidatureFormulaireDTO candidature){
        return ResponseEntity.ok(new AfficheCandidatureDTO(this.candidatureService.save(candidature)));
    }
}
