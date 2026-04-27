package fr.esgi.avis.use_case;

import fr.esgi.avis.dto.AvisDtoOut;
import fr.esgi.avis.port.AvisPort;
import fr.esgi.avis.use_case.impl.AvisUseCaseImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AvisUseCaseImplTest {

    @Mock
    private AvisPort avisPort;

    @InjectMocks
    private AvisUseCaseImpl avisUseCase;

    private AvisDtoOut avisDtoOut;

    @BeforeEach
    void setUp() {
        avisDtoOut = new AvisDtoOut(1L, "Très bon jeu", LocalDateTime.of(2024, 1, 15, 10, 0), 8.5f, 1L, 2L, null);
    }

    @Test
    void recuperUnAvis_idExistant_devraitRetournerLAvis() {
        when(avisPort.findById(1L)).thenReturn(Optional.of(avisDtoOut));

        AvisDtoOut result = avisUseCase.recuperUnAvis(1L);

        assertThat(result).isEqualTo(avisDtoOut);
        assertThat(result.getDescription()).isEqualTo("Très bon jeu");
        assertThat(result.getNote()).isEqualTo(8.5f);
        verify(avisPort).findById(1L);
    }

    @Test
    void recuperUnAvis_idInexistant_devraitLancerEntityNotFoundException() {
        when(avisPort.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> avisUseCase.recuperUnAvis(99L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");

        verify(avisPort).findById(99L);
    }
}
