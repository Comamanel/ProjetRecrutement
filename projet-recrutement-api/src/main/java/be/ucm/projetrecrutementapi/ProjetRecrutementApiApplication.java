package be.ucm.projetrecrutementapi;

import be.ucm.projetrecrutementapi.dal.entities.Projet;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatProjet;
import be.ucm.projetrecrutementapi.dal.entities.enums.TypeProjet;
import be.ucm.projetrecrutementapi.dal.repositories.GroupDAO;
import be.ucm.projetrecrutementapi.dal.repositories.ParticipationDAO;
import be.ucm.projetrecrutementapi.dal.repositories.ProjetDAO;
import be.ucm.projetrecrutementapi.services.UtilisateurServices;
import net.minidev.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import be.ucm.projetrecrutementapi.dal.entities.Group;
import be.ucm.projetrecrutementapi.dal.entities.enums.GroupEnum;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import be.ucm.projetrecrutementapi.dal.repositories.UtilisateurDAO;
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
	private UtilisateurDAO utilisateurDAO;

	@Autowired
	private ParticipationDAO participationDAO;

	@Autowired
	private ProjetDAO projetDAO;

	@Autowired
	private GroupDAO groupDAO;

	@EventListener(ApplicationReadyEvent.class)
	public void ajouterUtilisateur(){
		Group members = new Group();
		members.setNom(GroupEnum.MEMBRE);

		members = this.groupDAO.save(members);

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

		utilisateurDAO.save(user);

		Utilisateur user2 = new Utilisateur();
		user2.setPseudo("MaurChev");
		user2.setNom("Le Chevalier");
		user2.setPrenom("Maurice");
		user2.setEmail("maurice@chevalier.com");
		user2.setDateDeNaissance(LocalDate.of(1256, 4, 5));
		user2.setMotDePasse("ALAssaut");
		user2.setInfoSupp("Le chevalier le plus courageux du XXIeme siecle [T]");
		user2.setNumTel("000/000000");
		user2.setPays("Belgiqve");
		user2.setLienGit("maurice-chevalier.git");
		user2.setPhotoProfil("mauricelechevalier.png");
		user2.setCvDoc("maurice.pdf");
		user2.setGroup(members);

		utilisateurDAO.save(user2);


	}

	@EventListener(ApplicationReadyEvent.class)
	private void ajouterProjet(){
		Projet projet = new Projet();
		projet.setNom("TestProjet");
		projet.setDescription("Ceci est un test de projet pour voir si l'affichage fonctionne bien");
		projet.setDateDebut(LocalDate.of(2020, 1, 23));
		projet.setTypeProjet(TypeProjet.SER);
		projet.setMaxParticipants(10);
		projet.setDateFin(LocalDate.of(2080, 1, 10));
		projet.setTpsTravailHebdo(5);
		projet.setStatut(EtatProjet.ACT);

		Projet projet2 = new Projet();
		projet2.setNom("Application Sushi");
		projet2.setDescription("Une application qui permet de commander de délicieux sushis");
		projet2.setDateDebut(LocalDate.of(2019, 11, 18));
		projet2.setTypeProjet(TypeProjet.APP);
		projet2.setMaxParticipants(8);
		projet2.setDateFin(LocalDate.of(2020, 12, 1));
		projet2.setTpsTravailHebdo(4);
		projet2.setStatut(EtatProjet.ACT);

		this.projetDAO.save(projet);
		this.projetDAO.save(projet2);
	}

	@EventListener(ApplicationReadyEvent.class)
	private void ajouterProjet2(){
		Projet projet = new Projet();
		projet.setNom("TestProjet2");
		projet.setDescription("Ceci est un deuxième test de projet pour voir si l'affichage de plusieurs projets fonctionne bien");
		projet.setDateDebut(LocalDate.of(2020, 3, 24));
		projet.setTypeProjet(TypeProjet.SER);
		projet.setMaxParticipants(1);
		projet.setDateFin(LocalDate.of(2100, 1, 11));
		projet.setTpsTravailHebdo(10);
		projet.setStatut(EtatProjet.ARC);

		this.projetDAO.save(projet);
	}
}
