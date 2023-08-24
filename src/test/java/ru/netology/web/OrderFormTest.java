package ru.netology.web;

import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.*;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OrderFormTest {
    private WebDriver driver;

    @BeforeAll
    static  void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldValidFormTest() {
        driver.get("http://localhost:7777");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Настя Рахманова");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79852000000");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();

        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expected, text);
    }
}
