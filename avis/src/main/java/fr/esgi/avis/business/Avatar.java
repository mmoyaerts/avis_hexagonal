package fr.esgi.avis.business;

import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Avatar {
    private Long id;

    private String nom;

    private Utilisateur utilisateur;
}
