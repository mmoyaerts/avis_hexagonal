package fr.esgi.avis.mapper;

import fr.esgi.avis.business.Plateforme;
import fr.esgi.avis.dto.PlateformeDtoIn;
import fr.esgi.avis.dto.PlateformeDtoOut;

import java.util.Collections;
import java.util.List;

public class PlateformeMapper {

    public static PlateformeDtoOut toPlateformeDtoOut(Plateforme plateforme) {
        List<Long> jeuxIds = plateforme.getJeux() != null
                ? plateforme.getJeux().stream().map(j -> j.getId()).toList()
                : Collections.emptyList();

        return new PlateformeDtoOut(
                plateforme.getId(),
                plateforme.getNom(),
                jeuxIds
        );
    }

    public static Plateforme toPlateforme(PlateformeDtoIn plateformeDtoIn) {
        Plateforme plateforme = new Plateforme();
        plateforme.setId(plateformeDtoIn.getId());
        plateforme.setNom(plateformeDtoIn.getNom());
        return plateforme;
    }
}