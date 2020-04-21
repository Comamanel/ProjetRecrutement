package be.ucm.projetrecrutementapi.dal.repositories;

import be.ucm.projetrecrutementapi.dal.entities.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidatureDAO extends JpaRepository<Candidature, Long> {

    @Query(value="SELECT * FROM Candidature WHERE projet_id = :projetId", nativeQuery = true)
    public List<Candidature> findByProjet(Long projetId);
}
