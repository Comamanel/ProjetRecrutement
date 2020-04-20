package be.ucm.projetrecrutementapi.dal.repositories;

import be.ucm.projetrecrutementapi.api.dto.ProjetFiltreDTO;
import be.ucm.projetrecrutementapi.dal.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjetDAO extends JpaRepository<Projet, Long> {
    @Query(value = "SELECT * "
            + "FROM PROJET p JOIN PARTICIPATION_PROJET pp ON p.ID_PROJET = pp.PROJET_ID WHERE UTILISATEUR_ID  = :id", nativeQuery = true)
    public List<Projet> findByUserId(@Param("id") Long id);

    @Query(value="SELECT p"
            + " FROM Projet p"
            + " WHERE ( :#{#filtres.nom} is null OR p.nom LIKE CONCAT('%', CONCAT(:#{#filtres.nom}, '%')) )"
            + " AND ( :#{#filtres.dateDebutEgalConverti} is null OR p.dateDebut = :#{#filtres.dateDebutEgalConverti} )"
            + " AND ( :#{#filtres.dateDebutAvantConverti} is null OR p.dateDebut <= :#{#filtres.dateDebutAvantConverti} )"
            + " AND ( :#{#filtres.dateDebutApresConverti} is null OR p.dateDebut >= :#{#filtres.dateDebutApresConverti} )"
            + " AND ( :#{#filtres.typeProjet} is null OR p.typeProjet = :#{#filtres.typeProjet} )"
            + " AND ( :#{#filtres.maxParticipantsEgalA} is null OR p.maxParticipants = :#{#filtres.maxParticipantsEgalA} ) "
            + " AND ( :#{#filtres.maxParticipantsInferieurA} is null OR p.maxParticipants <= :#{#filtres.maxParticipantsInferieurA} )"
            + " AND ( :#{#filtres.maxParticipantsSuperieurA} is null OR p.maxParticipants >= :#{#filtres.maxParticipantsSuperieurA} ) "
            + " AND ( :#{#filtres.dateFinEgalConverti} is null OR p.dateFin = :#{#filtres.dateFinEgalConverti} )"
            + " AND ( :#{#filtres.dateFinAvantConverti} is null OR p.dateFin <= :#{#filtres.dateFinAvantConverti} )"
            + " AND ( :#{#filtres.dateFinApresConverti} is null OR p.dateFin >= :#{#filtres.dateFinApresConverti} )"
            + " AND ( :#{#filtres.tpsTravailHebdoEgal} is null OR p.tpsTravailHebdo = :#{#filtres.tpsTravailHebdoEgal} )"
            + " AND ( :#{#filtres.tpsTravailHebdoInferieurA} is null OR p.tpsTravailHebdo <= :#{#filtres.tpsTravailHebdoInferieurA} )"
            + " AND ( :#{#filtres.tpsTravailHebdoSuperieurA} is null OR p.tpsTravailHebdo >= :#{#filtres.tpsTravailHebdoSuperieurA} )"
            + " AND ( :#{#filtres.statut} is null OR p.statut = :#{#filtres.statut} )"
    )
    public List<Projet> getFiltered(@Param("filtres") ProjetFiltreDTO filtres);
}
