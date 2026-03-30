package fr.esgi.avis.mapper;

import fr.esgi.avis.business.Utilisateur;
import fr.esgi.avis.dto.UtilisateurDtoIn;
import fr.esgi.avis.dto.UtilisateurDtoOut;

import java.util.Collections;
import java.util.List;

public class UtilisateurMapper {

    public static UtilisateurDtoOut toUtilisateurDtoOut(Utilisateur utilisateur) {
        List<Long> avisIds = utilisateur.getAvis() != null
                ? utilisateur.getAvis().stream().map(a -> a.getId()).toList()
                : Collections.emptyList();

        return new UtilisateurDtoOut(
                utilisateur.getId(),
                utilisateur.getPseudo(),
                utilisateur.getEmail(),
                utilisateur.getMotDePasse(),
                utilisateur.getAvatar() != null ? utilisateur.getAvatar().getId() : null,
                avisIds
        );
    }
}