package org.example.padel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Team {
    private final String teamName;
    private int playedMatches;
    private int wonMatches;
    private int scoreDiff;
    private List<Team> playedAgainst;

    /**
     * Constructor for the class
     * @param teamName
     */
    public Team(String teamName){
        this.teamName = teamName;
        this.playedMatches = 0;
        this.wonMatches = 0;
        this.scoreDiff = 0;
        this.playedAgainst = new ArrayList<>();
    }

    public void updateTeam(int won, int scoreDiff, Team opponent){
        this.wonMatches += won;
        this.scoreDiff += scoreDiff;
        this.playedMatches++;
        playedAgainst.add(opponent);
    }

    // TODO: komma på hur jag ska göra med comparable, kanske som jag redan har och en egen
    // sorterings algoritm för att hitta nästa lag istället?

    public String getTeamName() {
        return teamName;
    }
    public int getPlayedMatches() {
        return playedMatches;
    }
    public int getWonMatches() {
        return wonMatches;
    }
    public int getScoreDiff() {
        return scoreDiff;
    }

    public List<Team> getPlayedAgainst() {
        return playedAgainst;
    }

    @Override
    public String toString(){
        return teamName;
    }

    public List<Team> notPlayedAgainst(List<Team> allTeams) {
        List<Team> notPlayed = new ArrayList<>(allTeams);
        for(Team team: this.playedAgainst) {
            notPlayed.remove(team);
        }
        return notPlayed;
    }

    // TODO: implementera en equal funktion
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Team team = (Team) obj;
        return Objects.equals(this.teamName, team.teamName);
    }


    @Override
    public int hashCode() {
        return Objects.hash(teamName); // Skapar hash baserat på namn
    }
}
