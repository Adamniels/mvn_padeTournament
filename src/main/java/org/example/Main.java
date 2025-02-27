package org.example;


import org.example.library.Utils;
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

    private static void eventLoop(){
        Tournament tournament = null;

        printStartMenu();
        char ans = Utils.getAnswer("SR");
        switch (ans) {
            case 'S':
                tournament = new Tournament(2);
                tournament.addTeam("Team1");
                tournament.addTeam("Team2");
                tournament.addTeam("Team3");
                tournament.addTeam("Team4");
                //tournament.addTeam("Team5");
                //tournament.addTeam("Team6");
                //tournament.addTeam("Team7");
                //tournament.addTeam("Team8");

                tournament.start(true, "First tournament");
                break;

            case 'R':
                // TODO: resume existing one(how can i do this?)

                // tournement.restoreFromFile(file); något sånt kanske?
                break;

            default:
                break;
        }

        boolean ongoingGames = false;
        boolean tournementStarted = true;
        boolean finalMatch = false;
        while (tournementStarted) {
            if(tournament == null){
                throw new RuntimeException("tournament not be initilazed");
            }

            if (tournament.isPlayoffStage()) {
                printPlayoffMenu();
            } else {
                printMainMenu();
            }

            ans = Utils.getAnswer("SPUQ");
            switch (ans) {
                case 'S':
                    if (ongoingGames) {
                        System.out.println("There is already a round being played, update it before starting a new\n");
                    } else {
                        tournament.playNextRound();
                        ongoingGames = true;
                    }
                    break;

                case 'P':
                    tournament.printStanding();
                    break;

                case 'U':
                    if (ongoingGames) {
                        finalMatch = tournament.updateTournamentRound();
                        ongoingGames = false;
                        if (finalMatch) {
                            // last game
                            System.out.println("TOURNEMENT IS OVER");
                            tournementStarted = false;
                        }
                    } else {
                        System.out.println("No ongoing matches, play a new round\n");
                    }
                    break;

                case 'Q':
                    tournementStarted = false;
                    //tournament.saveTournement();
                    break;

                default:
                    break;
            }
        }

    }

    public static void main(String[] args) {
       eventLoop();
    }
}