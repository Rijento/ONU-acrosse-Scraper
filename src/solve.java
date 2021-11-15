// Java Program to create a simple JComboBox
// and add elements to it
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

class solve extends JFrame implements ItemListener, ActionListener{

    // frame
    static JFrame f;
    static JFrame g;

    // label
    static JLabel l, l1, l2;

    // comboboxc1
    static JComboBox c1;

    public JButton b = new JButton("Click Me");
    // main class
    public static void main(String[] args)
    {
        // create a new frame
        f = new JFrame("frame");
        g = new JFrame("Frame");
        // create a object
        solve s = new solve();

        // set layout of frame
        f.setLayout(new FlowLayout());
        g.setLayout(new FlowLayout());

        // array of string containing cities
        String s1[] = { "Jalpaiguri", "Mumbai", "Noida", "Kolkata", "New Delhi" };

        // create checkbox
        c1 = new JComboBox(s1);

        // add ItemListener
        c1.addItemListener(s);
        s.b.addActionListener(s);

        // create labels
        l = new JLabel("select your city ");
        l1 = new JLabel("Jalpaiguri selected");
        l2 = new JLabel("Chad is a faggot");


        g.add(l2);

        // set color of text
        l.setForeground(Color.red);
        l1.setForeground(Color.blue);

        // create a new panel
        JPanel p = new JPanel();

        p.add(s.b);
        p.add(l);

        // add combobox to panel
        p.add(c1);

        p.add(l1);

        // add panel to frame
        f.add(p);

        // set the size of frame
        f.setSize(400, 300);
        g.setSize(400, 300);

        f.setVisible(true);
        g.setVisible(false);
    }
    public void itemStateChanged(ItemEvent e)
    {
        if (e.getSource() == c1) {

            l1.setText(c1.getSelectedItem() + " selected");
        }
    }

    public void actionPerformed(ActionEvent e) {
        f.setVisible(false);
        g.setVisible(true);
    }
}