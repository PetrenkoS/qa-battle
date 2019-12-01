package com.ls.battle.tests.ui;

import com.ls.battle.tests.TestBase;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import java.util.Random;

public class UsersTests extends TestBase {


    @Test
    @Description("Edit User Account test")
    public void editUserTest() {

        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'a'); // Adds accidental character to
        String firstName = "FN" + c;
        String lastName = "LN" + c;

        app.getUserHelper().login(USERNAME, PASSWORD);
        app.getUserHelper().openAccount();
        app.getUserHelper().editAccount(firstName, lastName);

    }

    @Test
    @Description("Save Payment Info test")
    public void savePaymentTest() {
        app.getUserHelper().login(USERNAME, PASSWORD);
        app.getUserHelper().openAccount();
        app.getUserHelper().editPaymentInfo("9999 9999 9999 9999", "Visa", 15);

    }

    @Test(enabled = false)
    @Description("Negative login test")
    public void negativeLoginTest() {
        app.getUserHelper().login("ggggggggg", "hhhhhhhh");
        // TODO: notification error expected
    }

}