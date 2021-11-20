import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PlayerViewController implements ActionListener, ItemListener {
    private PlayerView view;
    private Player player;
    private Team team;
    public PlayerViewController(PlayerView view, Player player, Team team) {
        this.view = view;
        this.player = player;
        this.team = team;
    }

    public void setFirstDisplay() {
        view.add(new PlayerPanel(player));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {

    }
}
