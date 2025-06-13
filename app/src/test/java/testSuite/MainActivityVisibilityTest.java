package testSuite;

import edu.upb.lp.test.activities.MainActivity;
import test.session.Session;

import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

// Clase de prueba que valida la visibilidad del contenedor de botones en MainActivity
public class MainActivityVisibilityTest {

    private AppiumDriver driver;
    private MainActivity mainActivity;

    // Se ejecuta antes de cada prueba, inicializa el driver y la actividad
    @Before
    public void setUp() {
        driver = Session.getInstance().getDevice();
        mainActivity = new MainActivity(driver);
    }

    // Verifica que el contenedor de botones sea visible
    @Test
    public void testButtonsContainerIsVisible() {
        assertTrue("El contenedor de botones no está visible", mainActivity.isButtonVisible());
    }

    // Finaliza el driver después de la prueba
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}