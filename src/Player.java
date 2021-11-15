import java.util.ArrayList;

public class Player implements Comparable<Player> {
    private String name;
    private String height;
    private int weight;
    private int number;
    private ArrayList<PlayerGame> games;
    Player(String name, int number, ArrayList<PlayerGame> games) {
        this.name = name;
        this.number = number;
        this.games = games;
    }

    public String getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getNumber() {
        return number;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAssists() {
        int assists = 0;
        for (PlayerGame game : games) {
            assists += game.getAssists();
        }
        return assists;
    }

    public int getGoalsScored() {
        int goals = 0;
        for (PlayerGame game : games) {
            goals += game.getGoalsScored();
        }
        return goals;
    }
    public int getGroundBalls() {
        int groundBalls = 0;
        for (PlayerGame game : games) {
            groundBalls += game.getGroundBalls();
        }
        return groundBalls;
    }

    public int getTurnovers() {
        int turnovers = 0;
        for (PlayerGame game : games) {
            turnovers += game.getTurnovers();
        }
        return turnovers;
    }

    public int getTurnoversCaused() {
        int turnoversCaused = 0;
        for (PlayerGame game : games) {
            turnoversCaused += game.getTurnoversCaused();
        }
        return turnoversCaused;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Player player) {
        return this.number - player.number;
    }
}
