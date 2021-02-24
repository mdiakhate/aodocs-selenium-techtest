package selenium.driver;

import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProspectTest {
    public String baseUrl = "https://www.google.com";
    public WebDriver driver;

    @BeforeAll
    public void initialize(){
        driver = WebDriverUtility.getWebDriver(Browser.CHROME);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void searchAodocs() throws InterruptedException,NoSuchElementException, NoSuchFrameException, TimeoutException {
        driver.get(baseUrl); // Navigate to Url
        WebDriverWait wait = new WebDriverWait(driver, 5);

        String searchGoogleFieldXpath = "/html/body/div[1]/div[3]/form/div[2]/div[1]/div[1]/div/div[2]/input";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchGoogleFieldXpath)));
        // Enter text "aodocs" and press "Enter"
        driver.findElement(By.xpath(searchGoogleFieldXpath)).sendKeys("aodocs" + Keys.ENTER);
        Thread.sleep(3000);

        //Handle google alert for cookies
        //wait.until(ExpectedConditions.alertIsPresent());
        //Alert alert = driver.switchTo().alert();
        int size = driver.findElements(By.tagName("iframe")).size();
        System.out.println(size);  //0 in headless mode 1 otherwise
        WebDriver iframe = driver.switchTo().parentFrame();
        System.out.println("Current frame : " + iframe.getTitle());
        /* WebElement iframe = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cnsw > iframe")));
        driver.switchTo().frame(iframe);
        String alertMessage = alert.getText();
        System.out.println("-------------" + alertMessage);
        alert.accept();
        Assertions.assertTrue(alertMessage.contains("cookie"));
        String acceptButtonClass = "RveJvd snByac";  // Bouton "j'accepte"
        driver.findElement(By.className(acceptButtonClass)).click();*/


        //In the result, open the website www.aodocs.com
        String aodocsWebsiteClass = "www.aodocs.com";
        String link = driver.findElement(By.partialLinkText(aodocsWebsiteClass)).getText();
        String[] str1 = link.split(" ");
        System.out.println(str1[str1.length - 1]);
        // we retrieve the link and append https
        String securedLink = "https://" + str1[str1.length - 1].substring(8);
        System.out.println(securedLink.trim());
        driver.get(securedLink.trim());

        System.out.println("-------------in " + driver.getTitle() + " -----------"); // Homepage

        String requestDemoButtonXpath = "//*[@id=\"DND_banner-module-1\"]/div/div/div[1]/div/div/a";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(requestDemoButtonXpath)));
        driver.findElement(By.xpath(requestDemoButtonXpath)).click();
        Thread.sleep(3000); // to prevent TimeoutException: Expected condition failed: waiting for visibility of element located by By.id: firstname...

        System.out.println("-------------Filling the form-----------");

        String firstNameFieldId = "firstname-384ed391-59a1-4016-bc91-62bb1307edb2_5179";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(firstNameFieldId)));
        driver.findElement(By.id(firstNameFieldId)).sendKeys("John");

        String generatedEmail = new RandomString().toString();
        System.out.println("-------------" + generatedEmail);
        String emailFieldId = "email-384ed391-59a1-4016-bc91-62bb1307edb2_5179";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(emailFieldId)));
        driver.findElement(By.id(emailFieldId)).sendKeys(generatedEmail);

        String companySizeElementId = "company_size__c-384ed391-59a1-4016-bc91-62bb1307edb2_5179";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(companySizeElementId)));
        WebElement companySizeElement = driver.findElement(By.id(companySizeElementId));
        Select companySizeSelect = new Select(companySizeElement);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",companySizeElement );
        companySizeSelect.selectByValue("51-350 employees");

        //Check if the error messages is displayed
        String emailErrorMessageClass = "hs-error-msg";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(emailErrorMessageClass)));
        Assertions.assertTrue(driver.findElement(By.className(emailErrorMessageClass)).isDisplayed());
        Thread.sleep(2000);

    }

    @AfterAll
    public void teardown(){
        WebDriverUtility.closeWebDriver(driver);
    }
}