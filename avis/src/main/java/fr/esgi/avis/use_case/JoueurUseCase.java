package fr.esgi.avis.use_case;

import fr.esgi.avis.dto.AvisDtoIn;
import fr.esgi.avis.dto.AvisDtoOut;
import fr.esgi.avis.dto.JoueurDtoIn;
import fr.esgi.avis.dto.JoueurDtoOut;

public interface JoueurUseCase {

    AvisDtoOut redigerAvis(AvisDtoIn avis);

    AvisDtoOut modifierAvis(AvisDtoIn avis);

    JoueurDtoOut creerJoueur(JoueurDtoIn joueur);
}
