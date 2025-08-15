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
    private static Session session = null;
    private final AppiumDriver device;

    private Session() {
        DesiredCapabilities caps = getDesiredCapabilities();

        try {
            device = new AndroidDriver(
                    new URL("http://127.0.0.1:4723/"), caps);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                device.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL de Appium malformada", e);
        }
    }

    @NonNull
    private static DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:deviceName", "Pixel 9 Pro XL");
        caps.setCapability("appium:platformVersion", "16");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:appPackage", "edu.upb.lp.genericgame");
        caps.setCapability("appium:appActivity", "edu.upb.lp.core.activities.AndroidGameActivity");
        caps.setCapability("appium:noReset", true);
        return caps;
    }

    public static Session getInstance() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }

    public AppiumDriver getDevice() {
        return device;
    }

    public static void resetInstance() {
        if (session != null) {
            session.device.quit();
            session = null;
        }
    }
}
