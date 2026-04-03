package fr.esgi.avis.mapper;

import fr.esgi.avis.business.Avatar;
import fr.esgi.avis.dto.AvatarDtoIn;
import fr.esgi.avis.dto.AvatarDtoOut;
import fr.esgi.avis.entity.AvatarEntity;

public class AvatarMapper {

    public static AvatarEntity toEntity(AvatarDtoIn dto) {
        if (dto == null) return null;

        AvatarEntity avatar = new AvatarEntity();
        avatar.setId(dto.getId());
        avatar.setNom(dto.getNom());

        return avatar;
    }

    public static AvatarDtoOut toDtoOut(AvatarEntity avatar) {
        if (avatar == null) return null;

        return new AvatarDtoOut(
                avatar.getId(),
                avatar.getNom(),
                avatar.getUtilisateur().getId());
    }
}