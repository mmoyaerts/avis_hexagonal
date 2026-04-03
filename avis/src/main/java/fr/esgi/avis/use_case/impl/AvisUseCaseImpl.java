package fr.esgi.avis.use_case.impl;

import fr.esgi.avis.business.Avis;
import fr.esgi.avis.dto.AvisDtoOut;
import fr.esgi.avis.mapper.AvisMapper;
import fr.esgi.avis.port.AvisPort;
import fr.esgi.avis.use_case.AvisUseCase;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AvisUseCaseImpl implements AvisUseCase {

    private final AvisPort avisPort;

    public AvisUseCaseImpl(AvisPort avisPort) {
        this.avisPort = avisPort;
    }

    @Override
    public AvisDtoOut recuperUnAvis(Long id) {
        return avisPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avis non trouvé avec l'id : " + id));
    }
}