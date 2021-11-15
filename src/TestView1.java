import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TestView1 extends JFrame {
    JButton test;
    JButton test2;
    TestController1 controller;
    public TestView1() {
        super();
        this.controller = new TestController1(this);
        this.test = new JButton("test");
        this.test2 = new JButton("test");
        this.test.addActionListener(this.controller);
        this.setLayout(new FlowLayout());

        this.add(test);
        this.setSize(400, 300);
    }
}
