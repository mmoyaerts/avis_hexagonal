// JoueurUseCaseImpl.java
package fr.esgi.avis.use_case.impl;

import fr.esgi.avis.dto.AvisDtoIn;
import fr.esgi.avis.dto.AvisDtoOut;
import fr.esgi.avis.dto.JoueurDtoIn;
import fr.esgi.avis.dto.JoueurDtoOut;
import fr.esgi.avis.entity.*;
import fr.esgi.avis.mapper.JoueurMapper;
import fr.esgi.avis.repository.*;
import fr.esgi.avis.use_case.JoueurUseCase;
import org.springframework.stereotype.Service;

@Service
public class JoueurUseCaseImpl implements JoueurUseCase {

    private final JoueurEntityRepository joueurRepository;
    private final AvisEntityRepository avisRepository;
    private final JeuEntityRepository jeuRepository;

    public JoueurUseCaseImpl(JoueurEntityRepository joueurRepository,
                             AvisEntityRepository avisRepository,
                             JeuEntityRepository jeuRepository) {
        this.joueurRepository = joueurRepository;
        this.avisRepository = avisRepository;
        this.jeuRepository = jeuRepository;
    }

    @Override
    public JoueurDtoOut creerJoueur(JoueurDtoIn joueurDtoIn) {
        if (joueurRepository.existsByEmail((joueurDtoIn.getEmail()))) {
            throw new RuntimeException("Un joueur avec cet email existe déjà : " + joueurDtoIn.email());
        }

        JoueurEntity joueurEntity = JoueurMapper.toJoueur(joueurDtoIn);
        JoueurEntity saved = joueurRepository.save(joueurEntity);
        return JoueurMapper.toJoueurDtoOut(saved);
    }

    @Override
    public AvisDtoOut redigerAvis(AvisDtoIn avisDtoIn) {
        JoueurEntity joueur = joueurRepository.findById(avisDtoIn.getJoueurId())
                .orElseThrow(() -> new RuntimeException("Joueur introuvable : " + avisDtoIn.joueurId()));

        JeuEntity jeu = jeuRepository.findById(avisDtoIn.getJeuId())
                .orElseThrow(() -> new RuntimeException("Jeu introuvable : " + avisDtoIn.jeuId()));

        AvisEntity avis = new AvisEntity();
        avis.setDescription(avisDtoIn.getDescription());
        avis.setNote(avisDtoIn.getNote());
        avis.setJoueur(joueur);
        avis.setJeu(jeu);

        AvisEntity saved = avisRepository.save(avis);
        return avisMapper.toDto(saved);
    }

    @Override
    public AvisDtoOut modifierAvis(AvisDtoIn avisDtoIn) {
        AvisEntity avis = avisRepository.findById(avisDtoIn.getId())
                .orElseThrow(() -> new RuntimeException("Avis introuvable : " + avisDtoIn.id()));

        avis.setDescription(avisDtoIn.getDescription());
        avis.setNote(avisDtoIn.getNote());

        AvisEntity saved = avisRepository.save(avis);
        return avisMapper.toDto(saved);
    }
}