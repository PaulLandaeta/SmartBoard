package test.testSuite;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import test.activities.DynamicTexts;
import test.session.Session;

public class BaseBugWorld {
    DynamicTexts dynamicTexts = new DynamicTexts();

    @BeforeEach
    public void before() {}

    @AfterEach
    public void after() {
        Session.getInstance().closeApp();
    }
}
