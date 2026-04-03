package fr.esgi.avis.controller.rest;

import fr.esgi.avis.dto.AvisDtoIn;
import fr.esgi.avis.dto.AvisDtoOut;
import fr.esgi.avis.dto.EditeurDtoIn;
import fr.esgi.avis.dto.EditeurDtoOut;
import fr.esgi.avis.use_case.AvisUseCase;
import fr.esgi.avis.use_case.JeuUseCase;
import fr.esgi.avis.use_case.JoueurUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/avis")
@Tag(name = "Avis", description = "Gestion des avis")
public class AvisRestController {

    private final AvisUseCase avisUseCase;
    private final JoueurUseCase joueurUseCase;

    public AvisRestController(AvisUseCase avisUseCase, JoueurUseCase joueurUseCase) {
        this.avisUseCase = avisUseCase;
        this.joueurUseCase = joueurUseCase;
    }

    @PostMapping("/{idUser}/{idJeu}")
    @Operation(summary = "Créer un avis pour un jeu")
    public ResponseEntity<AvisDtoOut> creerAvis(@RequestBody AvisDtoIn avisDtoIn, @PathVariable Long idUser, @PathVariable Long idJeu) {
        AvisDtoIn avis = new AvisDtoIn(
                avisDtoIn.getId(),
                avisDtoIn.getDescription(),
                avisDtoIn.getDateDeCreation(),
                avisDtoIn.getNote(),
                idJeu,
                idUser,
                avisDtoIn.getModerateurId()
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(joueurUseCase.redigerAvis(avis));
    }
}
