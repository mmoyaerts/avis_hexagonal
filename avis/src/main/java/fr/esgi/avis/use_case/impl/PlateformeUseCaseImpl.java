// PlateformeUseCaseImpl.java
package fr.esgi.avis.use_case.impl;

import fr.esgi.avis.dto.JeuDtoOut;
import fr.esgi.avis.dto.PlateformeDtoIn;
import fr.esgi.avis.entity.Plateforme;
import fr.esgi.avis.mapper.PlateformeMapper;
import fr.esgi.avis.repository.PlateformeRepository;
import fr.esgi.avis.use_case.PlateformeUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlateformeUseCaseImpl implements PlateformeUseCase {

    private final PlateformeRepository plateformeRepository;

    public PlateformeUseCaseImpl(PlateformeRepository plateformeRepository) {
        this.plateformeRepository = plateformeRepository;
    }

    @Override
    public List<JeuDtoOut> recupererJeuParPlateforme(PlateformeDtoIn plateformeDtoIn) {
        Plateforme plateforme = plateformeRepository.findById(plateformeDtoIn.id())
                .orElseThrow(() -> new RuntimeException("Plateforme introuvable : " + plateformeDtoIn.id()));

        return plateforme.getJeux()
                .stream()
                .map(PlateformeMapper::toJeuDtoOut)
                .toList();
    }
}