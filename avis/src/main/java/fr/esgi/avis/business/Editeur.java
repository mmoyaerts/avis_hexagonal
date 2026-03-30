package fr.esgi.avis.business;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class Editeur {
    private Long id;

    private String nom;

    private String logo;

    private List<Jeu> jeux;
}
