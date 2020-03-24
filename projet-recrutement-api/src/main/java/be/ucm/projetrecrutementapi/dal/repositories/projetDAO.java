package be.ucm.projetrecrutementapi.dal.repositories;

import be.ucm.projetrecrutementapi.dal.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface projetDAO extends JpaRepository<Projet, Long> {
    @Query(value = "SELECT * "
            + "FROM PROJET p JOIN PARTICIPATION_PROJET pp ON p.ID_PROJET = pp.PROJET_ID_PROJET WHERE UTILISATEUR_ID  = :id", nativeQuery = true)
    public List<Projet> findByUserId(@Param("id") Long id);
}
