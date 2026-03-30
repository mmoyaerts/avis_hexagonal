package fr.esgi.avis.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ModerateurEntity extends UtilisateurEntity {
    private String numeroDeTelephone;
}