package fr.esgi.avis.dto;

import fr.esgi.avis.business.Jeu;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link Jeu}
 */
@Value
public class JeuDtoOut implements Serializable {
    Long id;
    String nom;
    LocalDate dateDeSortie;
    String description;
    Long editeurId;
    List<Long> plateformeIds;
    Long genreId;
    String image;
    float prix;
    Long classificationId;
    List<Long> aviIds;
}