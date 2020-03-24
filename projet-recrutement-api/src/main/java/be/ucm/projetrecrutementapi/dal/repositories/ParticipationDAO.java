package be.ucm.projetrecrutementapi.dal.repositories;

import be.ucm.projetrecrutementapi.dal.entities.Participation_Projet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationDAO extends JpaRepository<Participation_Projet, Long> {
}
