package com.sp_databazy.Service;

import com.sp_databazy.Entity.Osoba;
import com.sp_databazy.Entity.Pacient;
import com.sp_databazy.Entity.TeplotnaTabulka;
import com.sp_databazy.Repository.PacientRepository;
import com.sp_databazy.Repository.TeplotnaTabulkaRepository;
import com.sp_databazy.Request.UlozTeplotnaTabulkaRequest;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeplotnaTabulkaService {

    private final TeplotnaTabulkaRepository teplotnaTabulkaRepository;
    private final PacientRepository pacientRepository;

    public TeplotnaTabulka ulozTeplotu(UlozTeplotnaTabulkaRequest request) {
        // Načítanie pacienta podľa ID
        Pacient pacient = pacientRepository.findById(request.getPacientId())
                .orElseThrow(() -> new NoSuchElementException("Pacient s týmto ID neexistuje"));

        // Vytvorenie a naplnenie objektu TeplotnaTabulka
        TeplotnaTabulka teplotnaTabulka = new TeplotnaTabulka();
        teplotnaTabulka.setTeplota(request.getTeplota());
        teplotnaTabulka.setZaznamenanie(request.getZaznamenanie());
        teplotnaTabulka.setPacient(pacient); // Nastavenie pacienta

        return teplotnaTabulkaRepository.save(teplotnaTabulka);
    }

    // Načítanie teploty podľa ID
    public TeplotnaTabulka getTeplotu(UUID id) {
        return teplotnaTabulkaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Teplotná tabuľka s týmto ID neexistuje"));
    }

    // Načítanie všetkých teplôt pacienta
    public List<TeplotnaTabulka> getZoznamTeplotPacient(UUID pacientId) {
        Pacient pacient = new Pacient();
        pacient.setId(pacientId);
        return teplotnaTabulkaRepository.findByPacient(pacient);
    }

    // Úprava teploty podľa ID
    public TeplotnaTabulka upravTeplotu(UUID id, TeplotnaTabulka teplotnaTabulka) {
        TeplotnaTabulka existujucaTeplota = teplotnaTabulkaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Teplotná tabuľka s týmto ID neexistuje"));

        existujucaTeplota.setTeplota(teplotnaTabulka.getTeplota());
        existujucaTeplota.setZaznamenanie(teplotnaTabulka.getZaznamenanie());

        return teplotnaTabulkaRepository.save(existujucaTeplota);
    }

    // Vymazanie teploty podľa ID
    public void vymazTeplotu(UUID id) {
        if (!teplotnaTabulkaRepository.existsById(id)) {
            throw new NoSuchElementException("Teplotná tabuľka s týmto ID neexistuje");
        }
        teplotnaTabulkaRepository.deleteById(id);
    }
}
