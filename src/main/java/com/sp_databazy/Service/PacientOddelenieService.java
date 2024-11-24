package com.sp_databazy.Service;

import com.sp_databazy.Entity.Doktor;
import com.sp_databazy.Entity.Oddelenie;
import com.sp_databazy.Entity.Pacient;
import com.sp_databazy.Entity.PacientOddelenie;
import com.sp_databazy.Repository.OddelenieRepository;
import com.sp_databazy.Repository.PacientOddelenieRepository;
import com.sp_databazy.Repository.PacientRepository;
import com.sp_databazy.Request.UlozPacientOddelenieRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PacientOddelenieService {
    private final PacientOddelenieRepository pacientOddelenieRepository;
    private final PacientRepository pacientRepository;
    private final OddelenieRepository oddelenieRepository;

    public PacientOddelenie ulozPacientOddelenie(UlozPacientOddelenieRequest request) {
        // Kontrola existencie záznamu pre pacienta a oddelenie

        Pacient pacient = pacientRepository.findById(request.getPacientId())
                .orElseThrow(() -> new NoSuchElementException("Pacient s ID '" + request.getPacientId() + "' neexistuje."));

        Oddelenie oddelenie = oddelenieRepository.findById(request.getOddelenieId())
                .orElseThrow(() -> new NoSuchElementException("Oddelenie s ID '" + request.getOddelenieId() + "' neexistuje."));

        if (pacientOddelenieRepository.findByPacientIdIdAndOddelenieId(pacient, oddelenie).isPresent()) {
            throw new IllegalArgumentException("Pacient je už priradený k tomuto oddeleniu.");
        }
        PacientOddelenie pacientOddelenie = new PacientOddelenie();
        pacientOddelenie.setPacientIdId(pacient);
        pacientOddelenie.setOddelenieId(oddelenie);
        pacientOddelenie.setDatumPriradenia(request.getDatumPriradenia());

        return pacientOddelenieRepository.save(pacientOddelenie);
    }

    public List<PacientOddelenie> getVsetkyPacientOddelenia() {
        return pacientOddelenieRepository.findAll();
    }

    public PacientOddelenie getPacientOddelenieById(UUID id) {
        return pacientOddelenieRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Záznam PacientOddelenie s ID '" + id + "' nebol nájdený.")
        );
    }

    // Získanie záznamov podľa oddelenia
    public List<PacientOddelenie> getZaznamyPodlaOddelenia(UUID oddelenieId) {
        Oddelenie oddelenie = oddelenieRepository.findById(oddelenieId)
                .orElseThrow(() -> new NoSuchElementException("Oddelenie s ID '" + oddelenieId + "' nebolo nájdené."));
        return pacientOddelenieRepository.findByOddelenieId(oddelenie);
    }

    // Získanie záznamov podľa pacienta
    public List<PacientOddelenie> getZaznamyPodlaPacienta(UUID pacientId) {
        Pacient pacient = pacientRepository.findById(pacientId)
                .orElseThrow(() -> new NoSuchElementException("Pacient s ID '" + pacientId + "' nebol nájdený."));
        return pacientOddelenieRepository.findByPacientIdId(pacient);
    }

    public void vymazPacientOddelenie(UUID id) {
        if (!pacientOddelenieRepository.existsById(id)) {
            throw new IllegalArgumentException("Záznam PacientOddelenie s ID '" + id + "' nebol nájdený.");
        }
        pacientOddelenieRepository.deleteById(id);
    }

    public PacientOddelenie upravPacientOddelenie(UUID id, UlozPacientOddelenieRequest request) {
        PacientOddelenie existujuce = pacientOddelenieRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Záznam PacientOddelenie s ID '" + id + "' nebol nájdený.")
        );

        Pacient pacient = pacientRepository.findById(request.getPacientId())
                .orElseThrow(() -> new NoSuchElementException("Pacient s ID '" + request.getPacientId() + "' neexistuje."));

        Oddelenie oddelenie = oddelenieRepository.findById(request.getOddelenieId())
                .orElseThrow(() -> new NoSuchElementException("Oddelenie s ID '" + request.getOddelenieId() + "' neexistuje."));

        existujuce.setPacientIdId(pacient);
        existujuce.setOddelenieId(oddelenie);
        existujuce.setDatumPriradenia(request.getDatumPriradenia());

        return pacientOddelenieRepository.save(existujuce);
    }
}
