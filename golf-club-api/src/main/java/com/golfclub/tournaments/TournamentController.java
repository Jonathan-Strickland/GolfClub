package com.golfclub.tournaments;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final TournamentService service;

    public TournamentController(TournamentService service) {
        this.service = service;
    }

    @PostMapping
    public Tournament createTournament(@RequestBody Tournament t) {
        return service.addTournament(t);
    }

    @GetMapping
    public List<Tournament> allTournaments() {
        return service.getAllTournaments();
    }

    @GetMapping("/{id}")
    public Tournament getById(@PathVariable Long id) {
        return service.getTournamentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found: " + id));
    }

    @GetMapping("/search")
    public List<Tournament> search(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String location
    ) {
        if (startDate != null) {
            return service.searchByStartDate(LocalDate.parse(startDate));
        } else if (location != null) {
            return service.searchByLocation(location);
        } else {
            return service.getAllTournaments();
        }
    }

    @PostMapping("/{tournamentId}/members")
    public Tournament addMember(
            @PathVariable Long tournamentId,
            @RequestParam Long memberId
    ) {
        return service.addMemberToTournament(tournamentId, memberId);
    }
}
