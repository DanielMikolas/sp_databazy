package com.sp_databazy.Service;

import com.sp_databazy.Entity.Poistovna;
import com.sp_databazy.Repository.PoistovnaRepository;
import com.sp_databazy.Request.UlozPoistovnuRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PoistovnaService {

    private final PoistovnaRepository poistovnaRepository;

    // Uloženie poisťovne
    public Poistovna ulozPoistovnu(UlozPoistovnuRequest ulozPoistovnuRequest) {
        Poistovna poistovna = new Poistovna();
        poistovna.setCisloPois(ulozPoistovnuRequest.getCisloPois());
        poistovna.setNazov(ulozPoistovnuRequest.getNazov());

        return poistovnaRepository.save(poistovna);
    }

    // Načítanie poisťovne podľa čísla poisťovne
    public Poistovna getPoistovna(String cisloPois) {
        return poistovnaRepository.findById(cisloPois)
                .orElseThrow(() -> new NoSuchElementException("Poisťovňa s týmto číslom poisťovne neexistuje"));
    }

    // Načítanie zoznamu všetkých poisťovní
    public List<Poistovna> getZoznamPoistovni() {
        return poistovnaRepository.findAll();
    }

    // Úprava poisťovne
    public Poistovna upravPoistovnu(String cisloPois, UlozPoistovnuRequest ulozPoistovnuRequest) {
        Poistovna poistovna = poistovnaRepository.findById(cisloPois)
                .orElseThrow(() -> new NoSuchElementException("Poisťovňa s týmto číslom poisťovne neexistuje"));

        poistovna.setCisloPois(ulozPoistovnuRequest.getCisloPois());
        poistovna.setNazov(ulozPoistovnuRequest.getNazov());

        return poistovnaRepository.save(poistovna);
    }

    // Vymazanie poisťovne
    public void vymazPoistovnu(String cisloPois) {
        if (!poistovnaRepository.existsById(cisloPois)) {
            throw new NoSuchElementException("Poisťovňa s týmto číslom poisťovne neexistuje");
        }
        poistovnaRepository.deleteById(cisloPois);
    }
}
