package be.ucm.projetrecrutementapi.dal.repositories;

import be.ucm.projetrecrutementapi.dal.entities.Maitrise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaitriseDAO extends JpaRepository<Maitrise, Long> {
}
