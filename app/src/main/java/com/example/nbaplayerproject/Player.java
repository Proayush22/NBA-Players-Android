package com.example.nbaplayerproject;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Player implements Parcelable {
    private String name;
    private String team;
    private String position;
    private String rank;
    private int image;
    private String height;
    private String weight;
    private String age;
    private String points;
    private String rebounds;
    private String assists;
    private String steals;
    private String blocks;
    private String turnovers;
    private String fg;
    private String ft;
    private String three;
    private String games;

    private Boolean queue;

    private int background;

    public Player(String rank, String name, String team, String position, int image) {
        this.name = name;
        this.team = team;
        this.position = position;
        this.rank = rank;
        this.image = image;
    }
    public Player(String rank, String name, String team, String position, int image,
                  String height, String weight, String age,  String points, String rebounds,
                  String assists, String steals, String blocks, String turnovers, String fg,
                  String ft, String three, String games) {
        this.name = name;
        this.team = team;
        this.position = position;
        this.rank = rank;
        this.image = image;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.turnovers = turnovers;
        this.fg = fg;
        this.ft = ft;
        this.three = three;
        this.games = games;
        this.queue = false;
    }

    protected Player(Parcel in) {
        name = in.readString();
        team = in.readString();
        position = in.readString();
        rank = in.readString();
        image = in.readInt();
        height = in.readString();
        weight = in.readString();
        age = in.readString();
        points = in.readString();
        rebounds = in.readString();
        assists = in.readString();
        steals = in.readString();
        blocks = in.readString();
        turnovers = in.readString();
        fg = in.readString();
        ft = in.readString();
        three = in.readString();
        games = in.readString();
        //queue = in.readBoolean();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public String getPosition() {
        return position;
    }

    public int getImage() {
        return image;
    }

    public String getRank() {
        return rank;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getAge() {
        return age;
    }

    public String getPoints() {
        return points;
    }

    public String getRebounds() {
        return rebounds;
    }

    public String getAssists() {
        return assists;
    }

    public String getSteals() {
        return steals;
    }

    public String getBlocks() {
        return blocks;
    }

    public String getTurnovers() {
        return turnovers;
    }

    public String getFg() {
        return fg;
    }

    public String getFt() {
        return ft;
    }

    public String getThree() {
        return three;
    }

    public String getGames() {
        return games;
    }
    public Boolean getQueue() {
        return queue;
    }
    public void setQueue(Boolean queue) {
        this.queue = queue;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(team);
        parcel.writeString(position);
        parcel.writeString(rank);
        parcel.writeInt(image);
        parcel.writeString(height);
        parcel.writeString(weight);
        parcel.writeString(age);
        parcel.writeString(points);
        parcel.writeString(rebounds);
        parcel.writeString(assists);
        parcel.writeString(steals);
        parcel.writeString(blocks);
        parcel.writeString(turnovers);
        parcel.writeString(fg);
        parcel.writeString(ft);
        parcel.writeString(three);
        parcel.writeString(games);
        //parcel.writeBoolean(queue);
    }
}
