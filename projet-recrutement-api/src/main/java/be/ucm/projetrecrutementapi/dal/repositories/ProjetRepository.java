package be.ucm.projetrecrutementapi.dal.repositories;

import be.ucm.projetrecrutementapi.dal.entities.Projet;
import org.springframework.data.repository.CrudRepository;

public interface ProjetRepository extends CrudRepository<Projet, Long> {
}
