import java.io.IOException;

public class TestBench {
    public static void main(String[] args) throws IOException, InterruptedException {
        Team team = WebScraper.getYearTeam("2015-16");
//        Map<String,String> test = WebScraper.getPlayerYears("Andrew Laudico","2016-17");
        int blarg = 0;
        Player testPlayer = WebScraper.getYearPlayer("laudico_andrew_drog","2015-16");
//        TeamView view1 = new TeamView();
        PlayerView view2 = new PlayerView(testPlayer, team);
//        view1.setVisible(true);
        view2.setVisible(true);
    }
}
