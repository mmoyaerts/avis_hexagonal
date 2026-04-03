package fr.esgi.avis.controller;

import fr.esgi.avis.dto.AvisDtoOut;
import fr.esgi.avis.dto.JeuDtoIn;
import fr.esgi.avis.dto.JeuDtoOut;
import fr.esgi.avis.use_case.JeuUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jeux")
@Tag(name = "Jeux", description = "Gestion des jeux vidéo")
public class JeuController {

    private final JeuUseCase jeuUseCase;

    public JeuController(JeuUseCase jeuUseCase) {
        this.jeuUseCase = jeuUseCase;
    }

    @GetMapping
    @Operation(summary = "Récupérer tous les jeux")
    public ResponseEntity<List<JeuDtoOut>> recupererJeux() {
        return ResponseEntity.ok(jeuUseCase.recupererJeux());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un jeu par son id")
    public ResponseEntity<JeuDtoOut> recupererJeu(@PathVariable Long id) {
        return ResponseEntity.ok(jeuUseCase.recupererJeu(id));
    }

    @GetMapping("/recherche")
    @Operation(summary = "Rechercher des jeux par nom")
    public ResponseEntity<List<JeuDtoOut>> recupererParNom(@RequestParam String nom) {
        return ResponseEntity.ok(jeuUseCase.recupererParNom(nom));
    }

    @PostMapping("/avis")
    @Operation(summary = "Récupérer les avis pour un jeu")
    public ResponseEntity<List<AvisDtoOut>> recupererAvisPourUnJeu(@RequestBody JeuDtoIn jeuDtoIn) {
        return ResponseEntity.ok(jeuUseCase.recupererAvisPourUnJeu(jeuDtoIn));
    }
}