package org.examnosql.backendlib.adherent.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepo utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        return utilisateurRepository.save(utilisateur);
    }

    public Optional<Utilisateur> findByUsername(String username) {
        return utilisateurRepository.findByUsername(username);
    }

    public boolean checkPassword(Utilisateur utilisateur, String rawPassword) {
        return passwordEncoder.matches(rawPassword, utilisateur.getMotDePasse());
    }
    public List<Utilisateur> getall(){
        return utilisateurRepository.findAll();
    }
    public boolean updatePassword(String username, String oldPassword, String newPassword) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findByUsername(username);
        if (utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();
            if (passwordEncoder.matches(oldPassword, utilisateur.getMotDePasse())) {
                utilisateur.setMotDePasse(passwordEncoder.encode(newPassword));
                utilisateurRepository.save(utilisateur);
                return true;
            }
        }
        return false;
    }
}
