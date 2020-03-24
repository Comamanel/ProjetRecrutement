package be.ucm.projetrecrutementapi.api.dto;

import be.ucm.projetrecrutementapi.dal.entities.Maitrise;
import be.ucm.projetrecrutementapi.dal.entities.enums.NiveauMaitrise;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MaitriseDTO {
    private Long id;
    private NiveauMaitrise niveauMaitrise;
    private ProjetTechnologieDTO technologie;

    public MaitriseDTO(Maitrise maitrise){
        this.id = maitrise.getId();
        this.niveauMaitrise = maitrise.getNiveauMaitrise();
        this.technologie = new ProjetTechnologieDTO(maitrise.getTechnologie());
    }
}
