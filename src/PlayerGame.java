public class PlayerGame {
    private String date;
    private String opponent;
    private String score;
    private int goalsScored;
    private int assists;
    private int turnovers;
    private int turnoversCaused;
    private int groundBalls;

    PlayerGame() {

    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getOpponent() {
        return opponent;
    }

    public String getScore() {
        return score;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getAssists() {
        return assists;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public int getTurnoversCaused() {
        return turnoversCaused;
    }

    public int getGroundBalls() {
        return groundBalls;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public void setGroundBalls(int groundBalls) {
        this.groundBalls = groundBalls;
    }

    public void setTurnovers(int turnovers) {
        this.turnovers = turnovers;
    }

    public void setTurnoversCaused(int turnoversCaused) {
        this.turnoversCaused = turnoversCaused;
    }
}
