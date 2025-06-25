package br.edu.unicesumar.api.Repository;

import br.edu.unicesumar.api.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
