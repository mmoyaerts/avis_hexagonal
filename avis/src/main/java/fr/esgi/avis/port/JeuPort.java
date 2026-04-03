package fr.esgi.avis.port;

import fr.esgi.avis.business.Jeu;
import fr.esgi.avis.dto.JeuDtoIn;
import fr.esgi.avis.dto.JeuDtoOut;

import java.util.List;
import java.util.Optional;

public interface JeuPort {
    List<JeuDtoOut> findAll();
    Optional<JeuDtoOut> findById(Long id);
    List<JeuDtoOut> findByNomContainingIgnoreCase(String nom);
    JeuDtoOut save(JeuDtoIn jeuDtoIn);
}