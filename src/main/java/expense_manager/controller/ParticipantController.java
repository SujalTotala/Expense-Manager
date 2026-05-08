package expense_manager.controller;

import expense_manager.model.Participant;
import expense_manager.service.ParticipantService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
@CrossOrigin // ✅ important for frontend requests
public class ParticipantController {

    private final ParticipantService service;

    public ParticipantController(ParticipantService service) {
        this.service = service;
    }

    // ✅ Add participant
    @PostMapping
    public Participant addParticipant(@RequestBody Participant participant) {
        return service.addParticipant(participant);
    }

    // ✅ Get all participants
    @GetMapping
    public List<Participant> getAllParticipants() {
        return service.getAllParticipants();
    }

    // ✅ Delete participant
    @DeleteMapping("/{id}")
    public void deleteParticipant(@PathVariable Long id) {
        service.deleteParticipant(id);
    }

    // ✅ Update participant
    @PutMapping("/{id}")
    public Participant updateParticipant(@PathVariable Long id, @RequestBody Participant updated) {
        return service.updateParticipant(id, updated);
    }
}