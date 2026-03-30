package fr.esgi.avis.use_case;

import fr.esgi.avis.DTO.*;
import fr.esgi.avis.business.Avatar;
import fr.esgi.avis.business.Avis;
import fr.esgi.avis.business.Utilisateur;

import java.util.List;

public interface UtilisateurUseCase {
    UtilisateurDtoOut recupererUtilisateur(String email, String motDePasse);

    AvatarDtoOut choisirAvatar(UtilisateurDtoIn utilisateur, AvatarDtoIn avatar);

    List<AvisDtoOut> recupererAvisParUtilisateur(UtilisateurDtoIn utilisateur);
}
