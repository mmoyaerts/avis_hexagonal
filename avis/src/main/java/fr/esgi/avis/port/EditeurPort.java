package fr.esgi.avis.port;

import fr.esgi.avis.business.Editeur;
import fr.esgi.avis.dto.EditeurDtoIn;
import fr.esgi.avis.dto.EditeurDtoOut;

import java.util.List;
import java.util.Optional;

public interface EditeurPort {
    Optional<EditeurDtoOut> findById(Long id);
    List<EditeurDtoOut> findAll();
    EditeurDtoOut save(EditeurDtoIn editeurDtoIn);
}