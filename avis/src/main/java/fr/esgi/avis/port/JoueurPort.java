package fr.esgi.avis.port;

import fr.esgi.avis.business.Joueur;
import fr.esgi.avis.dto.JoueurDtoIn;
import fr.esgi.avis.dto.JoueurDtoOut;

import java.util.Optional;

public interface JoueurPort {
    boolean existsByEmail(String email);
    JoueurDtoOut save(JoueurDtoIn joueurDtoIn);
    Optional<JoueurDtoOut> findById(Long id);
}