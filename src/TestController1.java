import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class TestController1 implements ItemListener, ListSelectionListener {
    TestView1 view;
    Team currentTeam;
    public TestController1(TestView1 view) throws IOException, InterruptedException {
        this.view = view;
        this.currentTeam = WebScraper.getYearTeam("2015-16");
    }

    public void setFirstDisplay() {
        this.view.playerTableItemModel.setPlayers(currentTeam.getPlayers());
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.view.yearDropdown) {
            String year = itemEvent.getItem().toString();
            if (!currentTeam.year.equals(year)) {
                try {
                    currentTeam = WebScraper.getYearTeam(year);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.view.playerTableItemModel.setPlayers(currentTeam.getPlayers());
                this.view.playerTable.repaint();
                this.view.playerTable.revalidate();
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        int i = this.view.playerTable.getSelectedRow();
        Player selected = this.view.playerTableItemModel.getPlayer(i);
        int test = 1;
    }
}
