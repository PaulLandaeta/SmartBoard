package testSuite;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import edu.upb.lp.test.activities.MainActivity;
import io.appium.java_client.AppiumDriver;
import test.session.Session;
import org.junit.Assert;
import org.openqa.selenium.By;


public class InitialPositionTest {

    private AppiumDriver driver;
    private MainActivity.Position position;

    // Posiciones que vamos a verificar
    private final By originalPosition = By.xpath("//android.widget.TableLayout[@resource-id=\"edu.upb.lp.genericgame:id/maingrid\"]/android.widget.TableRow[5]/android.widget.TextView[4]"); // leftBug
    private final By newPosition = By.xpath("//android.widget.TableLayout[@resource-id=\"edu.upb.lp.genericgame:id/maingrid\"]/android.widget.TableRow[4]/android.widget.TextView[4]");

    private MainActivity mainActivity;

    @Before
    public void setUp() {
        Session.resetInstance();
        driver = Session.getInstance().getDevice();
        mainActivity = new MainActivity(driver);
    }
    @Test
    public void testBugInitialPosition() {
        String actualText = driver.findElement(originalPosition).getText();

        String expectedBugText = "";

        System.out.println("Texto en la posición inicial: " + actualText);

        Assert.assertEquals("El bug no está en la posición inicial esperada.", expectedBugText,actualText);
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
