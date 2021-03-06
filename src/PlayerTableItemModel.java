import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class PlayerTableItemModel extends AbstractTableModel {
    ArrayList<Player> players;
    public PlayerTableItemModel(ArrayList<Player> players) {
        super();
        this.players = players;
    }

    public void setPlayers(ArrayList<Player> players) { this.players = players;}

    @Override
    public int getRowCount() {
        return players.size();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Number";
            case 1:
                return "Name";
            case 2:
                return "Games";
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
        Player player = players.get(i);
        switch (i1) {
            case 0:
                return player.getNumber();
            case 1:
                return player.getName();
            case 2:
                return player.getGameCount();
            case 3:
                return player.getAssists();
            case 4:
                return player.getGoalsScored();
            case 5:
                return player.getGroundBalls();
            case 6:
                return player.getTurnovers();
            case 7:
                return player.getTurnoversCaused();

        }
        return null;
    }

    public Player getPlayer(int i) {
        return players.get(i);
    }


}
