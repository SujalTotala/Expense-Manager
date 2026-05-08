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

    // ✅ Add
    public Participant addParticipant(Participant p) {
        return repository.save(p);
    }

    // ✅ Get all
    public List<Participant> getAllParticipants() {
        return repository.findAll();
    }

    // ✅ Delete
    public void deleteParticipant(Long id) {
        repository.deleteById(id);
    }

    // ✅ Update
    public Participant updateParticipant(Long id, Participant updated) {

        Participant p = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participant not found"));

        p.setName(updated.getName());

        return repository.save(p);
    }
}