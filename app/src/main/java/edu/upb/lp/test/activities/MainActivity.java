package edu.upb.lp.test.activities;

import io.appium.java_client.AppiumDriver;
import java.util.Objects;
import org.openqa.selenium.By;

public class MainActivity {

    private final AppiumDriver driver;
    private final By botones = By.id("edu.upb.lp.genericgame:id/buttons");

    public MainActivity(AppiumDriver driver) {
        this.driver = driver;
    }

    public boolean isHeaderTextVisible() {
        return Objects.requireNonNull(driver.getPageSource()).contains("Bug World");
    }

    public boolean isButtonsVisible() {
        return driver.findElement(botones).isDisplayed();
    }

    public void clickButtonRestart() {
        driver.findElement(By.xpath("//android.widget.Button[@text="RESTART"]")).click();
    }

    public void confirmRestartDialogIfPresent() {
        if (!driver.findElements(By.id("android:id/content")).isEmpty()) {
            driver.findElement(By.id("android:id/button1")).click();
        }
    }

    public void clickButtonPassDay() {
        driver.findElement(By.xpath("//android.widget.Button[@text="PASS DAY"]")).click();
    }

    public void clickButtonBuyFood() {
        driver.findElement(By.xpath("//android.widget.Button[@text="BUY FOOD"]")).click();
    }
}
