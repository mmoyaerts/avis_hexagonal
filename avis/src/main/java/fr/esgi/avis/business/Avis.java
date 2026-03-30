package fr.esgi.avis.business;

import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Data
public class Avis {
    private Long id;

    private String description;

    private LocalDateTime dateDeCreation;

    private float note;

    private Jeu jeu;

    private Joueur joueur;

    private Moderateur moderateur;
}
