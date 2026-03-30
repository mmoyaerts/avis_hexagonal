package fr.esgi.avis.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ClassificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String couleurRGB;

    private String nom;

    @OneToMany(mappedBy = "classification")
    private List<JeuEntity> jeux;
}