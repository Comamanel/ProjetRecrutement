package be.ucm.projetrecrutementapi;

import be.ucm.projetrecrutementapi.dal.entities.Participation_Projet;
import be.ucm.projetrecrutementapi.dal.entities.Projet;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatProjet;
import be.ucm.projetrecrutementapi.dal.entities.enums.TypeProjet;
import be.ucm.projetrecrutementapi.dal.repositories.ParticipationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import be.ucm.projetrecrutementapi.dal.entities.Group;
import be.ucm.projetrecrutementapi.dal.entities.enums.GroupEnum;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import be.ucm.projetrecrutementapi.dal.repositories.UtilisateurDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.lang.reflect.Type;
import java.time.LocalDate;

@SpringBootApplication
public class ProjetRecrutementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetRecrutementApiApplication.class, args);
	}

	@Autowired
	private UtilisateurDAO utilisateurDAO;

	@Autowired
	private ParticipationDAO participationDAO;

	@Autowired
	private be.ucm.projetrecrutementapi.dal.repositories.projetRepository projetRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void ajouterUtilisateur(){
		Group members = new Group();
		members.setNom(GroupEnum.MEMBRE);

		Utilisateur user = new Utilisateur();
		user.setPseudo("SpaceBichon");
		user.setNom("Le Moustachon");
		user.setPrenom("Hector");
		user.setEmail("a@a.com");
		user.setDateDeNaissance(LocalDate.of(2019, 10, 14));
		user.setMotDePasse("Réglisse");
		user.setInfoSupp("La preuve ultime que les humains ne sont pas les seuls à pouvoir coder !");
		user.setNumTel("000/000000");
		user.setPays("Belle Jique");
		user.setLienGit("hector.git");
		user.setPhotoProfil("hector.png");
		user.setCvDoc("hector.pdf");
		user.setGroup(members);

		this.utilisateurDAO.save(user);

		Projet projet = new Projet();
		projet.setName("TestProjet");
		projet.setDescription("Ceci est un test de projet pour voir si l'affichage fonctionne bien");
		projet.setDateDebut(LocalDate.of(2020, 3, 23));
		projet.setTypeProjet(TypeProjet.SER);
		projet.setMaxParticipants(10);
		projet.setDateFin(LocalDate.of(2100, 1, 10));
		projet.setTpsTravailHebdo(5);
		projet.setStatut(EtatProjet.ACT);

		Projet projet2 = new Projet();
		projet2.setName("Application Sushi");
		projet2.setDescription("Une application qui permet de commander de délicieux sushis");
		projet2.setDateDebut(LocalDate.of(2019, 11, 18));
		projet2.setTypeProjet(TypeProjet.APP);
		projet2.setMaxParticipants(8);
		projet2.setDateFin(LocalDate.of(2020, 12, 1));
		projet2.setTpsTravailHebdo(4);
		projet2.setStatut(EtatProjet.ACT);

		this.projetRepository.save(projet);
		this.projetRepository.save(projet2);

		Participation_Projet pprojet = new Participation_Projet();
		pprojet.setActif(true);
		pprojet.setProjet(projet);
		pprojet.setProprio(false);
		pprojet.setUtilisateur(user);

		Participation_Projet pprojet2 = new Participation_Projet();
		pprojet2.setActif(true);
		pprojet2.setProjet(projet2);
		pprojet2.setProprio(false);
		pprojet2.setUtilisateur(user);

		this.participationDAO.save(pprojet);
		this.participationDAO.save(pprojet2);

	}

}
