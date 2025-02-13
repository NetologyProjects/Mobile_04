package ru.netology.qa;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

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
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Pixel 4a");
        desiredCapabilities.setCapability("appium:app",
                "/home/serg/Android/2_Auto/02.4/appium-tests/artifacts/app-debug.apk");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        driver = new AndroidDriver(getUrl(), desiredCapabilities);
    }

    @Test
    public void testNotChangeText() {
        MobileElement el1 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput"));
        el1.isDisplayed();
        el1.click();
        el1.sendKeys(textToSet);

        MobileElement el2 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonChange"));
        el2.isDisplayed();
        el2.click();

        MobileElement el3 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput"));
        el3.isDisplayed();
        el3.click();
        el3.sendKeys("   ");

        MobileElement el4 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonChange"));
        el4.isDisplayed();
        el4.click();

        MobileElement el5 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/textToBeChanged"));
        el5.isDisplayed();
        Assertions.assertEquals(textToSet, el5.getText());

    }

    @Test
    public void testOpenText() {
        MobileElement el1 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput"));
        el1.isDisplayed();
        el1.click();
        el1.sendKeys(textToSet);

        MobileElement el2 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonActivity"));
        el2.isDisplayed();
        el2.click();

        MobileElement el3 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/text"));
        el3.isDisplayed();
        Assertions.assertEquals(textToSet, el3.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}