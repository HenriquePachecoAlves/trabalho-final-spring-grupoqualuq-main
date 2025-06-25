package br.edu.unicesumar.api.Controller;

import br.edu.unicesumar.api.Model.Speaker;
import br.edu.unicesumar.api.Service.SpeakerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/speakers")
public class SpeakerController {
    private final SpeakerService speakerService;

    public SpeakerController(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    @PostMapping
    public ResponseEntity<Speaker> create(@RequestBody @Valid Speaker speaker) {
        return ResponseEntity.status(201).body(speakerService.create(speaker));
    }

    @GetMapping
    public ResponseEntity<List<Speaker>> getAll() {
        List<Speaker> listSpeaker = speakerService.findAll();
        return ResponseEntity.ok(listSpeaker);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Speaker> getById(@PathVariable Long id) {
        Speaker speaker = speakerService.searchId(id);
        return ResponseEntity.ok(speaker);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Speaker> updateSpeaker(@PathVariable Long id, @RequestBody @Valid Speaker speaker) {
        Speaker Speaker = speakerService.update(speaker, id);
        return ResponseEntity.ok(speaker);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        speakerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
