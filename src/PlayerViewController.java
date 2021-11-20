import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class PlayerViewController implements ActionListener, ItemListener {
    private PlayerView view;
    private Player player;
    private Team team;
    public PlayerViewController(PlayerView view, Player player, Team team) {
        this.view = view;
        this.player = player;
        this.team = team;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == view.nothingButton) {
            // Set the player window back to the original size
            view.panel1.setPreferredSize(new Dimension(920,500));
            view.panel1.scrollPane.setPreferredSize(new Dimension(920, 300));
            view.panel1.repaint();
            view.panel1.revalidate();

            // hide the bits that don't need to be shown anymore
            view.panel2.setVisible(false);
            view.playerDropdown.setVisible(false);
            view.yearDropdown.setVisible(false);
        } else if (actionEvent.getSource() == view.playerButton) {
            view.playerDropdown.setVisible(true);
            view.yearDropdown.setVisible(false);
            this.shrinkPanel1();
            this.setupPanel2((Player) view.playerDropdown.getSelectedItem());
        } else if (actionEvent.getSource() == view.yearButton) {
            view.yearDropdown.setVisible(true);
            view.playerDropdown.setVisible(false);
            view.headerPanel.add(view.yearDropdown);
            this.shrinkPanel1();
            try {
                this.setupPanel2((ComboItem) view.yearDropdown.getSelectedItem());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        view.headerPanel.repaint();
        view.headerPanel.revalidate();
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        if (itemEvent.getSource() == view.playerDropdown) {
            setupPanel2((Player) itemEvent.getItem());
        } else if (itemEvent.getSource() == view.yearDropdown) {
            try {
                setupPanel2((ComboItem) itemEvent.getItem());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void shrinkPanel1() {
        view.panel1.setPreferredSize(new Dimension(520,500));
        view.panel1.scrollPane.setPreferredSize(new Dimension(520, 300));
        view.panel1.repaint();
        view.panel1.revalidate();
    }

    private void setupPanel2(Player player) {
        view.panel2.setPlayer(player);
        view.panel2.setPreferredSize(new Dimension(520,500));
        view.panel2.scrollPane.setPreferredSize(new Dimension(520, 300));
        view.panel2.setVisible(true);
    }

    private void setupPanel2(ComboItem item) throws IOException {
        Player temp = WebScraper.getYearPlayer(item.getValue(), item.getLabel());
        view.panel2.setPlayer(temp);
        view.panel2.setPreferredSize(new Dimension(520,500));
        view.panel2.scrollPane.setPreferredSize(new Dimension(520, 300));
        view.panel2.setVisible(true);
    }
}
