package fr.esgi.avis.dto;

import fr.esgi.avis.business.Utilisateur;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Utilisateur}
 */
@Value
@Data
public class UtilisateurDtoOut implements Serializable {
    Long id;
    String pseudo;
    String email;
    String motDePasse;
    Long avatarId;
    List<Long> aviIds;

}