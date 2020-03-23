package be.ucm.projetrecrutementapi.api.dto;

import be.ucm.projetrecrutementapi.dal.entities.Maitrise;
import be.ucm.projetrecrutementapi.dal.entities.Technologie;
import be.ucm.projetrecrutementapi.dal.entities.enums.NiveauMaitrise;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaitriseDTO {
    private Long id;
    private NiveauMaitrise niveauMaitrise;
    private Technologie technologie;

    public MaitriseDTO(){};
    public MaitriseDTO(Maitrise maitrise){
        this.id = maitrise.getId();
        this.niveauMaitrise = maitrise.getNiveauMaitrise();
        this.technologie = maitrise.getTechnologie();
    }
}
