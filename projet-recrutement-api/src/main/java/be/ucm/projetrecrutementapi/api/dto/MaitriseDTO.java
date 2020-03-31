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
    private TechnologieDTO technologie;

    public Maitrise toEntity(){
        Maitrise maitrise = new Maitrise();
        maitrise.setNiveauMaitrise(this.niveauMaitrise);
        maitrise.setTechnologie(this.technologie.toEntity());
        return maitrise;
    }

    public MaitriseDTO(Maitrise maitrise){
        this.id = maitrise.getId();
        this.niveauMaitrise = maitrise.getNiveauMaitrise();
        this.technologie = new TechnologieDTO(maitrise.getTechnologie());
    }
}
