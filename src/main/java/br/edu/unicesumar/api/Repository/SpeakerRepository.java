package br.edu.unicesumar.api.Repository;

import br.edu.unicesumar.api.Model.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {

}
