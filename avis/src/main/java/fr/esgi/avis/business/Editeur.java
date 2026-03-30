package fr.esgi.avis.business;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
public class Editeur {
    private Long id;

    private String nom;

    private String logo;

    private List<Jeu> jeux;
}
