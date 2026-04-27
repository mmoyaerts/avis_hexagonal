package fr.esgi.avis.use_case;

import fr.esgi.avis.dto.*;
import fr.esgi.avis.port.AvatarPort;
import fr.esgi.avis.port.AvisPort;
import fr.esgi.avis.port.UtilisateurPort;
import fr.esgi.avis.use_case.impl.UtilisateurUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UtilisateurUseCaseImplTest {

    @Mock
    private UtilisateurPort utilisateurPort;

    @Mock
    private AvatarPort avatarPort;

    @Mock
    private AvisPort avisPort;

    @InjectMocks
    private UtilisateurUseCaseImpl utilisateurUseCase;

    private UtilisateurDtoIn utilisateurDtoIn;
    private UtilisateurDtoOut utilisateurDtoOut;
    private AvatarDtoIn avatarDtoIn;
    private AvatarDtoOut avatarDtoOut;

    @BeforeEach
    void setUp() {
        utilisateurDtoIn = new UtilisateurDtoIn(1L, "Bob", "bob@example.com", "pass", null, List.of());
        utilisateurDtoOut = new UtilisateurDtoOut(1L, "Bob", "bob@example.com", "pass", null, List.of());
        avatarDtoIn = new AvatarDtoIn(10L, "Héros", 1L);
        avatarDtoOut = new AvatarDtoOut(10L, "Héros", 1L);
    }

    @Test
    void recupererUtilisateur_credentialsValides_devraitRetournerLUtilisateur() {
        when(utilisateurPort.findByEmailAndMotDePasse("bob@example.com", "pass"))
                .thenReturn(Optional.of(utilisateurDtoOut));

        UtilisateurDtoOut result = utilisateurUseCase.recupererUtilisateur("bob@example.com", "pass");

        assertThat(result).isEqualTo(utilisateurDtoOut);
        assertThat(result.getPseudo()).isEqualTo("Bob");
        verify(utilisateurPort).findByEmailAndMotDePasse("bob@example.com", "pass");
    }

    @Test
    void recupererUtilisateur_credentialsInvalides_devraitLancerRuntimeException() {
        when(utilisateurPort.findByEmailAndMotDePasse("bob@example.com", "mauvais"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> utilisateurUseCase.recupererUtilisateur("bob@example.com", "mauvais"))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void recupererUtilisateurbyId_idExistant_devraitRetournerLUtilisateur() {
        when(utilisateurPort.findById(1L)).thenReturn(Optional.of(utilisateurDtoOut));

        UtilisateurDtoOut result = utilisateurUseCase.recupererUtilisateurbyId(1L);

        assertThat(result).isEqualTo(utilisateurDtoOut);
        verify(utilisateurPort).findById(1L);
    }

    @Test
    void recupererUtilisateurbyId_idInexistant_devraitLancerRuntimeException() {
        when(utilisateurPort.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> utilisateurUseCase.recupererUtilisateurbyId(99L))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void choisirAvatar_utilisateurEtAvatarExistants_devraitRetournerLAvatar() {
        when(utilisateurPort.findById(1L)).thenReturn(Optional.of(utilisateurDtoOut));
        when(avatarPort.findById(10L)).thenReturn(Optional.of(avatarDtoOut));
        when(utilisateurPort.save(any(UtilisateurDtoIn.class))).thenReturn(utilisateurDtoOut);

        AvatarDtoOut result = utilisateurUseCase.choisirAvatar(utilisateurDtoIn, avatarDtoIn);

        assertThat(result).isEqualTo(avatarDtoOut);
        assertThat(result.getNom()).isEqualTo("Héros");
        verify(utilisateurPort).save(argThat(dto -> dto.getAvatarId().equals(10L)));
    }

    @Test
    void choisirAvatar_utilisateurInexistant_devraitLancerRuntimeException() {
        when(utilisateurPort.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> utilisateurUseCase.choisirAvatar(utilisateurDtoIn, avatarDtoIn))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("1");

        verify(utilisateurPort, never()).save(any());
    }

    @Test
    void choisirAvatar_avatarInexistant_devraitLancerRuntimeException() {
        when(utilisateurPort.findById(1L)).thenReturn(Optional.of(utilisateurDtoOut));
        when(avatarPort.findById(10L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> utilisateurUseCase.choisirAvatar(utilisateurDtoIn, avatarDtoIn))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("10");

        verify(utilisateurPort, never()).save(any());
    }

    @Test
    void recupererAvisParUtilisateur_devraitRetournerLesAvis() {
        AvisDtoOut avis1 = new AvisDtoOut(1L, "Super", LocalDateTime.now(), 9.0f, 1L, 1L, null);
        AvisDtoOut avis2 = new AvisDtoOut(2L, "Bien", LocalDateTime.now(), 7.0f, 2L, 1L, null);

        when(avisPort.findByUser(utilisateurDtoIn)).thenReturn(List.of(avis1, avis2));

        List<AvisDtoOut> result = utilisateurUseCase.recupererAvisParUtilisateur(utilisateurDtoIn);

        assertThat(result).hasSize(2);
        verify(avisPort).findByUser(utilisateurDtoIn);
    }

    @Test
    void recupererAvisParUtilisateur_aucunAvis_devraitRetournerListeVide() {
        when(avisPort.findByUser(utilisateurDtoIn)).thenReturn(List.of());

        List<AvisDtoOut> result = utilisateurUseCase.recupererAvisParUtilisateur(utilisateurDtoIn);

        assertThat(result).isEmpty();
    }
}
