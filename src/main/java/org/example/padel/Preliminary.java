package org.example.padel;

import java.util.*;

public class Preliminary implements TournamentStage {
    private List<Team> teams;
    private List<Match> ongoingMatches;
    private List<Match> allMatches;
    private int playedMatches;
    private int totalMatches; // TODO calculate this inside contructor
    private int courts;

    public Preliminary(int courts){
        this.ongoingMatches = new ArrayList<>();
        this.allMatches = new ArrayList<>();
        this.playedMatches = 0;
        this.totalMatches = 0;
        this.courts = courts;
    }

    @Override
    public void start(List<Team> teams) {
        this.teams = teams;
        this.totalMatches = countMatches(teams.size(), this.courts);

        // creates matches based on all the teams, every team meet each other one time
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                Match match = new Match(teams.get(i), teams.get(j));
                this.allMatches.add(match);
            }
        }
        Collections.shuffle(this.allMatches);

    }

    // helper playRound()
    static private Match findNextMatch(List<Match> matches, List<Team> unavailableTeams){
        int leastPlayed = Integer.MAX_VALUE;
        int leastPlayedAgainst = Integer.MAX_VALUE;
        Team against = null;
        Match leastPlayedMatch = null;
        for(Match match: matches){
            // check if team already is playing
            // TODO: måste priotera matcher som är mot motståndare med lägst antal spelade matcher

            if(!unavailableTeams.contains(match.getTeam1()) && !unavailableTeams.contains(match.getTeam2())) {
                int played = match.getTeamLeastPlayed();
                if (played < leastPlayed) {
                    leastPlayed = played;
                    leastPlayedMatch = match;
                }
            }
        }
        return leastPlayedMatch;
    }

    static private List<Match> nextRoundMatches(List<Match> allMatches, int courts){
        List<Team> unavailableTeams = new ArrayList<>();
        List<Match> roundMatches = new ArrayList<>();
        for(int i = 0; i < courts; i++){
            Match newMatch = findNextMatch(allMatches, unavailableTeams);
            if(newMatch != null){
                roundMatches.add(newMatch);
                allMatches.remove(newMatch);
                unavailableTeams.add(newMatch.getTeam1());
                unavailableTeams.add(newMatch.getTeam2());
            }
        }
        return roundMatches;
    }

    @Override
    public void playRound() {
        // har mina nästa matcher, men ska prioritera dem så att dem matcher med lag som har minst spelade, spelas först
        this.ongoingMatches = nextRoundMatches(this.allMatches, this.courts);

        System.out.println(this.ongoingMatches);
    }



    @Override
    public boolean updateRound() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < ongoingMatches.size(); i++) {
            Match match = ongoingMatches.get(i);
            System.out.println("Score " + match);
            System.out.print("team " + match.getTeam1() + ": ");
            int team1score = scanner.nextInt();

            System.out.print("team " + match.getTeam2() + ": ");
            int team2score = scanner.nextInt();
            System.out.println("");

            match.updateMatch(team1score, team2score);

            String winner = match.getWinner();
            int scoreDiff = match.getDiffScore();
            if (winner.equals("team1")) {
                // team1 vann
                match.getTeam1().updateTeam(scoreDiff, 1, match.getTeam2());
                match.getTeam2().updateTeam(-scoreDiff, 0, match.getTeam1());
            } else if (winner.equals("team2")) {
                // team 2 vann
                match.getTeam2().updateTeam(scoreDiff, 1, match.getTeam1());
                match.getTeam1().updateTeam(-scoreDiff, 0, match.getTeam2());
            } else {
                // draw
                match.getTeam1().updateTeam(scoreDiff, 0, match.getTeam2());
                match.getTeam2().updateTeam(scoreDiff, 0, match.getTeam1());
            }
        }

        // remove all ongoing matches
        this.playedMatches += ongoingMatches.size();
        this.ongoingMatches.clear();
        return this.allMatches.isEmpty();

    }

    @Override
    public boolean isPlayoff() {
        return false;
    }


    private static int countMatches(int teams, int courts) {
        int matches = (teams * (teams - 1)) / 2;
        return matches;
    }
    private static int countPrelRounds(int teams, int courts) {
        int matches = (teams * (teams - 1)) / 2;
        System.out.println("matches: " + matches);
        int rounds = (int) ((matches / 2.0) + 0.5);
        return rounds;
    }
}

// byter sätt att göra det på
// helper playRound()
//static private List<Match> NextRoundMatches(List<Team> teams, int courts, int matchesLeft){
//    // sort all teams based on number of played matches
//    List<Team> allTeams = new ArrayList<>(teams);
//    allTeams.sort(Comparator.comparingInt(Team::getPlayedMatches));
//    System.out.println("sorterad: " + allTeams);
//
//    List<Match> matches = new ArrayList<>();
//    int maximumMatches = allTeams.size() - 1;
//
//    // ta ett lag i taget hitta en motståndare med minst antal spelade matcher och som laget inte mött
//    for(int i = 0; i < courts; i++){
//
//        Team team1 = allTeams.getFirst();
//        allTeams.removeFirst();
//        Team team2 = null;
//
//
//        // TODO: problem för att det kan bli så att jag i första tar en motståndare som hade passat bättre i andra
//        // kanske kan göra så att jag har alla lag jag INTE mött i en lista istället och väljer ut från den, på så
//        // sätt borde jag kunna kolla ifall bådas motståndare krockar eller inte, kolla med chat om han har några ideer.
//
//        if(team1.getPlayedMatches() < maximumMatches) {// max number of matches for each team
//
//        }
//    }
//    return matches;
//}