package fr.esgi.avis.controller.rest;

import fr.esgi.avis.dto.*;
import fr.esgi.avis.mapper.ModerateurMapper;
import fr.esgi.avis.use_case.ModerateurUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/moderateur")
@Tag(name = "Modérateur", description = "Actions réservées au modérateur")
public class ModerateurRestController{

    private final ModerateurUseCase moderateurUseCase;

    public ModerateurRestController(ModerateurUseCase moderateurUseCase) {
        this.moderateurUseCase = moderateurUseCase;
    }

    @PutMapping("/avis/{moderateurId}")
    public ResponseEntity<AvisDtoOut> modererAvis(
            @RequestBody AvisDtoIn avisDtoIn,
            @PathVariable Long moderateurId
    ) {
        ModerateurDtoOut moderateurDtoOut = moderateurUseCase.recupererModerateur(moderateurId);
        ModerateurDtoIn moderateurDtoIn = ModerateurMapper.toDtoIn(moderateurDtoOut);
        return ResponseEntity.ok(moderateurUseCase.modererAvis(avisDtoIn, moderateurDtoIn));
    }

    @PostMapping("/jeux")
    @Operation(summary = "Ajouter un nouveau jeu")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<JeuDtoOut> ajouterJeu(@RequestBody JeuDtoIn jeuDtoIn) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(moderateurUseCase.ajouterJeu(jeuDtoIn));
    }

    @PostMapping("/editeurs")
    @Operation(summary = "Créer un nouvel éditeur")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EditeurDtoOut> creerEditeur(@RequestBody EditeurDtoIn editeurDtoIn) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(moderateurUseCase.creerEditeur(editeurDtoIn));
    }
}