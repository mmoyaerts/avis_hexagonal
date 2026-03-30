// EditeurUseCaseImpl.java
package fr.esgi.avis.use_case.impl;

import fr.esgi.avis.dto.EditeurDtoIn;
import fr.esgi.avis.dto.EditeurDtoOut;
import fr.esgi.avis.dto.JeuDtoOut;
import fr.esgi.avis.entity.Editeur;
import fr.esgi.avis.entity.EditeurEntity;
import fr.esgi.avis.mapper.EditeurMapper;
import fr.esgi.avis.repository.EditeurEntityRepository;
import fr.esgi.avis.repository.EditeurRepository;
import fr.esgi.avis.use_case.EditeurUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditeurUseCaseImpl implements EditeurUseCase {

    private final EditeurEntityRepository editeurRepository;

    public EditeurUseCaseImpl(EditeurEntityRepository editeurRepository) {
        this.editeurRepository = editeurRepository;
    }

    @Override
    public List<JeuDtoOut> recuperJeuxParEditeur(EditeurDtoIn editeurDtoIn) {
        EditeurEntity editeur = editeurRepository.findById(editeurDtoIn.getId())
                .orElseThrow(() -> new RuntimeException("Éditeur introuvable avec l'id : " + editeurDtoIn.id()));

        return editeur.getJeux()
                .stream()
                .map(EditeurMapper::toJeuDtoOut)
                .toList();
    }

    @Override
    public List<EditeurDtoOut> recuperEditeurs() {
        return editeurRepository.findAll()
                .stream()
                .map(EditeurMapper::toEditeurDtoOut)
                .toList();
    }

    @Override
    public EditeurDtoOut recupererEditeurById(Long id) {
        return editeurRepository.findById(id)
                .map(EditeurMapper::toEditeurDtoOut)
                .orElseThrow(() -> new RuntimeException("Éditeur introuvable avec l'id : " + id));
    }
}