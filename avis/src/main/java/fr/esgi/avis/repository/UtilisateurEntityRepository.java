package fr.esgi.avis.repository;

import fr.esgi.avis.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurEntityRepository extends JpaRepository<UtilisateurEntity, Long> {
}