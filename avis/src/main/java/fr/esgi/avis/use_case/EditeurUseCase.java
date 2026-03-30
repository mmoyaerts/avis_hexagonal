package fr.esgi.avis.use_case;

import fr.esgi.avis.dto.EditeurDtoIn;
import fr.esgi.avis.dto.EditeurDtoOut;
import fr.esgi.avis.dto.JeuDtoOut;

import java.util.List;

public interface EditeurUseCase {
    List<JeuDtoOut> recuperJeuxParEditeur(EditeurDtoIn editeur);

    List<EditeurDtoOut> recuperEditeurs();

    EditeurDtoOut recupererEditeurById(Long id);
}
