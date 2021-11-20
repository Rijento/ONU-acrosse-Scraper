import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.Console;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class TeamViewController implements ItemListener, ListSelectionListener {
    TeamView view;
    Team currentTeam;
    boolean bounceflag = true;

    public TeamViewController(TeamView view) throws IOException, InterruptedException {
        this.view = view;
        this.currentTeam = WebScraper.getYearTeam("2015-16");
    }

    public TeamViewController(TeamView view, Team team) {
        this.view = view;
        this.currentTeam = team;
    }

    public void setFirstDisplay() {
        this.view.playerTableItemModel.setPlayers(currentTeam.getPlayers());
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.view.yearDropdown) {
            String year = itemEvent.getItem().toString();
            if (!currentTeam.year.equals(year)) {
                try {
                    currentTeam = WebScraper.getYearTeam(year);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.view.playerTableItemModel.setPlayers(currentTeam.getPlayers());
                this.view.playerTable.repaint();
                this.view.playerTable.revalidate();
            }
        } else if (itemEvent.getSource() == this.view.sortingDropdown) {
            Comparator<Player> playerComparator;
            switch (itemEvent.getItem().toString()) {
                case "Name":
                    playerComparator = Collections.reverseOrder(Comparator.comparing(Player::getName));
                    break;
                case "Games":
                    playerComparator = Comparator.comparing(Player::getGameCount);
                    break;
                case "Assists":
                    playerComparator = Comparator.comparing(Player::getAssists);
                    break;
                case "Goals":
                    playerComparator = Comparator.comparing(Player::getGoalsScored);
                    break;
                case "Ground Balls":
                    playerComparator = Comparator.comparing(Player::getGroundBalls);
                    break;
                case "Turnovers":
                    playerComparator = Comparator.comparing(Player::getTurnovers);
                    break;
                case "Turnovers Caused":
                    playerComparator = Comparator.comparing(Player::getTurnoversCaused);
                    break;
                default:
                    playerComparator = Collections.reverseOrder(Comparator.comparing(Player::getNumber));
                    break;
            }
            Collections.sort(currentTeam.getPlayers(), Collections.reverseOrder(playerComparator));
            this.view.playerTableItemModel.setPlayers(currentTeam.getPlayers());
            this.view.playerTable.repaint();
            this.view.playerTable.revalidate();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {

        if (bounceflag) { // For some reason it was opening two new windows whenever you clicked on a player.
            int i = this.view.playerTable.getSelectedRow();
            Player selected = this.view.playerTableItemModel.getPlayer(i);
            try {
                PlayerView view = new PlayerView(selected, currentTeam);
                view.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bounceflag = false;
        } else {
            bounceflag = true;
        }
    }
}
