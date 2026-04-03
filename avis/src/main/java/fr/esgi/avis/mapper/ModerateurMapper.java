package fr.esgi.avis.mapper;

import fr.esgi.avis.business.Moderateur;
import fr.esgi.avis.dto.ModerateurDtoIn;
import fr.esgi.avis.entity.ModerateurEntity;

import java.util.Collections;
import java.util.List;

public class ModerateurMapper {

    public static ModerateurEntity toEntity(ModerateurDtoIn dto) {
        if (dto == null) return null;

        ModerateurEntity moderateur = new ModerateurEntity();
        moderateur.setId(dto.getId());
        moderateur.setPseudo(dto.getPseudo());
        moderateur.setEmail(dto.getEmail());
        moderateur.setMotDePasse(dto.getMotDePasse());
        moderateur.setNumeroDeTelephone(dto.getNumeroDeTelephone());

        return moderateur;
    }

}