package be.ucm.projetrecrutementapi;

import be.ucm.projetrecrutementapi.dal.entities.Group;
import be.ucm.projetrecrutementapi.dal.entities.enums.GroupEnum;
import be.ucm.projetrecrutementapi.dal.entities.Utilisateur;
import be.ucm.projetrecrutementapi.dal.repositories.UtilisateurDAO;
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
	private UtilisateurDAO utilisateurDAO;

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

		utilisateurDAO.save(user);
	}

}
