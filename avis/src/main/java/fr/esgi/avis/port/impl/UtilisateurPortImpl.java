package fr.esgi.avis.port.impl;

import fr.esgi.avis.business.Utilisateur;
import fr.esgi.avis.dto.UtilisateurDtoIn;
import fr.esgi.avis.dto.UtilisateurDtoOut;
import fr.esgi.avis.mapper.UtilisateurMapper;
import fr.esgi.avis.port.UtilisateurPort;
import fr.esgi.avis.repository.UtilisateurEntityRepository;

import java.util.Optional;

public class UtilisateurPortImpl implements UtilisateurPort {

    private final UtilisateurEntityRepository utilisateurEntityRepository;

    public UtilisateurPortImpl(UtilisateurEntityRepository utilisateurEntityRepository) {
        this.utilisateurEntityRepository = utilisateurEntityRepository;
    }

    @Override
    public Optional<UtilisateurDtoOut> findByEmailAndMotDePasse(String email, String motDePasse) {
        return utilisateurEntityRepository.findByEmailAndMotDePasse(email, motDePasse)
                .map(UtilisateurMapper::toUtilisateurDtoOut); // entity → business
    }

    @Override
    public Optional<UtilisateurDtoOut> findById(Long id) {
        return utilisateurEntityRepository.findById(id)
                .map(UtilisateurMapper::toUtilisateurDtoOut);
    }

    @Override
    public UtilisateurDtoOut save(UtilisateurDtoIn utilisateurDtoIn) {
        return UtilisateurMapper.toUtilisateurDtoOut(
                utilisateurEntityRepository.save(
                        UtilisateurMapper.toUtilisateurEntity(utilisateurDtoIn) // business → entity
                )
        );
    }
}