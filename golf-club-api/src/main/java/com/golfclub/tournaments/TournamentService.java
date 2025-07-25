package com.golfclub.tournaments;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.golfclub.member.Member;
import com.golfclub.member.MemberService;

@Service
public class TournamentService {

    private final TournamentRepository repo;
    private final MemberService memberService;

    public TournamentService(TournamentRepository repo, MemberService memberService) {
        this.repo = repo;
        this.memberService = memberService;
    }

    public Tournament addTournament(Tournament t) {
        return repo.save(t);
    }

    public List<Tournament> getAllTournaments() {
        return repo.findAll();
    }

    public Optional<Tournament> getTournamentById(Long id) {
        return repo.findById(id);
    }

    public List<Tournament> searchByStartDate(LocalDate date) {
        return repo.findByStartDate(date);
    }

    public List<Tournament> searchByLocation(String loc) {
        return repo.findByLocationContainingIgnoreCase(loc);
    }

    @Transactional
    public Tournament addMemberToTournament(Long tournamentId, Long memberId) {
        Tournament t = repo.findById(tournamentId)
                .orElseThrow(() -> new IllegalArgumentException("Tournament not found: " + tournamentId));
        Member m = memberService.getMemberById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found: " + memberId));
        t.getParticipatingMembers().add(m);
        return repo.save(t);
    }
}
