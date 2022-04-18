package id.co.bricktest.scrapapplication.service;

import id.co.bricktest.scrapapplication.exception.GeneralException;
import id.co.bricktest.scrapapplication.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static id.co.bricktest.scrapapplication.common.SeleniumConstant.*;

@Service
@Slf4j
public class SeleniumService {

    private WebDriver driver = null;
    private WebDriverWait wait = null;

    private void setUpDriver() {
        try {
            ChromeOptions options = new ChromeOptions();
//            options.addArguments("headless");
            System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, 5);
        } catch (Exception e) {
            log.error("setUpDriver {}", e.getMessage());
        }
    }

    public List<Product> scrapWebTarget(int count) throws IOException {
        setUpDriver();
        if (null == driver) {
            throw new GeneralException("500", "Internal Server Error", "no driver found installation on the directory", "SEL");
        }
        //waiting to html page loaded successfully
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //open new window
        List<String> tabs = prepareTwoTabs(js);
        List<Product> productList = new ArrayList<>();
        for (int page = 1; productList.size() < count; page++) {
            driver.switchTo().window(tabs.get(0));
            String path = BASE_URL + HANDPHONE_PATH;
            path = path + PAGE + page;

            final List<WebElement> elements = getElementListByScrollingDown(path, tabs.get(0), js); // switch to main tab
            js.executeScript("window.scrollBy(0,600)");
            for (WebElement e : elements) {
                JavascriptExecutor loadProduct = (JavascriptExecutor) driver;
                loadProduct.executeScript("window.scrollBy(0,300)");
                //get product detail url
                path = e.findElement(By.tagName(aTAG)).getAttribute(HREF);
                if (isTopAdsLink(path)) {
                    path = extractTopAdsLink(path);
                }
                driver.switchTo().window(tabs.get(1));
                driver.get(path);
                js.executeScript("window.scrollBy(0,300)");
                waitOnElement();
                Product product = extractProduct();
                productList.add(product);
                if (productList.size() == count) {
                    break;
                }
                driver.switchTo().window(tabs.get(0));
            }
        }
        assert driver != null;
        driver.quit();
        return productList;
    }

    private void waitOnElement() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(id.co.bricktest.scrapapplication.common.SeleniumConstant.XPATH_MERCHANT_NAME)));
    }

    private List<WebElement> getElementListByScrollingDown(String url, String tab, JavascriptExecutor js) {
        driver.switchTo().window(tab);
        driver.get(url);
        js.executeScript(JS_SCROLL_MEDIUM);
        return driver.findElements(By.xpath(XPATH_PRODUCT_LIST));
    }

    private boolean isTopAdsLink(String path) {
        return path.contains(TOP_ADS_URL);
    }

    private String extractTopAdsLink(String path) throws IOException {
        return URLDecoder.decode(path.substring(path.indexOf(PARAM_R) + 2).split(AMP)[0], StandardCharsets.UTF_8.name());
    }

    private List<String> prepareTwoTabs(JavascriptExecutor js) {
        driver.get("https://www.google.com");
        js.executeScript("window.open()");
        return new ArrayList<>(driver.getWindowHandles());
    }

    private Product extractProduct() {
        String name = getText(XPATH_PRODUCT_NAME);
        String desc = getText(XPATH_PRODUCT_DESCRIPTION);
        String imageUrl = getTextByAttribute();
        String price = getText(XPATH_PRODUCT_PRICE).split(RUPIAH_SIGN)[1].replace(DOT, EMPTY);
        String merchant = getText(XPATH_MERCHANT_NAME);
        String rating = getText(XPATH_PRODUCT_RATING);
        return Product.builder()
                .name(name)
                .description(desc)
                .imageUrl(imageUrl)
                .merchant(merchant)
                .price(Double.parseDouble(price))
                .rating(rating == null || rating.isEmpty() ? 0.0 : Double.parseDouble(rating))
                .build();
    }

    private String getText(String xpath) {
        return driver.findElements(By.xpath(xpath)).isEmpty() ? "" : driver.findElement(By.xpath(xpath)).getText();
    }

    private String getTextByAttribute() {
        return driver.findElements(By.xpath(id.co.bricktest.scrapapplication.common.SeleniumConstant.XPATH_PRODUCT_IMG_URL)).isEmpty() ? EMPTY : driver.findElement(By.xpath(id.co.bricktest.scrapapplication.common.SeleniumConstant.XPATH_PRODUCT_IMG_URL)).getAttribute(id.co.bricktest.scrapapplication.common.SeleniumConstant.SRC);
    }

}
