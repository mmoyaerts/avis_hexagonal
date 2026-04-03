package fr.esgi.avis.mapper;

import fr.esgi.avis.business.Joueur;
import fr.esgi.avis.dto.JoueurDtoIn;
import fr.esgi.avis.dto.JoueurDtoOut;
import fr.esgi.avis.entity.AvisEntity;
import fr.esgi.avis.entity.JoueurEntity;

import java.util.Collections;
import java.util.List;

public class JoueurMapper {

    public static JoueurEntity toEntity(JoueurDtoIn dto) {
        if (dto == null) return null;

        JoueurEntity joueur = new JoueurEntity();
        joueur.setId(dto.getId());
        joueur.setPseudo(dto.getPseudo());
        joueur.setEmail(dto.getEmail());
        joueur.setMotDePasse(dto.getMotDePasse());
        joueur.setDateDeNaissance(dto.getDateDeNaissance());

        return joueur;
    }

    public static JoueurDtoOut toDtoOut(JoueurEntity joueur) {
        if (joueur == null) return null;

        return new JoueurDtoOut(
                joueur.getDateDeNaissance(),
                joueur.getId(),
                joueur.getPseudo(),
                joueur.getEmail(),
                joueur.getMotDePasse(),
                joueur.getAvatar().getId(),
                joueur.getAvis()
                        .stream()
                        .map(AvisEntity::getId)
                        .toList()
        );
    }
}