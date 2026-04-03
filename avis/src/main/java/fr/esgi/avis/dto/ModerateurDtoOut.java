package fr.esgi.avis.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link fr.esgi.avis.business.Moderateur}
 */
@Value
public class ModerateurDtoOut implements Serializable {
    String numeroDeTelephone;
    Long id;
    String pseudo;
    String email;
    String motDePasse;
    Long avatarId;
    List<Long> aviIds;
}