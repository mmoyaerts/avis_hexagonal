package fr.esgi.avis.port.impl;

import fr.esgi.avis.business.Utilisateur;
import fr.esgi.avis.dto.UtilisateurDtoIn;
import fr.esgi.avis.dto.UtilisateurDtoOut;
import fr.esgi.avis.mapper.UtilisateurMapper;
import fr.esgi.avis.port.UtilisateurPort;
import fr.esgi.avis.repository.UtilisateurEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UtilisateurPortImpl implements UtilisateurPort {

    private final UtilisateurEntityRepository utilisateurEntityRepository;

    public UtilisateurPortImpl(UtilisateurEntityRepository utilisateurEntityRepository) {
        this.utilisateurEntityRepository = utilisateurEntityRepository;
    }

    @Override
    public Optional<UtilisateurDtoOut> findByEmailAndMotDePasse(String email, String motDePasse) {
        return utilisateurEntityRepository.findByEmailAndMotDePasse(email, motDePasse)
                .map(UtilisateurMapper::toDtoOut); // entity → business
    }

    @Override
    public Optional<UtilisateurDtoOut> findById(Long id) {
        return utilisateurEntityRepository.findById(id)
                .map(UtilisateurMapper::toDtoOut);
    }

    @Override
    public UtilisateurDtoOut save(UtilisateurDtoIn utilisateurDtoIn) {
        return UtilisateurMapper.toDtoOut(
                utilisateurEntityRepository.save(
                        UtilisateurMapper.toEntity(utilisateurDtoIn) // business → entity
                )
        );
    }
}