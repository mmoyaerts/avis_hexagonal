package fr.esgi.avis.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class AvisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private LocalDateTime dateDeCreation;

    private float note;

    @ManyToOne
    private JeuEntity jeu;

    @ManyToOne
    private JoueurEntity joueur;

    @ManyToOne
    private ModerateurEntity moderateur;
}