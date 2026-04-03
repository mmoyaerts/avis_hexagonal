package fr.esgi.avis.config;

import fr.esgi.avis.entity.*;
import fr.esgi.avis.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            UtilisateurEntityRepository userRepo,
            AvatarEntityRepository avatarRepo,
            JeuEntityRepository jeuRepo,
            GenreEntityRepository genreRepo,
            EditeurEntityRepository editeurRepo,
            ClassificationEntityRepository classificationRepo,
            PlateformeEntityRepository plateformeRepo,
            AvisEntityRepository avisRepo
    ) {
        return args -> {

            if (userRepo.count() > 0) return;

            // 🎭 AVATARS
            AvatarEntity avatar1 = new AvatarEntity();
            avatar1.setNom("Naruto");

            AvatarEntity avatar2 = new AvatarEntity();
            avatar2.setNom("Luffy");

            avatarRepo.saveAll(List.of(avatar1, avatar2));

            // 👤 UTILISATEURS
            JoueurEntity user1 = new JoueurEntity();
            user1.setPseudo("player1");
            user1.setEmail("player1@mail.com");
            user1.setMotDePasse("1234");
            user1.setAvatar(avatar1);

            JoueurEntity user2 = new JoueurEntity();
            user2.setPseudo("player2");
            user2.setEmail("player2@mail.com");
            user2.setMotDePasse("1234");
            user2.setAvatar(avatar2);

            userRepo.saveAll(List.of(user1, user2));

            // 🎮 GENRE
            GenreEntity genre = new GenreEntity();
            genre.setNom("RPG");
            genreRepo.save(genre);

            // 🏢 EDITEUR
            EditeurEntity editeur = new EditeurEntity();
            editeur.setNom("Ubisoft");
            editeur.setLogo("ubisoft.png");
            editeurRepo.save(editeur);

            // 🔞 CLASSIFICATION
            ClassificationEntity classification = new ClassificationEntity();
            classification.setNom("PEGI 18");
            classification.setCouleurRGB("FF0000");
            classificationRepo.save(classification);

            // 💻 PLATEFORMES
            PlateformeEntity pc = new PlateformeEntity();
            pc.setNom("PC");

            PlateformeEntity ps5 = new PlateformeEntity();
            ps5.setNom("PS5");

            plateformeRepo.saveAll(List.of(pc, ps5));

            // 🎮 JEUX
            JeuEntity jeu1 = new JeuEntity();
            jeu1.setNom("Assassin's Creed");
            jeu1.setDescription("Jeu d'aventure");
            jeu1.setPrix(59.99f);
            jeu1.setEditeur(editeur);
            jeu1.setGenre(genre);
            jeu1.setClassification(classification);
            jeu1.setPlateformes(List.of(pc, ps5));

            jeuRepo.save(jeu1);

            // ⭐ AVIS
            AvisEntity avis1 = new AvisEntity();
            avis1.setDescription("Excellent jeu !");
            avis1.setNote(9);
            avis1.setDateDeCreation(LocalDateTime.now());
            avis1.setJeu(jeu1);
            avis1.setJoueur((JoueurEntity) user1);

            AvisEntity avis2 = new AvisEntity();
            avis2.setDescription("Pas mal");
            avis2.setNote(7);
            avis2.setDateDeCreation(LocalDateTime.now());
            avis2.setJeu(jeu1);
            avis2.setJoueur((JoueurEntity) user2);

            avisRepo.saveAll(List.of(avis1, avis2));

            System.out.println("🔥 DATA INITIALIZED");
        };
    }
}