package fr.esgi.avis.use_case;

import fr.esgi.avis.business.Jeu;
import fr.esgi.avis.business.Plateforme;

import java.util.List;

public interface PlateformeUseCase {
    List<Jeu> recupererJeuParPlateforme(Plateforme plateforme);
}
