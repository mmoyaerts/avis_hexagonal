package fr.esgi.avis.dto;

import fr.esgi.avis.business.Plateforme;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Plateforme}
 */
@Value
@Data
public class PlateformeDtoOut implements Serializable {
    Long id;
    String nom;
    List<Long> jeuxIds;
}