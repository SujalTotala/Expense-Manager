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

    public Participant addParticipant(Participant participant) {
        return repository.save(participant);
    }

    public List<Participant> getAllParticipants() {
        return repository.findAll();
    }
}