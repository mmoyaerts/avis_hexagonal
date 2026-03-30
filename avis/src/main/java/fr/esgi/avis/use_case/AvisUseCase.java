package fr.esgi.avis.use_case;

import fr.esgi.avis.dto.AvisDtoOut;

public interface AvisUseCase {
    AvisDtoOut recuperUnAvis(Long id);
}
