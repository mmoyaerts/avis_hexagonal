package fr.esgi.avis.mapper;

import fr.esgi.avis.business.Joueur;
import fr.esgi.avis.dto.JoueurDtoIn;
import fr.esgi.avis.dto.JoueurDtoOut;

import java.util.Collections;
import java.util.List;

public class JoueurMapper {

    public static JoueurDtoOut toJoueurDtoOut(Joueur joueur) {
        List<Long> avisIds = joueur.getAvis() != null
                ? joueur.getAvis().stream().map(a -> a.getId()).toList()
                : Collections.emptyList();

        return new JoueurDtoOut(
                joueur.getDateDeNaissance(),
                joueur.getId(),
                joueur.getPseudo(),
                joueur.getEmail(),
                joueur.getMotDePasse(),
                joueur.getAvatar() != null ? joueur.getAvatar().getId() : null,
                avisIds
        );
    }

    public static Joueur toJoueur(JoueurDtoIn joueurDtoIn) {
        Joueur joueur = new Joueur();
        joueur.setId(joueurDtoIn.getId());
        joueur.setPseudo(joueurDtoIn.getPseudo());
        joueur.setEmail(joueurDtoIn.getEmail());
        joueur.setMotDePasse(joueurDtoIn.getMotDePasse());
        joueur.setDateDeNaissance(joueurDtoIn.getDateDeNaissance());
        // avatar et avis → récupérés via leurs repositories dans le use case
        return joueur;
    }
}