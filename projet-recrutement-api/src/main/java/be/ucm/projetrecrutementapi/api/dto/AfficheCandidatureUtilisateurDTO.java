package be.ucm.projetrecrutementapi.api.dto;

import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AfficheCandidatureUtilisateurDTO {
    private Long id;
    private String email;
    private String pseudo;
    private LocalDate dateDeNaissance;
    private String nom;
    private String prenom;
    private String infoSupp;
    private String numTel;
    private String pays;
    private String lienGit;
    private String photoProfil;
    private String cvDoc;
    private List<MaitriseDTO> maitrises;

    public AfficheCandidatureUtilisateurDTO(){
        this.maitrises = new ArrayList<>();
    }

    public AfficheCandidatureUtilisateurDTO(Utilisateur utilisateur) {
        this();
        this.id = utilisateur.getId();
        this.email = utilisateur.getEmail();
        this.pseudo = utilisateur.getPseudo();
        this.dateDeNaissance = utilisateur.getDateDeNaissance();
        this.nom = utilisateur.getNom();
        this.prenom = utilisateur.getPrenom();
        this.infoSupp = utilisateur.getInfoSupp();
        this.numTel = utilisateur.getNumTel();
        this.pays = utilisateur.getPays();
        this.lienGit = utilisateur.getLienGit();
        this.photoProfil = utilisateur.getPhotoProfil();
        this.cvDoc = utilisateur.getCvDoc();

        if(utilisateur.getMaitrises() != null)
            this.maitrises = utilisateur.getMaitrises().stream().map(MaitriseDTO::new).collect(Collectors.toList());
    }
}
