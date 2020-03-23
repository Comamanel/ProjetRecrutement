package be.ucm.projetrecrutementapi;

import be.ucm.projetrecrutementapi.dal.entities.Projet;
import be.ucm.projetrecrutementapi.dal.entities.Technologie;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatProjet;
import be.ucm.projetrecrutementapi.dal.entities.enums.TypeProjet;
import be.ucm.projetrecrutementapi.dal.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;

@SpringBootApplication
public class ProjetRecrutementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetRecrutementApiApplication.class, args);
	}

	@Autowired
	private ProjetRepository projetRepository;

	@EventListener(ApplicationReadyEvent.class)
	private void ajouterProjet(){
		Projet projet = new Projet();
		projet.setName("TestProjet");
		projet.setDescription("Ceci est un test de projet pour voir si l'affichage fonctionne bien");
		projet.setDateDebut(LocalDate.of(2020, 3, 23));
		projet.setTypeProjet(TypeProjet.SER);
		projet.setMaxParticipants(10);
		projet.setDateFin(LocalDate.of(2100, 1, 10));
		projet.setTpsTravailHebdo(5);
		projet.setStatut(EtatProjet.ACT);

		this.projetRepository.save(projet);
	}
}
