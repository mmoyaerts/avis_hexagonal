package fr.esgi.avis.use_case;

import fr.esgi.avis.business.Editeur;
import fr.esgi.avis.business.Jeu;

import java.util.List;

public interface EditeurUseCase {
    List<Jeu> recuperJeuxParEditeur(Editeur editeur);

    List<Editeur> recuperEditeurs();

    Editeur recupererEditeurById(Long id);
}
