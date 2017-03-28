import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Teams;
import com.teamtreehouse.Organizer;

public class LeagueManager {
  private Organizer bae;
  
  
  public static void main(String[] args) {
    Player[] players = Players.load();
    System.out.printf("There are currently %d registered players.%n", players.length);
    // Your code here!
    Organizer bae = new Organizer();
    bae.run();
    
  }

}
