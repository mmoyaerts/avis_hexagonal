package fr.esgi.avis.use_case.impl;

import fr.esgi.avis.dto.EditeurDtoIn;
import fr.esgi.avis.dto.EditeurDtoOut;
import fr.esgi.avis.dto.JeuDtoOut;
import fr.esgi.avis.port.EditeurPort;
import fr.esgi.avis.use_case.EditeurUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditeurUseCaseImpl implements EditeurUseCase {

    private final EditeurPort editeurPort;

    public EditeurUseCaseImpl(EditeurPort editeurPort) {
        this.editeurPort = editeurPort;
    }

    @Override
    public List<Long> recuperJeuxParEditeur(EditeurDtoIn editeurDtoIn) {
        return editeurPort.findById(editeurDtoIn.getId())
                .orElseThrow(() -> new RuntimeException("Éditeur introuvable avec l'id : " + editeurDtoIn.getId()))
                .getJeuxIds()
                .stream()
                .toList();
    }

    @Override
    public List<EditeurDtoOut> recuperEditeurs() {
        return editeurPort.findAll()
                .stream()
                .toList();
    }

    @Override
    public EditeurDtoOut recupererEditeurById(Long id) {
        return editeurPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Éditeur introuvable avec l'id : " + id));
    }
}