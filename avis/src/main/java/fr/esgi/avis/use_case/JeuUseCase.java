package fr.esgi.avis.use_case;

import fr.esgi.avis.DTO.AvisDtoOut;
import fr.esgi.avis.DTO.JeuDtoIn;
import fr.esgi.avis.DTO.JeuDtoOut;
import fr.esgi.avis.business.Avis;
import fr.esgi.avis.business.Jeu;

import java.util.List;

public interface JeuUseCase {
    List<JeuDtoOut> recupererJeux();

    JeuDtoOut recupererJeu(Long id);

    List<JeuDtoOut> recupererParNom(String nom);

    List<AvisDtoOut> recupererAvisPourUnJeu(JeuDtoIn jeu);
}
