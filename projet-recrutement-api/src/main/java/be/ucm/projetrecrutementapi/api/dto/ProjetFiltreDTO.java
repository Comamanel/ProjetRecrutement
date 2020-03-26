package be.ucm.projetrecrutementapi.api.dto;

import be.ucm.projetrecrutementapi.dal.entities.enums.EtatProjet;
import be.ucm.projetrecrutementapi.dal.entities.enums.TypeProjet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ProjetFiltreDTO {
    private String nom;
    private Date dateDebutEgal;
    private Date dateDebutAvant;
    private Date dateDebutApres;
    private TypeProjet typeProjet;
    private Integer maxParticipantsSuperieurA;
    private Integer maxParticipantsInferieurA;
    private Integer maxParticipantsEgalA;
    private Date dateFinEgal;
    private Date dateFinAvant;
    private Date dateFinApres;
    private Integer tpsTravailHebdoEgal;
    private Integer tpsTravailHebdoInferieurA;
    private Integer tpsTravailHebdoSuperieurA;
    private EtatProjet statut;

    private LocalDate dateDebutEgalConverti;
    private LocalDate dateDebutAvantConverti;
    private LocalDate dateDebutApresConverti;
    private LocalDate dateFinEgalConverti;
    private LocalDate dateFinAvantConverti;
    private LocalDate dateFinApresConverti;
}
