package com.sp_databazy.Service;

import com.sp_databazy.Entity.*;
import com.sp_databazy.Entity.Enums.Rola;
import com.sp_databazy.Repository.*;
import com.sp_databazy.Request.UlozPouzivatelaRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PouzivatelService {

    private final PasswordEncoder passwordEncoder;

    private final PouzivatelRepository pouzivatelRepository;
    private final DoktorRepository doktorRepository;
    private final SestraRepository sestraRepository;
    private final OsobaRepository osobaRepository;
    private final PacientRepository pacientRepository;
    private final PoistovnaRepository poistovnaRepository;

    public void ulozPouzivatela(UlozPouzivatelaRequest ulozPouzivatelaRequest){
        try {
            checkUserAvailability(ulozPouzivatelaRequest);

            // 1. Vyhľadajte osobu podľa rodného čísla
            Osoba osoba = osobaRepository.findByRodneCislo(ulozPouzivatelaRequest.getRodneCislo())
                    .orElseGet(() -> vytvorNovuOsobu(ulozPouzivatelaRequest));

            // 2. Vytvorte Používateľa
            Pouzivatel pouzivatel = new Pouzivatel();
            pouzivatel.setOsobaId(osoba); // prepojenie s existujúcou alebo novou osobou
            pouzivatel.setEmail(ulozPouzivatelaRequest.getEmail());
            pouzivatel.setHeslo(passwordEncoder.encode(ulozPouzivatelaRequest.getHeslo()));
            pouzivatel.setRola(ulozPouzivatelaRequest.getRola());

            // 3. Uložte Používateľa
            pouzivatelRepository.save(pouzivatel);

            // 4. Uloženie údajov do špecifických tabuliek na základe roly
            if (pouzivatel.getRola() == Rola.PACIENT) {
                Pacient pacient = new Pacient();
                pacient.setCisloPoistenca(ulozPouzivatelaRequest.getCisloPoistenca());
                pacient.setPouzivatelId(pouzivatel);

                // Načítanie Poistovne podľa cisla poisťovne
                Poistovna poistovna = poistovnaRepository.findById(ulozPouzivatelaRequest.getCisloPoistovne())
                        .orElseThrow(() -> new NoSuchElementException("Poisťovňa s týmto kódom neexistuje"));
                pacient.setPoistovna(poistovna);

                pacientRepository.save(pacient);
            } else if (pouzivatel.getRola() == Rola.DOKTOR) {
                Doktor doktor = new Doktor();
                doktor.setSpecializacia(ulozPouzivatelaRequest.getSpecializacia());
                doktor.setOddelenie(ulozPouzivatelaRequest.getOddelenie());
                doktor.setPouzivatelId(pouzivatel);
                doktorRepository.save(doktor);
            } else if (pouzivatel.getRola() == Rola.SESTRA) {
                Sestra sestra = new Sestra();
                sestra.setOddelenie(ulozPouzivatelaRequest.getOddelenie());
                sestra.setPouzivatelId(pouzivatel);
                sestraRepository.save(sestra);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving user: " + e.getMessage());
        }

    }

    // Pomocná metóda na vytvorenie novej osoby
    private Osoba vytvorNovuOsobu(UlozPouzivatelaRequest ulozPouzivatelaRequest) {
        Osoba novaOsoba = new Osoba();
        novaOsoba.setMeno(ulozPouzivatelaRequest.getMeno());
        novaOsoba.setPriezvisko(ulozPouzivatelaRequest.getPriezvisko());
        novaOsoba.setDatumNarodenia(ulozPouzivatelaRequest.getDatumNarodenia());
        novaOsoba.setRodneCislo(ulozPouzivatelaRequest.getRodneCislo());
        novaOsoba.setAdresa(ulozPouzivatelaRequest.getAdresa());
        novaOsoba.setTyp(ulozPouzivatelaRequest.getTyp()); // Napríklad, môžete prednastaviť typ

        return osobaRepository.save(novaOsoba); // Uloženie novej osoby
    }

    private void checkUserAvailability(UlozPouzivatelaRequest request) throws Exception {
        // Check if email already exists
        boolean emailExists = pouzivatelRepository.findAll().stream()
                .anyMatch(pouzivatel -> pouzivatel.getEmail().equals(request.getEmail()));

        if (emailExists) {
            throw new Exception("User with the given email already exists");
        }
        // Check if person with the same rodneCislo already exists
        Optional<Osoba> existingOsoba = osobaRepository.findByRodneCislo(request.getRodneCislo());
        if (existingOsoba.isPresent()) {
            throw new Exception("Person with the given rodne cislo already exists");
        }
    }

    // **Get Používateľa podľa ID**
    public Pouzivatel getPouzivatel(UUID id) {
        return pouzivatelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Používateľ s týmto ID neexistuje"));
    }

    // **Get Zoznam všetkých používateľov**
    public List<Pouzivatel> getZoznamPouzivatelov() {
        return pouzivatelRepository.findAll();
    }

    // **Aktualizácia používateľa**
    public void upravPouzivatela(UUID id, UlozPouzivatelaRequest ulozPouzivatelaRequest) {
        Pouzivatel pouzivatel = pouzivatelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Používateľ s týmto ID neexistuje"));

        // Aktualizácia osobných údajov
        pouzivatel.setEmail(ulozPouzivatelaRequest.getEmail());

        // Uloženie zmien v Používateľovi
        pouzivatelRepository.save(pouzivatel);

        // Ak je rola PACIENT, upravíme údaje v tabuľke Pacient
        if (pouzivatel.getRola() == Rola.PACIENT) {
            Pacient pacient = pacientRepository.findByPouzivatelId(pouzivatel)
                    .orElseThrow(() -> new NoSuchElementException("Pacient s týmto používateľom neexistuje"));
            pacient.setCisloPoistenca(ulozPouzivatelaRequest.getCisloPoistenca());
            pacientRepository.save(pacient);
        }
        // Ak je rola DOKTOR, upravíme údaje v tabuľke Doktor
        else if (pouzivatel.getRola() == Rola.DOKTOR) {
            Doktor doktor = doktorRepository.findByPouzivatelId(pouzivatel)
                    .orElseThrow(() -> new NoSuchElementException("Doktor s týmto používateľom neexistuje"));
            doktor.setSpecializacia(ulozPouzivatelaRequest.getSpecializacia());
            doktor.setOddelenie(ulozPouzivatelaRequest.getOddelenie());
            doktorRepository.save(doktor);
        }
        // Ak je rola SESTRA, upravíme údaje v tabuľke Sestra
        else if (pouzivatel.getRola() == Rola.SESTRA) {
            Sestra sestra = sestraRepository.findByPouzivatelId(pouzivatel)
                    .orElseThrow(() -> new NoSuchElementException("Sestra s týmto používateľom neexistuje"));
            sestra.setOddelenie(ulozPouzivatelaRequest.getOddelenie());
            sestraRepository.save(sestra);
        }
    }
    // **Vymazanie používateľa**
    public void vymazPouzivatela(UUID id) {
        Pouzivatel pouzivatel = pouzivatelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Používateľ s týmto ID neexistuje"));

        // Vymazanie používateľa z príslušnej tabuľky podľa roly
        if (pouzivatel.getRola() == Rola.PACIENT) {
            Pacient pacient = pacientRepository.findByPouzivatelId(pouzivatel)
                    .orElseThrow(() -> new NoSuchElementException("Pacient s týmto používateľom neexistuje"));
            pacientRepository.delete(pacient);
        } else if (pouzivatel.getRola() == Rola.DOKTOR) {
            Doktor doktor = doktorRepository.findByPouzivatelId(pouzivatel)
                    .orElseThrow(() -> new NoSuchElementException("Doktor s týmto používateľom neexistuje"));
            doktorRepository.delete(doktor);
        } else if (pouzivatel.getRola() == Rola.SESTRA) {
            Sestra sestra = sestraRepository.findByPouzivatelId(pouzivatel)
                    .orElseThrow(() -> new NoSuchElementException("Sestra s týmto používateľom neexistuje"));
            sestraRepository.delete(sestra);
        }

        // Vymazanie samotného používateľa
        pouzivatelRepository.delete(pouzivatel);
    }
    public Optional<Pouzivatel> getPouzivatelByEmail(String email) {
        return pouzivatelRepository.findPouzivatelByEmail(email);
    }


}
