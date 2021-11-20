import javax.swing.*;
import java.awt.*;

public class PlayerView extends JFrame {
    JRadioButton noneButton;
    JRadioButton playerButton;
    JRadioButton yearButton;
    public PlayerView(Player player, Team team) {
        super();
        this.setResizable(false); // We don't want this to be resizable
        this.setLayout(new FlowLayout());

    }
}
