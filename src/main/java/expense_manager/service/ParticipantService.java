package expense_manager.service;

import expense_manager.model.Participant;
import expense_manager.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {

    private final ParticipantRepository repository;

    public ParticipantService(ParticipantRepository repository) {
        this.repository = repository;
    }

    public Participant addParticipant(Participant p) {
        return repository.save(p);
    }

    public List<Participant> getAllParticipants() {
        return repository.findAll();
    }

    public void deleteParticipant(Long id) {
        repository.deleteById(id);
    }

    public Participant updateParticipant(Long id, Participant updated) {
        Participant p = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        p.setName(updated.getName());

        return repository.save(p);
    }
}