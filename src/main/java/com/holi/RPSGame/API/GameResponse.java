package com.holi.RPSGame.API;

public class GameResponse {
    private String userPick;
    private String computerPick;
    private String result;


    public GameResponse(String userPick, String computerPick, String result) {
        this.userPick = userPick;
        this.computerPick = computerPick;
        this.result = result;
    }


    public String getUserPick() {

        return userPick;
    }

    public String getComputerPick() {

        computerPick = computerPick.substring(0, 1).toUpperCase() + computerPick.substring(1);

        return computerPick;
    }

    public String getResult() {
        return result;
    }
}
