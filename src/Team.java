import java.util.ArrayList;

public class Team {
    ArrayList<Player> players;
    int wins;
    int losses;
    int games;
    String year;

    Team(String year, int wins, int losses, int games) {
        this.year = year;
        this.wins = wins;
        this.losses = losses;
        this.games = games;
        this.players = new ArrayList<>();
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getGames() {
        return games;
    }

    public int getLosses() {
        return losses;
    }

    public int getWins() {
        return wins;
    }

    public String getYear() {
        return year;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

}
