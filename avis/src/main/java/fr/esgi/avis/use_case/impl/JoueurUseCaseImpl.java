package fr.esgi.avis.use_case.impl;

import fr.esgi.avis.business.Avis;
import fr.esgi.avis.business.Jeu;
import fr.esgi.avis.business.Joueur;
import fr.esgi.avis.dto.*;
import fr.esgi.avis.mapper.AvisMapper;
import fr.esgi.avis.mapper.JoueurMapper;
import fr.esgi.avis.port.AvisPort;
import fr.esgi.avis.port.JeuPort;
import fr.esgi.avis.port.JoueurPort;
import fr.esgi.avis.use_case.JoueurUseCase;
import org.springframework.stereotype.Service;

public class JoueurUseCaseImpl implements JoueurUseCase {

    private final JoueurPort joueurPort; // ✅ plus de repository
    private final AvisPort avisPort;     // ✅ plus de repository
    private final JeuPort jeuPort;       // ✅ plus de repository

    public JoueurUseCaseImpl(JoueurPort joueurPort, AvisPort avisPort, JeuPort jeuPort) {
        this.joueurPort = joueurPort;
        this.avisPort = avisPort;
        this.jeuPort = jeuPort;
    }

    @Override
    public JoueurDtoOut creerJoueur(JoueurDtoIn joueurDtoIn) {
        if (joueurPort.existsByEmail(joueurDtoIn.getEmail())) {
            throw new RuntimeException("Un joueur avec cet email existe déjà : " + joueurDtoIn.getEmail());
        }

        return joueurPort.save(joueurDtoIn);
    }

    @Override
    public AvisDtoOut redigerAvis(AvisDtoIn avisDtoIn) {
        JoueurDtoOut joueurDtoOut = joueurPort.findById(avisDtoIn.getJoueurId())
                .orElseThrow(() -> new RuntimeException("Joueur introuvable : " + avisDtoIn.getJoueurId()));

        JeuDtoOut jeuDtoOut = jeuPort.findById(avisDtoIn.getJeuId())
                .orElseThrow(() -> new RuntimeException("Jeu introuvable : " + avisDtoIn.getJeuId()));

        return avisPort.save(avisDtoIn);
    }

    @Override
    public AvisDtoOut modifierAvis(AvisDtoIn avisDtoIn) {
        AvisDtoOut avisExiste = avisPort.findById(avisDtoIn.getId())
                .orElseThrow(() -> new RuntimeException("Avis introuvable : " + avisDtoIn.getId()));

        return avisPort.save(avisDtoIn);
    }
}