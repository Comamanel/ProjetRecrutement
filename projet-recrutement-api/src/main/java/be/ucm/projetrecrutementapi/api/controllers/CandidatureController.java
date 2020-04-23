package be.ucm.projetrecrutementapi.api.controllers;

import be.ucm.projetrecrutementapi.Exceptions.CandidatureNonValideException;
import be.ucm.projetrecrutementapi.api.dto.AfficheCandidatureDTO;
import be.ucm.projetrecrutementapi.api.dto.AfficheNouvelleParticipationDTO;
import be.ucm.projetrecrutementapi.api.dto.CandidatureFormulaireDTO;
import be.ucm.projetrecrutementapi.api.dto.TraitementCandidatureFormulaireDTO;
import be.ucm.projetrecrutementapi.dal.entities.Participation_Projet;
import be.ucm.projetrecrutementapi.services.CandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/candidature")
public class CandidatureController {

    @Autowired
    private CandidatureService candidatureService;

    @GetMapping("projet/{projetId}")
    public ResponseEntity<List<AfficheCandidatureDTO>> getByProjet(@PathVariable Long projetId){


        return ResponseEntity.ok(this.candidatureService.findByProjet(projetId).stream().map(AfficheCandidatureDTO::new).collect(Collectors.toList()));
    }

    @PostMapping("new")
    public ResponseEntity<AfficheCandidatureDTO> createCandidature(@RequestBody @Valid CandidatureFormulaireDTO candidature) throws CandidatureNonValideException {
        try{
            return ResponseEntity.ok(new AfficheCandidatureDTO(this.candidatureService.save(candidature)));
        }
        catch (EntityNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch(CandidatureNonValideException e){
            e.printStackTrace();
            throw new CandidatureNonValideException();
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("acceptation")
    public ResponseEntity<AfficheNouvelleParticipationDTO> acceptationCandidature(@RequestBody TraitementCandidatureFormulaireDTO traitementCandidatureFormulaireDTO){
        Participation_Projet participation_projet = this.candidatureService.validerCandidature(traitementCandidatureFormulaireDTO);

        return ResponseEntity.ok(new AfficheNouvelleParticipationDTO(participation_projet));
    }

    @PostMapping("refus")
    public ResponseEntity<AfficheCandidatureDTO> refusCandidature(@RequestBody TraitementCandidatureFormulaireDTO traitementCandidatureFormulaireDTO){
        return ResponseEntity.ok(new AfficheCandidatureDTO(this.candidatureService.refuserCandidature(traitementCandidatureFormulaireDTO)));
    }

}
