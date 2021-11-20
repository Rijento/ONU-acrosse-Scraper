import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PlayerPanel extends JPanel {
    private Player player;
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
        this.setLayout(new FlowLayout()); // we are using a flow layout
    }
}
