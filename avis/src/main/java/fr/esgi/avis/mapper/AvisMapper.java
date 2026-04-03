package fr.esgi.avis.mapper;

import fr.esgi.avis.dto.AvisDtoIn;
import fr.esgi.avis.dto.AvisDtoOut;
import fr.esgi.avis.entity.AvisEntity;

public class AvisMapper {

    public static AvisDtoOut toAvisDtoOut(AvisEntity avis) {
        return new AvisDtoOut(
                avis.getId(),
                avis.getDescription(),
                avis.getDateDeCreation(),
                avis.getNote(),
                avis.getJeu() != null ? avis.getJeu().getId() : null,
                avis.getJoueur() != null ? avis.getJoueur().getId() : null,
                avis.getModerateur() != null ? avis.getModerateur().getId() : null
        );
    }

    public static AvisEntity toAvisEntity(AvisDtoIn avisDtoIn) {
        AvisEntity avis = new AvisEntity();
        avis.setId(avisDtoIn.getId());
        avis.setDescription(avisDtoIn.getDescription());
        avis.setDateDeCreation(avisDtoIn.getDateDeCreation());
        avis.setNote(avisDtoIn.getNote());

        // ⚠️ relations gérées ailleurs (use case)
        return avis;
    }
}