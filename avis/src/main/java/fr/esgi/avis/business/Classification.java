package fr.esgi.avis.business;

import lombok.Data;

import java.util.List;

@Data
public class Classification {
    private Long id;

    private String couleurRGB;

    private String nom;

    private List<Jeu> jeux;
}
