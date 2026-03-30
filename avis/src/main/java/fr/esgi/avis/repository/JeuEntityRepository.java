package fr.esgi.avis.repository;

import fr.esgi.avis.entity.JeuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JeuEntityRepository extends JpaRepository<JeuEntity, Long> {
}