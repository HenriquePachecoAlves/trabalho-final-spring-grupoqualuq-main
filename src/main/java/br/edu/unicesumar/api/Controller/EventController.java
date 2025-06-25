package br.edu.unicesumar.api.Controller;

import br.edu.unicesumar.api.Model.Event;
import br.edu.unicesumar.api.Service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    public ResponseEntity<List<Event>> listAll() {
        List<Event> events = eventService.listAll();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> searchId(@PathVariable Long id) {
        Event event = eventService.searchId(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody @Valid Event event) {
        Event criado = eventService.create(event);
        return ResponseEntity.created(URI.create("/events/" + criado.getId())).body(criado);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Event> update(@PathVariable Long id, @RequestBody @Valid Event event) {
        Event update = eventService.update(id, event);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
