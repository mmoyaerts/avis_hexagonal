package fr.esgi.avis.port.impl;

import fr.esgi.avis.business.Plateforme;
import fr.esgi.avis.dto.PlateformeDtoIn;
import fr.esgi.avis.dto.PlateformeDtoOut;
import fr.esgi.avis.mapper.PlateformeMapper;
import fr.esgi.avis.port.PlateformePort;
import fr.esgi.avis.repository.PlateformeEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PlateformePortImpl implements PlateformePort {

    private final PlateformeEntityRepository plateformeEntityRepository;

    public PlateformePortImpl(PlateformeEntityRepository plateformeEntityRepository) {
        this.plateformeEntityRepository = plateformeEntityRepository;
    }

    @Override
    public Optional<PlateformeDtoOut> findById(Long id) {
        return plateformeEntityRepository.findById(id)
                .map(PlateformeMapper::toDtoOut); // entity → business
    }
}