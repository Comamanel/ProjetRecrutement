package be.ucm.projetrecrutementapi.api.dto;

import be.ucm.projetrecrutementapi.dal.entities.Group;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class DataUtilisateurDTO {
    private Long id;
    private String email;
    private String pseudo;
    private LocalDate dateDeNaissance;
    private String motDePasse;

    public Utilisateur toEntity(){
        Utilisateur nouvelUtilisateur = new Utilisateur();
        nouvelUtilisateur.setPseudo(this.pseudo);
        nouvelUtilisateur.setEmail(this.email);
        nouvelUtilisateur.setDateDeNaissance(this.dateDeNaissance);
        nouvelUtilisateur.setMotDePasse(this.motDePasse);
        return nouvelUtilisateur;
    }

    public DataUtilisateurDTO(){};
    public DataUtilisateurDTO(Utilisateur utilisateur){
        this.id = utilisateur.getId();
        this.email = utilisateur.getEmail();
        this.pseudo = utilisateur.getPseudo();
        this.dateDeNaissance = utilisateur.getDateDeNaissance();
        this.motDePasse = utilisateur.getMotDePasse();
    }
}
