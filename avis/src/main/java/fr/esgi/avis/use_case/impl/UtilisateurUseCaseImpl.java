package fr.esgi.avis.use_case.impl;

import fr.esgi.avis.business.Avatar;
import fr.esgi.avis.business.Utilisateur;
import fr.esgi.avis.dto.*;
import fr.esgi.avis.mapper.AvatarMapper;
import fr.esgi.avis.mapper.AvisMapper;
import fr.esgi.avis.mapper.UtilisateurMapper;
import fr.esgi.avis.port.AvatarPort;
import fr.esgi.avis.port.AvisPort;
import fr.esgi.avis.port.UtilisateurPort;
import fr.esgi.avis.use_case.UtilisateurUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurUseCaseImpl implements UtilisateurUseCase {

    private final UtilisateurPort utilisateurPort;
    private final AvatarPort avatarPort;
    private final AvisPort avisPort;

    public UtilisateurUseCaseImpl(UtilisateurPort utilisateurPort, AvatarPort avatarPort, AvisPort avisPort) {
        this.utilisateurPort = utilisateurPort;
        this.avatarPort = avatarPort;
        this.avisPort = avisPort;
    }

    @Override
    public UtilisateurDtoOut recupererUtilisateur(String email, String motDePasse) {
        return utilisateurPort.findByEmailAndMotDePasse(email, motDePasse)
                .orElseThrow(() -> new RuntimeException("Email ou mot de passe incorrect" + email + motDePasse));
    }

    @Override
    public UtilisateurDtoOut recupererUtilisateurbyId(Long id) {
        return utilisateurPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Email ou mot de passe incorrect"));
    }

    @Override
    public AvatarDtoOut choisirAvatar(UtilisateurDtoIn utilisateurDtoIn, AvatarDtoIn avatarDtoIn) {
        UtilisateurDtoOut utilisateurDtoOut = utilisateurPort.findById(utilisateurDtoIn.getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable : " + utilisateurDtoIn.getId()));

        AvatarDtoOut avatarDtoOut = avatarPort.findById(avatarDtoIn.getId())
                .orElseThrow(() -> new RuntimeException("Avatar introuvable : " + avatarDtoIn.getId()));

        UtilisateurDtoIn dto = new UtilisateurDtoIn(
                utilisateurDtoOut.getId(),
                utilisateurDtoOut.getPseudo(),
                utilisateurDtoOut.getEmail(),
                utilisateurDtoOut.getMotDePasse(),
                avatarDtoOut.getId(),
                utilisateurDtoOut.getAviIds()
        );
        utilisateurPort.save(dto);

        return avatarDtoOut;
    }

    @Override
    public List<AvisDtoOut> recupererAvisParUtilisateur(UtilisateurDtoIn utilisateurDtoIn) {
        return avisPort.findByUser(utilisateurDtoIn)
                .stream()
                .toList();
    }
}