package com.ls.battle.appmanager;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ArticlesHelper {


    //Articles
    private static ElementsCollection clientTypes = $$(".btn.btn-outline-primary.tree-main-button");

    private static ElementsCollection clients = $$(".sub-tree");
    private static ElementsCollection articleNames = $$(".btn.btn-outline-info");
    private static SelenideElement downloadButton = $(byText("Download info"));
    private static SelenideElement article = $(".form-control");
    private static SelenideElement articleTitle = $(".card-title");
    private static SelenideElement moveToSaved = $("div#dataCard > .card > div > div:nth-of-type(3) > button:nth-of-type(1)");
    private static SelenideElement savedArticleName = $(".right-sub-tree .btn-outline-info");
    private static SelenideElement savedClientType = $(".text-right [type]");
    private static SelenideElement savedArticlesHeader = $("#mainContainer div:nth-of-type(3) .text-center");
    private static SelenideElement removeFromSaved = $("div:nth-of-type(3) > button:nth-of-type(2)");
    private static SelenideElement imageScroller = $(".ui-slider-horizontal");
    private static SelenideElement image = $("img#heroImage");


    public void countArticles(int index, int expAmount) {
        clientTypes.get(index).click();
        int amount = clients.get(index).$$(".sub-tree-element").size();
        Assert.assertEquals(expAmount, amount);
    }

    public void checkAmountArticles() {
        countArticles(0, 2);
        countArticles(1, 2);
        countArticles(2, 10);
    }

    public void chooseClientType(String type) {
        clientTypes.findBy(text(type)).click();
    }

    public void openArticleByName(String name) {
        articleNames.findBy(text(name)).click();
        articleTitle.waitUntil(appear, 15000).shouldHave(text(name));
    }

    public void downloadFile() {
        downloadButton.click();
        sleep(2000);
    }

    public String getArticleText() {
        return article.getValue();
    }


    public void compareText() throws Exception {
        // Download file, get text from site and from file, compare texts and delete file.

        downloadFile();
        String articleText = getArticleText();

        String home = System.getProperty("user.home");
        String fileName = home + "/Downloads/data.txt";

        Path path = Paths.get(fileName);
        byte[] bytes = Files.readAllBytes(path);
        @SuppressWarnings("unused")
        List<String> allLines = Files.readAllLines(path, UTF_8);
        deleteFile();
        Assert.assertEquals(articleText, allLines, "======= Article text differs from site text =======");
    }


    public void saveArticle(String articleName) {
        //Scroll down to the end of element to enable Move to Saved button
        String height = article.getAttribute("scrollHeight");
        WebDriver webDriver = WebDriverRunner.getWebDriver();
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        String script = "document.querySelector('.form-control').scrollTop += " + height;
        jse.executeScript(script);
        //Save article
        moveToSaved.shouldBe(enabled).click();
        savedArticlesHeader.scrollTo().should(exist);
        savedClientType.click();
        savedArticleName.shouldHave(text(articleName)).click();
    }

    public void removeFromSaved() {
        removeFromSaved.click();
        savedArticlesHeader.should(disappear);
    }

    public void deleteFile() {
        String home = System.getProperty("user.home");
        String fileName = home + "/Downloads/data.txt";
        File file = new File(fileName);
        try {
            file.delete();
            System.out.println("========== File deleted ==========");
        } catch (Exception e) {
            Assert.fail("========== File doesn't exist ==========");

        }
    }

    public void changeImageSize() {
        int height1 = Integer.parseInt(image.getAttribute("clientHeight"));
        int width1 = Integer.parseInt(image.getAttribute("clientWidth"));
        imageScroller.scrollTo();
        //Move image scroller to the right
        WebDriver webDriver = WebDriverRunner.getWebDriver();
        imageScroller.click();
        Actions move = new Actions(webDriver);
        move.moveToElement(imageScroller).clickAndHold();
        move.moveByOffset(250, 0);
        move.release();
        move.perform();
        int height2 = Integer.parseInt(image.getAttribute("clientHeight"));
        int width2 = Integer.parseInt(image.getAttribute("clientWidth"));
        //Compare image height and width before and after scrolling
        Assert.assertTrue(height1 < height2);
        Assert.assertTrue(width1 < width2);

    }
}
