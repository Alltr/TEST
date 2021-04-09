package ru.Yandex;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class FirstTest {
    public ChromeDriver driver;


    @Test
    public void firstTest()
            throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.get("https://yandex.ru/");

        Set<String> oldWindows = driver.getWindowHandles();

        WebElement marketButton = driver.findElement(By.xpath("//li[@class='services-new__list-item'][2]"));
        marketButton.findElement(By.xpath("//a[@data-id='market']")).click();

        Set<String> newWindows = driver.getWindowHandles();
        newWindows.removeAll(oldWindows);
        String newWindowHandle = newWindows.iterator().next();

        driver.close();
        driver.switchTo().window(newWindowHandle);


        WebElement sectionElectronicsButton = driver.findElement(By.xpath("//span[text()='Электроника']"));
        sectionElectronicsButton.click();

        WebElement sectionCellPhonesButton = driver.findElement(By.xpath("//a[text()='Мобильные телефоны']"));
        sectionCellPhonesButton.click();

        WebElement allFiltersButton = driver.findElement(By.xpath("//span[text()='Все фильтры']"));
        allFiltersButton.click();

        driver.findElement(By.xpath("//input[@placeholder='8 670 000']")).sendKeys("20000");

        WebElement sizeButton = driver.findElement(By.xpath("//div[@data-filter-id='4925721']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sizeButton);
        sizeButton.click();
        driver.findElement(By.xpath("//input[@placeholder='0.66']")).sendKeys("3");

        WebElement manufacturer = driver.findElement(By.xpath("//div[@data-filter-id='7893318']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", manufacturer);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[text()='Apple']")));

        List<WebElement> popularManufacturer = driver.findElements(By.xpath("//div[@data-filter-id='7893318']//label [@class='cyT3QvXmK8']"));
        Random rand = new Random();
        int randomCompany1 = rand.nextInt(popularManufacturer.size());
        popularManufacturer.get(randomCompany1).click();
        popularManufacturer.remove(randomCompany1);
        int randomCompany2 = rand.nextInt(popularManufacturer.size());
        popularManufacturer.get(randomCompany2).click();
        popularManufacturer.remove(randomCompany2);
        int randomCompany3 = rand.nextInt(popularManufacturer.size());
        popularManufacturer.get(randomCompany3).click();
        popularManufacturer.remove(randomCompany3);
        int randomCompany4 = rand.nextInt(popularManufacturer.size());
        popularManufacturer.get(randomCompany4).click();
        popularManufacturer.remove(randomCompany4);
        int randomCompany5 = rand.nextInt(popularManufacturer.size());
        popularManufacturer.get(randomCompany5).click();
        popularManufacturer.remove(randomCompany5);
        int randomCompany6 = rand.nextInt(popularManufacturer.size());
        popularManufacturer.get(randomCompany6).click();
        popularManufacturer.remove(randomCompany6);

        WebElement showOffers = driver.findElement(By.xpath("//a[text()[contains(.,'Показать')]]"));
        showOffers.click();

        int numberOfTelephones = 10;
        List<WebElement> phones = driver.findElements(By.xpath("//article[@class='_1_IxNTwqll cia-vs cia-cs']"));
        System.out.println(numberOfTelephones == phones.size());
        System.out.println("На первой странице не 10 телефонов, а  " + phones.size());

        String firstPositionText = driver.findElement(By.xpath("//article[@class='_1_IxNTwqll cia-vs cia-cs'][1]//div[@class='_1B9w_GzQuM']//span[@data-tid='ce80a508']")).getText();

        WebElement sortByNewness = driver.findElement(By.xpath("//button[text()='по новизне']"));
        sortByNewness.click();

        Set<String> oldWindows2 = driver.getWindowHandles();

        String locator = String.format("//*[text()='%s']", firstPositionText);

        for (; ; ) {
            WebElement buttonText = driver.findElement(By.xpath("//button[contains(@id,'dropdown-control')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buttonText);
            if (driver.findElement(By.xpath("//button[contains(@id,'dropdown-control')]")).getText().equals("Показать все")) {
                break;
            }
            driver.findElement(By.xpath("//button[text()='Показать ещё']")).click();
        }

        driver.findElement(By.xpath(locator)).click();

        Set<String> newWindows2 = driver.getWindowHandles();
        newWindows2.removeAll(oldWindows2);
        String newWindowHandle2 = newWindows2.iterator().next();
        driver.switchTo().window(newWindowHandle2);

        String rating = driver.findElement(By.xpath("//span[@class='_3nFvoU2Uov']")).getText();
        System.out.println(firstPositionText + " имеет рейтинг " + rating);

        driver.quit();

    }

}
