package fr.esgi.avis.business;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class Plateforme {
    private Long id;

    private String nom;

    private List<Jeu> jeux;
}
