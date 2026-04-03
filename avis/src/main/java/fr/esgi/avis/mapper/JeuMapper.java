package fr.esgi.avis.mapper;

import fr.esgi.avis.business.Jeu;
import fr.esgi.avis.dto.JeuDtoIn;
import fr.esgi.avis.dto.JeuDtoOut;
import fr.esgi.avis.entity.AvisEntity;
import fr.esgi.avis.entity.JeuEntity;
import fr.esgi.avis.entity.PlateformeEntity;

import java.util.List;
import java.util.Objects;

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
                Objects.requireNonNullElse(jeu.getPlateformes(), List.<PlateformeEntity>of()) // ✅ type explicite
                        .stream()
                        .map(PlateformeEntity::getId)                                 // ✅ lambda
                        .toList(),
                jeu.getGenre() != null ? jeu.getGenre().getId() : null,
                jeu.getImage(),
                jeu.getPrix(),
                jeu.getClassification() != null ? jeu.getClassification().getId() : null,
                Objects.requireNonNullElse(jeu.getAvis(), List.<AvisEntity>of())              // ✅ type explicite
                        .stream()
                        .map(AvisEntity::getId)                                             // ✅ lambda
                        .toList()
        );
    }
}