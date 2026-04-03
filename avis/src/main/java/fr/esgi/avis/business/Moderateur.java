package fr.esgi.avis.business;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class Moderateur extends Utilisateur{
    private String numeroDeTelephone;
}
