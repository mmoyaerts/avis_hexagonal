package fr.esgi.avis.repository;

import fr.esgi.avis.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreEntityRepository extends JpaRepository<GenreEntity, Long> {
}