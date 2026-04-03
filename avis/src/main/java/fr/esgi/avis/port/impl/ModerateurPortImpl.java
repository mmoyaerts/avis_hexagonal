package fr.esgi.avis.port.impl;

import fr.esgi.avis.business.Plateforme;
import fr.esgi.avis.dto.ModerateurDtoOut;
import fr.esgi.avis.dto.PlateformeDtoIn;
import fr.esgi.avis.dto.PlateformeDtoOut;
import fr.esgi.avis.mapper.ModerateurMapper;
import fr.esgi.avis.mapper.PlateformeMapper;
import fr.esgi.avis.port.ModerateurPort;
import fr.esgi.avis.repository.ModerateurEntityRepository;
import fr.esgi.avis.repository.PlateformeEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ModerateurPortImpl implements ModerateurPort {

    private final ModerateurEntityRepository moderateurEntityRepository;

    public ModerateurPortImpl(ModerateurEntityRepository moderateurEntityRepository) {
        this.moderateurEntityRepository = moderateurEntityRepository;
    }

    @Override
    public Optional<ModerateurDtoOut> findById(Long id) {
        return moderateurEntityRepository.findById(id)
                .map(ModerateurMapper::toDtoOut);
    }
}