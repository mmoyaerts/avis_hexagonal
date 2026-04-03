package fr.esgi.avis.port.impl;

import fr.esgi.avis.business.Joueur;
import fr.esgi.avis.dto.JoueurDtoIn;
import fr.esgi.avis.dto.JoueurDtoOut;
import fr.esgi.avis.mapper.JoueurMapper;
import fr.esgi.avis.port.JoueurPort;
import fr.esgi.avis.repository.JoueurEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JoueurPortImpl implements JoueurPort {

    private final JoueurEntityRepository joueurEntityRepository;

    public JoueurPortImpl(JoueurEntityRepository joueurEntityRepository) {
        this.joueurEntityRepository = joueurEntityRepository;
    }

    @Override
    public boolean existsByEmail(String email) {
        return joueurEntityRepository.existsByEmail(email);
    }

    @Override
    public JoueurDtoOut save(JoueurDtoIn joueurDtoIn) {
        return JoueurMapper.toDtoOut(
                joueurEntityRepository.save(
                        JoueurMapper.toEntity(joueurDtoIn)
                )
        );
    }

    @Override
    public Optional<JoueurDtoOut> findById(Long id) {
        return joueurEntityRepository.findById(id)
                .map(JoueurMapper::toDtoOut);
    }
}