import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

public class TestView1 extends JFrame {
    JButton test;
    JTable playerTable;
    TestController1 controller;
    PlayerTableItemModel playerTableItemModel;
    JComboBox yearDropdown;
    public TestView1() throws IOException, InterruptedException {
        super();
        this.controller = new TestController1(this);
//        this.test = new JButton("test");
        String[] options = {"2015-16", "2016-17", "2017-18", "2018-19", "2019-20", "2020-21"};
        this.yearDropdown = new JComboBox(options);
        this.yearDropdown.addItemListener(controller);

        this.playerTable = new JTable();
        this.playerTableItemModel = new PlayerTableItemModel(new ArrayList());

        this.playerTable.setModel(playerTableItemModel);
        this.playerTable.getSelectionModel().addListSelectionListener(controller);

        JScrollPane scrollPane = new JScrollPane(playerTable);

        this.setLayout(new FlowLayout());

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

//        this.add(test);
        this.add(yearDropdown);
        this.add(scrollPane);
        this.setSize(400, 300);

        this.controller.setFirstDisplay();
    }


}
