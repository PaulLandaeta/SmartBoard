package testSuite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import edu.upb.lp.test.activities.DynamicTexts;
import io.appium.java_client.AppiumDriver;
import test.controls.Label;
import test.session.Session;


public class DynamicTextTest {
    private AppiumDriver driver;
    private DynamicTexts dynamicTexts;

    @Before
    public void setUp() {
        Session.resetInstance();
        driver = Session.getInstance().getDevice();
        dynamicTexts = new DynamicTexts(driver);
    }

    @Test
    public void testBuyFoodUpdatesMoneyAndFoodPrice() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.widget.TextView[contains(@text, 'Money:')]")));

            Label moneyLabel = dynamicTexts.getMoneyLabel();
            Label foodLabel = dynamicTexts.getFpriceLabel();

            wait.until(d -> {
                assertEquals("Money: 100", moneyLabel.getText());
                assertEquals("Food price: 10", foodLabel.getText());
                return true;
            });

            dynamicTexts.clickButtonBuyFood();

            wait.until(d -> {
                assertEquals("Money: 90", moneyLabel.getText());
                assertEquals("Food price: 11", foodLabel.getText());
                return true;
            });

        } catch (Exception e) {
            System.out.println("Estado actual de la pantalla: " + driver.getPageSource());
            throw e;
        }
    }

    @Test
    public void testPassDayUpdatesTime() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            Label timeLabel = wait.until(d -> {
                Label label = dynamicTexts.getTimeLabel();
                assertNotNull("El elemento TimeLabel no debería ser null", label);
                return label;
            });

            assertEquals("El tiempo inicial debería ser 0", timeLabel.getText(), "Time: 0" );
            dynamicTexts.clickButtonPassDay();
            wait.until(d -> {
                String currentTime = timeLabel.getText();
                assertEquals("El tiempo debería incrementarse después de pasar un día",
                        "Time: 1", timeLabel.getText() );
                return true;
            });

        } catch (Exception e) {

            System.out.println("Estado actual de la pantalla: " + driver.getPageSource());
            fail("Falló el test de paso de día: " + e.getMessage());
        }
    }

    @Test
    public void testRestartUpdatesScore() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Compras
        dynamicTexts.clickButtonBuyFood();

        // Verificaciones
        dynamicTexts.clickButtonRestart();

        Label moneyLabel = dynamicTexts.getMoneyLabel();
        wait.until(d -> {
            assertEquals("El dinero debería volver a 100",
                    "Money: 100", moneyLabel.getText());
            return true;
        });


    }



    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}