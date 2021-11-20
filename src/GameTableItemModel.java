import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class GameTableItemModel extends AbstractTableModel {
    ArrayList<PlayerGame> games;
    public GameTableItemModel(ArrayList<PlayerGame> games) {
        super();
        this.games = games;
    }

    public void setGames(ArrayList<PlayerGame> games) {
        this.games = games;
    }

    @Override
    public int getRowCount() {
        return games.size();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Date";
            case 1:
                return "Opponent";
            case 2:
                return "Score";
            case 3:
                return "Assists";
            case 4:
                return "Goals";
            case 5:
                return "Ground Balls";
            case 6:
                return "Turnovers";
            case 7:
                return "Turnovers Caused";
        }
        return "???";
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        PlayerGame game = games.get(i);
        switch (i1) {
            case 0:
                return game.getDate();
            case 1:
                return game.getOpponent();
            case 2:
                return game.getScore();
            case 3:
                return game.getAssists();
            case 4:
                return game.getGoalsScored();
            case 5:
                return game.getGroundBalls();
            case 6:
                return game.getTurnovers();
            case 7:
                return game.getTurnoversCaused();
        }
        return null;
    }
}
