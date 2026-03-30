package fr.esgi.avis.use_case.impl;

import fr.esgi.avis.business.Avis;
import fr.esgi.avis.business.Editeur;
import fr.esgi.avis.business.Jeu;
import fr.esgi.avis.business.Moderateur;
import fr.esgi.avis.dto.*;
import fr.esgi.avis.mapper.AvisMapper;
import fr.esgi.avis.mapper.EditeurMapper;
import fr.esgi.avis.mapper.JeuMapper;
import fr.esgi.avis.mapper.ModerateurMapper;
import fr.esgi.avis.repository.AvisEntityRepository;
import fr.esgi.avis.repository.EditeurEntityRepository;
import fr.esgi.avis.repository.JeuEntityRepository;
import fr.esgi.avis.repository.ModerateurEntityRepository;
import fr.esgi.avis.use_case.ModerateurUseCase;
import jakarta.persistence.EntityNotFoundException;

public class ModerateurUseCaseImpl implements ModerateurUseCase {

    private final AvisEntityRepository avisEntityRepository;
    private final JeuEntityRepository jeuEntityRepository;
    private final EditeurEntityRepository editeurEntityRepository;
    private final ModerateurEntityRepository moderateurEntityRepository;
    private final AvisMapper avisMapper;
    private final JeuMapper jeuMapper;
    private final EditeurMapper editeurMapper;
    private final ModerateurMapper moderateurMapper;

    public ModerateurUseCaseImpl(
            AvisEntityRepository avisEntityRepository,
            JeuEntityRepository jeuEntityRepository,
            EditeurEntityRepository editeurEntityRepository,
            ModerateurEntityRepository moderateurEntityRepository,
            AvisMapper avisMapper,
            JeuMapper jeuMapper,
            EditeurMapper editeurMapper,
            ModerateurMapper moderateurMapper
    ) {
        this.avisEntityRepository = avisEntityRepository;
        this.jeuEntityRepository = jeuEntityRepository;
        this.editeurEntityRepository = editeurEntityRepository;
        this.moderateurEntityRepository = moderateurEntityRepository;
        this.avisMapper = avisMapper;
        this.jeuMapper = jeuMapper;
        this.editeurMapper = editeurMapper;
        this.moderateurMapper = moderateurMapper;
    }

    @Override
    public AvisDtoOut modererAvis(AvisDtoIn avisDtoIn, ModerateurDtoIn moderateurDtoIn) {
        Avis avis = avisMapper.toEntity(avisDtoIn);
        Moderateur moderateur = moderateurMapper.toEntity(moderateurDtoIn);
        avis.setModerateur(moderateur);
        return avisMapper.toDto(avisEntityRepository.save(avis));
    }

    @Override
    public JeuDtoOut ajouterJeu(JeuDtoIn jeuDtoIn) {
        Jeu jeu = jeuMapper.toEntity(jeuDtoIn);
        return jeuMapper.toDto(jeuEntityRepository.save(jeu));
    }

    @Override
    public EditeurDtoOut creerEditeur(EditeurDtoIn editeurDtoIn) {
        Editeur editeur = editeurMapper.toEntity(editeurDtoIn);
        return editeurMapper.toDto(editeurEntityRepository.save(editeur));
    }
}