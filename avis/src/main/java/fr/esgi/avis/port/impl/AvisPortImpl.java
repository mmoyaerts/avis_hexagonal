package fr.esgi.avis.port.impl;

import fr.esgi.avis.business.Avis;
import fr.esgi.avis.dto.AvisDtoIn;
import fr.esgi.avis.dto.AvisDtoOut;
import fr.esgi.avis.dto.JeuDtoIn;
import fr.esgi.avis.dto.UtilisateurDtoIn;
import fr.esgi.avis.mapper.AvisMapper;
import fr.esgi.avis.mapper.JeuMapper;
import fr.esgi.avis.mapper.UtilisateurMapper;
import fr.esgi.avis.port.AvisPort;
import fr.esgi.avis.repository.AvisEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AvisPortImpl implements AvisPort {

    private final AvisEntityRepository avisEntityRepository;

    public AvisPortImpl(AvisEntityRepository avisEntityRepository) {
        this.avisEntityRepository = avisEntityRepository;
    }

    @Override
    public Optional<AvisDtoOut> findById(Long id) {
        return avisEntityRepository.findById(id)
                .map(AvisMapper::toAvisDtoOut);
    }

    @Override
    public List<AvisDtoOut> findByJeu(JeuDtoIn jeuDtoIn) {
        return avisEntityRepository.findByJeu(JeuMapper.toEntity(jeuDtoIn))
                .stream()
                .map(AvisMapper::toAvisDtoOut)
                .toList();
    }

    public List<AvisDtoOut> findByUser(UtilisateurDtoIn userDtoIn) {
        return avisEntityRepository.findByJoueurId(UtilisateurMapper.toEntity(userDtoIn).getId())
                .stream()
                .map(AvisMapper::toAvisDtoOut)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        avisEntityRepository.deleteById(id);
    }


    @Override
    public AvisDtoOut save(AvisDtoIn avisDtoIn) {
        return AvisMapper.toAvisDtoOut(
                avisEntityRepository.save(
                        AvisMapper.toAvisEntity(avisDtoIn)
                )
        );
    }
}