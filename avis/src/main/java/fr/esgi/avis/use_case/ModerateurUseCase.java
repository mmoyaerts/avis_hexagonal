package fr.esgi.avis.use_case;

import fr.esgi.avis.business.Avis;
import fr.esgi.avis.business.Editeur;
import fr.esgi.avis.business.Jeu;
import fr.esgi.avis.business.Moderateur;
import org.springframework.boot.Banner;

import java.util.List;

public interface ModerateurUseCase {

    Avis modererAvis(Avis avis, Moderateur moderateur);

    Jeu ajouterJeu(Jeu jeu);

    Moderateur creerModerateur(Moderateur moderateur);

    Moderateur creerEditeur(Editeur editeur);

}
