package com.sp_databazy.Service;

import com.sp_databazy.Entity.Strava;
import com.sp_databazy.Repository.StravaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StravaService {
    private final StravaRepository stravaRepository;

    // Uloženie novej stravy
    public Strava createStrava(Strava strava) {
        return stravaRepository.save(strava);
    }

    // Získanie stravy podľa ID
    public Optional<Strava> getStravaById(UUID id) {
        return stravaRepository.findById(id);
    }

    // Získanie zoznamu všetkých stráv
    public List<Strava> getAllStrava() {
        return stravaRepository.findAll();
    }

    // Aktualizácia existujúcej stravy
    public Strava updateStrava(UUID id, Strava updatedStrava) {
        Optional<Strava> existingStravaOpt = stravaRepository.findById(id);

        if (existingStravaOpt.isPresent()) {
            Strava existingStrava = existingStravaOpt.get();
            existingStrava.setTyp(updatedStrava.getTyp());  // Aktualizuj iba typ stravy
            return stravaRepository.save(existingStrava);   // Ulož aktualizovanú entitu
        }
        return null;  // Vracia null, ak strava neexistuje
    }

    // Vymazanie stravy podľa ID
    public boolean deleteStrava(UUID id) {
        if (stravaRepository.existsById(id)) {
            stravaRepository.deleteById(id);
            return true;
        }
        return false;  // Vracia false, ak strava neexistuje
    }
}
