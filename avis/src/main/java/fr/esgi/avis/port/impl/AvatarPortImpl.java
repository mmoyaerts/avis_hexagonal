package fr.esgi.avis.port.impl;

import fr.esgi.avis.business.Avatar;
import fr.esgi.avis.dto.AvatarDtoOut;
import fr.esgi.avis.mapper.AvatarMapper;
import fr.esgi.avis.port.AvatarPort;
import fr.esgi.avis.repository.AvatarEntityRepository;

import java.util.Optional;

public class AvatarPortImpl implements AvatarPort {

    private final AvatarEntityRepository avatarEntityRepository;

    public AvatarPortImpl(AvatarEntityRepository avatarEntityRepository) {
        this.avatarEntityRepository = avatarEntityRepository;
    }

    @Override
    public Optional<AvatarDtoOut> findById(Long id) {
        return avatarEntityRepository.findById(id)
                .map(AvatarMapper::toAvatarDtoOut); // entity → business
    }
}