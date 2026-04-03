// UtilisateurUseCaseImpl.java
package fr.esgi.avis.use_case.impl;

import fr.esgi.avis.business.Utilisateur;
import fr.esgi.avis.dto.*;
import fr.esgi.avis.entity.Avatar;
import fr.esgi.avis.entity.AvatarEntity;
import fr.esgi.avis.entity.UtilisateurEntity;
import fr.esgi.avis.mapper.UtilisateurMapper;
import fr.esgi.avis.repository.AvatarEntityRepository;
import fr.esgi.avis.repository.AvatarEntityRepository;
import fr.esgi.avis.repository.UtilisateurEntityRepository;
import fr.esgi.avis.repository.UtilisateurEntityRepository;
import fr.esgi.avis.use_case.UtilisateurUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurUseCaseImpl implements UtilisateurUseCase {

    private final UtilisateurEntityRepository utilisateurRepository;
    private final AvatarEntityRepository avatarRepository;

    public UtilisateurUseCaseImpl(UtilisateurEntityRepository utilisateurRepository,
                                  AvatarEntityRepository avatarRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.avatarRepository = avatarRepository;
    }

    @Override
    public UtilisateurDtoOut recupererUtilisateur(String email, String motDePasse) {
        UtilisateurEntity utilisateur = utilisateurRepository
                .findByEmailAndMotDePasse(email, motDePasse)
                .orElseThrow(() -> new RuntimeException("Email ou mot de passe incorrect"));
        return utilisateurMapper.toDto(utilisateur);
    }

    @Override
    public AvatarDtoOut choisirAvatar(UtilisateurDtoIn utilisateurDtoIn, AvatarDtoIn avatarDtoIn) {
        UtilisateurEntity utilisateur = utilisateurRepository.findById(utilisateurDtoIn.id())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable : " + utilisateurDtoIn.id()));

        AvatarEntity avatar = avatarRepository.findById(avatarDtoIn.id())
                .orElseThrow(() -> new RuntimeException("Avatar introuvable : " + avatarDtoIn.id()));

        utilisateur.setAvatar(avatar);
        utilisateurRepository.save(utilisateur);

        return avatarMapper.toDto(avatar);
    }

    @Override
    public List<AvisDtoOut> recupererAvisParUtilisateur(UtilisateurDtoIn utilisateurDtoIn) {
        UtilisateurEntity utilisateur = utilisateurRepository.findById(utilisateurDtoIn.id())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable : " + utilisateurDtoIn.id()));

        return utilisateur.getAvis()
                .stream()
                .map(avisMapper::toDto)
                .toList();
    }
}