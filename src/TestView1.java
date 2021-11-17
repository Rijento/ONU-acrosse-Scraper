import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class TestView1 extends JFrame {
    JButton test;
    JTable playerTable;
    TestController1 controller;
    PlayerTableItemModel playerTableItemModel;
    Team currentTeam;
    public TestView1() throws IOException, InterruptedException {
        super();

        this.currentTeam = WebScraper.getYearTeam("2015-16");
        this.controller = new TestController1(this);
        this.test = new JButton("test");
        this.playerTable = new JTable();
        this.playerTableItemModel = new PlayerTableItemModel(currentTeam.getPlayers());

        this.playerTable.setModel(playerTableItemModel);
        this.playerTable.getSelectionModel().addListSelectionListener(controller);

        JScrollPane scrollPane = new JScrollPane(playerTable);

        this.test.addActionListener(this.controller);
        this.setLayout(new FlowLayout());

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.add(test);
        this.add(scrollPane);
        this.setSize(400, 300);
    }


}
