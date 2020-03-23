package be.ucm.projetrecrutementapi.api.dto;

import be.ucm.projetrecrutementapi.dal.entities.Maitrise;
import be.ucm.projetrecrutementapi.dal.entities.Technologie;
import be.ucm.projetrecrutementapi.dal.entities.enums.NiveauMaitrise;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
public class AfficheProjetMaitriseDTO {
    private Long id;
    private NiveauMaitrise niveauMaitrise;
    private AfficheProjetTechnologieDTO technologie;

    public AfficheProjetMaitriseDTO(Maitrise maitrise){
        this.id = maitrise.getId();
        this.niveauMaitrise = maitrise.getNiveauMaitrise();
        this.technologie = new AfficheProjetTechnologieDTO(maitrise.getTechnologie());
    }
}
