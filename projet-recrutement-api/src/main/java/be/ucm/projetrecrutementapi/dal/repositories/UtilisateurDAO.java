package be.ucm.projetrecrutementapi.dal.repositories;

import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurDAO extends JpaRepository<Utilisateur, Long> {
}
