package fr.esgi.avis.use_case;

import fr.esgi.avis.dto.PlateformeDtoIn;
import fr.esgi.avis.dto.PlateformeDtoOut;
import fr.esgi.avis.port.PlateformePort;
import fr.esgi.avis.use_case.impl.PlateformeUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlateformeUseCaseImplTest {

    @Mock
    private PlateformePort plateformePort;

    @InjectMocks
    private PlateformeUseCaseImpl plateformeUseCase;

    private PlateformeDtoIn plateformeDtoIn;
    private PlateformeDtoOut plateformeDtoOut;

    @BeforeEach
    void setUp() {
        plateformeDtoIn = new PlateformeDtoIn(1L, "Nintendo Switch", List.of(1L, 2L, 3L));
        plateformeDtoOut = new PlateformeDtoOut(1L, "Nintendo Switch", List.of(1L, 2L, 3L));
    }

    @Test
    void recupererJeuParPlateforme_plateformeExistante_devraitRetournerLesJeuxIds() {
        when(plateformePort.findById(1L)).thenReturn(Optional.of(plateformeDtoOut));

        List<Long> result = plateformeUseCase.recupererJeuParPlateforme(plateformeDtoIn);

        assertThat(result).containsExactly(1L, 2L, 3L);
        verify(plateformePort).findById(1L);
    }

    @Test
    void recupererJeuParPlateforme_plateformeInexistante_devraitLancerRuntimeException() {
        PlateformeDtoIn plateformeInexistante = new PlateformeDtoIn(99L, "Inconnue", List.of());
        when(plateformePort.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> plateformeUseCase.recupererJeuParPlateforme(plateformeInexistante))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");
    }

    @Test
    void recupererJeuParPlateforme_plateformeSansJeux_devraitRetournerListeVide() {
        PlateformeDtoOut plateformeSansJeux = new PlateformeDtoOut(2L, "PS5", List.of());
        PlateformeDtoIn plateformeDtoInSansJeux = new PlateformeDtoIn(2L, "PS5", List.of());
        when(plateformePort.findById(2L)).thenReturn(Optional.of(plateformeSansJeux));

        List<Long> result = plateformeUseCase.recupererJeuParPlateforme(plateformeDtoInSansJeux);

        assertThat(result).isEmpty();
    }
}
