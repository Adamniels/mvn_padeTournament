package org.example.padel;

import java.util.List;

public interface TournamentStage {
    void start(List<Team> teams);

    void playRound();

    boolean updateRound();

    boolean isPlayoff();
}
