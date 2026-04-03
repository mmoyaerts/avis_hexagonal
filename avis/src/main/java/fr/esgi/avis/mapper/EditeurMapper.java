package fr.esgi.avis.mapper;

import fr.esgi.avis.business.Editeur;
import fr.esgi.avis.dto.EditeurDtoIn;
import fr.esgi.avis.dto.EditeurDtoOut;
import fr.esgi.avis.entity.EditeurEntity;
import fr.esgi.avis.entity.JeuEntity;

import java.util.Collections;
import java.util.List;

public class EditeurMapper {

    public static EditeurEntity toEntity(EditeurDtoIn dto) {
        if (dto == null) return null;

        EditeurEntity editeur = new EditeurEntity();
        editeur.setId(dto.getId());
        editeur.setNom(dto.getNom());
        editeur.setLogo(dto.getLogo());

        return editeur;
    }

    public static EditeurDtoOut toDtoOut(EditeurEntity editeur) {
        if (editeur == null) return null;

        return new EditeurDtoOut(
                editeur.getId(),
                editeur.getNom(),
                editeur.getLogo(),
                editeur.getJeux()
                        .stream()
                        .map(JeuEntity::getId)
                        .toList()
        );
    }
}