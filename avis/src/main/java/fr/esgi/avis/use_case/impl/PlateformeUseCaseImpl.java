package fr.esgi.avis.use_case.impl;

import fr.esgi.avis.dto.JeuDtoOut;
import fr.esgi.avis.dto.PlateformeDtoIn;
import fr.esgi.avis.mapper.JeuMapper;
import fr.esgi.avis.port.PlateformePort;
import fr.esgi.avis.use_case.PlateformeUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

public class PlateformeUseCaseImpl implements PlateformeUseCase {

    private final PlateformePort plateformePort;

    public PlateformeUseCaseImpl(PlateformePort plateformePort) {
        this.plateformePort = plateformePort;
    }

    @Override
    public List<Long> recupererJeuParPlateforme(PlateformeDtoIn plateformeDtoIn) {
        return plateformePort.findById(plateformeDtoIn.getId())
                .orElseThrow(() -> new RuntimeException("Plateforme introuvable : " + plateformeDtoIn.getId()))
                .getJeuxIds()
                .stream()
                .toList();
    }
}