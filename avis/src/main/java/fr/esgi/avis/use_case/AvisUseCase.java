package fr.esgi.avis.use_case;

import fr.esgi.avis.business.Avis;
import fr.esgi.avis.business.Joueur;
import fr.esgi.avis.business.Moderateur;

import java.util.List;

public interface AvisUseCase {
    Avis recuperUnAvis(Long id);
}
