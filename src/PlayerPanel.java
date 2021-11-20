import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerPanel extends JPanel {
    private Player player;
    JScrollPane scrollPane;
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

    private void setupPanel() { // Putting this in its own function to make things nice and clean
        this.setLayout(new FlowLayout(FlowLayout.LEFT)); // we are using a flow layout. Left aligned this time
        JPanel basicStuff = new JPanel();
        basicStuff.setLayout(new GridLayout(0, 2)); // One column for the stat name, one for the actual stat
        basicStuff.add(new JLabel("Number: "));
        basicStuff.add(new JLabel(String.valueOf(player.getNumber())));
        basicStuff.add(new JLabel("Name: "));
        basicStuff.add(new JLabel(player.getName()));
        basicStuff.add(new JLabel("Height: "));
        basicStuff.add(new JLabel(player.getHeight()));
        basicStuff.add(new JLabel("Weight: "));
        basicStuff.add(new JLabel(String.valueOf(player.getWeight())));
        this.add(basicStuff);

        // Add table of games
        JTable gamesTable = new JTable();
        gamesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        GameTableItemModel model = new GameTableItemModel(player.getGames());
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

        scrollPane = new JScrollPane(gamesTable); // need to put table in a scroll pane to make header show.
        scrollPane.setPreferredSize(new Dimension(920, 300));

        this.add(scrollPane);
        this.setPreferredSize(new Dimension(1220, 500));
    }
}
