package fr.esgi.avis.port;

import fr.esgi.avis.dto.JoueurDtoOut;
import fr.esgi.avis.dto.ModerateurDtoOut;

import java.util.Optional;

public interface ModerateurPort {
    Optional<ModerateurDtoOut> findById(Long id);
}
