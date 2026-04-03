package fr.esgi.avis.controller.rest;

import fr.esgi.avis.dto.*;
import fr.esgi.avis.entity.UtilisateurEntity;
import fr.esgi.avis.mapper.UtilisateurMapper;
import fr.esgi.avis.repository.UtilisateurEntityRepository;
import fr.esgi.avis.use_case.UtilisateurUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurControllerRest {

    private final UtilisateurUseCase utilisateurUseCase;

    public UtilisateurControllerRest(UtilisateurUseCase utilisateurUseCase) {
        this.utilisateurUseCase = utilisateurUseCase;
    }

    // 🔐 LOGIN
    @PostMapping("/login")
    public ResponseEntity<UtilisateurDtoOut> login(@RequestBody UtilisateurDtoIn user) {
        return ResponseEntity.ok(
                utilisateurUseCase.recupererUtilisateur(user.getEmail(),user.getMotDePasse())
        );
    }

    // 🎭 CHOISIR AVATAR
    @PostMapping("/{id}/avatar")
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
    public ResponseEntity<List<Long>> getAvis(@PathVariable Long id) {
        UtilisateurDtoIn utilisateurDtoIn = UtilisateurMapper.toDtoIn(utilisateurUseCase.recupererUtilisateurbyId(id));

        return ResponseEntity.ok(
                utilisateurUseCase.recupererAvisParUtilisateur(
                        utilisateurDtoIn
                )
        );
    }
}