import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TestController1 implements ItemListener, ActionListener, ListSelectionListener {
    TestView1 view;
    public TestController1(TestView1 view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.view.test) {
            this.view.test.setText("DONE");
        }
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {

    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        int i = this.view.playerTable.getSelectedColumn();
        Player selected = this.view.playerTableItemModel.getPlayer(i);
        int test = 1;
    }
}
