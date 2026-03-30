package fr.esgi.avis.repository;

import fr.esgi.avis.entity.ClassificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassificationEntityRepository extends JpaRepository<ClassificationEntity, Long> {
}