package testSuite;

import org.junit.Test;

import io.appium.java_client.AppiumDriver;
import test.activities.BugScreen;
import test.session.Session;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;

public class TestBugger {
    private AppiumDriver driver;
    private BugScreen bugScreen;

    // Posiciones que vamos a verificar
    private final By originalPosition = By.xpath("//android.widget.TableLayout[@resource-id=\"edu.upb.lp.genericgame:id/maingrid\"]/android.widget.TableRow[5]/android.widget.TextView[4]"); // leftBug
    private final By newPosition = By.xpath("//android.widget.TableLayout[@resource-id=\"edu.upb.lp.genericgame:id/maingrid\"]/android.widget.TableRow[4]/android.widget.TextView[4]");   // adjacentCell

    @Before
    public void setUp() throws Exception {
        Session.getInstance().setUp();
        bugScreen = new BugScreen();
    }

    // Verificar movimiento visual del jugador
    @Test
    public void testBugMovement() {
        String originalText = bugScreen.leftBug.getText();
        bugScreen.moveBug(bugScreen.leftBug, bugScreen.adjacentCell);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String textInOriginal = Session.getInstance().getDevice().findElement(originalPosition).getText();
        String textInNew = Session.getInstance().getDevice().findElement(newPosition).getText();

        System.out.println("Texto original: " + originalText);
        System.out.println("Texto en celda original: " + textInOriginal);
        System.out.println("Texto en nueva celda: " + textInNew);

        boolean moved = !textInOriginal.equals(originalText) || textInNew.equals(originalText);

        org.junit.Assert.assertTrue("El bug no se movió ni desapareció, la UI no cambió", moved);
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
