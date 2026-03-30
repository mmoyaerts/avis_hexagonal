package fr.esgi.avis.business;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Classification {
    private Long id;

    private String couleurRGB;

    private String nom;

    private List<Jeu> jeux;
}
