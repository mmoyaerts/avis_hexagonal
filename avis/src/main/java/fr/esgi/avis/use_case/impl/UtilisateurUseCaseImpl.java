// UtilisateurUseCaseImpl.java
package fr.esgi.avis.use_case.impl;

import fr.esgi.avis.business.Utilisateur;
import fr.esgi.avis.dto.*;
import fr.esgi.avis.entity.Avatar;
import fr.esgi.avis.entity.Utilisateur;
import fr.esgi.avis.mapper.UtilisateurMapper;
import fr.esgi.avis.repository.AvatarRepository;
import fr.esgi.avis.repository.UtilisateurRepository;
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
        Utilisateur utilisateur = utilisateurRepository
                .findByEmailAndMotDePasse(email, motDePasse)
                .orElseThrow(() -> new RuntimeException("Email ou mot de passe incorrect"));

        return UtilisateurMapper.toUtilisateurDtoOut(utilisateur);
    }

    @Override
    public AvatarDtoOut choisirAvatar(UtilisateurDtoIn utilisateurDtoIn, AvatarDtoIn avatarDtoIn) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurDtoIn.id())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable : " + utilisateurDtoIn.id()));

        Avatar avatar = avatarRepository.findById(avatarDtoIn.id())
                .orElseThrow(() -> new RuntimeException("Avatar introuvable : " + avatarDtoIn.id()));

        utilisateur.setAvatar(avatar);
        utilisateurRepository.save(utilisateur);

        return UtilisateurMapper.toAvatarDtoOut(avatar);
    }

    @Override
    public List<AvisDtoOut> recupererAvisParUtilisateur(UtilisateurDtoIn utilisateurDtoIn) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurDtoIn.id())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable : " + utilisateurDtoIn.id()));

        return utilisateur.getAvis()
                .stream()
                .map(UtilisateurMapper::toAvisDtoOut)
                .toList();
    }
}