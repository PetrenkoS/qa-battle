package com.ls.battle.appmanager;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class UserHelper {


    //Login page
    private static SelenideElement userName = $("#loginInput");
    private static SelenideElement userPassword = $("#passwordInput");
    private static SelenideElement signInButton = $("img");
    private static ElementsCollection loginForm = $$(".form-group");

    //Home page
    private static SelenideElement avatar = $("img#avatar");
    private static SelenideElement header = $(".card-header.text-center");

    //User account
    private static SelenideElement userFN = $("input#firstNameInput");
    private static SelenideElement userLN = $("input#lastNameInput");
    private static SelenideElement userProfile = $("a#v-pills-home-tab");
    private static SelenideElement saveUserInfo = $(".btn.btn-primary");

    //Payment info
    private static SelenideElement paymentInfo = $("a#v-pills-messages-tab");
    private static SelenideElement cardNumber = $("input#cardNumberInput");
    private static SelenideElement cardType = $("select#paymentSystemSelect");
    private static SelenideElement savePaymentInfo = $("div#v-pills-messages .btn.btn-primary");
    private static SelenideElement paymentDate = $("#paymentRange");
    private static SelenideElement currentDate = $("h6");

    public void login(String username, String password) {

        loginForm.get(0).click();
        userName.waitUntil(exist, 8000).click();
        userName.sendKeys(username);
        loginForm.get(1).click();
        userPassword.setValue(password);
        $(withText("Hover me faster!")).click();
        signInButton.waitUntil(exist, 15000).click();
        confirm("Are you sure you want to login?");
        confirm("Really sure?");
        header.shouldHave(text("Articles to read"));
    }

    public void openAccount() {
        avatar.click();
        header.shouldHave(text("User profile settings"));
    }

    public void editAccount(String fn, String ln) {
        userFN.setValue(fn);
        userLN.setValue(ln);
        saveUserInfo.click();
        paymentInfo.click();
        userProfile.click();
        userFN.shouldHave(value(fn));
        userLN.shouldHave(value(ln));
    }

    public void editPaymentInfo(String number, String type, int date) {
        paymentInfo.click();
        cardNumber.setValue(number);
        cardType.selectOption(type);

        WebElement slider = paymentDate;
        for(int i=0;i<=(date-2);i++){
            slider.sendKeys(Keys.ARROW_RIGHT);
        }
        savePaymentInfo.click();
        userProfile.click();
        paymentInfo.click();
        cardNumber.shouldHave(value(number));
        cardType.shouldHave(text(type));
        currentDate.shouldHave(text("Current value: " + date));
    }


}
