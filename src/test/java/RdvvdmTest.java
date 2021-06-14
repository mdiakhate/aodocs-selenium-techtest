import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;

public class RdvvdmTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void rdvvdm() {
        driver.get("https://www.val-de-marne.gouv.fr/booking/create/4963/0");
        driver.manage().window().setSize(new Dimension(1440, 788));
        driver.findElement(By.id("condition")).click();
        driver.findElement(By.name("nextButton")).click();
        driver.findElement(By.id("planning19866")).click();
        driver.findElement(By.name("nextButton")).click();
        // TODO : ajouter snapshot
        assert(driver.findElement(By.id("FormBookingCreate")).getText().contains("plus de plage horaire libre pour votre demande de rendez-vous. Veuillez recommencer ultérieurement."));
        driver.close();
    }
}
