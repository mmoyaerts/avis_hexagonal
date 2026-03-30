package fr.esgi.avis.use_case;

import fr.esgi.avis.DTO.EditeurDtoIn;
import fr.esgi.avis.DTO.EditeurDtoOut;
import fr.esgi.avis.DTO.JeuDtoOut;
import fr.esgi.avis.business.Editeur;
import fr.esgi.avis.business.Jeu;

import java.util.List;

public interface EditeurUseCase {
    List<JeuDtoOut> recuperJeuxParEditeur(EditeurDtoIn editeur);

    List<EditeurDtoOut> recuperEditeurs();

    EditeurDtoOut recupererEditeurById(Long id);
}
