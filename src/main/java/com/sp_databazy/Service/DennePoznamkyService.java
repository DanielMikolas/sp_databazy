package com.sp_databazy.Service;

import com.sp_databazy.Entity.DennePoznamky;
import com.sp_databazy.Entity.Pacient;
import com.sp_databazy.Entity.Sestra;
import com.sp_databazy.Repository.DennePoznamkyRepository;
import com.sp_databazy.Repository.PacientRepository;
import com.sp_databazy.Repository.SestraRepository;
import com.sp_databazy.Request.UlozDennePoznamkyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DennePoznamkyService {

    private final DennePoznamkyRepository dennePoznamkyRepository;
    private final PacientRepository pacientRepository;
    private final SestraRepository sestraRepository;

    // Uloženie novej dennej poznámky
    public DennePoznamky ulozPoznamku(UlozDennePoznamkyRequest request) {
        Pacient pacient = pacientRepository.findById(request.getPacientId())
                .orElseThrow(() -> new NoSuchElementException("Pacient s týmto ID neexistuje"));

        Sestra sestra = sestraRepository.findById(request.getSestraId())
                .orElseThrow(() -> new NoSuchElementException("Sestra s týmto ID neexistuje"));

        DennePoznamky poznamka = new DennePoznamky();
        poznamka.setPacient(pacient);
        poznamka.setSestra(sestra);
        poznamka.setDatumCas(request.getDatumCas());
        poznamka.setPoznamka(request.getPoznamka());

        return dennePoznamkyRepository.save(poznamka);
    }


    // Načítanie dennej poznámky podľa ID
    public DennePoznamky getDennePoznamku(UUID id) {
        return dennePoznamkyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Denná poznámka s týmto ID neexistuje"));
    }

    // Načítanie všetkých denníkov pacienta
    public List<DennePoznamky> getZoznamDennePoznamky(UUID pacientId) {
        Pacient pacient = pacientRepository.findById(pacientId)
                .orElseThrow(() -> new NoSuchElementException("Pacient s týmto ID neexistuje"));

        return dennePoznamkyRepository.findByPacient(pacient);
    }

    // Načítanie denníkov sestry
    public List<DennePoznamky> getZoznamDennePoznamkySestry(UUID sestraId) {
        Sestra sestra = sestraRepository.findById(sestraId)
                .orElseThrow(() -> new NoSuchElementException("Pacient s týmto ID neexistuje"));

        return dennePoznamkyRepository.findBySestra(sestra);
    }

    // Úprava dennej poznámky
    public DennePoznamky upravDennePoznamku(UUID id, DennePoznamky dennePoznamky) {
        DennePoznamky existujucaPoznamka = dennePoznamkyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Denná poznámka s týmto ID neexistuje"));

        existujucaPoznamka.setDatumCas(dennePoznamky.getDatumCas());
        existujucaPoznamka.setPoznamka(dennePoznamky.getPoznamka());

        return dennePoznamkyRepository.save(existujucaPoznamka);
    }

    // Vymazanie dennej poznámky
    public void vymazDennePoznamku(UUID id) {
        if (!dennePoznamkyRepository.existsById(id)) {
            throw new NoSuchElementException("Denná poznámka s týmto ID neexistuje");
        }
        dennePoznamkyRepository.deleteById(id);
    }
}
