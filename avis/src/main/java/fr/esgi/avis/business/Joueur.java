package fr.esgi.avis.business;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Joueur extends Utilisateur{
    private LocalDate dateDeNaissance;

    private Avatar avatar;

    private List<Avis> avis;
}
