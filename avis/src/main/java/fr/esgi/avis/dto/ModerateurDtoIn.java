package fr.esgi.avis.dto;

import fr.esgi.avis.business.Moderateur;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Moderateur}
 */
@Value
public class ModerateurDtoIn implements Serializable {
    String numeroDeTelephone;
    Long id;
    String pseudo;
    String email;
    String motDePasse;
    Long avatarId;
    List<Long> aviIds;
}