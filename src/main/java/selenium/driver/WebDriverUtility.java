package selenium.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;

public class WebDriverUtility {

    public static WebDriver getWebDriver(Browser browser) {
        WebDriver webDriver;
        switch (browser) {
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver(getFireFoxOptions());
                break;
            case SAFARI:
                String safaridriverPath =  "/usr/bin/safaridriver";
                System.setProperty("webdriver.safari.driver",safaridriverPath);
                webDriver= new SafariDriver();
                break;
            case CHROME:
            default:
                String chromeDriverPath = "/Users/diakhate/chromedriver";
                System.setProperty("webdriver.chrome.driver",chromeDriverPath);
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver(getChromeOptions());
        }
        webDriver.manage().window().maximize();

        return webDriver;
    }

    public static void closeWebDriver(WebDriver webDriver) {
        if (webDriver != null)
            webDriver.close();
            webDriver.quit();
    }


    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        // To start chrome in english
        options.addArguments("lang=en-GB");
        // To start chrome without security warning
        options.addArguments("disable-infobars");
        //Phantom browser
        //options.addArguments("--headless");
        //args.add("--disable-gpu");   // to activate for windows
        //options.addArguments("start-maximized");
        return options;
    }

    private static FirefoxOptions getFireFoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", "en-GB");
        options.setProfile(profile);
        return options;
    }

    public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file

        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination

        File DestFile=new File(fileWithPath);

        //Copy file at destination

        FileUtils.copyFile(SrcFile, DestFile);

    }

}
