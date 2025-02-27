package com.holi.RPSGame.Service;

import com.holi.RPSGame.API.GameResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class GameService {

    private static final List<String> selection = List.of("schere", "stein", "papier", "brunnen");

    public GameService() {
    }

    /**
     * Verarbeitet die Usereingabe (case-insensitive), prüft sie und bestimmt den Spielausgang.
     * Der Computer wählt zufällig eine Option aus der Liste aller möglichen Züge.
     * Der Spieler-Zug wird per Index asu der Liste aller möglichen Züge bestimmt,
     * sodass beide Züge verglichen und das Ergebnis ermittelt werden kann.
     *
     * @param userPick - Auswahl des Spielers als String
     * @return Gameresponse mit den Zügen von Spieler und Computer sowie dem Rundenergebnis.
     */
    public GameResponse play(String userPick) {

        if (userPick == null || userPick.trim().isEmpty()) {
            throw new IllegalArgumentException("Eingabe darf nicht leer oder null sein!");

        }

        String rawUserPick = userPick.toLowerCase().trim();

        if (!selection.contains(rawUserPick)) {
            throw new IllegalArgumentException("Ungültige Eingabe! Erlaubte Werte: " + selection);
        }


        Random randomPickInt = new Random();
        int computerPick = randomPickInt.nextInt(selection.size());
        int humanPick = selection.indexOf(rawUserPick);
        String decision = getDecision(computerPick, humanPick);

        String computerPickAsString = selection.get(computerPick);


        return new GameResponse(userPick, computerPickAsString, decision);
    }

    /**
     * Vergleich beide Züge, um einen Spielergebniss CHCzu ermitteln.
     *
     * @param computerPick Auswahl des Computers als Index-Wert
     * @param humanPick Auswahl des Spielers als Index-Wert
     * @return Gibt das Spielergebnis als String zurück
     */
    @NotNull
    private String getDecision(int computerPick, int humanPick) {
        String decision;
        if (computerPick == humanPick) {
            decision = "UNENTSCHIEDEN";
        } else {
            decision = theWinnerIs(humanPick, computerPick) ? "GEWONNEN" : "VERLOREN";
        }
        return decision;
    }


    /**
     * Auswertung des Spielergebniss als Matrix.
     *
     * @param player Index der Spieler Auswahl als Y-Koordinate
     * @param computer Index der Spieler Auswahl als X-Koordinate
     * @return Gibt das Spielergebnis als Booleanwert an der ausgewählten Koordinate zurück
     */
    public boolean theWinnerIs(int player, int computer) {

        boolean[][] winMatrix = {
                         // Schere Stein Papier Brunnen
                /*Schere*/ {false, false, true, false},
                /*Stein*/  {true, false, true, false},
                /*Papier*/ {false, true, false, true},
               /*Brunnen*/ {true, true, false, false}

        };


        return winMatrix[player][computer];
    }
}
