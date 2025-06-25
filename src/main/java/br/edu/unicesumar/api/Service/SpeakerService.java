package br.edu.unicesumar.api.Service;

import br.edu.unicesumar.api.Model.Speaker;
import br.edu.unicesumar.api.Repository.SpeakerRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SpeakerService {
    private final SpeakerRepository speakerRepository;

    public SpeakerService(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    public List<Speaker> findAll() {
        return speakerRepository.findAll();
    }

    public Speaker searchId(Long id) {
        return speakerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Palestrante nao econtrado"));
    }

    public Speaker create(Speaker speaker) {
        return speakerRepository.save(speaker);
    }

    @Transactional
    public Speaker update(Speaker speaker, Long id) {
        Speaker existent = searchId(id);

        existent.setName(speaker.getName());
        existent.setMiniResume(speaker.getMiniResume());
        existent.setInstitution(speaker.getInstitution());

        return speakerRepository.save(existent);
    }

    @Transactional
    public void delete(Long id) {

        Speaker speaker = searchId(id); // reutiliza método que já valida se existe

        if (!speaker.getEventos().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Não é possível excluir um palestrante vinculado a eventos.");
        }

        speakerRepository.delete(speaker);
    }


}
