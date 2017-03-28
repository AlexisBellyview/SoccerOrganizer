package com.teamtreehouse.model;

import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Teams implements Comparable<Teams>{

  private String mTeamName;
  private String mCoach;
  private List<Player> mPlayers;
  private Map<Integer,Player>  byHeight;
  private Map<String, Integer> playerXP;
  private int xpCount;
  private int noXPCount;
  

  
  public Teams(String teamName, String coach){
    mTeamName = teamName;
    mCoach = coach;
    mPlayers = new ArrayList<Player>();
    byHeight = new TreeMap<>();
    playerXP = new TreeMap<>();
   

  } 
  @Override
  public String toString(){
  return "Team name: " + mTeamName + "  Coach name: " + mCoach;
    
  }
  
  public String getTeamName(){
    return mTeamName;
  }

  public String getCoachName(){
    return mCoach;
  
  
  }
  

  
    @Override
  public int compareTo(Teams other) {
    if(equals(other)){
    return 0;
    
    }
    return mTeamName.compareTo(other.getTeamName());
  
}
  
  public Player getCurrentPlayer(List<Player> mPlayers, int x){
    Player activePlayer = mPlayers.get(x);
    return activePlayer;
  
  }

  public List<Player> getPlayerSet(){
    return mPlayers;
  }
  
  public void addPlayer(Player player){
    mPlayers.add(player);
  
  }
  public void removePlayer (int rp){
    mPlayers.remove(rp);
  
  }
  
  public Map<Integer, Player> mapByHeight(){
    int H=0;
    Integer h = new Integer(H);
    for (Player playa : mPlayers){
      H = playa.getHeightInInches();
      h = H;
      byHeight.put(h, playa);
    
    }

  return byHeight;
  }
 
  public Map<String, Integer> teamXP(){
    xpCount = 0;
    noXPCount = 0;
    for (Player player : mPlayers){
      if (player.isPreviousExperience() == true){
          xpCount++;
      }
      playerXP.put("Has Exp",xpCount);
      playerXP.put("No Exp",mPlayers.size() - xpCount);
    }
    return playerXP;
  }
  
  
}