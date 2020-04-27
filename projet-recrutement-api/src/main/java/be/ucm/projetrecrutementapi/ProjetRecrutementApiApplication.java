package be.ucm.projetrecrutementapi;

import be.ucm.projetrecrutementapi.api.dto.MaitriseDTO;
import be.ucm.projetrecrutementapi.dal.entities.*;
import be.ucm.projetrecrutementapi.dal.entities.enums.EtatProjet;
import be.ucm.projetrecrutementapi.dal.entities.enums.NiveauMaitrise;
import be.ucm.projetrecrutementapi.dal.entities.enums.TypeProjet;
import be.ucm.projetrecrutementapi.dal.repositories.*;
import be.ucm.projetrecrutementapi.dal.repositories.ParticipationDAO;
import be.ucm.projetrecrutementapi.dal.repositories.ProjetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import be.ucm.projetrecrutementapi.dal.entities.enums.GroupEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;

@SpringBootApplication
public class ProjetRecrutementApiApplication {

	@Autowired
	private UtilisateurDAO utilisateurDAO;

	@Autowired
	private ParticipationDAO participationDAO;

	@Autowired
	private ProjetDAO projetDAO;

	@Autowired
	private TechnologieDAO technologieDAO;

	@Autowired
	private GroupDAO groupDAO;

	@Autowired
	private MaitriseDAO maitriseDAO;

	public static void main(String[] args) {
		SpringApplication.run(ProjetRecrutementApiApplication.class, args);
	}


	@EventListener(ApplicationReadyEvent.class)
	private void ajouts(){
		this.ajouterTechnologies();
		this.ajouterMaitrises();
		this.ajouterUtilisateur();
		this.ajouterProjet();
		this.ajouterProjet2();
		this.ajouterParticipation();
	}

	private void ajouterTechnologies(){
		Technologie technologie1 = new Technologie();
		technologie1.setCreateur("Sun Microsystem");
		technologie1.setNom("Java");
		this.technologieDAO.save(technologie1);

		Technologie technologie2 = new Technologie();
		technologie2.setCreateur("Google et des randoms apparemment");
		technologie2.setNom("Angular");
		this.technologieDAO.save(technologie2);

		Technologie technologie3 = new Technologie();
		technologie3.setCreateur("Apache");
		technologie3.setNom("Struts2");
		this.technologieDAO.save(technologie3);

		Technologie technologie4 = new Technologie();
		technologie4.setCreateur("IBM");
		technologie4.setNom("DB2");
		this.technologieDAO.save(technologie4);
	}

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
		user2.getMaitrises().add(maitriseDAO.findById(5L).orElse(null));
		utilisateurDAO.save(user2);


	}

	private void ajouterMaitrises(){
		Maitrise m1 = new Maitrise();
		m1.setNiveauMaitrise(NiveauMaitrise.AVA);
		Technologie techno1 = technologieDAO.findById(1L).orElse(null);
		m1.setTechnologie(techno1);

		Maitrise m2 = new Maitrise();
		m2.setNiveauMaitrise(NiveauMaitrise.DEB);
		Technologie techno2 = technologieDAO.findById(2L).orElse(null);
		m2.setTechnologie(techno2);

		Maitrise m3 = new Maitrise();
		m3.setNiveauMaitrise(NiveauMaitrise.INT);
		Technologie techno3 = technologieDAO.findById(3L).orElse(null);
		m3.setTechnologie(techno3);

		Maitrise m4 = new Maitrise();
		m4.setNiveauMaitrise(NiveauMaitrise.AVA);
		Technologie techno4 = technologieDAO.findById(1L).orElse(null);
		m4.setTechnologie(techno4);

		Maitrise m5 = new Maitrise();
		m5.setNiveauMaitrise(NiveauMaitrise.INT);
		Technologie techno5 = technologieDAO.findById(2L).orElse(null);
		m5.setTechnologie(techno5);

		maitriseDAO.save(m1);
		maitriseDAO.save(m2);
		maitriseDAO.save(m3);
		maitriseDAO.save(m4);
		maitriseDAO.save(m5);
	}

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
		projet.getMaitrisesDemandees().add(maitriseDAO.findById(1L).orElse(null));
		projet.getMaitrisesDemandees().add(maitriseDAO.findById(2L).orElse(null));
		projet.getMaitrisesDemandees().add(maitriseDAO.findById(3L).orElse(null));

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

	private void ajouterParticipation(){
		Participation_Projet participation = new Participation_Projet();
		participation.setActif(true);
		participation.setProprio(true);
		participation.setUtilisateur(this.utilisateurDAO.findById(1L).orElse(new Utilisateur()));
		participation.setProjet(this.projetDAO.findById(1L).orElse(new Projet()));


		Participation_Projet participation2 = new Participation_Projet();
		participation2.setActif(true);
		participation2.setProprio(false);
		participation2.setUtilisateur(this.utilisateurDAO.findById(2L).orElse(new Utilisateur()));
		participation2.setProjet(this.projetDAO.findById(1L).orElse(new Projet()));

		this.participationDAO.save(participation);
		this.participationDAO.save(participation2);
	}
}
