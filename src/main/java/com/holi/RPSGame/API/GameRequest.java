package com.holi.RPSGame.API;


import jakarta.validation.constraints.NotNull;

public class GameRequest {

    @NotNull(message = "Ein Wert für pick muss gesetzt sein")
    private String pick;

    public GameRequest() {
    }

    public GameRequest(String pick) {
        this.pick = pick;
    }

    public String getPick() {
        return pick;
    }

    public void setPick(String pick) {
        this.pick = pick;
    }
}
