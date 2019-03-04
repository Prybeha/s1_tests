package SupportClasses.SetupClass;

import SupportClasses.AllureFunc.ScreenShotUtil;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Listeners({ScreenShotUtil.class})
public class SetupClass {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static WebDriver GetDriver(){
        return driver;
    }

    public static WebDriverWait GetDriverWait(){
        return wait;
    }

    @Parameters("browser")
    @BeforeMethod
    public void setup (String browser){
        if(browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");
            driver = new ChromeDriver();
        }

        else if(browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\SeleniumDrivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        }

        else if(browser.equalsIgnoreCase("opera")) {
            OperaOptions options = new OperaOptions();
            options.setBinary("C:\\Users\\vprybeha\\AppData\\Local\\Programs\\Opera\\58.0.3135.68\\opera.exe");
            System.setProperty("webdriver.opera.driver","C:\\SeleniumDrivers\\operadriver.exe");
            driver = new OperaDriver(options);
        }

        wait = new WebDriverWait(driver,20, 500);
        driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown(ITestResult result, Method test_name) throws IOException{
        if (result.getStatus()==ITestResult.FAILURE ) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = "./target/allure-results/" + test_name.getName();
            FileHandler.copy(screenshot, new File(path));
        }
        else{
            driver.quit();
        }
    }
}
