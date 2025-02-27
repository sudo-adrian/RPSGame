package com.holi.RPSGame;

import com.holi.RPSGame.API.GameResponse;
import com.holi.RPSGame.Service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameServiceTest {

    private GameService gameService;
    private List<String> selection;


    @BeforeEach
    public void initService(){
        this.gameService = new GameService();
        selection = List.of("Schere", "Stein", "Papier", "Brunnen");

    }


    /**
     * Testet, ob bei Gleichheit der Computer und Spieler Auswahl Unentschieden zurückgegeben wird
     */
    @Test
    public void testUnentschieden() {
        for (String choice : selection) {
            GameResponse response = gameService.play(choice);
            if (response.getComputerPick().equals(response.getUserPick())) {
                assertEquals("UNENTSCHIEDEN", response.getResult());
            }

        }
    }

    /**
     * Testet, ob der Spieler in den definierten Fällen gewinnt.
     * -- Schere (0) gg. Papier (2)
     * -- Stein (1) gg. Schere (0)
     * -- Papier (2) gg. Brunnen (3)
     */
    @Test
    public void playerWins(){
        assertTrue(gameService.theWinnerIs(0, 2));
        assertTrue(gameService.theWinnerIs(1, 0));
        assertTrue(gameService.theWinnerIs(2, 3));
    }

    /**
     * Testet, ob der Spieler in den definierten Fällen verliert
     * --Schere (0) gg. Stein (1)
     * --Stein (1) gg. Brunnen (3)
     * -- Brunnen (3) gg. Papier (2)
     */
    @Test
    public void playerVerliert(){
        assertFalse(gameService.theWinnerIs(0, 1));
        assertFalse(gameService.theWinnerIs(1, 3));
        assertFalse(gameService.theWinnerIs(3, 2));
    }

    /**
     * Testet die Validierung der Benutzereingabe.
     * Stellt sicher, dass nur Werte aus der erlaubten Auswahl ("Schere", "Stein", "Papier", "Brunnen")
     * akzeptiert werden und dass ungültige Eingaben korrekt behandelt werden.
     */

    @Test
    public void testUngültigeEingabe() {
        assertThrows(IllegalArgumentException.class, () ->
                gameService.play("Baum")
        );
    }

    /**
     * Testet die korrekte Verarbeitung der Eingabe unabhängig der Groß- und Kleinschreibung.
     */
    @Test
    public void testCaseInsensitive() {
        assertDoesNotThrow(() ->
                gameService.play("schere")
        );
    }

    /**
     * Testet die Validierung der Benutzereingabe und die Erstellung einer gültigen GameResponse.
     */
    @Test
    public void testChoiceValidation(){
        String userPick = "schere";
        GameResponse gameResponse = gameService.play(userPick);

        assertNotNull(gameResponse);
        assertEquals(userPick, gameResponse.getUserPick());
        assertNotNull(gameResponse.getComputerPick());
        assertNotNull(gameResponse.getResult());
    }



}
