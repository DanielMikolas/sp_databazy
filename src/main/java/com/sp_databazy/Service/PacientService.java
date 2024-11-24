package com.sp_databazy.Service;

import com.sp_databazy.Entity.Doktor;
import com.sp_databazy.Entity.Pacient;
import com.sp_databazy.Entity.Poistovna;
import com.sp_databazy.Entity.Pouzivatel;
import com.sp_databazy.Repository.PacientRepository;
import com.sp_databazy.Repository.PoistovnaRepository;
import com.sp_databazy.Repository.PouzivatelRepository;
import com.sp_databazy.Request.UlozPacientaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PacientService {

    private final PacientRepository pacientRepository;
    private final PouzivatelRepository pouzivatelRepository;
    private final PoistovnaRepository poistovnaRepository;

    // Vytvorenie pacienta
    public Pacient ulozPacienta(UlozPacientaRequest ulozPacientRequest) {
        Pacient pacient = new Pacient();
        pacient.setCisloPoistenca(ulozPacientRequest.getCisloPoistenca());

        // Načítanie poisťovne podľa jej ID
        Poistovna poistovna = poistovnaRepository.findById(ulozPacientRequest.getCisloPoistovne())
                .orElseThrow(() -> new NoSuchElementException("Poisťovňa s týmto kódom neexistuje"));
        pacient.setPoistovna(poistovna);

        // Načítanie Pouzivatela podľa jeho ID
        Pouzivatel pouzivatel = pouzivatelRepository.findById(ulozPacientRequest.getPouzivatelId())
                .orElseThrow(() -> new IllegalArgumentException("Používateľ s týmto ID neexistuje"));

        pacient.setPouzivatelId(pouzivatel);  // Priradenie Pouzivatela do entity Sestra
        return pacientRepository.save(pacient);
    }

    // Načítanie pacienta podľa ID
    public Pacient getPacient(UUID id) {
        return pacientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pacient s týmto ID neexistuje"));
    }

    // Načítanie zoznamu všetkých pacientov
    public List<Pacient> getZoznamPacientov() {
        return pacientRepository.findAll();
    }

    // Úprava informácií o pacientovi
    public Pacient upravPacienta(UUID id, UlozPacientaRequest ulozPacientRequest) {
        Pacient pacient = pacientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pacient s týmto ID neexistuje"));

        pacient.setCisloPoistenca(ulozPacientRequest.getCisloPoistenca());

        return pacientRepository.save(pacient);
    }

    // Vymazanie pacienta
    public void vymazPacienta(UUID id) {
        if (!pacientRepository.existsById(id)) {
            throw new NoSuchElementException("Pacient s týmto ID neexistuje");
        }
        pacientRepository.deleteById(id);
    }
}
