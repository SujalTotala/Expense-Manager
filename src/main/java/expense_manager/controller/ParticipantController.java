package expense_manager.controller;

import expense_manager.model.Participant;
import expense_manager.service.ParticipantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantService service;

    public ParticipantController(ParticipantService service) {
        this.service = service;
    }

    @PostMapping
    public Participant addParticipant(@RequestBody Participant participant) {
        return service.addParticipant(participant);
    }

    @GetMapping
    public List<Participant> getAllParticipants() {
        return service.getAllParticipants();
    }
}