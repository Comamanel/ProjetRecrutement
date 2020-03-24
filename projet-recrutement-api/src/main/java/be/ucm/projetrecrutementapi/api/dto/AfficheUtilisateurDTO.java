package be.ucm.projetrecrutementapi.api.dto;

import be.ucm.projetrecrutementapi.dal.entities.*;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatProjet;
import be.ucm.projetrecrutementapi.dal.entities.enums.TypeProjet;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class AfficheUtilisateurDTO {
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
    private Group group;
    private List<RoleDTO> roles;
    private List<Participation_Projet_DTO> projetsParticipes;
    private List<AfficheProjetDTO> projetsCrees;
    private List<MaitriseDTO> maitrises;

    public AfficheUtilisateurDTO(){};
    public AfficheUtilisateurDTO(Utilisateur utilisateur){
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
        this.group = utilisateur.getGroup();
        this.roles = utilisateur.getRoles().stream().map(RoleDTO::new).collect(Collectors.toList());
        this.projetsParticipes = utilisateur.getProjetsParticipes().stream().map(Participation_Projet_DTO::new).collect(Collectors.toList());
        this.projetsCrees = utilisateur.getProjetsCrees().stream().map(AfficheProjetDTO::new).collect(Collectors.toList());
        this.maitrises = utilisateur.getMaitrises().stream().map(MaitriseDTO::new).collect(Collectors.toList());
    }
}
