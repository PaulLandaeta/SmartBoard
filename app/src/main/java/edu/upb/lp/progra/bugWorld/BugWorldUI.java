package edu.upb.lp.progra.bugWorld;

import edu.upb.lp.core.adapter.AndroidLibrary;
import edu.upb.lp.core.adapter.AppConnector;
import edu.upb.lp.core.deck.Deck;
import edu.upb.lp.genericgame.R;

public class BugWorldUI implements AppConnector {
    private BugWorld world;
    private int selectedh = -1;
    private int selectedv = -1;
    private int time = 0;
    private AndroidLibrary gui;

    private ScoreManager scoreManager;

    public BugWorldUI(AndroidLibrary gui) {
        int highScore = gui.retrieveInt("HighScore");
        String highScoreName = gui.retrieveString("HighScoreName");
        world = new BugWorld(this, highScore, highScoreName);
        this.gui = gui;
        scoreManager = new ScoreManager(this, highScore, highScoreName);
    }

    @Override
    public void onButtonPressed(String name) {
        if (name.equals("Restart")) {
            scoreManager.checkHighScore(world.getScore());
        } else {
            if (name.equals("Pass day")) {
                world.day();
            } else if (name.equals("Buy food")) {
                world.buyFood();
            } else if (name.equals("Clean cell")) {
                world.cleanCell(selectedv, selectedh);
            } else if (name.equals("Sell bug")) {
                world.sellBug(selectedv, selectedh);
            } else if (name.equals("Show Tutorial")) {
                Deck deck =  new Deck("Presiona START");
                deck.addCard("PANTALLA 1", "Esta es la pantalla 1", "bugs_old_bug");
                deck.addCard("PANTALLA 2", "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n" +
                        "\n" +
                        "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.", "bugs_dead_bug");
				gui.showDeck(deck);
            }
            updateInterface();
        }
    }

    @Override
    public void onCellPressed(int vertical, int horizontal) {
        if (selectedh == horizontal && selectedv == vertical) {
            // Same position as before selected again, deselect it
            selectedh = -1;
            selectedv = -1;
        } else {
            if (selectedh > -1
                    && world.getCell(selectedv, selectedh).isBugAlive()
                    && Math.abs(selectedh - horizontal) <= 1
                    && Math.abs(selectedv - vertical) <= 1) {
                // Neighboring position, move bug
                world.moveBug(selectedv, selectedh, vertical, horizontal);
            }
            // Select the new position
            selectedh = horizontal;
            selectedv = vertical;
        }
        updateInterface();
    }

    @Override
    public void initialiseInterface() {
        // General configuration

        gui.setDynamicTitle("Bug World");
        gui.setToolbarColors(R.color.aqua, R.color.blue);
        gui.configureScreen(world.getSizeVertical(), world.getSizeHorizontal(),
                1, 1, true, 0.4);

        // Text fields
        // Score
        gui.addTextField("Score", "Score: " + world.getScore(), 15, 20);

        // Money
        gui.addTextField("MoneyP", "Money: " + world.getMoney(), 15, 20);

        // Food price
        gui.addTextField("Food price", "Food price: " + world.getFoodPrice(),
                15, 20);

        //Time
        gui.addTextField("Timer", "Time: " + time / 100, 15, 20);

        // A dummy text to make space
        gui.addTextField("Dummy", "", 0, 20);

        //Buttons
        gui.addButton("Restart", 12, 35);
        gui.addButton("Pass day", 12, 35);
        gui.addButton("Buy food", 12, 35);
        gui.addButton("Show Tutorial", 12, 35);

        updateInterface();
    }

    private void updateInterface() {
        // Text fields
        gui.updateTextField("Timer", "Time: " + time / 100);
        // Score
        gui.updateTextField("Score", "Score: " + world.getScore());

        // Money
        gui.updateTextField("MoneyP", "Money: " + world.getMoney());

        // Food price
        gui.updateTextField("Food price", "Food price: " + world.getFoodPrice());

        // Cells
        for (int vertical = 0; vertical < world.getSizeVertical(); vertical++) {
            for (int horizontal = 0; horizontal < world.getSizeHorizontal(); horizontal++) {
                boolean selected = horizontal == selectedh
                        && vertical == selectedv;
                getImage(vertical, horizontal, selected);
            }
        }

        // Bug description
        if (selectedh > -1 && world.getCell(selectedv, selectedh).isBugAlive()) {
            Bug bug = world.getCell(selectedv, selectedh).getBug();
            gui.addTextField("SelectedBug", "Selected bug:", 15, 20);
            gui.addTextField("Age", "  age: " + bug.getAge(), 15, 20);
            gui.addTextField("Hunger", "  hunger: " + bug.getHunger(), 15, 20);
            gui.addTextField("Fun", "  fun: " + bug.getFun(), 15, 20);
        } else {
            gui.removeTextField("SelectedBug");
            gui.removeTextField("Age");
            gui.removeTextField("Hunger");
            gui.removeTextField("Fun");
        }

        // Buttons
//		gui.addButton("Restart", 15, 40);
//		gui.addButton("Pass day", 15, 40);
//		gui.addButton("Buy food", 15, 40);
        if (selectedh != -1 && !world.getCell(selectedv, selectedh).isEmpty()) {
            if (world.getCell(selectedv, selectedh).isBugAlive()) {
                gui.addButton("Sell bug", 12, 35);
                gui.removeButton("Clean cell");
            } else {
                gui.addButton("Clean cell", 12, 35);
                gui.removeButton("Sell bug");
            }
        } else {
            gui.removeButton("Sell bug");
            gui.removeButton("Clean cell");
        }
    }

    private void getImage(int vertical, int horizontal, boolean selected) {
        Cell cell = world.getCell(vertical, horizontal);
        if (cell.isEmpty() || cell.getFood() > 0) {
            if (selected) {
                gui.setImageOnCell(vertical, horizontal, "colors_blue");
            } else {
                gui.setImageOnCell(vertical, horizontal, "colors_grey");
            }
            gui.setTextOnCell(vertical, horizontal, ""
                    + (cell.getFood() > 0 ? cell.getFood() : ""));
        } else {
            gui.setTextOnCell(vertical, horizontal, "");
            String imageName;
            Bug bug = cell.getBug();
            if (bug.isDead()) {
                imageName = "bugs_dead_bug";
            } else if (bug.getHunger() > 10) {
                imageName = "bugs_hungry_bug";
            } else if (bug.getFun() < 10) {
                imageName = "bugs_sad_bug";
            } else if (bug.getAge() > 15) {
                imageName = "bugs_old_bug";
            } else {
                imageName = "bugs_happy_bug";
            }
            if (selected) {
                imageName += "_selected";
            }
            gui.setImageOnCell(vertical, horizontal, imageName);
        }
    }

    public void showMessage(String message) {
        gui.reproduceSound("ping");
        gui.showTemporaryMessage(message);
    }

    public void askUserName(ScoreManager scoreManager) {
        gui.askUserText(
                "Congratulations! New high score!\nPlease insert your name",
                scoreManager);
    }

    public void storeHighScore(int score) {
        showMessage("Congratulations! New High Score!");
        gui.storeInt("HighScore", score);
        askUserName(scoreManager);
    }

    public void storeHighScoreName(String name) {
        gui.storeString("HighScoreName", name);

    }

    public void endGame(int highScore, String highScoreName) {
        gui.reproduceSound("ping");
        gui.reproduceSound("ping");
        gui.showTemporaryMessage("Game ended\nHigh score: " + highScore + " ("
                + highScoreName + ")");
        world = new BugWorld(this, highScore, highScoreName);
    }
}
