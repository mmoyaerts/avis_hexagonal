package fr.esgi.avis.use_case.impl;

import fr.esgi.avis.business.Jeu;
import fr.esgi.avis.dto.AvisDtoOut;
import fr.esgi.avis.dto.JeuDtoIn;
import fr.esgi.avis.dto.JeuDtoOut;
import fr.esgi.avis.mapper.AvisMapper;
import fr.esgi.avis.mapper.JeuMapper;
import fr.esgi.avis.port.AvisPort;
import fr.esgi.avis.port.JeuPort;
import fr.esgi.avis.use_case.JeuUseCase;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JeuUseCaseImpl implements JeuUseCase {

    private final JeuPort jeuPort;   // ✅ plus de repository
    private final AvisPort avisPort; // ✅ plus de repository

    public JeuUseCaseImpl(JeuPort jeuPort, AvisPort avisPort) {
        this.jeuPort = jeuPort;
        this.avisPort = avisPort;
    }

    @Override
    public List<JeuDtoOut> recupererJeux() {
        return jeuPort.findAll()
                .stream()
                .toList();
    }

    @Override
    public JeuDtoOut recupererJeu(Long id) {
        return jeuPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jeu non trouvé avec l'id : " + id));
    }

    @Override
    public List<JeuDtoOut> recupererParNom(String nom) {
        return jeuPort.findByNomContainingIgnoreCase(nom)
                .stream()
                .toList();
    }

    @Override
    public List<AvisDtoOut> recupererAvisPourUnJeu(JeuDtoIn jeuDtoIn) {
        return avisPort.findByJeu(jeuDtoIn)
                .stream()
                .toList();
    }
}