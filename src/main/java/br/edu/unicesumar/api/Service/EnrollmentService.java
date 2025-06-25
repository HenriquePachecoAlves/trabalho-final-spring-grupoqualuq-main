package br.edu.unicesumar.api.Service;

import br.edu.unicesumar.api.Model.Enrollment;
import br.edu.unicesumar.api.Model.Event;
import br.edu.unicesumar.api.Model.Student;
import br.edu.unicesumar.api.Repository.EnrollmentRepository;
import br.edu.unicesumar.api.Repository.EventRepository;
import br.edu.unicesumar.api.Repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EventRepository eventRepository;
    private final StudentRepository studentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, EventRepository eventRepository, StudentRepository studentRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.eventRepository = eventRepository;
        this.studentRepository = studentRepository;
    }

    public Enrollment RegisterStudent (Long idEvent, Long idStudent){
        Event event = eventRepository.findById(idEvent).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Evento nao encontrado"));

        Student student = studentRepository.findById(idStudent).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno nao encontrado"));

        if(event.getDate().isBefore(LocalDateTime.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nao e possivel se inscrever em eventos passados");
        }

        boolean
        alreadyWritten = enrollmentRepository
                .findAll().stream()
                .anyMatch(e -> e.getStudent().getId().equals(idStudent)
                        && e.getEvent().getId().equals(idEvent)
                        && e.isActive());

        if (alreadyWritten) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno já está inscrito neste evento.");
        }

        long activeSubscribers = enrollmentRepository
                .findAll().stream()
                .filter(e -> e.getEvent().getId().equals(idEvent) && e.isActive())
                .count();

        if (activeSubscribers >= event.getLimitParticpants()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não há vagas disponíveis neste evento.");
        }

        List<Enrollment> StudentRegistrations = enrollmentRepository.findAll().stream()
                .filter(e -> e.getStudent().getId().equals(idStudent) && e.isActive())
                .toList();

        for (Enrollment insc : StudentRegistrations) {
            if (insc.getEvent().getDate().equals(event.getDate())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Conflito de horário com outro evento em que o aluno já está inscrito.");
            }
        }
        Enrollment nova = new Enrollment();
        nova.setEvent(event);
        nova.setStudent(student);
        nova.setDate(LocalDateTime.now());
        nova.setActive(true);

        return enrollmentRepository.save(nova);
    }
    @Transactional
    public void unsubscribe(Long idEnrollment) {
        Enrollment inscricao = enrollmentRepository.findById(idEnrollment)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inscrição não encontrada"));

        inscricao.setActive(false);
        enrollmentRepository.save(inscricao);
    }
    public List<Enrollment> listByStudent(Long idStudent) {
        return enrollmentRepository.findAll().stream()
                .filter(e -> e.getStudent().getId().equals(idStudent))
                .toList();
    }
}
