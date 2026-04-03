package fr.esgi.avis.repository;

import fr.esgi.avis.business.Jeu;
import fr.esgi.avis.entity.AvisEntity;
import fr.esgi.avis.entity.JeuEntity;
import fr.esgi.avis.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvisEntityRepository extends JpaRepository<AvisEntity, Long> {
    List<AvisEntity> findByJeu(JeuEntity jeu);
    List<AvisEntity> findByJoueurId(Long id);

}