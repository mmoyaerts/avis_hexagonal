package fr.esgi.avis.business;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
public class Jeu {
    private Long id;

    private String nom;

    private LocalDate dateDeSortie;

    private String description;

    private Editeur editeur;

    private List<Plateforme> plateformes;

    private Genre genre;

    private String image;

    private float prix;

    private Classification classification;
}
