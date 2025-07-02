package test.activities;

import org.openqa.selenium.By;

import test.controls.Button;
import test.controls.Label;

public class DynamicTexts {
    public Button restart = new Button(By.xpath("//android.widget.Button[@text=\"RESTART\"]"));
    public Button passDay = new Button(By.xpath("//android.widget.Button[@text=\"PASS DAY\"]"));
    public Button buyFood = new Button(By.xpath("//android.widget.Button[@text=\"BUY FOOD\"]"));
    public Label getScoreLabel(String texts){
        Label score = new Label(By.xpath("(//android.widget.TextView[@text=\"Score: 0\"])[2]"));
        return score;
    }
    public Label getMoneyLabel(String texts){
        Label money = new Label(By.xpath("//android.widget.TextView[@text=\"Money: 100\"]"));
        return money;
    }
    public Label getFpriceLabel(String texts){
        Label foodPrice = new Label(By.xpath("//android.widget.TextView[@text=\"Food price: 10\"]"));
        return foodPrice;
    }
    public Label getTimeLabel(String texts){
        Label time = new Label(By.xpath("//android.widget.TextView[@text=\"Time: 0\"]"));
        return time;
    }
}
