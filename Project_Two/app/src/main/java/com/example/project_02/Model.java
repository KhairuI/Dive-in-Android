package com.example.project_02;

public class Model {
    String playerName;
    String playerType;

    public Model(String playerName, String playerType) {
        this.playerName = playerName;
        this.playerType = playerType;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerType() {
        return playerType;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }
}
