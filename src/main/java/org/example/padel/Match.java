package org.example.padel;

public class Match {
    private Team team1;
    private Team team2;
    private int team1pts;
    private int team2pts;

    public Match(Team team1, Team team2){
        this.team1 = team1;
        this.team2 = team2;
        this.team1pts = 0;
        this.team2pts = 0;
    }

    public void updateMatch(int team1pts, int team2pts){
        this.team1pts = team1pts;
        this.team2pts = team2pts;
    }

    public String getWinner(){
        if(team1pts > team2pts){
            return "team1";
        }else if(team2pts > team1pts){
            return "team2";
        }else {
            return "draw";
        }
    }

    public int getDiffScore(){
       return Math.abs(team1pts-team2pts);
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    @Override
    public String toString(){
        return team1 + " vs " + team2;
    }
}
