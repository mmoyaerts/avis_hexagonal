package fr.esgi.avis.controller.rest;

import fr.esgi.avis.dto.*;
import fr.esgi.avis.entity.AvisEntity;
import fr.esgi.avis.entity.UtilisateurEntity;
import fr.esgi.avis.mapper.UtilisateurMapper;
import fr.esgi.avis.repository.UtilisateurEntityRepository;
import fr.esgi.avis.use_case.UtilisateurUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@Tag(name = "Utilisateur", description = "Action pour un utilisateur")
public class UtilisateurControllerRest {

    private final UtilisateurUseCase utilisateurUseCase;

    public UtilisateurControllerRest(UtilisateurUseCase utilisateurUseCase) {
        this.utilisateurUseCase = utilisateurUseCase;
    }

    // 🔐 LOGIN
    @PostMapping("/login")
    @Operation(summary = "Connexion")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UtilisateurDtoOut> login(@RequestBody LoginDtoIn loginDtoIn) {
        return ResponseEntity.ok(
                utilisateurUseCase.recupererUtilisateur(loginDtoIn.email(), loginDtoIn.motDePasse())
        );
    }

    // 🎭 CHOISIR AVATAR
    @PostMapping("/{id}/avatar")
    @Operation(summary = "Choisir son avatar")
    public ResponseEntity<AvatarDtoOut> choisirAvatar(
            @PathVariable Long id,
            @RequestBody AvatarDtoIn avatarDto
    ) {
       UtilisateurDtoIn utilisateurDtoIn = UtilisateurMapper.toDtoIn(utilisateurUseCase.recupererUtilisateurbyId(id));
        return ResponseEntity.ok(
                utilisateurUseCase.choisirAvatar(utilisateurDtoIn,avatarDto)
        );
    }

    // 📝 RECUPERER AVIS UTILISATEUR
    @GetMapping("/{id}/avis")
    @Operation(summary = "Récupérer ses avis")
    public ResponseEntity<List<AvisDtoOut>> getAvis(@PathVariable Long id) {
        UtilisateurDtoIn utilisateurDtoIn = UtilisateurMapper.toDtoIn(utilisateurUseCase.recupererUtilisateurbyId(id));


        return ResponseEntity.ok(
               utilisateurUseCase.recupererAvisParUtilisateur(
                        utilisateurDtoIn
                )
        );
    }
}