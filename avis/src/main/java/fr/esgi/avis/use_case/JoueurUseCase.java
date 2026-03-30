package fr.esgi.avis.use_case;

import fr.esgi.avis.business.Avis;
import fr.esgi.avis.business.Joueur;

import java.util.List;

public interface JoueurUseCase {

    Avis redigerAvis(Avis avis);

    Avis modifierAvis(Avis avis);

    Joueur creerJoueur(Joueur joueur);
}
