package fr.esgi.avis.DTO;

import fr.esgi.avis.business.Utilisateur;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Utilisateur}
 */
@Value
public class UtilisateurDtoIn implements Serializable {
    Long id;
    String pseudo;
    String email;
    String motDePasse;
    Long avatarId;
    List<Long> aviIds;
}