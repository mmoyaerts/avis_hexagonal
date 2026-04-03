package fr.esgi.avis.mapper;

import fr.esgi.avis.business.Moderateur;
import fr.esgi.avis.dto.ModerateurDtoIn;
import fr.esgi.avis.dto.ModerateurDtoOut;
import fr.esgi.avis.entity.AvisEntity;
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

    public static ModerateurDtoOut toDtoOut(ModerateurEntity dto) {
        if (dto == null) return null;

        return new ModerateurDtoOut(
                dto.getNumeroDeTelephone(),
                dto.getId(),
                dto.getPseudo(),
                dto.getEmail(),
                dto.getMotDePasse(),
                dto.getAvatar().getId(),
                dto.getAvis()
                        .stream()
                        .map(AvisEntity::getId)
                        .toList()
        );
    }

    public static ModerateurDtoIn toDtoIn(ModerateurDtoOut dto) {
        if (dto == null) return null;

        return new ModerateurDtoIn(
                dto.getNumeroDeTelephone(),
                dto.getId(),
                dto.getPseudo(),
                dto.getEmail(),
                dto.getMotDePasse(),
                dto.getAvatarId(),
                dto.getAviIds()
        );
    }

}