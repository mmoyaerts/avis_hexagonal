package fr.esgi.avis.entity;

import fr.esgi.avis.business.Classification;
import fr.esgi.avis.business.Editeur;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Entity
@Data
public class JeuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length=100)
    @NotBlank(message="Merci de préciser le nom du jeu")
    @Size(min=1, message="Le nom du jeu doit comporter au moins {min} caractères")
    private String nom;

    private LocalDate dateDeSortie;

    private String description;

    @ManyToOne // ManyJeuxToOneEditeur
    private EditeurEntity editeur;

    @ManyToMany
    private List<PlateformeEntity> plateformes;

    @ManyToOne
    private GenreEntity genre;

    private String image;

    private float prix;

    @ManyToOne
    private ClassificationEntity classification;

    @OneToMany
    private List<AvisEntity> avis;
}
