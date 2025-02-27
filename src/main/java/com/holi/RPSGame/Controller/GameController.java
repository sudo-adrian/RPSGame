package com.holi.RPSGame.Controller;


import com.holi.RPSGame.API.GameRequest;
import com.holi.RPSGame.API.GameResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.holi.RPSGame.Service.GameService;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Nimmt die Benutzereingabe entgegen, validiert sie und übergibt sie an die Spiel-Logik.
     * Anschließend wird eine GameResponse mit den gewählten Zügen und dem Spielergebnis zurückgegeben.
     *
     * @param gameRequest - Die Anfrage mit der Auswahl des Spielers.
     * @return ResponseEntity mit der GameResponse, die den Zug des Spielers, den Zug des Computers und das Ergebnis enthält.
     */
    @PostMapping("/play")
    public ResponseEntity<GameResponse> playGame(@Valid @RequestBody GameRequest gameRequest) {

        GameResponse response = gameService.play(gameRequest.getPick());

        return ResponseEntity.ok(response);
    }
}