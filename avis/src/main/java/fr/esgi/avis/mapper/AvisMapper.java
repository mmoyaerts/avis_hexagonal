package fr.esgi.avis.mapper;

import fr.esgi.avis.business.Avis;
import fr.esgi.avis.dto.AvisDtoIn;
import fr.esgi.avis.dto.AvisDtoOut;

public class AvisMapper {

    public static AvisDtoOut toAvisDtoOut(Avis avis) {
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

    public static Avis toAvis(AvisDtoIn avisDtoIn) {
        Avis avis = new Avis();
        avis.setId(avisDtoIn.getId());
        avis.setDescription(avisDtoIn.getDescription());
        avis.setDateDeCreation(avisDtoIn.getDateDeCreation());
        avis.setNote(avisDtoIn.getNote());
        // jeu, joueur, moderateur → récupérés via leurs repositories dans le use case
        return avis;
    }
}