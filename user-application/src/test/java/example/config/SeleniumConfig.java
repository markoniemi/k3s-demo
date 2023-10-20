package example.config;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import io.github.bonigarcia.wdm.WebDriverManager;

// TODO Add @Configuration
public class SeleniumConfig {
  @Bean
  public WebDriver getWebDriver() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("headless");
    options.addArguments("window-size=1200x800");
    options.addArguments("no-sandbox");
    options.addArguments("proxy-server='direct://'");
    options.addArguments("proxy-bypass-list=*");
    options.addArguments("remote-allow-origins=*");        
    ChromeDriver chromeDriver = new ChromeDriver(options);
    chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    return chromeDriver;
  }
}
