package fr.esgi.avis.use_case;

import fr.esgi.avis.DTO.AvisDtoIn;
import fr.esgi.avis.DTO.AvisDtoOut;
import fr.esgi.avis.DTO.JoueurDtoIn;
import fr.esgi.avis.DTO.JoueurDtoOut;
import fr.esgi.avis.business.Avis;
import fr.esgi.avis.business.Joueur;

import java.util.List;

public interface JoueurUseCase {

    AvisDtoOut redigerAvis(AvisDtoIn avis);

    AvisDtoOut modifierAvis(AvisDtoIn avis);

    JoueurDtoOut creerJoueur(JoueurDtoIn joueur);
}
