package be.ucm.projetrecrutementapi.dal.repositories;

import be.ucm.projetrecrutementapi.dal.entities.Technologie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologieDAO extends JpaRepository<Technologie, Long> {
}
