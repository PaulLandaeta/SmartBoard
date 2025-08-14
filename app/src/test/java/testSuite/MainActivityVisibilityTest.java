package testSuite;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import edu.upb.lp.test.activities.MainActivity;
import io.appium.java_client.AppiumDriver;
import test.session.Session;
import org.junit.Assert;

import org.openqa.selenium.By;

public class MainActivityVisibilityTest {
    private AppiumDriver driver;
    private MainActivity mainActivity;

    @Before
    public void setUp() {
        Session.resetInstance();
        driver = Session.getInstance().getDevice();
        mainActivity = new MainActivity(driver);
    }
    @Test
    public void testClickPassDayAndCheckAge() {
        mainActivity.clickButtonPassDay();

        String cellXPath = "//android.widget.TableLayout[@resource-id='edu.upb.lp.genericgame:id/maingrid']/android.widget.TableRow[5]/android.widget.TextView[5]";
        driver.findElement(By.xpath(cellXPath)).click();

        boolean ageFound = !driver.findElements(By.xpath("//android.widget.TextView[@text='  age: 1']")).isEmpty();
        Assert.assertTrue("No se encontró el texto '  age: 1' después de pasar el día y seleccionar la casilla.", ageFound);
    }
    @Test
    public void testClickRestartButton() {
        for(int i  = 0; i <= 10;i++){
            mainActivity.clickButtonBuyFood();
        }
        for (int i = 0; i<= 15;i++){
            mainActivity.clickButtonPassDay();
        }

        mainActivity.clickButtonRestart();
        mainActivity.confirmRestartDialogIfPresent();

        boolean casillasEntre0y20 = false;
        for (int i = 0; i <= 20; i++) {
            String xpath = "//android.widget.TextView[@text='" + i + "']";
            if (!driver.findElements(By.xpath(xpath)).isEmpty()) {
                casillasEntre0y20 = true;
                break;
            }
        }

        Assert.assertFalse("No se borro la comida de la pantalla", casillasEntre0y20);

        Assert.assertFalse("No se encontró 'Score: 0'", driver.findElements(By.xpath("(//android.widget.TextView[@text='Score: 0'])[2]")).isEmpty());
        Assert.assertFalse("No se encontró 'Money: 100'", driver.findElements(By.xpath("//android.widget.TextView[@text='Money: 100']")).isEmpty());
        Assert.assertFalse("No se encontró 'Food price: 10'", driver.findElements(By.xpath("//android.widget.TextView[@text='Food price: 10']")).isEmpty());
        Assert.assertFalse("No se encontró 'Time: 0'", driver.findElements(By.xpath("//android.widget.TextView[@text='Time: 0']")).isEmpty());
    }

    @Test
    public void testClickBuyFoodButton() {
        mainActivity.clickButtonBuyFood();

        boolean foodTextFound = !driver.findElements(By.xpath("//android.widget.TextView[@text='20']")).isEmpty();

        Assert.assertTrue("No se encontró comida despues de comprar comida.", foodTextFound);
    }
    @Test
    public void testButtonsVisibility() {
        Assert.assertTrue("Los botones no son visibles", mainActivity.isButtonsVisible());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
