package fr.esgi.avis.use_case;

import fr.esgi.avis.dto.*;
import fr.esgi.avis.port.AvisPort;
import fr.esgi.avis.port.EditeurPort;
import fr.esgi.avis.port.JeuPort;
import fr.esgi.avis.port.ModerateurPort;
import fr.esgi.avis.use_case.impl.ModerateurUseCaseImpl;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ModerateurUseCaseImplTest {

    @Mock
    private AvisPort avisPort;

    @Mock
    private JeuPort jeuPort;

    @Mock
    private EditeurPort editeurPort;

    @Mock
    private ModerateurPort moderateurPort;

    @InjectMocks
    private ModerateurUseCaseImpl moderateurUseCase;

    private ModerateurDtoIn moderateurDtoIn;
    private ModerateurDtoOut moderateurDtoOut;
    private AvisDtoIn avisDtoIn;
    private AvisDtoOut avisDtoOut;
    private JeuDtoIn jeuDtoIn;
    private JeuDtoOut jeuDtoOut;
    private EditeurDtoIn editeurDtoIn;
    private EditeurDtoOut editeurDtoOut;

    @BeforeEach
    void setUp() {
        moderateurDtoIn = new ModerateurDtoIn("0612345678", 1L, "ModoPro",
                "modo@example.com", "securePass", null, List.of());
        moderateurDtoOut = new ModerateurDtoOut("0612345678", 1L, "ModoPro",
                "modo@example.com", "securePass", null, List.of());
        avisDtoIn = new AvisDtoIn(1L, "Avis à modérer", LocalDateTime.now(), 6.0f, 1L, 1L, null);
        avisDtoOut = new AvisDtoOut(1L, "Avis à modérer", LocalDateTime.now(), 6.0f, 1L, 1L, 1L);
        jeuDtoIn = new JeuDtoIn(null, "Nouveau Jeu", LocalDate.now(), "Description", 1L,
                List.of(1L), 1L, "image.jpg", 39.99f, 1L, List.of());
        jeuDtoOut = new JeuDtoOut(10L, "Nouveau Jeu", LocalDate.now(), "Description", 1L,
                List.of(1L), 1L, "image.jpg", 39.99f, 1L, List.of());
        editeurDtoIn = new EditeurDtoIn(null, "Nintendo", "nintendo.png", List.of());
        editeurDtoOut = new EditeurDtoOut(5L, "Nintendo", "nintendo.png", List.of());
    }

    @Test
    void modererAvis_devraitAssocierModerateur() {
        when(avisPort.save(any(AvisDtoIn.class))).thenReturn(avisDtoOut);

        AvisDtoOut result = moderateurUseCase.modererAvis(avisDtoIn, moderateurDtoIn);

        assertThat(result).isEqualTo(avisDtoOut);
        assertThat(result.getModerateurId()).isEqualTo(1L);

        // Vérifie que l'id du modérateur est bien injecté dans l'avis sauvegardé
        verify(avisPort).save(argThat(dto -> dto.getModerateurId().equals(1L)));
    }

    @Test
    void ajouterJeu_devraitSauvegarderLeJeu() {
        when(jeuPort.save(jeuDtoIn)).thenReturn(jeuDtoOut);

        JeuDtoOut result = moderateurUseCase.ajouterJeu(jeuDtoIn);

        assertThat(result).isEqualTo(jeuDtoOut);
        assertThat(result.getNom()).isEqualTo("Nouveau Jeu");
        verify(jeuPort).save(jeuDtoIn);
    }

    @Test
    void creerEditeur_devraitSauvegarderLEditeur() {
        when(editeurPort.save(editeurDtoIn)).thenReturn(editeurDtoOut);

        EditeurDtoOut result = moderateurUseCase.creerEditeur(editeurDtoIn);

        assertThat(result).isEqualTo(editeurDtoOut);
        assertThat(result.getNom()).isEqualTo("Nintendo");
        verify(editeurPort).save(editeurDtoIn);
    }

    @Test
    void recupererModerateur_idExistant_devraitRetournerLeModo() {
        when(moderateurPort.findById(1L)).thenReturn(Optional.of(moderateurDtoOut));

        ModerateurDtoOut result = moderateurUseCase.recupererModerateur(1L);

        assertThat(result).isEqualTo(moderateurDtoOut);
        assertThat(result.getPseudo()).isEqualTo("ModoPro");
        verify(moderateurPort).findById(1L);
    }

    @Test
    void recupererModerateur_idInexistant_devraitLancerEntityNotFoundException() {
        when(moderateurPort.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> moderateurUseCase.recupererModerateur(99L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }
}
