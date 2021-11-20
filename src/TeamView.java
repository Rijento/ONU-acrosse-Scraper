import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class TeamView extends JFrame {
    JTable playerTable;
    TeamViewController controller;
    PlayerTableItemModel playerTableItemModel;
    JComboBox yearDropdown;
    JComboBox sortingDropdown;
    public TeamView() throws IOException, InterruptedException {
        // Setting up the class
        super();
        this.setResizable(false); // We don't want this to be resizable
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // We want the program to close if the window does
        this.setLayout(new FlowLayout()); // we are using a flow layout
        controller = new TeamViewController(this);
        this.setupView();
        controller.setFirstDisplay();
    }

    public TeamView(Team teamIn) { // This is going to be useful when going BACK from the player view
        // Setting up the class
        super();
        this.setResizable(false); // We don't want this to be resizable
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // We want the program to close if the window does
        this.setLayout(new FlowLayout()); // we are using a flow layout
        controller = new TeamViewController(this, teamIn);
        this.setupView();
        controller.setFirstDisplay();
    }

    private void setupView() {
        // Setting up the Year Dropdown
        String[] yearOptions = {"2015-16", "2016-17", "2017-18", "2018-19", "2019-20", "2020-21"};
        yearDropdown = new JComboBox(yearOptions);
        yearDropdown.addItemListener(controller);

        // Setting up the Sorting Dropdown
        String[] statOptions = {"Number", "Name", "Games", "Assists", "Goals", "Ground Balls", "Turnovers", "Turnovers Caused"};
        sortingDropdown = new JComboBox(statOptions);
        sortingDropdown.addItemListener(controller);

        // Setting up the Player Table
        playerTable = new JTable();
        playerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        playerTableItemModel = new PlayerTableItemModel(new ArrayList());
        playerTable.setModel(playerTableItemModel);
        playerTable.getSelectionModel().addListSelectionListener(controller);

        // Set column Widths
        TableColumnModel columnModel = playerTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(75);
        columnModel.getColumn(1).setPreferredWidth(125);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(5).setPreferredWidth(125);
        columnModel.getColumn(6).setPreferredWidth(125);
        columnModel.getColumn(7).setPreferredWidth(150);

        JScrollPane scrollPane = new JScrollPane(playerTable);
        scrollPane.setPreferredSize(new Dimension(920, 400));
        JLabel spacer = new JLabel();
        spacer.setPreferredSize(new Dimension(50, 25));

        this.add(new JLabel("Year: "));
        this.add(yearDropdown);
        this.add(spacer);
        this.add(new JLabel("Sort By:"));
        this.add(sortingDropdown);
        this.add(scrollPane);
        this.setSize(920, 475);
    }

}
