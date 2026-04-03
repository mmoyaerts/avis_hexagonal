package fr.esgi.avis.port;

import fr.esgi.avis.business.Utilisateur;
import fr.esgi.avis.dto.UtilisateurDtoIn;
import fr.esgi.avis.dto.UtilisateurDtoOut;

import java.util.Optional;

public interface UtilisateurPort {
    Optional<UtilisateurDtoOut> findByEmailAndMotDePasse(String email, String motDePasse);
    Optional<UtilisateurDtoOut> findById(Long id);
    UtilisateurDtoOut save(UtilisateurDtoIn utilisateurDtoIn);
}