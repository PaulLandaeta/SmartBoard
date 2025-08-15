package edu.upb.lp.test.activities;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import test.controls.Label;

public class DynamicTexts {
    //mejoras para test de textos dinamicos
    private final AppiumDriver driver;
    public DynamicTexts(AppiumDriver driver) {
        this.driver = driver;
    }
    public void clickButtonRestart() {
        driver.findElement(By.xpath("//android.widget.Button[@text=\"RESTART\"]")).click();
    }
    public void clickButtonPassDay() {
        driver.findElement(By.xpath("//android.widget.Button[@text=\"PASS DAY\"]")).click();
    }
    public void clickButtonBuyFood() {
        driver.findElement(By.xpath("//android.widget.Button[@text=\"BUY FOOD\"]")).click();
    }
    public Label getMoneyLabel(){
        return new Label(By.xpath("//android.widget.TextView[contains(@text, 'Money:')]"));
    }
    public Label getFpriceLabel(){
        return new Label(By.xpath("//android.widget.TextView[contains(@text, 'Food price:')]"));
    }
    public Label getTimeLabel(){
        Label time = new Label(By.xpath("//android.widget.TextView[contains(@text, 'Time:')]"));
        return time;
    }
}
