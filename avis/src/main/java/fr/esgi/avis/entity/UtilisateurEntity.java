package fr.esgi.avis.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
public abstract class UtilisateurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String pseudo;

    protected String email;

    protected String motDePasse;

    @OneToOne
    private AvatarEntity avatar;

    @OneToMany(mappedBy = "joueur")
    private List<AvisEntity> avis;
}