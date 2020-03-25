package be.ucm.projetrecrutementapi.services;

import be.ucm.projetrecrutementapi.api.dto.ProjetFiltreDTO;
import be.ucm.projetrecrutementapi.dal.entities.Projet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ProjetService {
    Optional<Projet> findById(Long id);
    List<Projet> findByUserId(Long id);
    List<Projet> findAll();
    public List<Projet> findAllFiltered(ProjetFiltreDTO filtres);
}
