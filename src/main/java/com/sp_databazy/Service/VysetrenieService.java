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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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


    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public ResponseEntity<byte[]> stiahnutPrilohu(UUID idVysetrenie) throws IOException {
        Optional<Vysetrenie> priloha = vysetrenieRepository.findById(idVysetrenie);

        if (priloha.isPresent()) {
            File file = new File(priloha.get().getNazov());
            byte[] content = Files.readAllBytes(file.toPath());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", file.getName());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(content);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    public String saveLargeObject(MultipartFile file) throws IOException {
        File uploadDir = new File("uploads");
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        File subor = new File(uploadDir.getAbsolutePath() + File.separator + file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(subor);
        fos.write(file.getBytes());
        fos.close();

        return subor.getAbsolutePath();

    }


}
