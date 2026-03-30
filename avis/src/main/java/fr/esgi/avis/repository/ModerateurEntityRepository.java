package fr.esgi.avis.repository;

import fr.esgi.avis.entity.ModerateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModerateurEntityRepository extends JpaRepository<ModerateurEntity, Long> {
}