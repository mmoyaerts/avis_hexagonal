package fr.esgi.avis.DTO;

import fr.esgi.avis.business.Joueur;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link Joueur}
 */
@Value
public class JoueurDtoIn implements Serializable {
    LocalDate dateDeNaissance;
    Long id;
    String pseudo;
    String email;
    String motDePasse;
    Long avatarId;
    List<Long> aviIds;
}