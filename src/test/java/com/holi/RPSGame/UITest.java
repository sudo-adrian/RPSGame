
package com.holi.RPSGame;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;


/**
 *Woll
 */
public class UITest {

    private static Playwright playwright;
    private static Browser browser;
    private  BrowserContext context;
    private Page page;

    /**
     *Starten des Playwright Browsers im sichtbaren Modus (Headless = false)
     * wird immer vor dem Test aufgerufen
     */
    @BeforeAll
    static void launcheBrowser(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    /**
     * Erstellt einen neuen Browser-Kontext mit einer neuen Seite
     * wird immer vor dem Test aufgerufen
     */
    @BeforeEach
    void createContextAndPage(){
        context = browser.newContext();
        page = context.newPage();

    }

    /**
     * UI Test, ob durch das Klicken des Auswahlbuttons, die Spielerauswahl zu einer korrekten Anfrage an die API führt.
     */
    @Test
    public void testButtonClick(){

        page.navigate("http://localhost:8080");

        page.click("button:has-text('Schere')");

       String resultText = page.textContent("#result");
       assertNotNull(resultText);
       assertTrue(resultText.contains("Du:"));
       assertTrue(resultText.contains("Computer:"));
       assertTrue(resultText.contains("Ergebnis:"));

    }

    @AfterEach
    public void closeContext(){
        context.close();
    }
    @AfterAll
    static void closeBrowser() {
        browser.close();
        playwright.close();

    }
}

