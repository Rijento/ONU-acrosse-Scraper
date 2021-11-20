import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerViewController implements ActionListener {
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
}
