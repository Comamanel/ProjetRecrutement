package be.ucm.projetrecrutementapi.services;

import be.ucm.projetrecrutementapi.api.dto.ProjetFiltreDTO;
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


  @Override
    public List<Projet> findByUserId(Long id) { return this.projetDAO.findByUserId(id); }

    @Override
    public List<Projet> findAll() {
        return this.projetDAO.findAll();
    }

    @Override
    public List<Projet> findAllFiltered(ProjetFiltreDTO filtres) {
        if(filtres.getDateDebutEgal() != null){
            filtres.setDateDebutEgalConverti(filtres.getDateDebutEgal().toLocalDate());
        }

        if(filtres.getDateDebutAvant() != null){
            filtres.setDateDebutAvantConverti(filtres.getDateDebutAvant().toLocalDate());
        }

        if(filtres.getDateDebutApres() != null){
            filtres.setDateDebutApresConverti(filtres.getDateDebutApres().toLocalDate());
        }

        if(filtres.getDateFinEgal() != null){
            filtres.setDateFinEgalConverti(filtres.getDateFinEgal().toLocalDate());
        }

        if(filtres.getDateFinAvant() != null){
            filtres.setDateFinAvantConverti(filtres.getDateFinAvant().toLocalDate());
        }

        if(filtres.getDateFinApres() != null){
            filtres.setDateFinApresConverti(filtres.getDateFinApres().toLocalDate());
        }
        return this.projetDAO.getFiltered(filtres);
    }
}
