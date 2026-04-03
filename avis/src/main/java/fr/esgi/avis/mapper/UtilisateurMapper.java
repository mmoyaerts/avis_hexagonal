package fr.esgi.avis.mapper;

import fr.esgi.avis.business.Utilisateur;
import fr.esgi.avis.dto.UtilisateurDtoIn;
import fr.esgi.avis.dto.UtilisateurDtoOut;
import fr.esgi.avis.entity.AvisEntity;
import fr.esgi.avis.entity.UtilisateurEntity;

import java.util.Collections;
import java.util.List;

public class UtilisateurMapper {

    public static UtilisateurEntity toEntity(UtilisateurDtoIn dto) {
        if (dto == null) return null;

        UtilisateurEntity user = new UtilisateurEntity() {};
        user.setId(dto.getId());

        return user;
    }

    public static UtilisateurDtoOut toDtoOut(UtilisateurEntity user) {
        if (user == null) return null;

        return new UtilisateurDtoOut(
                user.getId(),
                user.getPseudo(),
                user.getEmail(),
                user.getMotDePasse(),
                user.getAvatar().getId(),
                user.getAvis()
                        .stream()
                        .map(AvisEntity::getId)
                        .toList());
    }

    public static UtilisateurDtoIn toDtoIn(UtilisateurDtoOut user) {
        if (user == null) return null;
        return new UtilisateurDtoIn(
                user.getId(),
                user.getPseudo(),
                user.getEmail(),
                user.getMotDePasse(),
                user.getAvatarId(),
                user.getAviIds()
        );

    }
}