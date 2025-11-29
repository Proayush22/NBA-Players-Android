package com.example.nbaplayerproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Player> players;
    //public static final String STRING_KEY ="save";
    public static final String savedlist = "player";

    //TextView timer, timerL;
    //TextView currPick, currPickL;
    //TextView lastPick, lastPickL;
    //ProgressBar roundProgress, roundProgressL;
    //ProgressBar draftProgressL;

    TextView stat, height, weight, age, points, rebounds, assists, steals, blocks, turnovers, fgp,
            ftp, threepp, games;
    Button draft;


    public float timerValue = 0;
    public Integer round = 1;
    public Integer pick = 1;

    private String playerSelected = "";

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList(savedlist, players);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        timer = findViewById(R.id.textViewTimer);
        timerL = findViewById(R.id.textViewTimerL);
        currPick = findViewById(R.id.textViewCurrPick);
        currPickL = findViewById(R.id.textViewCurrPickL);
        lastPick = findViewById(R.id.textViewLastPick);
        lastPickL = findViewById(R.id.textViewLastPickL);
        roundProgress = findViewById(R.id.roundProgress);
        roundProgressL = findViewById(R.id.roundProgressL);
        draftProgressL = findViewById(R.id.draftProgressL);
        */


        stat = findViewById(R.id.textViewStats);
        height = findViewById(R.id.textViewHeight);
        weight = findViewById(R.id.textViewWeight);
        age = findViewById(R.id.textViewAge);
        points = findViewById(R.id.textViewPoints);
        rebounds = findViewById(R.id.textViewRebounds);
        assists = findViewById(R.id.textViewAssists);
        steals = findViewById(R.id.textViewSteals);
        blocks = findViewById(R.id.textViewBlocks);
        turnovers = findViewById(R.id.textViewTurnovers);
        fgp = findViewById(R.id.textViewFGP);
        ftp = findViewById(R.id.textViewFTP);
        threepp = findViewById(R.id.textView3PP);
        games = findViewById(R.id.textViewGamesPlayed);
        players = new ArrayList<>();


        if (savedInstanceState != null) {
            players = savedInstanceState.getParcelableArrayList(savedlist);
        }
        else {
            setPlayers(players);
        }

        listView = findViewById(R.id.list_id);

        CustomAdapter adapter = new CustomAdapter(this, R.layout.adapter_layout, players);
        listView.setAdapter(adapter);


        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) { // i is the position of the item that was clicked
                    Toast.makeText(MainActivity.this, "You clicked on "+players.get(i).getName(), Toast.LENGTH_SHORT).show();
                }
            });

        }
        else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(MainActivity.this, players.get(i).getName(), Toast.LENGTH_SHORT).show();

                    if(playerSelected.equals("")) {
                        playerSelected = players.get(i).getName();

                        stat.setText(players.get(i).getName() + "'s 2022-23 Stats");
                        height.setText(players.get(i).getHeight());
                        weight.setText(players.get(i).getWeight());
                        age.setText(players.get(i).getAge());
                        points.setText(players.get(i).getPoints());
                        rebounds.setText(players.get(i).getRebounds());
                        assists.setText(players.get(i).getAssists());
                        steals.setText(players.get(i).getSteals());
                        blocks.setText(players.get(i).getBlocks());
                        turnovers.setText(players.get(i).getTurnovers());
                        fgp.setText(players.get(i).getFg());
                        ftp.setText(players.get(i).getFt());
                        threepp.setText(players.get(i).getThree());
                        games.setText(players.get(i).getGames());
                    } else if (playerSelected.equals(players.get(i).getName())){
                        playerSelected = "";
                        stat.setText("Fantasy Scoring Guide");
                        height.setText("Point Scored: 1 point");
                        weight.setText("Three-point made: 1 point");
                        age.setText("Field goal attempted: -1 point");
                        points.setText("Free Throw made: 1 point");
                        rebounds.setText("Free Throw attempted: -1 point");
                        assists.setText("Rebound: 1 point");
                        steals.setText("Assist: 2 points");
                        blocks.setText("Steal: 4 points");
                        turnovers.setText("Block: 4 points");
                        fgp.setText("Turnover: -2 points");
                        ftp.setText("");
                        threepp.setText("");
                        games.setText("");
                    } else {
                        playerSelected = players.get(i).getName();
                        stat.setText(players.get(i).getName() + "'s 2022-23 Stats");
                        height.setText(players.get(i).getHeight());
                        weight.setText(players.get(i).getWeight());
                        age.setText(players.get(i).getAge());
                        points.setText(players.get(i).getPoints());
                        rebounds.setText(players.get(i).getRebounds());
                        assists.setText(players.get(i).getAssists());
                        steals.setText(players.get(i).getSteals());
                        blocks.setText(players.get(i).getBlocks());
                        turnovers.setText(players.get(i).getTurnovers());
                        fgp.setText(players.get(i).getFg());
                        ftp.setText(players.get(i).getFt());
                        threepp.setText(players.get(i).getThree());
                        games.setText(players.get(i).getGames());
                    }

                }
            });
        }

    }

    private void setPlayers(ArrayList<Player> players) {
        players.add(new Player("1", "Nikola Jokic", "DEN", "C", R.drawable.jokic, "Height: 6'11",
                "Weight: 284 lbs", "Age: 28", "Points: 24.5", "Rebounds: 11.8", "Assists: 9.4",
                "Steals: 1.3",
                "Blocks: 0.9", "Turnover: 2.9", "FG%: 63.2", "FT%: 82.2", "3P%: 38.3", "Games " +
                "Played: 69"));
        players.add(new Player("2", "Giannis Antetokounmpo", "MIL", "PF, C", R.drawable.giannis, "Height: 6'11",
                "Weight: 242 lbs", "Age: 26", "Points: 31.1", "Rebounds: 11.8", "Assists: 5.7",
                "Steals: 0.8",
                "Blocks: 0.8", "Turnover: 3.9", "FG%: 55.3", "FT%: 64.5", "3P%: 27.5",
                "Games Played: 61"));
        players.add(new Player("3", "Luka Doncic", "DAL", "PG, SG", R.drawable.luka, "Height: 6'7",
                "Weight: 230 lbs", "Age: 24", "Points: 32.4", "Rebounds: 8.6", "Assists: 8.0",
                "Steals: 1.4",
                "Blocks: 0.5", "Turnover: 3.6", "FG%: 49.6", "FT%: 74.2", "3P%: 34.2",
                "Games Played: 66"));
        players.add(new Player("4", "Shai Gilgeous-Alexander", "OKC", "PG, SG", R.drawable.shai, "Height: 6'6",
                "Weight: 195 lbs", "Age: 25", "Points: 31.4", "Rebounds: 4.8", "Assists: 5.5",
                "Steals: 1.6",
                "Blocks: 1", "Turnover: 2.8", "FG%: 51.0", "FT%: 90.5", "3P%: 34.5",
                "Games Played: 68"));
        players.add(new Player("5", "Joel Embiid", "PHI", "PF, C", R.drawable.embiid, "Height: 7'0",
                "Weight: 280 lbs", "Age: 29", "Points: 33.1", "Rebounds: 10.2", "Assists: 4.2",
                "Steals: 1.0",
                "Blocks: 1.7", "Turnover: 3.4", "FG%: 54.8", "FT%: 85.7", "3P%: 33.0",
                "Games Played: 66"));
        players.add(new Player("6", "Tyrese Haliburton", "IND", "PG, SG", R.drawable.haliburton,
                "Height: 6'5", "Weight: 185 lbs", "Age: 23", "Points: 20.7", "Rebounds: 3.7",
                "Assists: 10.4", "Steals: 1.6", "Blocks: 0.4", "Turnover: 2.5", "FG%: 49.0", "FT%: 87.1", "3P%: 40.0", "Games Played: 56"));
        players.add(new Player("7", "Jayson Tatum", "BOS", "SF, PF", R.drawable.tatum, "Height: " +
                "6'8", "Weight: 210 lbs", "Age: 25", "Points: 30.1", "Rebounds: 8.8",
                "Assists: 4.6",
                "Steals: 1.1", "Blocks: 0.7", "Turnover: 2.9", "FG%: 46.6", "FT%: 85.4", "3P%: 35" +
                ".0", "Games Played: 74"));
        players.add(new Player("8", "Trae Young", "ATL", "PG", R.drawable.trae, "Height: 6'1",
                "Weight: 164 lbs", "Age: 25", "Points: 26.2", "Rebounds: 3.0", "Assists: 10.2",
                "Steals: 1.1", "Blocks: 0.1", "Turnover: 4.1", "FG%: 43.0", "FT%: 88.6", "3P%: 33" +
                ".5", "Games Played: 73"));
        players.add(new Player("9", "Anthony Edwards", "MIN", "SG, SF", R.drawable.ant, "Height: " +
                "6'4", "Weight: 225 lbs", "Age: 22", "Points: 24.6", "Rebounds: 5.8",
                "Assists: 4.4",
                "Steals: 1.6", "Blocks: 0.7", "Turnover: 3.3", "FG%: 45.9", "FT%: 75.6", "3P%: 36" +
                ".9", "Games Played: 79"));
        players.add(new Player("10", "Damontas Sabonis", "SAC", "PF, C", R.drawable.saboner, "Height: " +
                "6'10", "Weight: 240 lbs", "Age: 27", "Points: 19.1", "Rebounds: 12.3",
                "Assists: 7.3",
                "Steals: 0.8", "Blocks: 0.5", "Turnover: 2.9", "FG%: 61.5", "FT%: 74.2", "3P%: 77" +
                ".3", "Games Played: 79"));
        players.add(new Player("11", "Devin Booker", "PHX", "SG", R.drawable.booker, "Height: " +
                "6'5", "Weight: 206 lbs", "Age: 24", "Points: 25.6", "Rebounds: 4.2",
                "Assists: 4.3",
                "Steals: 0.9", "Blocks: 0.3", "Turnover: 3.2", "FG%: 48.4", "FT%: 86.1", "3P%: 34" +
                ".0", "Games Played: 67"));
        players.add(new Player("12", "Donovan Mitchell", "CLE", "SG", R.drawable.dmitchell,
                "Height: " +
                "6'1", "Weight: 215 lbs", "Age: 25", "Points: 26.4", "Rebounds: 4.4",
                "Assists: 5.2",
                "Steals: 1.0", "Blocks: 0.3", "Turnover: 3.1", "FG%: 43.8", "FT%: 84.0", "3P%: 38" +
                ".6", "Games Played: 53"));
        players.add(new Player("13", "Lebron James", "LAL", "SF, PF", R.drawable.lebron, "Height: " +
                "6'9", "Weight: 250 lbs", "Age: 36", "Points: 25.0", "Rebounds: 7.8",
                "Assists: 7.7",
                "Steals: 1.1", "Blocks: 0.6", "Turnover: 3.7", "FG%: 51.3", "FT%: 69.8", "3P%: 36" +
                ".5", "Games Played: 45"));
        players.add(new Player("14", "Anthony Davis", "LAL", "PF, C", R.drawable.ad, "Height: " +
                "6'10", "Weight: 253 lbs", "Age: 28", "Points: 21.8", "Rebounds: 7.9",
                "Assists: 3.1",
                "Steals: 1.3", "Blocks: 1.8", "Turnover: 2.0", "FG%: 51.3", "FT%: 73.8", "3P%: 26" +
                ".9", "Games Played: 36"));
        players.add(new Player("15", "LaMelo Ball", "CHA", "PG", R.drawable.lamelo, "Height: " +
                "6'6", "Weight: 180 lbs", "Age: 20", "Points: 15.7", "Rebounds: 5.9",
                "Assists: 6.1",
                "Steals: 1.6", "Blocks: 0.4", "Turnover: 2.8", "FG%: 43.6", "FT%: 75.8", "3P%: 35" +
                ".2", "Games Played: 51"));
        players.add(new Player("16", "Scottie Barnes", "TOR", "SF, PF", R.drawable.barnes, "Height: " +
                "6'9", "Weight: 227 lbs", "Age: 20", "Points: 10.7", "Rebounds: 4.3",
                "Assists: 3.1",
                "Steals: 1.5", "Blocks: 0.7", "Turnover: 1.7", "FG%: 46.2", "FT%: 62.1", "3P%: 27" +
                ".5", "Games Played: 68"));
        players.add(new Player("17", "Victor Wembanyama", "SA", "PF, C", R.drawable.wemby, "Height: " +
                "7'2", "Weight: 209 lbs", "Age: 18", "Points: 10.7", "Rebounds: 4.3",
                "Assists: 3.1",
                "Steals: 1.5", "Blocks: 0.7", "Turnover: 1.7", "FG%: 46.2", "FT%: 62.1", "3P%: 27" +
                ".5", "Games Played: 68"));
        players.add(new Player("18", "Stephen Curry", "GSW", "PG, SG", R.drawable.curry, "Height: " +
                "6'3", "Weight: 185 lbs", "Age: 33", "Points: 32.0", "Rebounds: 5.5",
                "Assists: 5.8",
                "Steals: 1.2", "Blocks: 0.1", "Turnover: 3.4", "FG%: 48.2", "FT%: 91.6", "3P%: 42" +
                ".1", "Games Played: 63"));
        players.add(new Player("19", "Jalen Brunson", "NYK", "PG, SG", R.drawable.brunson, "Height: " +
                "6'1", "Weight: 190 lbs", "Age: 24", "Points: 12.6", "Rebounds: 3.4",
                "Assists: 3.5",
                "Steals: 0.8", "Blocks: 0.1", "Turnover: 1.5", "FG%: 52.3", "FT%: 79.5", "3P%: 40" +
                ".5", "Games Played: 69"));
        players.add(new Player("20", "De'Aaron Fox", "SAC", "PG", R.drawable.fox, "Height: " +
                "6'3", "Weight: 185 lbs", "Age: 23", "Points: 25.2", "Rebounds: 3.5",
                "Assists: 7.2",
                "Steals: 1.5", "Blocks: 0.5", "Turnover: 3.2", "FG%: 48.0", "FT%: 72.9", "3P%: 32" +
                ".2", "Games Played: 58"));
        players.add(new Player("22", "Kevin Durant", "PHX", "SF, PF", R.drawable.durant, "Height: " +
                "6'10", "Weight: 240 lbs", "Age: 33", "Points: 26.9", "Rebounds: 7.1",
                "Assists: 5.6",
                "Steals: 0.7", "Blocks: 1.1", "Turnover: 3.5", "FG%: 54.0", "FT%: 87.3", "3P%: 45" +
                ".0", "Games Played: 35"));
        players.add(new Player("22", "Damian Lillard", "MIL", "PG", R.drawable.lillard, "Height: " +
                "6'2", "Weight: 195 lbs", "Age: 31", "Points: 28.8", "Rebounds: 4.2",
                "Assists: 7.5",
                "Steals: 1.0", "Blocks: 0.3", "Turnover: 3.0", "FG%: 45.1", "FT%: 92.8", "3P%: 39" +
                ".1", "Games Played: 67"));
        players.add(new Player("23", "Dejounte Murray", "ATL", "PG, SG", R.drawable.dejounte, "Height: " +
                "6'4", "Weight: 180 lbs", "Age: 25", "Points: 15.7", "Rebounds: 7.1",
                "Assists: 7.1",
                "Steals: 1.5", "Blocks: 0.3", "Turnover: 2.8", "FG%: 43.6", "FT%: 75.8", "3P%: 35" +
                ".2", "Games Played: 51"));
        players.add(new Player("24", "DeMar Derozan", "CHI", "SG, SF", R.drawable.derozan, "Height: " +
                "6'6", "Weight: 220 lbs", "Age: 32", "Points: 21.6", "Rebounds: 4.2",
                "Assists: 6.9",
                "Steals: 1.0", "Blocks: 0.3", "Turnover: 2.4", "FG%: 49.5", "FT%: 88.0", "3P%: 25" +
                ".7", "Games Played: 68"));
        players.add(new Player("25", "Kyrie Irving", "DAL", "PG, SG", R.drawable.kyrie, "Height: " +
                "6'2", "Weight: 195 lbs", "Age: 29", "Points: 26.9", "Rebounds: 4.8",
                "Assists: 6.0",
                "Steals: 1.4", "Blocks: 0.6", "Turnover: 2.4", "FG%: 50.6", "FT%: 92.2", "3P%: 39" +
                ".9", "Games Played: 54"));
        players.add(new Player("26", "Lauri Markkanen", "UTA", "PF, C", R.drawable.lauri, "Height" +
                ": " +
                "7'0", "Weight: 240 lbs", "Age: 24", "Points: 13.6", "Rebounds: 5.3",
                "Assists: 0.9",
                "Steals: 0.5", "Blocks: 0.4", "Turnover: 0.9", "FG%: 48.0", "FT%: 82.9", "3P%: 40" +
                ".2", "Games Played: 51"));
        players.add(new Player("27", "Julius Randle", "NYK", "PF, C", R.drawable.randle, "Height: " +
                "6'8", "Weight: 250 lbs", "Age: 26", "Points: 24.1", "Rebounds: 10.2",
                "Assists: 6.0",
                "Steals: 0.8", "Blocks: 0.2", "Turnover: 3.4", "FG%: 45.6", "FT%: 80.8", "3P%: 41" +
                ".1", "Games Played: 71"));
        players.add(new Player("28", "Mikal Bridges", "BKN", "SG, SF", R.drawable.mikal, "Height: " +
                "6'6", "Weight: 210 lbs", "Age: 25", "Points: 13.5", "Rebounds: 4.3",
                "Assists: 2.1",
                "Steals: 1.1", "Blocks: 0.9", "Turnover: 1.0", "FG%: 54.3", "FT%: 84.5", "3P%: 42" +
                ".5", "Games Played: 72"));
        players.add(new Player("29", "Bam Adebayo", "MIA", "C", R.drawable.bam, "Height: " +
                "6'9", "Weight: 255 lbs", "Age: 24", "Points: 18.7", "Rebounds: 9.0",
                "Assists: 5.4",
                "Steals: 1.2", "Blocks: 1.0", "Turnover: 2.6", "FG%: 57.0", "FT%: 79.1", "3P%: 25" +
                ".0", "Games Played: 64"));
        players.add(new Player("30", "Alperen Sengun", "HOU", "C", R.drawable.sengun, "Height: " +
                "6'10", "Weight: 240 lbs", "Age: 19", "Points: 10.7", "Rebounds: 4.3",
                "Assists: 3.1",
                "Steals: 1.5", "Blocks: 0.7", "Turnover: 1.7", "FG%: 46.2", "FT%: 62.1", "3P%: 27" +
                ".5", "Games Played: 68"));
        players.add(new Player("31", "Tyrese Maxey", "PHI", "PG, SG", R.drawable.maxey, "Height: " +
                "6'2", "Weight: 185 lbs", "Age: 21", "Points: 15.7", "Rebounds: 3.5",
                "Assists: 7.1",
                "Steals: 1.5", "Blocks: 0.3", "Turnover: 2.8", "FG%: 43.6", "FT%: 75.8", "3P%: 35" +
                ".2", "Games Played: 51"));
        players.add(new Player("32", "Nikola Vucevic", "CHI", "C", R.drawable.nikola, "Height: " +
                "6'11", "Weight: 260 lbs", "Age: 30", "Points: 23.4", "Rebounds: 11.8",
                "Assists: 3.8",
                "Steals: 1.1", "Blocks: 0.7", "Turnover: 2.9", "FG%: 51.0", "FT%: 90.5", "3P" +
                "%: 29" + ".8", "Games Played: 72"));
        players.add(new Player("33", "Paolo Banchero", "ORL", "SF, PF", R.drawable.paolo,
                "Height: 6'10", "Weight: 240 lbs", "Age: 21", "Points: 20.0", "Rebounds: 6.9",
                "Assists: 3.1", "Steals: 1.5", "Blocks: 0.7", "Turnover: 1.7", "FG%: 46.2",
                "FT%: 62.1", "3P%: 27", "Games Played: 68"));
        players.add(new Player("34", "Karl-Anthony Towns", "MIN", "PF, C", R.drawable.kat,
                "Height: 7'0", "Weight: 248 lbs", "Age: 25", "Points: 20.8", "Rebounds: 8.1",
                "Assists: 4.8", "Steals: 0.8", "Blocks: 1.2", "Turnover: 2.7", "FG%: 48.6",
                "FT%: 85.2", "3P%: 38.7", "Games Played: 29"));
        players.add(new Player("35", "Pascal Siakam", "IND", "PF, C", R.drawable.pascal,
                "Height: 6'8", "Weight: 245 lbs", "Age: 27", "Points: 21.4", "Rebounds: 7.2",
                "Assists: 4.5", "Steals: 1.1", "Blocks: 0.7", "Turnover: 2.6", "FG%: 45.5",
                "FT%: 82.7", "3P%: 29.7", "Games Played: 56"));
        players.add(new Player("36", "Tyus Jones", "WAS", "PG", R.drawable.tyus, "Height: 6'1",
                "Weight: " +
                "196 lbs", "Age: 27", "Points: 10.3", "Rebounds: 2.5", "Assists: 5.2", "Steals: " +
                "1.0", "Blocks: 0.1", "Turnover: 0.9", "FG%: 43.8", "FT%: 80.6", "3P%: 37.1",
                "Games Played: 80"));
        players.add(new Player("37", "Jamal Murray", "DEN", "PG, SG", R.drawable.murray, "Height" +
                ": 6'4", "Weight: 215 lbs", "Age: 24", "Points: 21.2", "Rebounds: 4.0", "Assists:" +
                " " + "4.8", "Steals: 1.3", "Blocks: 0.3", "Turnover: 2.2", "FG%: 47.7", "FT%: " + "89.3", "3P%: 40.8", "Games Played: 48"));
        players.add(new Player("38", "Kristaps Porzingis", "BOS", "PF, C", R.drawable.kristaps,
                "Height: 7'3", "Weight: 240 lbs", "Age: 26", "Points: 20.1", "Rebounds: 8.9",
                "Assists: 1.6", "Steals: 0.5", "Blocks: 1.3", "Turnover: 1.5", "FG%: 47.6", "FT%: " + "85.2", "3P%: 37.6", "Games Played: 43"));
        players.add(new Player("39", "Chet Holmgren", "OKC", "PF, C", R.drawable.chet, "Height: " +
                "7'0", "Weight: 195 lbs", "Age: 19", "Points: 10.7", "Rebounds: 4.3",
                "Assists: 3.1",
                "Steals: 1.5", "Blocks: 0.7", "Turnover: 1.7", "FG%: 46.2", "FT%: 62.1", "3P%: 27" +
                ".5", "Games Played: 68"));
        players.add(new Player("40", "Paul George", "LAC", "SF, PF", R.drawable.pg, "Height: " +
                "6'8", "Weight: 220 lbs", "Age: 31", "Points: 23.3", "Rebounds: 6.6",
                "Assists: 5.2",
                "Steals: 1.2", "Blocks: 0.5", "Turnover: 3.0", "FG%: 45.1", "FT%: 92.8", "3P%: 39" +
                ".1", "Games Played: 67"));
        players.add(new Player("41", "Jaylen Brown", "BOS", "SG, SF", R.drawable.brown, "Height: " +
                "6'6", "Weight: 223 lbs", "Age: 27", "Points: 26.6", "Rebounds: 6.9",
                "Assists: 3.5",
                "Steals: 1.1", "Blocks: 0.4", "Turnover: 2.9", "FG%: 49.1", "FT%: 76.5", "3P%: 33" +
                ".5", "Games Played: 67"));
        players.add(new Player("42", "CJ McCollum", "NO", "PG, SG", R.drawable.cj, "Height: " +
                "6'3", "Weight: 190 lbs", "Age: 30", "Points: 23.1", "Rebounds: 4.7",
                "Assists: 4.7",
                "Steals: 1.0", "Blocks: 0.4", "Turnover: 2.5", "FG%: 47.3", "FT%: 83.8", "3P%: 40" +
                ".2", "Games Played: 69"));
        players.add(new Player("43", "James Harden", "LAC", "PG, SG", R.drawable.harden, "Height" +
                ": 6'5", "Weight: 220 lbs", "Age: 32", "Points: 24.6", "Rebounds: 4.8", "Assists" +
                ":" + " " + "10.9", "Steals: 1.2", "Blocks: 0.7", "Turnover: 4.0", "FG%: 44.4", "FT%: 86.6", "3P%: 36.8", "Games Played: 44"));
        players.add(new Player("44", "Cade Cunningham", "DET", "PG, SG", R.drawable.cade, "Height" +
                ": 6'6", "Weight: 220 lbs", "Age: 22", "Points: 19.9", "Rebounds: 6.2",
                "Assists" +
                ":" + " " + "6.6", "Steals: 1.5", "Blocks: 0.6", "Turnover: 3.3", "FG%: 41.5",
                "FT" +
                "%: 75.8", "3P%: 35.2", "Games Played: 12"));
        players.add(new Player("45", "Jalen Williams", "OKC", "PG, SG", R.drawable.jalen, "Height" +
                ": 6'6", "Weight: 220 lbs", "Age: 22", "Points: 19.9", "Rebounds: 6.2",
                "Assists" + ":" + " " + "6.6", "Steals: 1.5", "Blocks: 0.6", "Turnover: 3.3", "FG" +
                "%: 41.5", "FT" + "%: 75.8", "3P%: 35.2", "Games Played: 12"));
        players.add(new Player("46", "Zach Lavine", "CHI", "SG, SF", R.drawable.zach, "Height: " +
                "6'6", "Weight: 200 lbs", "Age: 26", "Points: 27.4", "Rebounds: 5.0",
                "Assists: 4.9",
                "Steals: 0.8", "Blocks: 0.5", "Turnover: 3.4", "FG%: 50.7", "FT%: 85.4", "3P%: 41" +
                ".9", "Games Played: 58"));
        players.add(new Player("47", "Brandon Ingram", "NO", "SG, SF, PF", R.drawable.ingram,
                "Height: 6'8", "Weight: 190 lbs", "Age: 24", "Points: 23.8", "Rebounds: 4.9",
                "Assists: 4.9", "Steals: 0.8", "Blocks: 0.5", "Turnover: 3.4", "FG%: 50.7", "FT%: 85.4", "3P%: 41.9", "Games Played: 58"));
        players.add(new Player("48", "Kawhi Leonard", "LAC", "SF, PF", R.drawable.leonard,
                "Height: 6'7", "Weight: 230 lbs", "Age: 30", "Points: 24.8", "Rebounds: 6.5",
                "Assists: 5.2", "Steals: 1.6", "Blocks: 0.5", "Turnover: 2.4", "FG%: 51.2", "FT%: 88.5", "3P%: 39.8", "Games Played: 52"));
        players.add(new Player("49", "Jaren Jackson Jr.", "MEM", "PF, C", R.drawable.jjj, "Height" +
                ": 6'11", "Weight: 260 lbs", "Age: 30", "Points: 23.4", "Rebounds: 11.8",
                "Assists" + ":" + " " + "3.8", "Steals: 1.1", "Blocks: 0.7", "Turnover: 2.9", "FG" + "%: " + "51.0", "FT%: 90.5", "3P%: 34.5", "Games Played: 68"));
        players.add(new Player("50", "Jimmy Butler", "MIA", "SF, PF", R.drawable.butler, "Height" +
                ": 6'7", "Weight: 230 lbs", "Age: 30", "Points: 24.8", "Rebounds: 6.5",
                "Assists" + ":" + " " + "5.2", "Steals: 1.6", "Blocks: 0.5", "Turnover: 2.4", "FG" + "%: " + "51.2", "FT%: 88.5", "3P%: 39.8", "Games Played: 52"));
        players.add(new Player("51", "Fred VanVleet", "HOU", "PG", R.drawable.vanvleet, "Height" +
                ": 6'1", "Weight: 196 lbs", "Age: 27", "Points: 10.3", "Rebounds: 2.5",
                "Assists" + ":" + " " + "5.2", "Steals: 1.0", "Blocks: 0.1", "Turnover: 0.9", "FG" + "%: " + "43.8", "FT%: 80.6", "3P%: 37.1", "Games Played: 80"));
        players.add(new Player("52", "Franz Wagner", "ORL", "SG, SF, PF", R.drawable.franz, "Height" +
                ": 6'9", "Weight: 227 lbs", "Age: 25", "Points: 13.5", "Rebounds: 4.3",
                "Assists" + ":" + " " + "2.1", "Steals: 1.1", "Blocks: 0.9", "Turnover: 1.0", "FG" + "%: " + "54.3", "FT%: 84.5", "3P%: 42.5", "Games Played: 72"));
        players.add(new Player("53", "Kyle Kuzma", "WAS", "SF, PF", R.drawable.kuzma, "Height: " +
                "6'9", "Weight: 220 lbs", "Age: 26", "Points: 23.3", "Rebounds: 6.6", "Assists: " + "5.2", "Steals: 1.2", "Blocks: 0.5", "Turnover: 3.0", "FG%: 45.1", "FT%: 92.8", "3P%: 39.1", "Games Played: 67"));
        players.add(new Player("54", "Zion Williamson", "NO", "PF, C", R.drawable.zion, "Height: " +
                "6'7", "Weight: 284 lbs", "Age: 21", "Points: 27.0", "Rebounds: 7.2",
                "Assists" + ":" + " " + "3.7", "Steals: 0.9", "Blocks: 0.6", "Turnover: 2.7", "FG" + "%: " + "61.1", "FT%: 70.9", "3P%: 29.8", "Games Played: 61"));
        players.add(new Player("55", "Jonas Valanciunas", "NO", "C", R.drawable.jonas, "Height: " +
                "6'11", "Weight: 260 lbs", "Age: 30", "Points: 23.4", "Rebounds: 11.8",
                "Assists" + ":" + " " + "3.8", "Steals: 1.1", "Blocks: 0.7", "Turnover: 2.9", "FG" + "%: " + "51.0", "FT%: 90.5", "3P%: 34.5", "Games Played: 68"));
        players.add(new Player("56", "Rudy Gobert", "MIN", "C", R.drawable.gobert, "Height: " +
                "7'1", "Weight: 258 lbs", "Age: 29", "Points: 14.4", "Rebounds: 13.5",
                "Assists" + ":" + " " + "1.3", "Steals: 0.8", "Blocks: 2.7", "Turnover: 1.9", "FG" + "%: " + "67.5", "FT%: 59.7", "3P%: 0.0", "Games Played: 71"));
        players.add(new Player("57", "Terry Rozier", "CHA", "PG, SG", R.drawable.rozier, "Height: " +
                "6'1", "Weight: 196 lbs", "Age: 27", "Points: 10.3", "Rebounds: 2.5",
                "Assists" + ":" + " " + "5.2", "Steals: 1.0", "Blocks: 0.1", "Turnover: 0.9", "FG" + "%: " + "43.8", "FT%: 80.6", "3P%: 37.1", "Games Played: 80"));
        players.add(new Player("58", "Miles Bridges", "CHA", "SF, PF", R.drawable.bridges, "Height: " +
                "6'6", "Weight: 220 lbs", "Age: 27", "Points: 23.3", "Rebounds: 6.6",
                "Assists" + ":" + " " + "5.2", "Steals: 1.2", "Blocks: 0.5", "Turnover: 3.0", "FG" + "%: " + "45.1", "FT%: 92.8", "3P%: 39.1", "Games Played: 67"));
        players.add(new Player("59", "Myles Turner", "IND", "C", R.drawable.myles, "Height: " +
                "6'11", "Weight: 260 lbs", "Age: 30", "Points: 23.4", "Rebounds: 11.8",
                "Assists" + ":" + " " + "3.8", "Steals: 1.1", "Blocks: 0.7", "Turnover: 2.9", "FG" + "%: " + "51.0", "FT%: 90.5", "3P%: 34.5", "Games Played: 68"));
        players.add(new Player("60", "Tyler Herro", "MIA", "PG, SG", R.drawable.herro, "Height: " +
                "6'5", "Weight: 195 lbs", "Age: 22", "Points: 23.3", "Rebounds: 6.6",
                "Assists" + ":" + " " + "5.2", "Steals: 1.2", "Blocks: 0.5", "Turnover: 3.0", "FG" + "%: " + "45.1", "FT%: 92.8", "3P%: 39.1", "Games Played: 67"));
        players.add(new Player("61", "Derrick White", "BOS", "PG, SG", R.drawable.white, "Height: " +
                "6'4", "Weight: 190 lbs", "Age: 29", "Points: 12.4", "Rebounds: 3.6",
                "Assists" + ":" + " " + "3.9", "Steals: 0.7", "Blocks: 0.9", "Turnover: 1.2",
                "FG" + "%: " + "46.2", "FT%: 87.5", "3P%: 38.1", "Games Played: 80"));
        players.add(new Player("62", "Jrue Holiday", "BOS", "PG, SG", R.drawable.holiday, "Height" +
                ": 6'4", "Weight: 205 lbs", "Age: 33", "Points: 19.3", "Rebounds: 5.1", "Assists: " + "6.7", "Steals: 1.8", "Blocks: 0.6", "Turnover: 2.6", "FG%: 50.3", "FT%: 81.9", "3P%: 39.2", "Games Played: 59"));
    }

    public void draftPlayer() {

    }
}