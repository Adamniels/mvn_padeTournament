package org.example.padel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tournament {
    private String tournamentName;
    private List<Team> teams;
    private int courts;
    private Preliminary preliminary;
    private Playoff playoff;
    private TournamentStage currentStage;

    public Tournament(int courts){
        this.teams = new ArrayList<>();
        this.courts = courts;
        this.preliminary = new Preliminary(courts);
        this.playoff = new Playoff(); // TODO:
        this.currentStage = preliminary;
    }

    public void addTeam(String teamName) {
        Team team = new Team(teamName);
        teams.add(team);
    }

    public void start(boolean newTournament, String name) {
        if(newTournament){
            this.tournamentName = name;
            currentStage.start(teams);
        }else {
            // TODO: load in a saved, use name to find the file
        }
    }

    public void playNextRound() {
        currentStage.playRound();
    }

    // return true om turneringen är klar
    public boolean updateTournamentRound() {
        if(currentStage.updateRound()) {
            // return true if last round
            if(!currentStage.isPlayoff()){
                // preliminary round change stage to playoff
                this.currentStage = this.playoff;
                this.currentStage.start(teams);
            }else{
                // tournament is over
                return true;
            }
        }
        return false;
    }

    public boolean isPlayoffStage(){
        return currentStage.isPlayoff();
    }

    // TODO:
    public void printStanding() {
//        // Sort the teams in the correct standings
//        Collections.sort(teams);
//        int place = 1;
//        for (Team team : teams) {
//            System.out.println(place + ". " + team + "\nMatches played: " + team.playedMatches + "\nWON: "
//                    + team.matchesWon + "\nScore: " + team.scoreDiff);
//            System.out.println("");
//            place++;
//        }
    }


}
