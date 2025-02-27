package com.holi.RPSGame;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Testet, ob eine gültiger POST-Request an die API korrekt verarbeitet wird.
     */
    @Test
    public void testValidRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/game/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"pick\": \"Schere\"}"))
                .andExpect(status().isOk()) // 200 OK
                .andExpect(jsonPath("$.userPick").value("Schere"))
                .andExpect(jsonPath("$.computerPick").exists())
                .andExpect(jsonPath("$.result").exists());
    }


    /**
     * Testet, ob ein fehlerhafter Request an die API korrekt abgelehnt wird.
     */
    @Test
    public void testInvalidRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/game/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"badRequest\": \"Schere\"}")) //
                .andExpect(status().isBadRequest()); //
    }

    /**
     * Testet, ob eine leere Anfrage korrekt abgelehnt wird.
     */
    @Test
    public void testEmptyRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/game/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")) //
                .andExpect(status().isBadRequest()); //
    }
}


