package fr.esgi.avis.mapper;

import fr.esgi.avis.business.Avatar;
import fr.esgi.avis.dto.AvatarDtoIn;
import fr.esgi.avis.dto.AvatarDtoOut;

public class AvatarMapper {

    public static AvatarDtoOut toAvatarDtoOut(Avatar avatar) {
        return new AvatarDtoOut(
                avatar.getId(),
                avatar.getNom(),
                avatar.getUtilisateur() != null ? avatar.getUtilisateur().getId() : null
        );
    }

    public static Avatar toAvatar(AvatarDtoIn avatarDtoIn) {
        Avatar avatar = new Avatar();
        avatar.setId(avatarDtoIn.getId());
        avatar.setNom(avatarDtoIn.getNom());
        return avatar;
    }
}