package test.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class BottomBarPage {

    private final AppiumDriver driver;
    private final List<String> expectedLabels =
            Arrays.asList("RESTART", "PASS DAY", "BUY FOOD", "SHOW TUTORIAL");

    public BottomBarPage(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Verifica que cada texto esperado tenga al menos un botón,
     * que esté visible y su texto coincida exactamente.
     */
    public void verifyAllButtons() {
        for (String label : expectedLabels) {
            List<WebElement> elems = driver.findElements(
                    AppiumBy.androidUIAutomator("new UiSelector().text(\"" + label + "\")")
            );
            if (elems.isEmpty()) {
                throw new AssertionError("Botón no encontrado: " + label);
            }
            WebElement btn = elems.get(0);
            if (!btn.isDisplayed()) {
                throw new AssertionError("Botón no visible: " + label);
            }
            if (!btn.getText().equals(label)) {
                throw new AssertionError(
                        String.format("Texto esperado '%s' pero se encontró '%s'", label, btn.getText())
                );
            }
        }
    }
}
