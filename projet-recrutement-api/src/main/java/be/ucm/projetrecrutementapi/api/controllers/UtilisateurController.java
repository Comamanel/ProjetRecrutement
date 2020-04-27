package be.ucm.projetrecrutementapi.api.controllers;

import be.ucm.projetrecrutementapi.api.dto.AfficheUtilisateurDTO;
import be.ucm.projetrecrutementapi.api.dto.AnnulationParticipationProjetDTO;
import be.ucm.projetrecrutementapi.api.dto.DataUtilisateurDTO;
import be.ucm.projetrecrutementapi.api.dto.MaitriseDTO;
import be.ucm.projetrecrutementapi.dal.entities.Maitrise;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import be.ucm.projetrecrutementapi.dal.repositories.UtilisateurDAO;
import be.ucm.projetrecrutementapi.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
@RequestMapping("/api/utilisateur")
public class UtilisateurController {

    @Autowired
    private UtilisateurDAO utilisateurDAO;

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("")
    public ResponseEntity<List<AfficheUtilisateurDTO>> getAll() {
        List<Utilisateur> utilisateurs = utilisateurDAO.findAll();

        return ResponseEntity.ok(utilisateurs.stream().map(AfficheUtilisateurDTO::new).collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity<AfficheUtilisateurDTO> getOne(@PathVariable Long id){
        AfficheUtilisateurDTO utilisateur = new AfficheUtilisateurDTO(utilisateurDAO.findById(id).orElseThrow(EntityNotFoundException::new));
        return ResponseEntity.ok(utilisateur);
    }

    @PostMapping("/create")
    public ResponseEntity<Utilisateur> createUser(@RequestBody DataUtilisateurDTO dataUtilisateurDTO){
        Utilisateur nouvelUtilisateur = dataUtilisateurDTO.toEntity();

        if (utilisateurService.testerNouvelUtilisateur(nouvelUtilisateur) != null) {
            return ResponseEntity.ok(utilisateurDAO.save(nouvelUtilisateur));
        }

        return null;

    }

    @PostMapping("/updateUserId={id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody AfficheUtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateurActif = utilisateurDAO.findById(id).orElse(null);
        Utilisateur utilisateurModif = utilisateurDTO.toEntity();

        utilisateurModif = utilisateurService.modifierInfosUtilisateur(utilisateurActif, utilisateurModif);

        return ResponseEntity.ok(utilisateurDAO.save(utilisateurModif));

    }

    @PostMapping("/updateUserId={id}/addSkills")
    public ResponseEntity updateUser(@PathVariable Long id, MaitriseDTO maitriseDTO) {
        Utilisateur utilisateurActif = utilisateurDAO.findById(id).orElse(null);
        Maitrise nouvelleMaitrise = maitriseDTO.toEntity();

        utilisateurActif = utilisateurService.ajouterMaitrise(utilisateurActif, nouvelleMaitrise);

        return ResponseEntity.ok(utilisateurDAO.save(utilisateurActif));
    }

    @PostMapping("/updateUserId={id}/removeSkillId={idMaitrise}")
    public ResponseEntity updateUser(@PathVariable Long id, @PathVariable Long idMaitrise) {
        Utilisateur utilisateurActif = utilisateurDAO.findById(id).orElseThrow(Error::new);
        Maitrise maitriseASupprimer = utilisateurActif.getMaitrises().stream().filter(mu -> mu.getId().equals(idMaitrise)).findFirst().orElse(null);

        utilisateurActif = utilisateurService.retirerMaitrise(utilisateurActif, maitriseASupprimer);

        return ResponseEntity.ok(utilisateurDAO.save(utilisateurActif));

    }

}
