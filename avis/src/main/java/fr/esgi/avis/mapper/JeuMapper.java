package fr.esgi.avis.mapper;

import fr.esgi.avis.business.Jeu;
import fr.esgi.avis.dto.JeuDtoIn;
import fr.esgi.avis.dto.JeuDtoOut;
import fr.esgi.avis.entity.AvisEntity;
import fr.esgi.avis.entity.JeuEntity;
import fr.esgi.avis.entity.PlateformeEntity;

import java.util.Collections;
import java.util.List;

public class JeuMapper {

    public static JeuEntity toEntity(JeuDtoIn dto) {
        if (dto == null) return null;

        JeuEntity jeu = new JeuEntity();
        jeu.setId(dto.getId());
        jeu.setNom(dto.getNom());
        jeu.setDateDeSortie(dto.getDateDeSortie());
        jeu.setDescription(dto.getDescription());
        jeu.setImage(dto.getImage());
        jeu.setPrix(dto.getPrix());

        return jeu;
    }

    public static JeuDtoOut toDtoOut(JeuEntity jeu) {
        if (jeu == null) return null;

        return new JeuDtoOut(
                jeu.getId(),
                jeu.getNom(),
                jeu.getDateDeSortie(),
                jeu.getDescription(),
                jeu.getEditeur() != null ? jeu.getEditeur().getId() : null,
                jeu.getPlateformes()
                        .stream()
                        .map(PlateformeEntity::getId)
                        .toList(),
                jeu.getGenre() != null ? jeu.getGenre().getId() : null,
                jeu.getImage(),
                jeu.getPrix(),
                jeu.getClassification() != null ? jeu.getClassification().getId() : null,
                jeu.getAvis()
                        .stream()
                        .map(AvisEntity::getId)
                        .toList()
        );
    }
}