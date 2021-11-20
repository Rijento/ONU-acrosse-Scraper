import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerView extends JFrame {
    private PlayerViewController controller;
    JRadioButton nothingButton;
    JRadioButton playerButton;
    JRadioButton yearButton;
    JComboBox playerDropdown;
    JComboBox yearDropdown;
    JPanel headerPanel;
    public PlayerView(Player player, Team team) throws IOException, InterruptedException {
        super();
        this.setResizable(false); // We don't want this to be resizable
        this.setLayout(new FlowLayout());
        controller = new PlayerViewController(this);

        // Create radio buttons
        nothingButton = new JRadioButton("Nothing");
        nothingButton.setSelected(true); // This one is selected by default
        playerButton = new JRadioButton("Different Player");
        yearButton = new JRadioButton("Different Year");

        // Add listener to the buttons
        nothingButton.addActionListener(controller);
        playerButton.addActionListener(controller);

        // Add buttons to a group so that only one can be selected at once
        ButtonGroup group = new ButtonGroup();
        group.add(nothingButton);
        group.add(playerButton);
        group.add(yearButton);

        // Add buttons to a panel with a label so that it all exists on one line
        headerPanel = new JPanel();
        headerPanel.add(new JLabel("Compare With: "));

        this.setupComboBoxes(player, team); //Only really need to use these values here thankfully

        this.add(headerPanel);

    }

    private void setupComboBoxes(Player player, Team team) throws IOException, InterruptedException {
        ArrayList<Player> players = new ArrayList();
        ArrayList<ComboItem> years = WebScraper.getPlayerYears(player.getName(), team.getYear());

        for (Player playerI : team.getPlayers()) {
            if (!playerI.getName().equals(player.getName())) { // Don't want to compare a player to themselves for same year
                players.add(playerI);
            }
        }

        // create the comboboxes
        playerDropdown = new JComboBox(players.toArray());
        yearDropdown = new JComboBox(years.toArray());

        // The view controller will handle adding them to the view when necessary, so we're done here!
    }
}
