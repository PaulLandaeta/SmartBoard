package edu.upb.lp.test.activities;

import io.appium.java_client.AppiumDriver;
import test.controls.Button;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Objects;

public class MainActivity {

    private final AppiumDriver driver;

    public MainActivity(AppiumDriver driver) {
        this.driver = driver;
    }

    private final By botones = By.id("edu.upb.lp.genericgame:id/buttons");

    public boolean isButtonsVisible() {
        return driver.findElement(botones).isDisplayed();
    }

    public void clickButtonRestart() {
        driver.findElement(By.xpath("//android.widget.Button[@text=\"RESTART\"]")).click();
    }

    public void confirmRestartDialogIfPresent() {
        if (!driver.findElements(By.id("android:id/content")).isEmpty()) {
            driver.findElement(By.id("android:id/button1")).click();
        }
    }

    public void clickButtonPassDay() {
        driver.findElement(By.xpath("//android.widget.Button[@text=\"PASS DAY\"]")).click();
    }

    public void clickButtonBuyFood() {
        driver.findElement(By.xpath("//android.widget.Button[@text=\"BUY FOOD\"]")).click();
    }

    public class Position {
        public Button leftBug;
        public Button rightBug;
        public Position() {
            this.leftBug = new Button(By.xpath("//android.widget.TableLayout[@resource-id=\"edu.upb.lp.genericgame:id/maingrid\"]/android.widget.TableRow[5]/android.widget.TextView[4]"));
            this.rightBug = new Button(By.xpath("//android.widget.TableLayout[@resource-id=\"edu.upb.lp.genericgame:id/maingrid\"]/android.widget.TableRow[5]/android.widget.TextView[5]"));
        }
    }

    public void clickButtonSellBug() {
        driver.findElement(By.xpath("//android.widget.Button[@text=\"SELL BUG\"]")).click();
    }

    public void clickCellXpath(String s){
        driver.findElement(By.xpath(s)).click();
    }

    public boolean isHeaderTextVisible() {
        return Objects.requireNonNull(driver.getPageSource()).contains("Bug World");
    }
}
