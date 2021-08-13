import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RdvvdmTest {
    private WebDriver driver;
    JavascriptExecutor js;
    @BeforeAll
    public void setUp() {
        String chromeDriverPath = "/Users/diakhate/chromedriver";
        System.setProperty("webdriver.chrome.driver",chromeDriverPath);
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
    }
    @AfterAll
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void rdvvdm() {
        driver.get("https://www.val-de-marne.gouv.fr/booking/create/4963/0");
        driver.manage().window().setSize(new Dimension(1440, 788));
        driver.findElement(By.id("condition")).click();
        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.id("planning20823")).click();
        driver.findElement(By.name("nextButton")).click();
        // TODO : add snapshot
        assert(driver.findElement(By.id("FormBookingCreate")).getText().contains("plus de plage horaire libre pour votre demande de rendez-vous. Veuillez recommencer ultérieurement."));
        driver.close();
    }
}
