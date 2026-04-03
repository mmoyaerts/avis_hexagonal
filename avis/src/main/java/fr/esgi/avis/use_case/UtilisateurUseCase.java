package fr.esgi.avis.use_case;

import fr.esgi.avis.dto.*;

import java.util.List;

public interface UtilisateurUseCase {
    UtilisateurDtoOut recupererUtilisateur(String email, String motDePasse);

    AvatarDtoOut choisirAvatar(UtilisateurDtoIn utilisateur, AvatarDtoIn avatar);

    List<Long> recupererAvisParUtilisateur(UtilisateurDtoIn utilisateur);
}
