package be.ucm.projetrecrutementapi.api.dto;

import be.ucm.projetrecrutementapi.dal.entities.enums.EtatProjet;
import be.ucm.projetrecrutementapi.dal.entities.enums.TypeProjet;

import java.time.LocalDate;

public class ProjetFiltreDTO {
    private String nom;
    private LocalDate dateDebutEgal;
    private LocalDate dateDebutAvant;
    private LocalDate dateDebutApres;
    private TypeProjet typeProjet;
    private Integer maxParticipantsSuperieurA;
    private Integer maxParticipantsInferieurA;
    private Integer maxParticipantsEgalA;
    private LocalDate dateFinEgal;
    private LocalDate dateFinAvant;
    private LocalDate dateFinApres;
    private Integer tpsTravailHebdoEgal;
    private Integer tpsTravailHebdoInferieurA;
    private Integer tpsTravailHebdoSuperieurA;
    private EtatProjet statut;
}
