package fr.esgi.avis.use_case;

import fr.esgi.avis.dto.*;

import java.util.List;

public interface UtilisateurUseCase {
    UtilisateurDtoOut recupererUtilisateur(String email, String motDePasse);

    UtilisateurDtoOut recupererUtilisateurbyId(Long id);

    AvatarDtoOut choisirAvatar(UtilisateurDtoIn utilisateur, AvatarDtoIn avatar);

    List<Long> recupererAvisParUtilisateur(UtilisateurDtoIn utilisateur);
}
