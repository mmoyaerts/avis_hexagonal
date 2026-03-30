package fr.esgi.avis.use_case;

import fr.esgi.avis.dto.AvisDtoOut;
import fr.esgi.avis.dto.JeuDtoIn;
import fr.esgi.avis.dto.JeuDtoOut;

import java.util.List;

public interface JeuUseCase {
    List<JeuDtoOut> recupererJeux();

    JeuDtoOut recupererJeu(Long id);

    List<JeuDtoOut> recupererParNom(String nom);

    List<AvisDtoOut> recupererAvisPourUnJeu(JeuDtoIn jeu);
}
