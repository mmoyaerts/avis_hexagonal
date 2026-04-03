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
import fr.esgi.avis.port.AvisPort;
import fr.esgi.avis.port.EditeurPort;
import fr.esgi.avis.port.JeuPort;
import fr.esgi.avis.use_case.ModerateurUseCase;

public class ModerateurUseCaseImpl implements ModerateurUseCase {

    private final AvisPort avisPort;           // ✅ plus de repository
    private final JeuPort jeuPort;             // ✅ plus de repository
    private final EditeurPort editeurPort;     // ✅ plus de repository

    public ModerateurUseCaseImpl(
            AvisPort avisPort,
            JeuPort jeuPort,
            EditeurPort editeurPort
    ) {
        this.avisPort = avisPort;
        this.jeuPort = jeuPort;
        this.editeurPort = editeurPort;
    }

    @Override
    public AvisDtoOut modererAvis(AvisDtoIn avisDtoIn, ModerateurDtoIn moderateurDtoIn) {
        AvisDtoIn avisDto = new AvisDtoIn(
                avisDtoIn.getId(),
                avisDtoIn.getDescription(),
                avisDtoIn.getDateDeCreation(),
                avisDtoIn.getNote(),
                avisDtoIn.getJeuId(),
                avisDtoIn.getJoueurId(),
                moderateurDtoIn.getId()
        );
        return avisPort.save(avisDto);
    }

    @Override
    public JeuDtoOut ajouterJeu(JeuDtoIn jeuDtoIn) {
        return jeuPort.save(jeuDtoIn);
    }

    @Override
    public EditeurDtoOut creerEditeur(EditeurDtoIn editeurDtoIn) {
        return editeurPort.save(editeurDtoIn);
    }
}