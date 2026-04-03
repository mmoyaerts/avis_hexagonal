package fr.esgi.avis.port;

import fr.esgi.avis.business.Plateforme;
import fr.esgi.avis.dto.PlateformeDtoOut;

import java.util.Optional;

public interface PlateformePort {
    Optional<PlateformeDtoOut> findById(Long id);
}