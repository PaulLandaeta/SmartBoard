package testSuite;

import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.pages.BottomBarPage;
import test.session.Session;

public class BottomBarTest {

    private AppiumDriver driver;
    private BottomBarPage bottomBar;

    @Before
    public void setUp() {
        Session.reset();
        driver    = Session.getInstance().getDevice();
        bottomBar = new BottomBarPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
        Session.reset();
    }

    @Test
    public void allButtonsArePresentAndCorrect() {
        bottomBar.verifyAllButtons();
    }
}
