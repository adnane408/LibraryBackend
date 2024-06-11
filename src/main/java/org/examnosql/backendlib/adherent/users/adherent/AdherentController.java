package org.examnosql.backendlib.adherent.users.adherent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/adherents")
public class AdherentController {

    @Autowired
    private AdherentService adherentService;

    @PostMapping("/add")
    public ResponseEntity<Adherent> addAdherent(@RequestBody Adherent adherent) {
        Adherent newAdherent = adherentService.addAdherent(adherent);
        return ResponseEntity.ok(newAdherent);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteAdherent(@PathVariable String username) {
        adherentService.deleteAdherent(username);
        System.out.println(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Adherent>> getAllAdherents() {
        List<Adherent> adherents = adherentService.getAllAdherents();
        return ResponseEntity.ok(adherents);
    }
}
