import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.io.IOException;

public class PlayerPanel extends JPanel {
    private Player player;
    JScrollPane scrollPane;
    JTable gamesTable;
    GameTableItemModel model;
    JLabel number, name, height, weight; // Basic Stat Labels
    JLabel assists, goals, groundBalls, turnovers, tCaused; // Aggregate stat labels

    public PlayerPanel(Player player) { // Pass in a player. Easy, but needs parent to have the player instantiated
        super();
        this.player = player;
        this.setupPanel();
    }
    public PlayerPanel(String identifier, String year) throws IOException { // Pass in a player's year & identifier.
        super();
        this.player = WebScraper.getYearPlayer(identifier, year);
        this.setupPanel();
    }

    public void setPlayer(Player player) {
        this.player = player;

        // Put the new basic stats into their labels
        this.number.setText(String.valueOf(player.getNumber()));
        this.name.setText(player.getName());
        this.height.setText(player.getHeight());
        this.weight.setText(String.valueOf(player.getWeight()));

        // Put the new games into the table
        this.model.setGames(player.getGames());

        // Redo the totals
        this.assists.setText(String.valueOf(player.getAssists()));
        this.goals.setText(String.valueOf(player.getGoalsScored()));
        this.groundBalls.setText(String.valueOf(player.getGroundBalls()));
        this.turnovers.setText(String.valueOf(player.getTurnovers()));
        this.tCaused.setText(String.valueOf(player.getTurnoversCaused()));

        // Repaint the Table
        this.gamesTable.repaint();
        this.gamesTable.revalidate();
    }

    private void setupPanel() { // Putting this in its own function to make things nice and clean
        this.setLayout(new FlowLayout(FlowLayout.LEFT)); // we are using a flow layout. Left aligned this time
        JPanel basicStuff = new JPanel();
        basicStuff.setLayout(new GridLayout(0, 2)); // One column for the stat name, one for the actual stat

        number = new JLabel(String.valueOf(player.getNumber()));
        name = new JLabel(player.getName());
        height = new JLabel(player.getHeight());
        weight = new JLabel(String.valueOf(player.getWeight()));

        basicStuff.add(new JLabel("Number: "));
        basicStuff.add(number);
        basicStuff.add(new JLabel("Name: "));
        basicStuff.add(name);
        basicStuff.add(new JLabel("Height: "));
        basicStuff.add(height);
        basicStuff.add(new JLabel("Weight: "));
        basicStuff.add(weight);
        this.add(basicStuff);

        // Add table of games
        gamesTable = new JTable();
        gamesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model = new GameTableItemModel(player.getGames());
        gamesTable.setModel(model);

        // Set the column Widths
        TableColumnModel columnModel = gamesTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(75);
        columnModel.getColumn(1).setPreferredWidth(125);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(5).setPreferredWidth(125);
        columnModel.getColumn(6).setPreferredWidth(125);
        columnModel.getColumn(7).setPreferredWidth(150);

        //Set up the scrollpane for the table
        scrollPane = new JScrollPane(gamesTable); // need to put table in a scroll pane to make header show.
        scrollPane.setPreferredSize(new Dimension(920, 300));
        this.add(scrollPane);

        //Totals Labels
        assists = new JLabel(String.valueOf(player.getAssists()));
        goals = new JLabel(String.valueOf(player.getGoalsScored()));
        groundBalls = new JLabel(String.valueOf(player.getGroundBalls()));
        turnovers = new JLabel(String.valueOf(player.getTurnovers()));
        tCaused = new JLabel(String.valueOf(player.getTurnoversCaused()));

        //Totals Section
        JPanel totals = new JPanel();
        totals.setLayout(new GridLayout(0,4));
        totals.add(new Label("Assists: "));
        totals.add(assists);
        totals.add(new Label("Goals: "));
        totals.add(goals);
        totals.add(new Label("Ground Balls: "));
        totals.add(groundBalls);
        totals.add(new Label("Turnovers: "));
        totals.add(turnovers);
        totals.add(new Label("Turnovers Caused: "));
        totals.add(tCaused);

        this.add(totals);

        this.setPreferredSize(new Dimension(920, 500));
    }
}
