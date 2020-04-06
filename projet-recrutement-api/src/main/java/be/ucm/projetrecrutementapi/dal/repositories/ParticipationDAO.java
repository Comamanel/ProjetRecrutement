package be.ucm.projetrecrutementapi.dal.repositories;

import be.ucm.projetrecrutementapi.dal.entities.Participation_Projet;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ParticipationDAO extends JpaRepository<Participation_Projet, Long> {
    @Query(value = "SELECT * FROM PARTICIPATION_PROJET pp WHERE pp.utilisateur_id = :utilisateurId AND pp.projet_id_projet = :projetId", nativeQuery = true)
    Optional<Participation_Projet> findByUserAndProject(Long utilisateurId, Long projetId);
}
