package fr.esgi.avis.use_case;

import fr.esgi.avis.dto.*;
import fr.esgi.avis.port.AvisPort;
import fr.esgi.avis.port.JeuPort;
import fr.esgi.avis.port.JoueurPort;
import fr.esgi.avis.use_case.impl.JoueurUseCaseImpl;
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
class JoueurUseCaseImplTest {

    @Mock
    private JoueurPort joueurPort;

    @Mock
    private AvisPort avisPort;

    @Mock
    private JeuPort jeuPort;

    @InjectMocks
    private JoueurUseCaseImpl joueurUseCase;

    private JoueurDtoIn joueurDtoIn;
    private JoueurDtoOut joueurDtoOut;
    private JeuDtoOut jeuDtoOut;
    private AvisDtoIn avisDtoIn;
    private AvisDtoOut avisDtoOut;

    @BeforeEach
    void setUp() {
        joueurDtoIn = new JoueurDtoIn(LocalDate.of(1995, 3, 20), null,
                "Alice", "alice@example.com", "password123", null, List.of());
        joueurDtoOut = new JoueurDtoOut(LocalDate.of(1995, 3, 20), 1L,
                "Alice", "alice@example.com", "password123", null, List.of());
        jeuDtoOut = new JeuDtoOut(1L, "Zelda", LocalDate.of(2023, 5, 12),
                "Un jeu d'aventure", 1L, List.of(), 1L, "zelda.jpg", 59.99f, 1L, List.of());
        avisDtoIn = new AvisDtoIn(null, "Super jeu !", LocalDateTime.now(), 9.0f, 1L, 1L, null);
        avisDtoOut = new AvisDtoOut(1L, "Super jeu !", LocalDateTime.now(), 9.0f, 1L, 1L, null);
    }

    @Test
    void creerJoueur_emailNonExistant_devraitCreerLeJoueur() {
        when(joueurPort.existsByEmail("alice@example.com")).thenReturn(false);
        when(joueurPort.save(joueurDtoIn)).thenReturn(joueurDtoOut);

        JoueurDtoOut result = joueurUseCase.creerJoueur(joueurDtoIn);

        assertThat(result).isEqualTo(joueurDtoOut);
        assertThat(result.getPseudo()).isEqualTo("Alice");
        verify(joueurPort).existsByEmail("alice@example.com");
        verify(joueurPort).save(joueurDtoIn);
    }

    @Test
    void creerJoueur_emailDejaExistant_devraitLancerRuntimeException() {
        when(joueurPort.existsByEmail("alice@example.com")).thenReturn(true);

        assertThatThrownBy(() -> joueurUseCase.creerJoueur(joueurDtoIn))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("alice@example.com");

        verify(joueurPort, never()).save(any());
    }

    @Test
    void redigerAvis_joueurEtJeuExistants_devraitSauvegarderLAvis() {
        when(joueurPort.findById(1L)).thenReturn(Optional.of(joueurDtoOut));
        when(jeuPort.findById(1L)).thenReturn(Optional.of(jeuDtoOut));
        when(avisPort.save(avisDtoIn)).thenReturn(avisDtoOut);

        AvisDtoOut result = joueurUseCase.redigerAvis(avisDtoIn);

        assertThat(result).isEqualTo(avisDtoOut);
        assertThat(result.getDescription()).isEqualTo("Super jeu !");
        verify(avisPort).save(avisDtoIn);
    }

    @Test
    void redigerAvis_joueurInexistant_devraitLancerRuntimeException() {
        when(joueurPort.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> joueurUseCase.redigerAvis(avisDtoIn))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("1");

        verify(avisPort, never()).save(any());
    }

    @Test
    void redigerAvis_jeuInexistant_devraitLancerRuntimeException() {
        when(joueurPort.findById(1L)).thenReturn(Optional.of(joueurDtoOut));
        when(jeuPort.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> joueurUseCase.redigerAvis(avisDtoIn))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("1");

        verify(avisPort, never()).save(any());
    }

    @Test
    void modifierAvis_avisExistant_devraitModifierLAvis() {
        AvisDtoIn avisModifie = new AvisDtoIn(1L, "Modifié", LocalDateTime.now(), 7.0f, 1L, 1L, null);
        AvisDtoOut avisModifieOut = new AvisDtoOut(1L, "Modifié", LocalDateTime.now(), 7.0f, 1L, 1L, null);

        when(avisPort.findById(1L)).thenReturn(Optional.of(avisDtoOut));
        when(avisPort.save(avisModifie)).thenReturn(avisModifieOut);

        AvisDtoOut result = joueurUseCase.modifierAvis(avisModifie);

        assertThat(result.getDescription()).isEqualTo("Modifié");
        verify(avisPort).save(avisModifie);
    }

    @Test
    void modifierAvis_avisInexistant_devraitLancerRuntimeException() {
        AvisDtoIn avisInexistant = new AvisDtoIn(99L, "Modifié", LocalDateTime.now(), 7.0f, 1L, 1L, null);
        when(avisPort.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> joueurUseCase.modifierAvis(avisInexistant))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");

        verify(avisPort, never()).save(any());
    }
}
