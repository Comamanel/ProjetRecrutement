package be.ucm.projetrecrutementapi.api.dto;

import be.ucm.projetrecrutementapi.dal.entities.Technologie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ProjetTechnologieDTO {
    private Long id;
    private String nom;
    private String createur;

    public Technologie toEntity(){
        Technologie technologie = new Technologie();
        technologie.setNom(this.nom);
        technologie.setCreateur(this.createur);
        return technologie;
    }

    public ProjetTechnologieDTO(Technologie technologie){
        this.id = technologie.getId();
        this.nom = technologie.getNom();
        this.createur= technologie.getCreateur();
    }
}
