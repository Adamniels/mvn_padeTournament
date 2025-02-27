package org.example.padel;

import java.util.List;

public class Playoff implements TournamentStage{
    private Match finalMatch;
    private Match bronzeMatch;
    private List<Match> semifinalMatches;
    private List<Match> quarterfinalMatches;

    @Override
    public void start(List<Team> teams) {
        int teamSize = teams.size();
        if(teamSize < 4) {
            // only final
        }else if(teamSize < 8) {

        }

        throw new RuntimeException("not implemented yet");
    }

    @Override
    public void playRound() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public boolean updateRound() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public boolean isPlayoff() {
        return true;
    }
}
