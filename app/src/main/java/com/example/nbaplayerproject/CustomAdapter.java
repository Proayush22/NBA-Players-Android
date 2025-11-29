package com.example.nbaplayerproject;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Player> {
    List<Player> list;
    List<Boolean> isClicked;
    Context context;
    int xmlResource;
    public CustomAdapter(Context context, int resource, List<Player> objects) {
        super(context, resource, objects);
        xmlResource = resource;
        list = objects;
        this.context = context;

        isClicked = new ArrayList<>(Arrays.asList(new Boolean[list.size()]));
        Collections.fill(isClicked, new Boolean(false));
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent); // return a view that displays the data at a specified position.
        // We are getting specific so we mute/delete this.
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View adapterLayout = layoutInflater.inflate(xmlResource, null);
        // root has to do with hierarchy of Views, keep null for our purposes ^

        TextView rank = adapterLayout.findViewById(R.id.textViewRank);
        TextView player = adapterLayout.findViewById(R.id.textView);
        TextView team = adapterLayout.findViewById(R.id.textView3);
        TextView pos = adapterLayout.findViewById(R.id.textView4);
        ImageView image = adapterLayout.findViewById(R.id.imageView);
        Button draft = adapterLayout.findViewById(R.id.button);
        Switch queue = adapterLayout.findViewById(R.id.switchQueue);
        LinearLayout layout = adapterLayout.findViewById(R.id.linearLayout);

        ColorStateList buttonColor = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_enabled}, // disabled
                        new int[]{android.R.attr.state_enabled}, // enabled
                },
                new int[]{
                        Color.rgb(216, 216, 216),
                        Color.rgb(0, 206, 185),
                }
        );
        draft.setBackgroundTintList(buttonColor);
        draft.setText("Draft");
        draft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        draft.setTextColor(Color.BLACK);
        Log.d("TAG", "post");
        //draft.setClickable(false);

        ColorStateList switchColor = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_checked}, // unchecked
                        new int[]{android.R.attr.state_checked}, // checked
                },
                new int[]{
                        Color.rgb(0, 154, 237),
                        Color.rgb(0, 42, 148),
                }
        );
        queue.setThumbTintList(switchColor);


        queue.setChecked(list.get(position).getQueue());
        setbackground(layout, list.get(position).getTeam(), rank, player, team, pos,
                list.get(position));
        queue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!list.get(position).getQueue()) {
                    setbg(layout, list.get(position).getTeam(), rank, player, team, pos);
                    list.get(position).setQueue(b);
                    Log.d("TAG", list.get(position).getQueue().toString());
                }
                else{
                    layout.setBackgroundColor(Color.TRANSPARENT);
                    if(list.get(position).getTeam().equals("DEN") || list.get(position).getTeam().equals("PHX") || list.get(position).getTeam().equals("NYK")){
                        if(parent.getResources().getConfiguration().uiMode == 33) {
                            rank.setTextColor(Color.LTGRAY);
                            player.setTextColor(Color.LTGRAY);
                            team.setTextColor(Color.LTGRAY);
                            pos.setTextColor(Color.LTGRAY);
                        }
                        else if(parent.getResources().getConfiguration().uiMode == 17) {
                            rank.setTextColor(Color.DKGRAY);
                            player.setTextColor(Color.DKGRAY);
                            team.setTextColor(Color.DKGRAY);
                            pos.setTextColor(Color.DKGRAY);
                        }
                    }
                    list.get(position).setQueue(b);
                    Log.d("TAG", list.get(position).getQueue().toString());
                }
            }
        });




        rank.setText(list.get(position).getRank());
        player.setText(list.get(position).getName());
        team.setText(list.get(position).getTeam());
        pos.setText(list.get(position).getPosition());
        image.setImageResource(list.get(position).getImage());
        return adapterLayout;
    }

    private void setbackground(LinearLayout layout, String team, TextView rank, TextView player,
                               TextView team1, TextView pos, Player playerObj) {
        if (playerObj.getQueue()) {
            if (team.equals("BOS")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.bostonbg));
                //layout.setBackgroundResource(R.drawable.bostonbg);
                layout.setBackgroundColor(Color.rgb(0, 122, 51));
            }
            if (team.equals("BKN")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.brooklynbg));
                //layout.setBackgroundResource(R.drawable.brooklynbg);
                layout.setBackgroundColor(Color.rgb(0, 0, 0));
            }
            if (team.equals("NYK")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.knicksbg));
                //layout.setBackgroundResource(R.drawable.knicksbg);
                layout.setBackgroundColor(Color.rgb(245, 132, 38));
                rank.setTextColor(Color.BLACK);
                player.setTextColor(Color.BLACK);
                team1.setTextColor(Color.BLACK);
                pos.setTextColor(Color.BLACK);
                pos.setTextColor(Color.BLACK);
            }
            if (team.equals("PHI")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.sixersbg));
                //layout.setBackgroundResource(R.drawable.phillybg);
                layout.setBackgroundColor(Color.rgb(0, 107, 182));
            }
            if (team.equals("TOR")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.raptorsbg));
                //layout.setBackgroundResource(R.drawable.raptorsbg);
                layout.setBackgroundColor(Color.rgb(206, 17, 65));
            }
            if (team.equals("CHI")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.bullsbg));
                //layout.setBackgroundResource(R.drawable.bullsbg);
                layout.setBackgroundColor(Color.rgb(206, 17, 65));
            }
            if (team.equals("CLE")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.cavsbg));
                //layout.setBackgroundResource(R.drawable.cavsbg);
                layout.setBackgroundColor(Color.rgb(134, 0, 56));
            }
            if (team.equals("DET")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.pistonsbg));
                //layout.setBackgroundResource(R.drawable.pistonsbg);
                layout.setBackgroundColor(Color.rgb(200, 16, 46));
            }
            if (team.equals("IND")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.pacersbg));
                //layout.setBackgroundResource(R.drawable.pacersbg);
                layout.setBackgroundColor(Color.rgb(0, 45, 98));
            }
            if (team.equals("MIL")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.bucksbg));
                //layout.setBackgroundResource(R.drawable.bucksbg);
                layout.setBackgroundColor(Color.rgb(0, 71, 27));
            }
            if (team.equals("ATL")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.hawksbg));
                //layout.setBackgroundResource(R.drawable.hawksbg);
                layout.setBackgroundColor(Color.rgb(225, 68, 52));
            }
            if (team.equals("CHA")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.hornetsbg));
                //layout.setBackgroundResource(R.drawable.hornetsbg);
                layout.setBackgroundColor(Color.rgb(29, 17, 96));
            }
            if (team.equals("MIA")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.heatbg));
                //layout.setBackgroundResource(R.drawable.miamibg);
                layout.setBackgroundColor(Color.rgb(152, 0, 46));
            }
            if (team.equals("ORL")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.magicbg));
                //layout.setBackgroundResource(R.drawable.magicbg);
                layout.setBackgroundColor(Color.rgb(0, 125, 197));
            }
            if (team.equals("WAS")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.wizardsbg));
                //layout.setBackgroundResource(R.drawable.wizardsbg);
                layout.setBackgroundColor(Color.rgb(0, 43, 92));
            }
            if (team.equals("DEN")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.nuggetsbg));
                //layout.setBackgroundResource(R.drawable.denverbg);
                layout.setBackgroundColor(Color.rgb(253, 184, 39));
                rank.setTextColor(Color.BLACK);
                player.setTextColor(Color.BLACK);
                team1.setTextColor(Color.BLACK);
                pos.setTextColor(Color.BLACK);

            }
            if (team.equals("MIN")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.timberwolvesbg));
                //layout.setBackgroundResource(R.drawable.timberwolvesbg);
                layout.setBackgroundColor(Color.rgb(12, 35, 64));
            }
            if (team.equals("OKC")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.thunderbg));
                //layout.setBackgroundResource(R.drawable.thunderbg);
                layout.setBackgroundColor(Color.rgb(0, 125, 195));
            }
            if (team.equals("POR")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.blazersbg));
                //layout.setBackgroundResource(R.drawable.blazersbg);
                layout.setBackgroundColor(Color.rgb(224, 58, 62));
            }
            if (team.equals("UTA")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.jazzbg));
                //layout.setBackgroundResource(R.drawable.jazzbg);
                layout.setBackgroundColor(Color.rgb(0, 43, 92));
            }
            if (team.equals("GSW")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.warriorsbg));
                //layout.setBackgroundResource(R.drawable.goldenstatebg);
                layout.setBackgroundColor(Color.rgb(29, 66, 138));
            }
            if (team.equals("LAC")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.clippersbg));
                //layout.setBackgroundResource(R.drawable.clippersbg);
                layout.setBackgroundColor(Color.rgb(200, 16, 46));
            }
            if (team.equals("LAL")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.lakersbg));
                //layout.setBackgroundResource(R.drawable.lakersbg);
                layout.setBackgroundColor(Color.rgb(85, 37, 130));
            }
            if (team.equals("PHX")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.sunsbg));
                //layout.setBackgroundResource(R.drawable.sunsbg);
                layout.setBackgroundColor(Color.rgb(229, 96, 32));
                rank.setTextColor(Color.BLACK);
                player.setTextColor(Color.BLACK);
                team1.setTextColor(Color.BLACK);
                pos.setTextColor(Color.BLACK);
            }
            if (team.equals("SAC")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.kingsbg));
                //layout.setBackgroundResource(R.drawable.kingsbg);
                layout.setBackgroundColor(Color.rgb(91, 43, 130));
            }
            if (team.equals("DAL")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.mavsbg));
                //layout.setBackgroundResource(R.drawable.mavsbg);
                layout.setBackgroundColor(Color.rgb(0, 125, 197));
            }
            if (team.equals("HOU")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.rocketsbg));
                //layout.setBackgroundResource(R.drawable.rocketsbg);
                layout.setBackgroundColor(Color.rgb(206, 17, 65));
            }
            if (team.equals("MEM")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.grizzliesbg));
                //layout.setBackgroundResource(R.drawable.grizzliesbg);
                layout.setBackgroundColor(Color.rgb(93, 118, 169));
            }
            if (team.equals("NO")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.pelicansbg));
                //layout.setBackgroundResource(R.drawable.pelicansbg);
                layout.setBackgroundColor(Color.rgb(0, 43, 92));
            }
            if (team.equals("SA")) {
                //layout.setBackground(context.getResources().getDrawable(R.drawable.spursbg));
                //layout.setBackgroundResource(R.drawable.spursbg);
                layout.setBackgroundColor(Color.rgb(0, 0, 0));
            }
        }
    }
    private void setbg(LinearLayout layout, String team, TextView rank, TextView player,
                       TextView team1, TextView pos){
        if (team.equals("BOS")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.bostonbg));
            //layout.setBackgroundResource(R.drawable.bostonbg);
            layout.setBackgroundColor(Color.rgb(0, 122, 51));
        }
        if (team.equals("BKN")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.brooklynbg));
            //layout.setBackgroundResource(R.drawable.brooklynbg);
            layout.setBackgroundColor(Color.rgb(0, 0, 0));
        }
        if (team.equals("NYK")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.knicksbg));
            //layout.setBackgroundResource(R.drawable.knicksbg);
            layout.setBackgroundColor(Color.rgb(245, 132, 38));
            rank.setTextColor(Color.BLACK);
            player.setTextColor(Color.BLACK);
            team1.setTextColor(Color.BLACK);
            pos.setTextColor(Color.BLACK);
            pos.setTextColor(Color.BLACK);
        }
        if (team.equals("PHI")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.sixersbg));
            //layout.setBackgroundResource(R.drawable.phillybg);
            layout.setBackgroundColor(Color.rgb(0, 107, 182));
        }
        if (team.equals("TOR")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.raptorsbg));
            //layout.setBackgroundResource(R.drawable.raptorsbg);
            layout.setBackgroundColor(Color.rgb(206, 17, 65));
        }
        if (team.equals("CHI")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.bullsbg));
            //layout.setBackgroundResource(R.drawable.bullsbg);
            layout.setBackgroundColor(Color.rgb(206, 17, 65));
        }
        if (team.equals("CLE")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.cavsbg));
            //layout.setBackgroundResource(R.drawable.cavsbg);
            layout.setBackgroundColor(Color.rgb(134, 0, 56));
        }
        if (team.equals("DET")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.pistonsbg));
            //layout.setBackgroundResource(R.drawable.pistonsbg);
            layout.setBackgroundColor(Color.rgb(200, 16, 46));
        }
        if (team.equals("IND")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.pacersbg));
            //layout.setBackgroundResource(R.drawable.pacersbg);
            layout.setBackgroundColor(Color.rgb(0, 45, 98));
        }
        if (team.equals("MIL")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.bucksbg));
            //layout.setBackgroundResource(R.drawable.bucksbg);
            layout.setBackgroundColor(Color.rgb(0, 71, 27));
        }
        if (team.equals("ATL")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.hawksbg));
            //layout.setBackgroundResource(R.drawable.hawksbg);
            layout.setBackgroundColor(Color.rgb(225, 68, 52));
        }
        if (team.equals("CHA")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.hornetsbg));
            //layout.setBackgroundResource(R.drawable.hornetsbg);
            layout.setBackgroundColor(Color.rgb(29, 17, 96));
        }
        if (team.equals("MIA")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.heatbg));
            //layout.setBackgroundResource(R.drawable.miamibg);
            layout.setBackgroundColor(Color.rgb(152, 0, 46));
        }
        if (team.equals("ORL")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.magicbg));
            //layout.setBackgroundResource(R.drawable.magicbg);
            layout.setBackgroundColor(Color.rgb(0, 125, 197));
        }
        if (team.equals("WAS")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.wizardsbg));
            //layout.setBackgroundResource(R.drawable.wizardsbg);
            layout.setBackgroundColor(Color.rgb(0, 43, 92));
        }
        if (team.equals("DEN")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.nuggetsbg));
            //layout.setBackgroundResource(R.drawable.denverbg);
            layout.setBackgroundColor(Color.rgb(253, 184, 39));
            rank.setTextColor(Color.BLACK);
            player.setTextColor(Color.BLACK);
            team1.setTextColor(Color.BLACK);
            pos.setTextColor(Color.BLACK);

        }
        if (team.equals("MIN")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.timberwolvesbg));
            //layout.setBackgroundResource(R.drawable.timberwolvesbg);
            layout.setBackgroundColor(Color.rgb(12, 35, 64));
        }
        if (team.equals("OKC")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.thunderbg));
            //layout.setBackgroundResource(R.drawable.thunderbg);
            layout.setBackgroundColor(Color.rgb(0, 125, 195));
        }
        if (team.equals("POR")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.blazersbg));
            //layout.setBackgroundResource(R.drawable.blazersbg);
            layout.setBackgroundColor(Color.rgb(224, 58, 62));
        }
        if (team.equals("UTA")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.jazzbg));
            //layout.setBackgroundResource(R.drawable.jazzbg);
            layout.setBackgroundColor(Color.rgb(0, 43, 92));
        }
        if (team.equals("GSW")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.warriorsbg));
            //layout.setBackgroundResource(R.drawable.goldenstatebg);
            layout.setBackgroundColor(Color.rgb(29, 66, 138));
        }
        if (team.equals("LAC")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.clippersbg));
            //layout.setBackgroundResource(R.drawable.clippersbg);
            layout.setBackgroundColor(Color.rgb(200, 16, 46));
        }
        if (team.equals("LAL")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.lakersbg));
            //layout.setBackgroundResource(R.drawable.lakersbg);
            layout.setBackgroundColor(Color.rgb(85, 37, 130));
        }
        if (team.equals("PHX")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.sunsbg));
            //layout.setBackgroundResource(R.drawable.sunsbg);
            layout.setBackgroundColor(Color.rgb(229, 96, 32));
            rank.setTextColor(Color.BLACK);
            player.setTextColor(Color.BLACK);
            team1.setTextColor(Color.BLACK);
            pos.setTextColor(Color.BLACK);
        }
        if (team.equals("SAC")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.kingsbg));
            //layout.setBackgroundResource(R.drawable.kingsbg);
            layout.setBackgroundColor(Color.rgb(91, 43, 130));
        }
        if (team.equals("DAL")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.mavsbg));
            //layout.setBackgroundResource(R.drawable.mavsbg);
            layout.setBackgroundColor(Color.rgb(0, 125, 197));
        }
        if (team.equals("HOU")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.rocketsbg));
            //layout.setBackgroundResource(R.drawable.rocketsbg);
            layout.setBackgroundColor(Color.rgb(206, 17, 65));
        }
        if (team.equals("MEM")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.grizzliesbg));
            //layout.setBackgroundResource(R.drawable.grizzliesbg);
            layout.setBackgroundColor(Color.rgb(93, 118, 169));
        }
        if (team.equals("NO")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.pelicansbg));
            //layout.setBackgroundResource(R.drawable.pelicansbg);
            layout.setBackgroundColor(Color.rgb(0, 43, 92));
        }
        if (team.equals("SA")) {
            //layout.setBackground(context.getResources().getDrawable(R.drawable.spursbg));
            //layout.setBackgroundResource(R.drawable.spursbg);
            layout.setBackgroundColor(Color.rgb(0, 0, 0));
        }
    }

}
