package fr.esgi.avis.business;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public abstract class Utilisateur {

    protected Long id;

    protected String pseudo;

    protected String email;

    protected String motDePasse;

    private Avatar avatar;

    private List<Avis> avis;
}
