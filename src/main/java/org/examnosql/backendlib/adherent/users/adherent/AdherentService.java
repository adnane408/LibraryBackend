package org.examnosql.backendlib.adherent.users.adherent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdherentService {

    @Autowired
    private AdherentRepository adherentRepository;

    public Adherent addAdherent(Adherent adherent) {
        return adherentRepository.save(adherent);
    }

    public void deleteAdherent(String adherentId) {
        adherentRepository.deleteAdherentByUsername(adherentId);
    }

    public List<Adherent> getAllAdherents() {
        return adherentRepository.findAll();
    }

    public Optional<Adherent> getAdherentById(String adherentId) {
        return adherentRepository.findById(adherentId);
    }
}
