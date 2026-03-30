package fr.esgi.avis.dto;

import fr.esgi.avis.business.Avatar;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Avatar}
 */
@Value
public class AvatarDtoOut implements Serializable {
    Long id;
    String nom;
    Long utilisateurId;
}