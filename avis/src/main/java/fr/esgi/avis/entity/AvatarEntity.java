package fr.esgi.avis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AvatarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @OneToOne
    private UtilisateurEntity utilisateur;
}
