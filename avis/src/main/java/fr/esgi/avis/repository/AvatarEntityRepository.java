package fr.esgi.avis.repository;

import fr.esgi.avis.entity.AvatarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarEntityRepository extends JpaRepository<AvatarEntity, Long> {
}