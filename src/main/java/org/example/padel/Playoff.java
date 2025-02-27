package org.example.padel;

import org.example.library.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Playoff implements TournamentStage{
    private List<Match> ongoing;
    private boolean bronzeMatch;
    private Utils.PlayoffRound currentRound;


    public Playoff(){
        this.ongoing = new ArrayList<>();
        this.currentRound = null;
        bronzeMatch = true;
    }

    @Override
    public void start(List<Team> teams) {
        int teamSize = teams.size();
        if(teamSize < 4) {
            Match finalMatch = new Match(teams.get(0), teams.get(1));
            this.ongoing.add(finalMatch);
            this.currentRound = Utils.PlayoffRound.FINAL;
            bronzeMatch = false;
        }else if(teamSize < 8) {
            Match semi1 = new Match(teams.get(0), teams.get(3));
            Match semi2 = new Match(teams.get(1), teams.get(2));
            this.ongoing.add(semi1);
            this.ongoing.add(semi2);
            this.currentRound = Utils.PlayoffRound.SEMIFINALS;
        }else {
            Match querter1 = new Match(teams.get(0), teams.get(7));
            Match querter2 = new Match(teams.get(1), teams.get(6));
            Match querter3 = new Match(teams.get(2), teams.get(5));
            Match querter4 = new Match(teams.get(3), teams.get(4));
            this.ongoing.add(querter1);
            this.ongoing.add(querter2);
            this.ongoing.add(querter3);
            this.ongoing.add(querter4);
            this.currentRound = Utils.PlayoffRound.QUARTERFINALS;
        }
    }

    @Override
    public void playRound() {
        switch (currentRound) {
            case FINAL:
                System.out.println("FINAL: " + this.ongoing.getFirst());
                if (bronzeMatch) {
                    System.out.println("BRONZE FINAL: " + this.ongoing.getLast());
                }
                break;

            case SEMIFINALS:
                System.out.println("SEMIFINALS: " + this.ongoing);
                break;

            case QUARTERFINALS:
                System.out.println("QUARTERFINALS: " + this.ongoing);
                break;

            case FINISHED:
                System.out.println("Tournament is already finished!");
                break;

            default:
                System.out.println("Unknown round state!");
                break;
        }
    }

    // helper updateRound()
    private static List<Match> pickWinnerAndCreateMatches(List<Match> playedMathes, boolean bronzeMatch, Utils.PlayoffRound currentRound){
        List<Match> nextMatches = new ArrayList<>();
        List<Team> winners = new ArrayList<>();
        List<Team> losers = new ArrayList<>();

        for (Match match: playedMathes){
            String winner = match.getWinner();
            if(winner.equals("team1")){
                winners.add(match.getTeam1());
                losers.add(match.getTeam2());
            }else if(winner.equals("team2")){
                winners.add(match.getTeam2());
                losers.add(match.getTeam1());
            }else{
                throw new RuntimeException("Cant have a draw in playoff");
            }
        }
        for(int i = 0; i < winners.size(); i=i+2){
            Match newMatch = new Match(winners.get(i), winners.get(i+1));
            nextMatches.add(newMatch);
        }
        if(currentRound == Utils.PlayoffRound.SEMIFINALS && bronzeMatch){
            for(int i = 0; i < losers.size(); i=i+2){
                Match newMatch = new Match(losers.get(i), losers.get(i+1));
                nextMatches.add(newMatch);
            }
        }


        return nextMatches;
    }

    private static void printResultTournament(List<Match> lastRoundMatches){
        Match finalMatch = lastRoundMatches.getFirst();
        lastRoundMatches.removeFirst();
        String winner = finalMatch.getWinner();
        String loser = "";
        if(winner.equals("team1")){
            winner = finalMatch.getTeam1().getTeamName();
            loser = finalMatch.getTeam2().getTeamName();
        }else if(winner.equals("team2")){
            winner = finalMatch.getTeam2().getTeamName();
            loser = finalMatch.getTeam1().getTeamName();
        }else{
            throw new RuntimeException("Cant have a draw in playoff");
        }
        System.out.println("TOURNAMENT RESULT:");
        System.out.println("1: " + winner);
        System.out.println("2. " + loser);
        

        if(!lastRoundMatches.isEmpty()){
            Match bronceMatch = lastRoundMatches.getFirst();
            lastRoundMatches.removeFirst();
            String winnerBronce = bronceMatch.getWinner();
            if(winnerBronce.equals("team1")){
                winnerBronce = bronceMatch.getTeam1().getTeamName();
            }else if(winnerBronce.equals("team2")){
                winnerBronce = bronceMatch.getTeam2().getTeamName();
            }else{
                throw new RuntimeException("Cant have a draw in playoff");
            }
            System.out.println("3. " + winnerBronce);
        }


    }

    @Override
    public boolean updateRound() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < ongoing.size(); i++) {
            Match match = ongoing.get(i);
            System.out.println("Score " + match);
            System.out.print("team " + match.getTeam1() + ": ");
            int team1score = scanner.nextInt();

            System.out.print("team " + match.getTeam2() + ": ");
            int team2score = scanner.nextInt();
            System.out.println("");

            match.updateMatch(team1score, team2score);

        }


        switch (currentRound) {
            case FINAL:
                printResultTournament(this.ongoing);
                this.currentRound = Utils.PlayoffRound.FINISHED;
                return true;

            case SEMIFINALS:
                this.ongoing = pickWinnerAndCreateMatches(this.ongoing, this.bronzeMatch, this.currentRound);
                this.currentRound = Utils.PlayoffRound.FINAL;
                return false;

            case QUARTERFINALS:
                this.ongoing = pickWinnerAndCreateMatches(this.ongoing, this.bronzeMatch, this.currentRound);
                this.currentRound = Utils.PlayoffRound.SEMIFINALS;
                return false;

            default:
                System.out.println("Unknown round state!");
                return false;
        }
    }

    @Override
    public boolean isPlayoff() {
        return true;
    }
}
