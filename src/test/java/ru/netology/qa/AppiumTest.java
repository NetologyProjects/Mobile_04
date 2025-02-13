package ru.netology.qa;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.qa.screens.MainScreen;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumTest {

    String textToSet = "Netology";

    private AndroidDriver driver;

    private URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @BeforeEach
    public void setUp() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Pixel 4a");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        driver = new AndroidDriver(getUrl(), desiredCapabilities);
    }

    @Test
    public void testNotChangeText() {
        MainScreen mainScreen = new MainScreen(driver);
        MobileElement el1 = mainScreen.userInput;
        el1.isDisplayed();
        el1.click();
        el1.sendKeys(textToSet);

        MobileElement el2 = mainScreen.buttonChange;
        el2.isDisplayed();
        el2.click();

        el1.isDisplayed();
        el1.click();
        el1.sendKeys("   ");

        el2.isDisplayed();
        el2.click();

        Assertions.assertEquals(textToSet, mainScreen.textToBeChanged.getText());

    }

    @Test
    public void testOpenText() {
        MainScreen mainScreen = new MainScreen(driver);
        MobileElement el1 = mainScreen.userInput;
        el1.isDisplayed();
        el1.click();
        el1.sendKeys(textToSet);

        MobileElement el2 = mainScreen.buttonActivity;
        el2.isDisplayed();
        el2.click();

        Assertions.assertEquals(textToSet, mainScreen.text.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}