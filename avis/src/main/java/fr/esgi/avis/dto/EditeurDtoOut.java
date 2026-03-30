package fr.esgi.avis.dto;

import fr.esgi.avis.business.Editeur;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Editeur}
 */
@Value
public class EditeurDtoOut implements Serializable {
    Long id;
    String nom;
    String logo;
    List<Long> jeuxIds;
}