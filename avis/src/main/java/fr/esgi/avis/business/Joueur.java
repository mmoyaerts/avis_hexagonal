package fr.esgi.avis.business;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Joueur extends Utilisateur{
    private LocalDate dateDeNaissance;

    private Avatar avatar;

    private List<Avis> avis;
}
