package fr.esgi.avis.mapper;

import fr.esgi.avis.business.Moderateur;
import fr.esgi.avis.dto.ModerateurDtoIn;

import java.util.Collections;
import java.util.List;

public class ModerateurMapper {

    public static Moderateur toModerateur(ModerateurDtoIn moderateurDtoIn) {
        Moderateur moderateur = new Moderateur();
        moderateur.setId(moderateurDtoIn.getId());
        moderateur.setPseudo(moderateurDtoIn.getPseudo());
        moderateur.setEmail(moderateurDtoIn.getEmail());
        moderateur.setMotDePasse(moderateurDtoIn.getMotDePasse());
        moderateur.setNumeroDeTelephone(moderateurDtoIn.getNumeroDeTelephone());
        return moderateur;
    }
}