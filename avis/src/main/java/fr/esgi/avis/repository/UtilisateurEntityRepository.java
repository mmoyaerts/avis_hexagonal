package fr.esgi.avis.repository;

import fr.esgi.avis.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurEntityRepository extends JpaRepository<UtilisateurEntity, Long> {
    Optional<UtilisateurEntity> findByEmailAndMotDePasse(String email, String motDePasse);
}