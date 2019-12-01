package com.ls.battle.tests.ui;

import com.ls.battle.tests.TestBase;
import io.qameta.allure.Description;
import org.testng.annotations.Test;


public class ArticlesTests extends TestBase {


    @Test
    @Description("Check amount of articles test")
    public void countArticlesTest() {
        app.getUserHelper().login(USERNAME, PASSWORD);
        app.getArticlesHelper().checkAmountArticles();
    }

    @Test
    @Description("Download article test")
    public void downloadArticleTest() throws Exception {
        String clientType = "Top level clients";
        String clientName = "Darth Vader";
        app.getUserHelper().login(USERNAME, PASSWORD);
        app.getArticlesHelper().chooseClientType(clientType);
        app.getArticlesHelper().openArticleByName(clientName);
        app.getArticlesHelper().compareText();
    }


    @Test
    @Description("Save and unsave article test")
    public void saveArticleTest() {
        String article = "Sasha Grey";
        String clientType = "Top level clients";
        app.getUserHelper().login(USERNAME, PASSWORD);
        app.getArticlesHelper().chooseClientType(clientType);
        app.getArticlesHelper().openArticleByName(article);
        app.getArticlesHelper().saveArticle(article);
        app.getArticlesHelper().removeFromSaved();
    }

    @Test
    @Description("Change image size test")
    public void changeImageSizeTest() {
        String article = "Test Advertiser";
        String clientType = "Advertisers";
        app.getUserHelper().login(USERNAME, PASSWORD);
        app.getArticlesHelper().chooseClientType(clientType);
        app.getArticlesHelper().openArticleByName(article);
        app.getArticlesHelper().changeImageSize();

    }
}