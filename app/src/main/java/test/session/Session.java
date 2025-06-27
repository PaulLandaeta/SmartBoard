package test.session;

import android.os.Build;

import androidx.annotation.NonNull;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Session {
    private static Session instance = null;
    private final AppiumDriver driver;

    private Session() {
        DesiredCapabilities caps = getDesiredCapabilities();

        try {
            driver = new AndroidDriver(
                    new URL("http://127.0.0.1:4723/"), caps);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL de Appium malformada", e);
        }
    }

    @NonNull
    private static DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName",       "Android");
        caps.setCapability("appium:deviceName",  "Pixel 9 Pro XL");
        caps.setCapability("appium:platformVersion", "16");
        caps.setCapability("appium:automationName",   "UiAutomator2");
        caps.setCapability("appium:appPackage",       "edu.upb.lp.genericgame");
        caps.setCapability("appium:appActivity",      "edu.upb.lp.core.activities.AndroidGameActivity");
        caps.setCapability("appium:noReset",          true);
        return caps;
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public AppiumDriver getDevice() {
        return driver;
    }

    public static void reset() {
        if (instance != null) {
            instance.driver.quit();
            instance = null;
        }
    }
}
