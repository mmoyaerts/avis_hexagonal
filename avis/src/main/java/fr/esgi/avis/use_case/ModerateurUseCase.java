package fr.esgi.avis.use_case;

import fr.esgi.avis.DTO.*;
import fr.esgi.avis.business.Avis;
import fr.esgi.avis.business.Editeur;
import fr.esgi.avis.business.Jeu;
import fr.esgi.avis.business.Moderateur;
import org.springframework.boot.Banner;

import java.util.List;

public interface ModerateurUseCase {

    AvisDtoOut modererAvis(AvisDtoIn avis, ModerateurDtoIn moderateur);

    JeuDtoOut ajouterJeu(JeuDtoIn jeu);

    EditeurDtoOut creerEditeur(EditeurDtoIn editeur);

}
