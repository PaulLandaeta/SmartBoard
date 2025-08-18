package test.activities;

import org.openqa.selenium.By;
import test.controls.Button;

public class BugScreen {
    public Button leftBug;
    public Button rightBug;
    public Button adjacentCell;

    public BugScreen() {
        this.leftBug = new Button(By.xpath("//android.widget.TableLayout[@resource-id=\"edu.upb.lp.genericgame:id/maingrid\"]/android.widget.TableRow[5]/android.widget.TextView[4]")); // [5][4]
        this.rightBug = new Button(By.xpath("//android.widget.TableLayout[@resource-id=\"edu.upb.lp.genericgame:id/maingrid\"]/android.widget.TableRow[5]/android.widget.TextView[5]")); // [5][5]

        // Cambiamos a una celda arriba vacía (ejemplo: [4][4])
        this.adjacentCell = new Button(By.xpath("//android.widget.TableLayout[@resource-id=\"edu.upb.lp.genericgame:id/maingrid\"]/android.widget.TableRow[4]/android.widget.TextView[4]"));
    }

    public void moveBug(Button bug, Button targetCell) {
        bug.click();
        targetCell.click();
    }
}
