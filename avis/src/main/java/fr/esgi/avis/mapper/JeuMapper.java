package fr.esgi.avis.mapper;

import fr.esgi.avis.business.Jeu;
import fr.esgi.avis.dto.JeuDtoIn;
import fr.esgi.avis.dto.JeuDtoOut;

import java.util.Collections;
import java.util.List;

public class JeuMapper {

    public static JeuDtoOut toJeuDtoOut(Jeu jeu) {
        List<Long> plateformeIds = jeu.getPlateformes() != null
                ? jeu.getPlateformes().stream().map(p -> p.getId()).toList()
                : Collections.emptyList();

        List<Long> avisIds = jeu.getAvis() != null
                ? jeu.getAvis().stream().map(a -> a.getId()).toList()
                : Collections.emptyList();

        return new JeuDtoOut(
                jeu.getId(),
                jeu.getNom(),
                jeu.getDateDeSortie(),
                jeu.getDescription(),
                jeu.getEditeur() != null ? jeu.getEditeur().getId() : null,
                plateformeIds,
                jeu.getGenre() != null ? jeu.getGenre().getId() : null,
                jeu.getImage(),
                jeu.getPrix(),
                jeu.getClassification() != null ? jeu.getClassification().getId() : null,
                avisIds
        );
    }

    public static Jeu toJeu(JeuDtoIn jeuDtoIn) {
        Jeu jeu = new Jeu();
        jeu.setId(jeuDtoIn.getId());
        jeu.setNom(jeuDtoIn.getNom());
        jeu.setDateDeSortie(jeuDtoIn.getDateDeSortie());
        jeu.setDescription(jeuDtoIn.getDescription());
        jeu.setImage(jeuDtoIn.getImage());
        jeu.setPrix(jeuDtoIn.getPrix());
        // editeur, plateformes, genre, classification, avis → récupérés via leurs repositories
        return jeu;
    }
}