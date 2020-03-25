package be.ucm.projetrecrutementapi.dal.repositories;

import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UtilisateurDAO extends JpaRepository<Utilisateur, Long> {
    @Query(value = "SELECT * FROM UTILISATEUR u WHERE u.email = :email", nativeQuery = true)
    Optional<Utilisateur> findByEmail(String email);

    @Query(value = "SELECT * FROM UTILISATEUR u WHERE u.pseudo = :pseudo", nativeQuery = true)
    Optional<Utilisateur> findByPseudo(String pseudo);
}
