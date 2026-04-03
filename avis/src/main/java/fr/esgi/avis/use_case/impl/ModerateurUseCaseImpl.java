package fr.esgi.avis.use_case.impl;

import fr.esgi.avis.dto.*;
import fr.esgi.avis.port.AvisPort;
import fr.esgi.avis.port.EditeurPort;
import fr.esgi.avis.port.JeuPort;
import fr.esgi.avis.port.ModerateurPort;
import fr.esgi.avis.use_case.ModerateurUseCase;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ModerateurUseCaseImpl implements ModerateurUseCase {

    private final AvisPort avisPort;           // ✅ plus de repository
    private final JeuPort jeuPort;             // ✅ plus de repository
    private final EditeurPort editeurPort;     // ✅ plus de repository
    private final ModerateurPort moderateurPort;

    public ModerateurUseCaseImpl(
            AvisPort avisPort,
            JeuPort jeuPort,
            EditeurPort editeurPort,
            ModerateurPort moderateurPort
    ) {
        this.avisPort = avisPort;
        this.jeuPort = jeuPort;
        this.editeurPort = editeurPort;
        this.moderateurPort = moderateurPort;
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

    @Override
    public ModerateurDtoOut recupererModerateur(Long id) {
        return moderateurPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Moderateur non trouvé avec l'id : " + id));
    }
}