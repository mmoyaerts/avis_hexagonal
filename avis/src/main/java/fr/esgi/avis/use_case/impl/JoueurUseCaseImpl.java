// JoueurUseCaseImpl.java
package fr.esgi.avis.use_case.impl;

import fr.esgi.avis.dto.AvisDtoIn;
import fr.esgi.avis.dto.AvisDtoOut;
import fr.esgi.avis.dto.JoueurDtoIn;
import fr.esgi.avis.dto.JoueurDtoOut;
import fr.esgi.avis.entity.Avis;
import fr.esgi.avis.entity.Jeu;
import fr.esgi.avis.entity.Joueur;
import fr.esgi.avis.mapper.JoueurMapper;
import fr.esgi.avis.repository.AvisRepository;
import fr.esgi.avis.repository.JeuRepository;
import fr.esgi.avis.repository.JoueurRepository;
import fr.esgi.avis.use_case.JoueurUseCase;
import org.springframework.stereotype.Service;

@Service
public class JoueurUseCaseImpl implements JoueurUseCase {

    private final JoueurRepository joueurRepository;
    private final AvisRepository avisRepository;
    private final JeuRepository jeuRepository;

    public JoueurUseCaseImpl(JoueurRepository joueurRepository,
                             AvisRepository avisRepository,
                             JeuRepository jeuRepository) {
        this.joueurRepository = joueurRepository;
        this.avisRepository = avisRepository;
        this.jeuRepository = jeuRepository;
    }

    @Override
    public JoueurDtoOut creerJoueur(JoueurDtoIn joueurDtoIn) {
        if (joueurRepository.existsByEmail(joueurDtoIn.email())) {
            throw new RuntimeException("Un joueur avec cet email existe déjà : " + joueurDtoIn.email());
        }

        Joueur joueur = new Joueur();
        joueur.setPseudo(joueurDtoIn.pseudo());
        joueur.setEmail(joueurDtoIn.email());
        joueur.setMotDePasse(joueurDtoIn.motDePasse()); // 🔒 pense à encoder avec BCrypt en prod

        Joueur saved = joueurRepository.save(joueur);
        return JoueurMapper.toJoueurDtoOut(saved);
    }

    @Override
    public AvisDtoOut redigerAvis(AvisDtoIn avisDtoIn) {
        Joueur joueur = joueurRepository.findById(avisDtoIn.joueurId())
                .orElseThrow(() -> new RuntimeException("Joueur introuvable : " + avisDtoIn.joueurId()));

        Jeu jeu = jeuRepository.findById(avisDtoIn.jeuId())
                .orElseThrow(() -> new RuntimeException("Jeu introuvable : " + avisDtoIn.jeuId()));

        Avis avis = new Avis();
        avis.setContenu(avisDtoIn.contenu());
        avis.setNote(avisDtoIn.note());
        avis.setJoueur(joueur);
        avis.setJeu(jeu);

        Avis saved = avisRepository.save(avis);
        return JoueurMapper.toAvisDtoOut(saved);
    }

    @Override
    public AvisDtoOut modifierAvis(AvisDtoIn avisDtoIn) {
        Avis avis = avisRepository.findById(avisDtoIn.id())
                .orElseThrow(() -> new RuntimeException("Avis introuvable : " + avisDtoIn.id()));

        avis.setContenu(avisDtoIn.contenu());
        avis.setNote(avisDtoIn.note());

        Avis saved = avisRepository.save(avis);
        return JoueurMapper.toAvisDtoOut(saved);
    }
}