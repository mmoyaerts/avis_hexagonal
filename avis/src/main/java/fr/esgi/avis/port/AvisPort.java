package fr.esgi.avis.port;

import fr.esgi.avis.business.Avis;
import fr.esgi.avis.business.Jeu;
import fr.esgi.avis.dto.*;

import java.util.List;
import java.util.Optional;

public interface AvisPort {
    Optional<AvisDtoOut> findById(Long id);
    List<AvisDtoOut> findByJeu(JeuDtoIn jeuDtoIn);
    AvisDtoOut save(AvisDtoIn avisDtoIn);
    List<AvisDtoOut> findByUser(UtilisateurDtoIn  userDtoIn);
    void deleteById(Long id);
}