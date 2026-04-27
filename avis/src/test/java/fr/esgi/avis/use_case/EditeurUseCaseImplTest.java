package fr.esgi.avis.use_case;

import fr.esgi.avis.dto.EditeurDtoIn;
import fr.esgi.avis.dto.EditeurDtoOut;
import fr.esgi.avis.port.EditeurPort;
import fr.esgi.avis.use_case.impl.EditeurUseCaseImpl;
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
class EditeurUseCaseImplTest {

    @Mock
    private EditeurPort editeurPort;

    @InjectMocks
    private EditeurUseCaseImpl editeurUseCase;

    private EditeurDtoIn editeurDtoIn;
    private EditeurDtoOut editeurDtoOut;
    private EditeurDtoOut editeurDtoOut2;

    @BeforeEach
    void setUp() {
        editeurDtoIn = new EditeurDtoIn(1L, "Nintendo", "nintendo.png", List.of(1L, 2L, 3L));
        editeurDtoOut = new EditeurDtoOut(1L, "Nintendo", "nintendo.png", List.of(1L, 2L, 3L));
        editeurDtoOut2 = new EditeurDtoOut(2L, "Ubisoft", "ubisoft.png", List.of(4L, 5L));
    }

    @Test
    void recuperJeuxParEditeur_editeurExistant_devraitRetournerListeJeuxIds() {
        when(editeurPort.findById(1L)).thenReturn(Optional.of(editeurDtoOut));

        List<Long> result = editeurUseCase.recuperJeuxParEditeur(editeurDtoIn);

        assertThat(result).containsExactly(1L, 2L, 3L);
        verify(editeurPort).findById(1L);
    }

    @Test
    void recuperJeuxParEditeur_editeurInexistant_devraitLancerRuntimeException() {
        EditeurDtoIn editeurInexistant = new EditeurDtoIn(99L, "Inconnu", null, List.of());
        when(editeurPort.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> editeurUseCase.recuperJeuxParEditeur(editeurInexistant))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");
    }

    @Test
    void recuperJeuxParEditeur_sansJeux_devraitRetournerListeVide() {
        EditeurDtoOut editeurSansJeux = new EditeurDtoOut(3L, "Indie Studio", "indie.png", List.of());
        EditeurDtoIn editeurDtoInSansJeux = new EditeurDtoIn(3L, "Indie Studio", "indie.png", List.of());
        when(editeurPort.findById(3L)).thenReturn(Optional.of(editeurSansJeux));

        List<Long> result = editeurUseCase.recuperJeuxParEditeur(editeurDtoInSansJeux);

        assertThat(result).isEmpty();
    }

    @Test
    void recuperEditeurs_devraitRetournerTousLesEditeurs() {
        when(editeurPort.findAll()).thenReturn(List.of(editeurDtoOut, editeurDtoOut2));

        List<EditeurDtoOut> result = editeurUseCase.recuperEditeurs();

        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(editeurDtoOut, editeurDtoOut2);
        verify(editeurPort).findAll();
    }

    @Test
    void recuperEditeurs_listeVide_devraitRetournerListeVide() {
        when(editeurPort.findAll()).thenReturn(List.of());

        List<EditeurDtoOut> result = editeurUseCase.recuperEditeurs();

        assertThat(result).isEmpty();
    }

    @Test
    void recupererEditeurById_idExistant_devraitRetournerLEditeur() {
        when(editeurPort.findById(1L)).thenReturn(Optional.of(editeurDtoOut));

        EditeurDtoOut result = editeurUseCase.recupererEditeurById(1L);

        assertThat(result).isEqualTo(editeurDtoOut);
        assertThat(result.getNom()).isEqualTo("Nintendo");
        verify(editeurPort).findById(1L);
    }

    @Test
    void recupererEditeurById_idInexistant_devraitLancerRuntimeException() {
        when(editeurPort.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> editeurUseCase.recupererEditeurById(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");
    }
}
