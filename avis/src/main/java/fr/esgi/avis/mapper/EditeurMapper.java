package fr.esgi.avis.mapper;

import fr.esgi.avis.business.Editeur;
import fr.esgi.avis.dto.EditeurDtoIn;
import fr.esgi.avis.dto.EditeurDtoOut;

import java.util.Collections;
import java.util.List;

public class EditeurMapper {

    public static EditeurDtoOut toEditeurDtoOut(Editeur editeur) {
        List<Long> jeuxIds = editeur.getJeux() != null
                ? editeur.getJeux().stream().map(j -> j.getId()).toList()
                : Collections.emptyList();

        return new EditeurDtoOut(
                editeur.getId(),
                editeur.getNom(),
                editeur.getLogo(),
                jeuxIds
        );
    }

    public static Editeur toEditeur(EditeurDtoIn editeurDtoIn) {
        Editeur editeur = new Editeur();
        editeur.setId(editeurDtoIn.getId());
        editeur.setNom(editeurDtoIn.getNom());
        editeur.setLogo(editeurDtoIn.getLogo());
        return editeur;
    }
}