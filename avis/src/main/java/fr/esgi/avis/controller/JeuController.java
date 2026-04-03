package fr.esgi.avis.controller;

import fr.esgi.avis.dto.AvisDtoOut;
import fr.esgi.avis.dto.JeuDtoIn;
import fr.esgi.avis.dto.JeuDtoOut;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JeuController {
    ResponseEntity<List<JeuDtoOut>> recupererJeux();
    ResponseEntity<JeuDtoOut> recupererJeu(Long id);
    ResponseEntity<List<JeuDtoOut>> recupererParNom(String nom);
    ResponseEntity<List<AvisDtoOut>> recupererAvisPourUnJeu(JeuDtoIn jeuDtoIn);
}