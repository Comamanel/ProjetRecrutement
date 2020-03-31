package be.ucm.projetrecrutementapi.dal.repositories;

import be.ucm.projetrecrutementapi.dal.entities.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatureDAO extends JpaRepository<Candidature, Long> {
}
