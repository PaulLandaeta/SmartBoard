package test.session;


import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import test.factoryDevices.FactoryDevices;

import java.net.MalformedURLException;
import java.net.URL;
public class Session {
    private static Session session = null;
    private AppiumDriver device;
    private Session(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:deviceName", "Pixel 9 Pro XL");
        caps.setCapability("appium:platformVersion", "16");
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:automationName", "uiautomator2");
        caps.setCapability("appium:appPackage", "edu.upb.lp.genericgame");
        caps.setCapability("appium:appActivity", "edu.upb.lp.core.activities.AndroidGameActivity");

        try {
            device = new AppiumDriver(new URL("http://127.0.0.1:4723/"), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Session getInstance(){
        if (session == null)
            session = new Session();
        return session;
    }

    public void closeApp(){
        device.quit();
        session = null;
    }
    public static void resetInstance() {
        if (session != null) {
            session.device.quit();
            session = null;
        }
    }
    public AppiumDriver getDevice(){
        return  device;
    }
    public void setUp() {}
}
