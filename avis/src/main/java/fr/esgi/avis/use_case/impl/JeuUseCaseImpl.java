package fr.esgi.avis.use_case.impl;

import fr.esgi.avis.business.Jeu;
import fr.esgi.avis.dto.AvisDtoOut;
import fr.esgi.avis.dto.JeuDtoIn;
import fr.esgi.avis.dto.JeuDtoOut;
import fr.esgi.avis.mapper.AvisMapper;
import fr.esgi.avis.mapper.JeuMapper;
import fr.esgi.avis.repository.AvisEntityRepository;
import fr.esgi.avis.repository.JeuEntityRepository;
import fr.esgi.avis.use_case.JeuUseCase;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class JeuUseCaseImpl implements JeuUseCase {
    private JeuEntityRepository jeuEntityRepository;
    private AvisEntityRepository avisEntityRepository;
    private JeuMapper jeuMapper;
    private AvisMapper avisMapper;

    public JeuUseCaseImpl(JeuEntityRepository jeuEntityRepository, AvisEntityRepository avisEntityRepository, JeuMapper jeuMapper, AvisMapper avisMapper) {
        this.jeuEntityRepository = jeuEntityRepository;
        this.avisEntityRepository = avisEntityRepository;
        this.jeuMapper = jeuMapper;
        this.avisMapper = avisMapper;
    }

    @Override
    public List<JeuDtoOut> recupererJeux() {
        return jeuEntityRepository.findAll()
                .stream()
                .map(JeuMapper::toJeuDtoOut)
                .toList();
    }

    @Override
    public JeuDtoOut recupererJeu(Long id) {
        return jeuEntityRepository.findById(id)
                .map(jeuMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Jeu non trouvé avec l'id : " + id));
    }

    @Override
    public List<JeuDtoOut> recupererParNom(String nom) {
        return jeuEntityRepository.findByNomContainingIgnoreCase(nom)
                .stream()
                .map(jeuMapper::toDto)
                .toList();
    }

    @Override
    public List<AvisDtoOut> recupererAvisPourUnJeu(JeuDtoIn jeu) {
        Jeu jeuMapper = jeuMapper.toEntity(jeu);
        return avisEntityRepository.findByJeu(jeuMapper)
                .stream()
                .map(avisMapper::toDto)
                .toList();
    }
}
