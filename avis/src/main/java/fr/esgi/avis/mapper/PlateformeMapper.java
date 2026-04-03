package fr.esgi.avis.mapper;

import fr.esgi.avis.business.Plateforme;
import fr.esgi.avis.dto.PlateformeDtoIn;
import fr.esgi.avis.dto.PlateformeDtoOut;
import fr.esgi.avis.entity.JeuEntity;
import fr.esgi.avis.entity.PlateformeEntity;

import java.util.Collections;
import java.util.List;

public class PlateformeMapper {

    public static PlateformeEntity toEntity(PlateformeDtoIn dto) {
        if (dto == null) return null;

        PlateformeEntity plateforme = new PlateformeEntity();
        plateforme.setId(dto.getId());
        plateforme.setNom(dto.getNom());

        return plateforme;
    }

    public static PlateformeDtoOut toDtoOut(PlateformeEntity plateforme) {
        if (plateforme == null) return null;

        return new PlateformeDtoOut(
                plateforme.getId(),
                plateforme.getNom(),
                plateforme.getJeux()
                        .stream()
                        .map(JeuEntity::getId)
                        .toList()
        );
    }
}