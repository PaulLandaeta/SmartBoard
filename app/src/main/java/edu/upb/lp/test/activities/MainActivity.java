package edu.upb.lp.test.activities;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;


public class MainActivity {

    private final AppiumDriver driver;


    public MainActivity(AppiumDriver driver) {
        this.driver = driver;
    }

    // Selector del contenedor que agrupa los botones
    private final By botones = By.id("edu.upb.lp.genericgame:id/buttons");

    // Verifica si el contenedor de botones está visible en pantalla
    public boolean isButtonVisible() {
        return driver.findElement(botones).isDisplayed();
    }
    public void tapHomeButton() {
        driver.findElement(By.xpath("//android.widget.Button[@text='Home']")).click();
    }

    public void clickSettingsButton() {
        driver.findElement(By.xpath("//android.widget.Button[@text='Settings']")).click();
    }

    public void clickProfileButton() {
        driver.findElement(By.xpath("//android.widget.Button[@text='Profile']")).click();
    }

}
