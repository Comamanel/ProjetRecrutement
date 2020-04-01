package be.ucm.projetrecrutementapi.api.controllers;

import be.ucm.projetrecrutementapi.api.dto.AfficheUtilisateurDTO;
import be.ucm.projetrecrutementapi.api.dto.DataUtilisateurDTO;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import be.ucm.projetrecrutementapi.dal.repositories.UtilisateurDAO;
import be.ucm.projetrecrutementapi.services.UtilisateurService;
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

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("")
    public ResponseEntity<List<AfficheUtilisateurDTO>> getAll(){
        List<Utilisateur> utilisateurs = utilisateurDAO.findAll();

        return ResponseEntity.ok(utilisateurs.stream().map(AfficheUtilisateurDTO::new).collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity<AfficheUtilisateurDTO> getOne(@PathVariable Long id){
        AfficheUtilisateurDTO utilisateur = new AfficheUtilisateurDTO(utilisateurDAO.getOne(id));
        return ResponseEntity.ok(utilisateur);
    }

    @PostMapping("/create")
    public ResponseEntity createUser (@RequestBody DataUtilisateurDTO dataUtilisateurDTO){
        Utilisateur nouvelUtilisateur = dataUtilisateurDTO.toEntity();

        if(utilisateurService.testerNouvelUtilisateur(nouvelUtilisateur) != null){
            return ResponseEntity.ok(utilisateurDAO.save(nouvelUtilisateur));
        }

        return null;

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
