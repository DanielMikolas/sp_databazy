package com.sp_databazy.Service;

import com.sp_databazy.Entity.Doktor;
import com.sp_databazy.Entity.Pacient;
import com.sp_databazy.Entity.Vysetrenie;
import com.sp_databazy.Repository.DoktorRepository;
import com.sp_databazy.Repository.PacientRepository;
import com.sp_databazy.Repository.VysetrenieRepository;
import com.sp_databazy.Request.UlozVysetrenieRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VysetrenieService {


    private final VysetrenieRepository vysetrenieRepository;
    private final PacientRepository pacientRepository;
    private final DoktorRepository doktorRepository;

    // Uloženie vyšetrenia
    public Vysetrenie ulozVysetrenie(UlozVysetrenieRequest request) {
        Pacient pacient = pacientRepository.findById(request.getPacientId())
                .orElseThrow(() -> new NoSuchElementException("Pacient s týmto ID neexistuje"));

        Doktor doktor = doktorRepository.findById(request.getDoktorId())
                .orElseThrow(() -> new NoSuchElementException("Doktor s týmto ID neexistuje"));

        Vysetrenie vysetrenie = new Vysetrenie();
        vysetrenie.setPacient(pacient);
        vysetrenie.setDoktor(doktor);
        vysetrenie.setPopis(request.getPopis());
        vysetrenie.setTyp(request.getTyp());
        vysetrenie.setPriloha(request.getPriloha());
        vysetrenie.setDatumVysetrenia(request.getDatumVysetrenia());

        return vysetrenieRepository.save(vysetrenie);
    }

    // Získanie všetkých vyšetrení
    public List<Vysetrenie> zoznamVysetreni() {
        return vysetrenieRepository.findAll();
    }

    // Získanie vyšetrenia podľa ID
    public Vysetrenie ziskajVysetrenie(UUID id) {
        return vysetrenieRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vyšetrenie s týmto ID neexistuje"));
    }

    @Transactional
    // Získanie zoznamu vyšetrení podľa pacienta ID
    public List<Vysetrenie> zoznamVysetreniPacienta(UUID pacientId) {
        Pacient pacient = pacientRepository.findById(pacientId)
                .orElseThrow(() -> new NoSuchElementException("Pacient s týmto ID neexistuje"));

        return vysetrenieRepository.findByPacient(pacient);
    }

    // Úprava vyšetrenia
    public Vysetrenie upravVysetrenie(UUID id, UlozVysetrenieRequest request) {
        Vysetrenie vysetrenie = vysetrenieRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vyšetrenie s týmto ID neexistuje"));

        if (request.getPopis() != null) {
            vysetrenie.setPopis(request.getPopis());
        }
        if (request.getTyp() != null) {
            vysetrenie.setTyp(request.getTyp());
        }
        if (request.getPriloha() != null) {
            vysetrenie.setPriloha(request.getPriloha());
        }
        if (request.getDatumVysetrenia() != null) {
            vysetrenie.setDatumVysetrenia(request.getDatumVysetrenia());
        }

        return vysetrenieRepository.save(vysetrenie);
    }

    // Vymazanie vyšetrenia
    public void vymazVysetrenie(UUID id) {
        if (!vysetrenieRepository.existsById(id)) {
            throw new NoSuchElementException("Vyšetrenie s týmto ID neexistuje");
        }
        vysetrenieRepository.deleteById(id);
    }
}
