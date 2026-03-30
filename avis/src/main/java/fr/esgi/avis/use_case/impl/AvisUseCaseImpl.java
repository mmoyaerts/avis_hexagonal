package fr.esgi.avis.use_case.impl;

import fr.esgi.avis.business.Avis;
import fr.esgi.avis.dto.AvisDtoOut;
import fr.esgi.avis.mapper.AvisMapper;
import fr.esgi.avis.repository.AvisEntityRepository;
import fr.esgi.avis.use_case.AvisUseCase;
import jakarta.persistence.EntityNotFoundException;

public class AvisUseCaseImpl implements AvisUseCase {

    private final AvisEntityRepository avisEntityRepository;
    private final AvisMapper avisMapper;

    public AvisUseCaseImpl(AvisEntityRepository avisEntityRepository, AvisMapper avisMapper) {
        this.avisEntityRepository = avisEntityRepository;
        this.avisMapper = avisMapper;
    }

    @Override
    public AvisDtoOut recuperUnAvis(Long id) {
        Avis avis = avisEntityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avis non trouvé avec l'id : " + id));
        return avisMapper.toDto(avis);
    }
}
