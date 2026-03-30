package fr.esgi.avis.repository;

import fr.esgi.avis.entity.JoueurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoueurEntityRepository extends JpaRepository<JoueurEntity, Long> {
    boolean existsByEmail(String email);
}