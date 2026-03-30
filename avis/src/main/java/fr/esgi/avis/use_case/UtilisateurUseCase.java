package fr.esgi.avis.use_case;

import fr.esgi.avis.business.Avatar;
import fr.esgi.avis.business.Avis;
import fr.esgi.avis.business.Utilisateur;

import java.util.List;

public interface UtilisateurUseCase {
    Utilisateur recupererUtilisateur(String email, String motDePasse);

    Avatar choisirAvatar(Utilisateur utilisateur, Avatar avatar);

    List<Avis> recupererAvisParUtilisateur(Utilisateur utilisateur);
}
