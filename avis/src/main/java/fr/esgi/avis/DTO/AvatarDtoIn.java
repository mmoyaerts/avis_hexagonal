package fr.esgi.avis.DTO;

import fr.esgi.avis.business.Avatar;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Avatar}
 */
@Value
public class AvatarDtoIn implements Serializable {
    Long id;
    String nom;
    Long utilisateurId;
}