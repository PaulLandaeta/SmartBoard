package test.testSuite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DynamicTextsTest extends BaseBugWorld{

    @Test
    public void testBuyFoodUpdatesMoneyAndFoodPrice() {
        String initialMoney = dynamicTexts.getMoneyLabel("*").getText().replace("Money: ", "");
        String initialFoodPrice = dynamicTexts.getFpriceLabel("*").getText().replace("Food price: ", "");

        dynamicTexts.buyFood.click();

        String newMoney = dynamicTexts.getMoneyLabel("*").getText().replace("Money: ", "");
        String newFoodPrice = dynamicTexts.getFpriceLabel("*").getText().replace("Food price: ", "");

        assertNotEquals(initialMoney, newMoney, "El dinero no cambió después de comprar comida");
        assertNotEquals(initialFoodPrice, newFoodPrice, "El precio de la comida no cambió después de comprar");

        assertTrue(Integer.parseInt(newMoney) < Integer.parseInt(initialMoney),
                "El dinero debería haber disminuido al comprar comida");
    }

    @Test
    public void testPassDayUpdatesTime() {

        String initialTime = dynamicTexts.getTimeLabel("*").getText().replace("Time: ", "");

        dynamicTexts.passDay.click();

        String newTime = dynamicTexts.getTimeLabel("*").getText().replace("Time: ", "");

        assertNotEquals(initialTime, newTime, "El tiempo no cambió después de pasar un día");

        assertTrue(Integer.parseInt(newTime) > Integer.parseInt(initialTime),
                "El tiempo debería haber aumentado al pasar un día");
    }

    @Test
    public void testRestartUpdatesScore() {

        String initialScore = dynamicTexts.getScoreLabel("*").getText().replace("Score: ", "");


        dynamicTexts.passDay.click();
        dynamicTexts.buyFood.click();

        String changedScore = dynamicTexts.getScoreLabel("*").getText().replace("Score: ", "");

        assertNotEquals(initialScore, changedScore, "El score debería haber cambiado después de las acciones");

        dynamicTexts.restart.click();

        String restartedScore = dynamicTexts.getScoreLabel("*").getText().replace("Score: ", "");

        assertNotEquals(changedScore, restartedScore, "El score no cambió después del reinicio");
        assertEquals(initialScore, restartedScore, "El score debería volver al valor inicial después del reinicio");
    }
}
