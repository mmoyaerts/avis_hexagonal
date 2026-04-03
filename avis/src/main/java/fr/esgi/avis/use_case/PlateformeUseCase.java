package fr.esgi.avis.use_case;

import fr.esgi.avis.dto.JeuDtoOut;
import fr.esgi.avis.dto.PlateformeDtoIn;

import java.util.List;

public interface PlateformeUseCase {
    List<Long> recupererJeuParPlateforme(PlateformeDtoIn plateforme);
}
