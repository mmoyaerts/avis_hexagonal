package fr.esgi.avis.DTO;

import fr.esgi.avis.business.Plateforme;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Plateforme}
 */
@Value
public class PlateformeDtoIn implements Serializable {
    Long id;
    String nom;
    List<Long> jeuxIds;
}