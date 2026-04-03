package fr.esgi.avis.port.impl;

import fr.esgi.avis.business.Jeu;
import fr.esgi.avis.dto.JeuDtoIn;
import fr.esgi.avis.dto.JeuDtoOut;
import fr.esgi.avis.mapper.JeuMapper;
import fr.esgi.avis.port.JeuPort;
import fr.esgi.avis.repository.JeuEntityRepository;

import java.util.List;
import java.util.Optional;

public class JeuPortImpl implements JeuPort {

    private final JeuEntityRepository jeuEntityRepository;

    public JeuPortImpl(JeuEntityRepository jeuEntityRepository) {
        this.jeuEntityRepository = jeuEntityRepository;
    }

    @Override
    public List<JeuDtoOut> findAll() {
        return jeuEntityRepository.findAll()
                .stream()
                .map(JeuMapper::toDtoOut) // entity → business
                .toList();
    }

    @Override
    public Optional<JeuDtoOut> findById(Long id) {
        return jeuEntityRepository.findById(id)
                .map(JeuMapper::toDtoOut);
    }

    @Override
    public List<JeuDtoOut> findByNomContainingIgnoreCase(String nom) {
        return jeuEntityRepository.findByNomContainingIgnoreCase(nom)
                .stream()
                .map(JeuMapper::toDtoOut)
                .toList();
    }

    @Override
    public JeuDtoOut save(JeuDtoIn jeuDtoIn) {
        return JeuMapper.toDtoOut(
                jeuEntityRepository.save(
                        JeuMapper.toEntity(jeuDtoIn)
                )
        );
    }

}