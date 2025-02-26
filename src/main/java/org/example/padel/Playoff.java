package org.example.padel;

import java.util.List;

public class Playoff implements TournamentStage{
    @Override
    public void start(List<Team> teams) {
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
