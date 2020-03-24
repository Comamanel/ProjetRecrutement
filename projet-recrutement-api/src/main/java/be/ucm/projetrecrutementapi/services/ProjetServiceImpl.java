package be.ucm.projetrecrutementapi.services;

import be.ucm.projetrecrutementapi.dal.entities.Projet;
import be.ucm.projetrecrutementapi.dal.repositories.ProjetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetServiceImpl implements ProjetService {
    @Autowired
    private ProjetDAO projetDAO;

    @Override
    public Optional<Projet> findById(Long id) {
        return this.projetDAO.findById(id);
    }

/*  @Override
    public List<Projet> findByUserId(Long id) { return this.projetRepository.findByUserId(id); }*/

    @Override
    public List<Projet> findAll() {
        return (List<Projet>)this.projetDAO.findAll();
    }
}
