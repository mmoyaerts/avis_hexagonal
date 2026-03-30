package fr.esgi.avis.business;

import lombok.Data;

import java.util.List;

@Data
public class Genre {
    private Long id;

    private String nom;

    private List<Jeu> jeux;
}
