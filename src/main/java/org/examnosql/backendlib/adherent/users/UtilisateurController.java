package org.examnosql.backendlib.adherent.users;

import org.examnosql.backendlib.DTO.UpdatePasswordRequest;
import org.examnosql.backendlib.adherent.users.adherent.Adherent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/add")
    public ResponseEntity<Utilisateur> createUser(@RequestBody Utilisateur utilisateur) {
        Utilisateur newUser = utilisateurService.createUtilisateur(utilisateur);
        return ResponseEntity.ok(newUser);
    }
    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAll() {
        return ResponseEntity.ok(utilisateurService.getall());
    }


    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticateUser(@RequestBody Utilisateur loginRequest) {
        Optional<Utilisateur> utilisateur = utilisateurService.findByUsername(loginRequest.getUsername());
        if (utilisateur.isPresent() && utilisateurService.checkPassword(utilisateur.get(), loginRequest.getMotDePasse())) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(utilisateur);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Authentication failed");
        }
    }
    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
        boolean isUpdated = utilisateurService.updatePassword(
                updatePasswordRequest.getUsername(),
                updatePasswordRequest.getOldPassword(),
                updatePasswordRequest.getNewPassword()
        );

        if (isUpdated) {
            return ResponseEntity.ok("Password updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password update failed");
        }
    }
}
