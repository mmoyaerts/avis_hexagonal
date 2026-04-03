package fr.esgi.avis.port.impl;

import fr.esgi.avis.business.Editeur;
import fr.esgi.avis.dto.EditeurDtoIn;
import fr.esgi.avis.dto.EditeurDtoOut;
import fr.esgi.avis.mapper.EditeurMapper;
import fr.esgi.avis.port.EditeurPort;
import fr.esgi.avis.repository.EditeurEntityRepository;

import java.util.List;
import java.util.Optional;

public class EditeurPortImpl implements EditeurPort {

    private final EditeurEntityRepository editeurEntityRepository;

    public EditeurPortImpl(EditeurEntityRepository editeurEntityRepository) {
        this.editeurEntityRepository = editeurEntityRepository;
    }

    @Override
    public Optional<EditeurDtoOut> findById(Long id) {
        return editeurEntityRepository.findById(id)
                .map(EditeurMapper::toEditeurDtoOut);
    }

    @Override
    public List<EditeurDtoOut> findAll() {
        return editeurEntityRepository.findAll()
                .stream()
                .map(EditeurMapper::toEditeurDtoOut)
                .toList();
    }

    @Override
    public EditeurDtoOut save(EditeurDtoIn editeurDtoIn) {
        return EditeurMapper.toEditeurDtoOut(
                editeurEntityRepository.save(
                        EditeurMapper.toEditeurEntity(editeurDtoIn) // business → entity
                )
        );
    }

}