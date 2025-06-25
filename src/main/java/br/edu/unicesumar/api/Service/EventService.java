package br.edu.unicesumar.api.Service;

import br.edu.unicesumar.api.Model.Event;
import br.edu.unicesumar.api.Model.Speaker;
import br.edu.unicesumar.api.Repository.EventRepository;
import br.edu.unicesumar.api.Repository.SpeakerRepository;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final SpeakerRepository speakerRepository;

    public EventService(EventRepository eventRepository, SpeakerRepository speakerRepository) {
        this.eventRepository = eventRepository;
        this.speakerRepository = speakerRepository;
    }

    public List<Event> listAll(){
        return eventRepository.findAll();
    }

    public Event searchId (Long id){
        return eventRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("Evento nao encontrado"+ id));
    }

    public Event create(Event event){
        if(event.getSpeaker() == null || event.getSpeaker().isEmpty()){
            throw new IllegalArgumentException("O elemento deve ter pelo menos um palestrante");
        }
        for (Speaker speaker : event.getSpeaker()) {
            speakerRepository.findById(speaker.getId())
                    .orElseThrow(() -> new NoSuchElementException("Palestrante com ID " + speaker.getId() + " não encontrado."));
        }
        return eventRepository.save(event);
    }

    public Event update(Long id, Event novo){
        Event Existing = searchId(id);

        if(novo.getSpeaker() == null || novo.getSpeaker().isEmpty()){
            throw new IllegalArgumentException("O elemento deve ter pelo menos um palestrante veinculado");
        }

        Existing.setName(novo.getName());
        Existing.setDescription(novo.getDescription());
        Existing.setDate(novo.getDate());
        Existing.setLimitParticpants(novo.getLimitParticpants());
        Existing.setLimitParticpants(novo.getLimitParticpants());
        Existing.setSpeaker(novo.getSpeaker());

        return eventRepository.save(Existing);
    }

    public void delete(Long id){
        Event Existing = searchId(id);
        eventRepository.delete(Existing);
    }

}
