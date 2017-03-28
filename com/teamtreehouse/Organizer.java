package com.teamtreehouse;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;

import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Teams;

public class Organizer{
  private Player[] mPlayers;
  private Teams mTeam;
  private BufferedReader mReader;
  private Map<String,String> mMenu;
  private List<Player> mAvailablePlayers = new ArrayList<Player>();
  private List<Player> mWaitingPlayers = new ArrayList<Player>();
  public List<Teams> mTeams;
  public List<Player> playerArray;
  private Map<Integer, Player> pbh;
  
  
  public Organizer(){
    mReader = new BufferedReader(new InputStreamReader(System.in));
    mMenu = new HashMap<String, String>();
    mTeams = new ArrayList<Teams>();
    pbh = new TreeMap<>();
    
    //add options to our menu
    
    playerArray =  Arrays.asList(Players.load());
    mMenu.put("New,   ", "Create a new team ");
    mMenu.put("Add,   ", "Add a player to your team ");
    mMenu.put("Remove,", "Take a player out of a team");
    mMenu.put("HR,    ", "Display active teams by height");
    mMenu.put("XP,    ", "Show active player experience");
    mMenu.put("Quit,  ", "Exit the program ");
    mMenu.put("Report ", "View a report of active teams.");
  }
  
  //Prompts for main menu options
  
  private String promptAction() throws IOException {
    System.out.printf("%n***Main Menu***%n");
    for (Map.Entry<String,String> option : mMenu.entrySet()){
      System.out.printf("Type %s to %s %n",
                        option.getKey(),
                        option.getValue());
    }
    System.out.print("What do you want to do?     ");
    String choice = mReader.readLine();
    return choice.trim().toLowerCase();
  
  }
  
  //remove players from the team
  
  private int removePlayers(Teams teamB) throws IOException{
    
    List<Player> currentTeamPlayers = teamB.getPlayerSet();
    int p = 1;
    for (Player playaz : currentTeamPlayers){
      System.out.printf("%d.  %s%n",p,playaz.toString());
      p++;    
    }
    String pickk = mReader.readLine();
    int pickasint = Integer.parseInt(pickk) -1;
    return pickasint;
    
  }
  
  // Print out all the teams
  
  private int showTeams() throws IOException{
    int i = 1;
    Collections.sort(mTeams);
    for (Teams iTeams : mTeams){
    System.out.printf("%d.)   %s%n",i,iTeams.toString());
      i++;
    }
    String pick = mReader.readLine();
    int pickAsInt = Integer.parseInt(pick) -1;
    return pickAsInt;
    
  }
  
  //print out all players
  
  private int showPlayers() throws IOException{
  int in = 1;
    Collections.sort(playerArray);
    for(Player play : playerArray){
    System.out.printf("%d.)   %s %n",in,play.toString());
      in++;
    }
    String playerChoice = mReader.readLine();
    int intPN = Integer.parseInt(playerChoice) - 1;
    return intPN;
  }

   
  //prompt for the name of your team and coach
  
  private Teams promptNewTeam() throws IOException{
    System.out.printf("Name your team  %n");
    String teamName = mReader.readLine();
    System.out.printf("Enter your coach name  %n");
    String coach = mReader.readLine();
    return new Teams(teamName, coach);
    
  }
  
  
  
  private Teams getTeamFromList(int i){
    Teams activeTeam = mTeams.get(i);
    return activeTeam;
  
  }
  
  private Player removePlayerFT(Teams team, int z){
    List<Player> zPlayers = team.getPlayerSet();
    Player zPlayer = zPlayers.get(z);
    return zPlayer;
  
  
  }
  
  //print out players by height
  
  private void showPlayersByHeight(Teams teamC){
    pbh = teamC.mapByHeight();
    for (Map.Entry<Integer, Player> byInches : pbh.entrySet()){
      System.out.printf("%d %s%n%n", byInches.getKey(), byInches.getValue());
    
    }
  
  }
  
  //print out players by experience
  
  public void XPReport(Teams teamD){
      Map<String,Integer> xpPlayers = new TreeMap();
      xpPlayers = teamD.teamXP();
      System.out.println("Experience report");
        for (Map.Entry<String, Integer> set : xpPlayers.entrySet()){
            System.out.printf("%s %d", set.getKey(), set.getValue());
        
        }
    
    }
  
  //show teams
  private void teamReport(Teams teamE){
    for(Player player : teamE.getPlayerSet()){
      System.out.printf(" %s %s %n", player.getFirstName(),player.getLastName());
    
    }
      
    }
  
  
  

  public void run(){
    String choice = "";
    do {
        try{
      choice = promptAction();
          switch(choice){
            case "new":
              //Add a player
              Teams team = promptNewTeam();
              mTeams.add(team);
            break;
            
            case "add":
              if (mTeams.size() == 0){
                System.out.println("Please create a team first");
                break;
              }
              System.out.println("Choose a team to add to ");
              int i = showTeams();
              
              Teams teamA = getTeamFromList(i);
              System.out.printf("%s chosen! %n", teamA);
              System.out.println("Current players in cycle");
              int currentPlayer = showPlayers();
              Player playerB = playerArray.get(currentPlayer);
              teamA.addPlayer(playerB);
              System.out.printf("Added %s to %s",playerB,teamA);
            break;
            
            case "remove":
              if (mTeams.size() == 0){
                System.out.println("Please create a team first");
                break;
              }
              System.out.println("Choose a Team to remove from");
              int z = showTeams();
              Teams teamB = getTeamFromList(z);
              int removeOne = removePlayers(teamB);
              Player playerC = removePlayerFT(teamB, removeOne);
              teamB.removePlayer(removeOne);
              System.out.printf("Removing %s",playerC );

            break;
            
            case "hr":
              if (mTeams.size() == 0){
                System.out.println("Please create a team first");
                break;
              }
              System.out.println("Current Height report");
              int heightTeams = showTeams();
              Teams teamC = getTeamFromList(heightTeams);
              showPlayersByHeight(teamC);
            break;
            
            case "xp":
              if (mTeams.size() == 0){
                System.out.println("Please create a team first");
                break;
              }
              System.out.println("Active XP:");
              int xpTeam = showTeams();
              Teams teamD = getTeamFromList(xpTeam);
              XPReport(teamD);
            break;
            
            case "report":
              if(mTeams.size() == 0){
              System.out.println("Please create a team first");
                    break;
            }
              System.out.println("Choose a Team to view");
              int r = showTeams();
              Teams teamE = getTeamFromList(r);
              System.out.println("Active team");
              teamReport(teamE);
            break;
              
            case "quit":
              System.out.println("Thanks for playing!!!!");
            break;
            
            default:
              System.out.printf("Unknown choice");
          
          
          }
        } catch(IOException ioe) {
        System.out.println("Problem with input");
        ioe.printStackTrace();
        }
    } 
  while (!choice.equals("quit"));
  }


}