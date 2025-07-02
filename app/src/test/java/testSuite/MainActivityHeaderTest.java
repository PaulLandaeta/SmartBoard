package testSuite;

import edu.upb.lp.test.activities.MainActivity;
import test.session.Session;
import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

// Prueba que verifica si el encabezado "Bug World" es visible en la pantalla principal
public class MainActivityHeaderTest {

    private AppiumDriver driver;
    private MainActivity mainActivity;

    @Before
    public void setUp() {
        driver = Session.getInstance().getDevice();
        mainActivity = new MainActivity(driver);
    }

    @Test
    public void testHeaderIsVisible() {
        assertTrue("El encabezado 'Bug World' no está visible en la pantalla", mainActivity.isHeaderTextVisible());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
