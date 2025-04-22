package br.com.fiap.spring_mvc.Selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class testeSelenium {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        cadastrarFilmeParaTeste();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void cadastrarFilmeParaTeste() {
        driver.get("http://localhost:8080/filme");

        driver.findElement(By.id("titulo")).sendKeys("Filme Teste");
        driver.findElement(By.id("diretor")).sendKeys("Diretor Teste");
        driver.findElement(By.id("categoria")).sendKeys("AÇÃO");
        driver.findElement(By.id("streaming")).sendKeys("Netflix");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Assertions.assertTrue(driver.getCurrentUrl().contains("/filme/list"));
    }

    @Test
    public void testUpdateFilme() {
        driver.get("http://localhost:8080/filme/list");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement detalhesButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@onclick, 'showModal')]")
        ));
        detalhesButton.click();

        WebElement editar = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href,'/filme/edit')]")
        ));
        editar.click();

        WebElement titulo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("titulo")));
        titulo.clear();
        titulo.sendKeys("Atualizado");

        driver.findElement(By.id("diretor")).clear();
        driver.findElement(By.id("diretor")).sendKeys("Diretor Atualizado");

        driver.findElement(By.id("streaming")).clear();
        driver.findElement(By.id("streaming")).sendKeys("Prime Video");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement listaFilmesSpan = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1/span[contains(text(), 'Lista de filmes')]")
        ));

        Assertions.assertNotNull(listaFilmesSpan);
    }

    @Test
    public void testDeleteFilme() {
        driver.get("http://localhost:8080/filme/list");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement detalhesButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@onclick, 'showModal')]")
        ));
        detalhesButton.click();

        WebElement deletar = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href,'/filme/delete')]")
        ));
        deletar.click();

        WebElement listaFilmesSpan = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1/span[contains(text(), 'Lista de filmes')]")
        ));

        Assertions.assertNotNull(listaFilmesSpan);
    }

    @Test
    public void testCadastroFilmeCamposVazios() {
        driver.get("http://localhost:8080/filme");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertTrue(driver.getCurrentUrl().contains("/filme"));
    }
}
