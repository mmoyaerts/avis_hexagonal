package fr.esgi.avis.port;

import fr.esgi.avis.business.Avatar;
import fr.esgi.avis.dto.AvatarDtoOut;

import java.util.Optional;

public interface AvatarPort {
    Optional<AvatarDtoOut> findById(Long id);
}