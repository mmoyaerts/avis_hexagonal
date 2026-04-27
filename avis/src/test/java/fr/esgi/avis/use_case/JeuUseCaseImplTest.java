package fr.esgi.avis.use_case;

import fr.esgi.avis.dto.AvisDtoOut;
import fr.esgi.avis.dto.JeuDtoIn;
import fr.esgi.avis.dto.JeuDtoOut;
import fr.esgi.avis.port.AvisPort;
import fr.esgi.avis.port.JeuPort;
import fr.esgi.avis.use_case.impl.JeuUseCaseImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JeuUseCaseImplTest {

    @Mock
    private JeuPort jeuPort;

    @Mock
    private AvisPort avisPort;

    @InjectMocks
    private JeuUseCaseImpl jeuUseCase;

    private JeuDtoOut jeu1;
    private JeuDtoOut jeu2;

    @BeforeEach
    void setUp() {
        jeu1 = new JeuDtoOut(1L, "Zelda", LocalDate.of(2023, 5, 12), "Un jeu d'aventure", 1L,
                List.of(1L, 2L), 1L, "zelda.jpg", 59.99f, 1L, List.of(1L, 2L));
        jeu2 = new JeuDtoOut(2L, "Mario", LocalDate.of(2022, 10, 28), "Un jeu de plateforme", 2L,
                List.of(1L), 2L, "mario.jpg", 49.99f, 2L, List.of());
    }

    @Test
    void recupererJeux_devraitRetournerTousLesJeux() {
        when(jeuPort.findAll()).thenReturn(List.of(jeu1, jeu2));

        List<JeuDtoOut> result = jeuUseCase.recupererJeux();

        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(jeu1, jeu2);
        verify(jeuPort).findAll();
    }

    @Test
    void recupererJeux_listeVide_devraitRetournerListeVide() {
        when(jeuPort.findAll()).thenReturn(List.of());

        List<JeuDtoOut> result = jeuUseCase.recupererJeux();

        assertThat(result).isEmpty();
    }

    @Test
    void recupererJeu_idExistant_devraitRetournerLeJeu() {
        when(jeuPort.findById(1L)).thenReturn(Optional.of(jeu1));

        JeuDtoOut result = jeuUseCase.recupererJeu(1L);

        assertThat(result).isEqualTo(jeu1);
        assertThat(result.getNom()).isEqualTo("Zelda");
        verify(jeuPort).findById(1L);
    }

    @Test
    void recupererJeu_idInexistant_devraitLancerEntityNotFoundException() {
        when(jeuPort.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> jeuUseCase.recupererJeu(99L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void recupererParNom_nomCorrespondant_devraitRetournerJeuxFiltres() {
        when(jeuPort.findByNomContainingIgnoreCase("zel")).thenReturn(List.of(jeu1));

        List<JeuDtoOut> result = jeuUseCase.recupererParNom("zel");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNom()).isEqualTo("Zelda");
        verify(jeuPort).findByNomContainingIgnoreCase("zel");
    }

    @Test
    void recupererParNom_aucunResultat_devraitRetournerListeVide() {
        when(jeuPort.findByNomContainingIgnoreCase("xyz")).thenReturn(List.of());

        List<JeuDtoOut> result = jeuUseCase.recupererParNom("xyz");

        assertThat(result).isEmpty();
    }

    @Test
    void recupererAvisPourUnJeu_devraitRetournerLesAvis() {
        JeuDtoIn jeuDtoIn = new JeuDtoIn(1L, "Zelda", LocalDate.of(2023, 5, 12),
                "Un jeu d'aventure", 1L, List.of(1L, 2L), 1L, "zelda.jpg", 59.99f, 1L, List.of(1L, 2L));
        AvisDtoOut avis1 = new AvisDtoOut(1L, "Excellent jeu", LocalDateTime.now(), 9.5f, 1L, 1L, null);
        AvisDtoOut avis2 = new AvisDtoOut(2L, "Bof", LocalDateTime.now(), 5.0f, 1L, 2L, null);

        when(avisPort.findByJeu(jeuDtoIn)).thenReturn(List.of(avis1, avis2));

        List<AvisDtoOut> result = jeuUseCase.recupererAvisPourUnJeu(jeuDtoIn);

        assertThat(result).hasSize(2);
        verify(avisPort).findByJeu(jeuDtoIn);
    }
}
