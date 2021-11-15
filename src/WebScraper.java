import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WebScraper {

    public static Team getYearTeam(String year) throws IOException, InterruptedException {
        ArrayList<Player> players = getYearPlayers(year);
        String url = "https://www.onusports.com/sports/mlax/" + year + "/schedule";
        Document doc = Jsoup.connect(url).get();
        Elements teamStats = doc.select("div.team-stats div.cat");
        String winLossString = teamStats.get(0).select("span.value").text();
        int wins = Integer.parseInt(winLossString.split("-")[0]);
        int losses = Integer.parseInt(winLossString.split("-")[1]);
        int games = Integer.parseInt(teamStats.get(8).select("span.value").text());

        Team team = new Team(year, wins, losses, games);
        team.setPlayers(players);

        return team;
    }

    private static ArrayList<Player> getYearPlayers(String year) throws IOException, InterruptedException {
        String url = "https://www.onusports.com/sports/mlax/" + year + "/roster";
        Document doc = Jsoup.connect(url).get();
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();

        Elements playerRows = doc.select("div.mod-roster div.roster table tbody tr");

        for (Element playerRow : playerRows) {
            String href = playerRow.select("th a").get(0).attributes().get("href");
            String[] split = href.split("/");
            String identifier = split[split.length-1];
            Thread thread = new Thread(() -> {
                try {
                    players.add(getYearPlayer(identifier, year));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        Collections.sort(players);
        return players;
    }

    public static Player getYearPlayer(String identifier, String year) throws IOException {
        String url = "https://www.onusports.com/sports/mlax/" + year + "/bios/" + identifier + "?view=gamelog";
        Document doc = Jsoup.connect(url).get();
        String name = doc.select("div.player-name span.name").text();
        int number = Integer.parseInt(doc.select("div.player-name span.number").text());
        Elements infoTable = doc.select("div.player-about div.player-info table tbody tr");
        String height = infoTable.get(0).select("td.value").text();
        int weight = Integer.parseInt(infoTable.get(1).select("td.value").text());
        String position = infoTable.get(5).select("td.value").text(); // Note to self, find better way of handling this

        Elements gameBlock = doc.select("div.tab-panel.active div.stats-wrap div.stats-box.full div.scrollable table tbody tr");
        ArrayList<PlayerGame> games = new ArrayList<>();
        if (gameBlock.size() > 0 && !position.equals("GK")) {
            gameBlock.remove(0); // Remove the header
            String tempstring;
            for (Element row : gameBlock) {
                PlayerGame game = new PlayerGame();
                Elements columns = row.select("td");

                tempstring = columns.get(0).text();
                game.setDate(tempstring);

                tempstring = columns.get(1).text();
                game.setOpponent(tempstring);

                tempstring = columns.get(2).select("a").text();
                game.setScore(tempstring);

                tempstring = columns.get(3).text();
                game.setGoalsScored(tempstring.equals("-") ? 0 : Integer.parseInt(tempstring));

                tempstring = columns.get(4).text();
                game.setAssists(tempstring.equals("-") ? 0 : Integer.parseInt(tempstring));

                tempstring = columns.get(8).text();
                game.setGroundBalls(tempstring.equals("-") ? 0 : Integer.parseInt(tempstring));

                tempstring = columns.get(9).text();
                game.setTurnovers(tempstring.equals("-") ? 0 : Integer.parseInt(tempstring));

                tempstring = columns.get(10).text();
                game.setTurnoversCaused(tempstring.equals("-") ? 0 : Integer.parseInt(tempstring));

                games.add(game);
            }
        }

        Player thePlayer = new Player(name, number, games);
        thePlayer.setHeight(height);
        thePlayer.setWeight(weight);

        return thePlayer;
    }

    public static Map<String,String> getPlayerYears(String name, String currentYear) throws IOException, InterruptedException {
        Map<String,String> years = new HashMap(); // A map for year and player identifier
        ArrayList<Thread> threads = new ArrayList<>();

        class yearFetcher implements Runnable {
            private String year;
            yearFetcher(String yearIn) {
                year = yearIn;
            }
            @Override
            public void run() {
                Document doc = null;
                try {
                    doc = Jsoup.connect("https://www.onusports.com/sports/mlax/" + year + "/roster").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements playerRows = doc.select("div.mod-roster div.roster table tbody tr");
                for (Element playerRow : playerRows) {
                    Element nameLink = playerRow.select("th a").get(0);
                    String pName = nameLink.text();
                    if (pName.equals(name)) {
                        String href = nameLink.attributes().get("href");
                        String[] split = href.split("/");
                        String identifier = split[split.length - 1];
                        years.put(year, identifier);
                    }
                }
            }
        }

        if (!currentYear.equals("2015-16")) {
            Thread thread = new Thread(new yearFetcher("2015-16"));
            thread.start();
            threads.add(thread);
        }
        if (!currentYear.equals("2016-17")) {
            Thread thread = new Thread(new yearFetcher("2016-17"));
            thread.start();
            threads.add(thread);
        }
        if (!currentYear.equals("2017-18")) {
            Thread thread = new Thread(new yearFetcher("2017-18"));
            thread.start();
            threads.add(thread);
        }
        if (!currentYear.equals("2018-19")) {
            Thread thread = new Thread(new yearFetcher("2018-19"));
            thread.start();
            threads.add(thread);
        }
        if (!currentYear.equals("2019-20")) {
            Thread thread = new Thread(new yearFetcher("2019-20"));
            thread.start();
            threads.add(thread);
        }
        if (!currentYear.equals("2020-21")) {
            Thread thread = new Thread(new yearFetcher("2020-21"));
            thread.start();
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.join();
        }

        return years;
    }
}
