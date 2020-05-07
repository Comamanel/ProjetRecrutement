package be.ucm.projetrecrutementapi.api.controllers;

import be.ucm.projetrecrutementapi.api.dto.*;
import be.ucm.projetrecrutementapi.dal.entities.Participation_Projet;
import be.ucm.projetrecrutementapi.dal.entities.Maitrise;
import be.ucm.projetrecrutementapi.dal.entities.Projet;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import be.ucm.projetrecrutementapi.dal.repositories.ParticipationDAO;
import be.ucm.projetrecrutementapi.dal.repositories.ProjetDAO;
import be.ucm.projetrecrutementapi.dal.repositories.UtilisateurDAO;
import be.ucm.projetrecrutementapi.services.ProjetService;
import be.ucm.projetrecrutementapi.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value={ "/api/projet" })
public class ProjetController {

    @Autowired
    private ProjetService projetService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private UtilisateurDAO utilisateurDAO;

    @Autowired
    private ProjetDAO projetDAO;

    @Autowired
    private ParticipationDAO participationDAO;

    @GetMapping(value={"/{id}"})
    public ResponseEntity<AfficheProjetDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(
                new AfficheProjetDTO(
                    this.projetService.findById(id).orElse(new Projet())
        ));
    }

    @GetMapping("/maitrises/{id}")
    public ResponseEntity<List<MaitriseDTO>> getMaitrises(@PathVariable Long id){
        Set<Maitrise> maitrises = projetDAO.findById(id).orElseThrow(EntityNotFoundException::new).getMaitrisesDemandees();
        List<MaitriseDTO> maitriseDTOList = maitrises.stream().map(MaitriseDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(maitriseDTOList);
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

    @PostMapping("/CurrentUserId={id}/create")
    public ResponseEntity createUser (@PathVariable Long id, @RequestBody ProjetDTO dataProjetDTO){
        Projet nouveauprojet = dataProjetDTO.toEntity();
        Utilisateur utilisateurActif = utilisateurDAO.findById(id).orElse(null);

        if(projetService.testerValiditeProjet(utilisateurActif, nouveauprojet) != null){
            Participation_Projet pp = new Participation_Projet();
            pp.setActif(true);
            pp.setUtilisateur(utilisateurActif);
            pp.setProjet(nouveauprojet);
            pp.setProprio(true);
            projetDAO.save(nouveauprojet);
            return ResponseEntity.ok(participationDAO.save(pp));
        }
        return null;
    }

    @PostMapping("/CurrentUserId={userId}/ProjectId={projetId}")
    public ResponseEntity updateUser(@PathVariable Long projetId, @PathVariable Long userId, @RequestBody ProjetDTO projetDTO) {
        Projet projetActif = projetDAO.findById(projetId).orElse(null);
        Projet projetModif = projetDTO.toEntity();
        Utilisateur utilisateurActif = utilisateurDAO.findById(userId).orElse(null);

        projetModif = projetService.modifierInfosProjet(utilisateurActif, projetActif, projetModif);

        return ResponseEntity.ok(projetDAO.save(projetModif));
    }

    @PostMapping("changer-proprietaire")
    public ResponseEntity<ChangementProprietaireFormulaire> changerProprietaire(@RequestBody ChangementProprietaireFormulaire changementProprietaireFormulaire){
        changementProprietaireFormulaire.setResponse(this.projetService.changerProprietaireProjet(changementProprietaireFormulaire));

        return ResponseEntity.ok(changementProprietaireFormulaire);
    }

    @PostMapping("participation/annulation")
    public ResponseEntity<AnnulationParticipationProjetDTO> annulationParticipation(@RequestBody AnnulationParticipationProjetDTO annulationParticipationProjet){
        annulationParticipationProjet = this.projetService.annulationParticipationProjet(annulationParticipationProjet);

        return ResponseEntity.ok(annulationParticipationProjet);
    }

    @PostMapping("/updateProjectId={id}/addSkills")
    public ResponseEntity updateUser(@PathVariable Long id, MaitriseDTO maitriseDTO) {
        Projet projetActif = projetDAO.findById(id).orElse(null);
        Maitrise nouvelleMaitrise = maitriseDTO.toEntity();

        projetActif = projetService.ajouterMaitrise(projetActif, nouvelleMaitrise);

        return ResponseEntity.ok(projetDAO.save(projetActif));
    }

    @PostMapping("/updateProjectId={id}/removeSkillId={idMaitrise}")
    public ResponseEntity updateUser(@PathVariable Long id, @PathVariable Long idMaitrise) {
        Projet projetActif = projetDAO.findById(id).orElseThrow(Error::new);
        Maitrise maitriseASupprimer = projetActif.getMaitrisesDemandees().stream().filter(mu -> mu.getId().equals(idMaitrise)).findFirst().orElse(null);

        projetActif = projetService.retirerMaitrise(projetActif, maitriseASupprimer);

        return ResponseEntity.ok(projetDAO.save(projetActif));

    }

}



