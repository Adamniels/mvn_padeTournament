package org.example;


import org.example.padel.Match;
import org.example.padel.Preliminary;
import org.example.padel.Team;
import org.example.padel.Tournament;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static void printStartMenu() {
        System.out.println(
                "\n==== Start MENU ====\n" +
                        "(S)tart new Tournement\n" +
                        "(R)esume Tournement");

    }

    private static void printMainMenu() {
        System.out.println(
                "\n==== Tournement Started Menu ====\n" +
                        "(S)tart new round/show ongoing matches\n" +
                        "(P)rint stats\n" +
                        "(U)pdate ongoing matches\n" +
                        "(Q)uit");
    }

    private static void printPlayoffMenu() {
        System.out.println("\n==== Playoff Menu ====\n" +
                "(S)tart next playoff round\n" +
                "(P)rint stats preliminary rounds\n" +
                "(U)pdate playoff matches\n" +
                "(Q)uit");
    }

    public static void main(String[] args) {
        Tournament tournament = new Tournament(2);
        tournament.addTeam("Team1");
        tournament.addTeam("Team2");
        tournament.addTeam("Team3");
        tournament.addTeam("Team4");
        //tournament.addTeam("Team5");
        //tournament.addTeam("Team6");
        //tournament.addTeam("Team7");
        //tournament.addTeam("Team8");

        tournament.start(true, "first tournament");
        do{
            if(tournament.isPlayoffStage()){
                printPlayoffMenu();
            }else {
                printMainMenu();
            }
            // TODO: Fortsätt här eller gör klart paddel_tournament
            tournament.playNextRound();
        } while(!tournament.updateTournamentRound());
    }
}