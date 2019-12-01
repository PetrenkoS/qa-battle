package com.ls.battle.appmanager;

public class ApplicationManager {

    private final ArticlesHelper articlesHelper = new ArticlesHelper();
    private final UserHelper userHelper = new UserHelper();


    public ArticlesHelper getArticlesHelper() {
        return articlesHelper;
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }


}
