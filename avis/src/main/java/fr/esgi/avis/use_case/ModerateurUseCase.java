package fr.esgi.avis.use_case;

import fr.esgi.avis.dto.*;

public interface ModerateurUseCase {

    AvisDtoOut modererAvis(AvisDtoIn avis, ModerateurDtoIn moderateur);

    JeuDtoOut ajouterJeu(JeuDtoIn jeu);

    EditeurDtoOut creerEditeur(EditeurDtoIn editeur);

}
