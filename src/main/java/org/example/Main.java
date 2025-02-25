package org.example;


import org.example.padel.Match;
import org.example.padel.Preliminary;
import org.example.padel.Team;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Team team1 = new Team("team 1");
        Team team2 = new Team("team 2");
        Team team3 = new Team("team 3");
        Team team4 = new Team("team 4");
        Team team5 = new Team("team 5");
        Team team6 = new Team("team 6");
        Team team7 = new Team("team 7");
        Team team8 = new Team("team 8");

        List<Team> teamList = new ArrayList<>();
        teamList.add(team1);
        teamList.add(team2);
        teamList.add(team3);
        teamList.add(team4);
        teamList.add(team5);
        teamList.add(team6);
//        teamList.add(team7);
//        teamList.add(team8);


        Preliminary prel = new Preliminary(2);
        prel.start(teamList);

        prel.playRound();
        prel.updateRound();

        prel.playRound();
        prel.updateRound();

        prel.playRound();
        prel.updateRound();

        prel.playRound();
        prel.updateRound();

        prel.playRound();
        prel.updateRound();

        prel.playRound();
        prel.updateRound();

        prel.playRound();
        prel.updateRound();

        prel.playRound();
        prel.updateRound();


        System.out.println(team1.getPlayedMatches());
        System.out.println(team2.getPlayedMatches());
        System.out.println(team3.getPlayedMatches());
        System.out.println(team4.getPlayedMatches());
        System.out.println(team5.getPlayedMatches());
        System.out.println(team6.getPlayedMatches());
        System.out.println(team7.getPlayedMatches());
        System.out.println(team8.getPlayedMatches());

    }
}