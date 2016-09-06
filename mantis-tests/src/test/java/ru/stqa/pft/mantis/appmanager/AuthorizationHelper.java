package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by popdv on 05.09.2016.
 */
public class AuthorizationHelper extends HelperBase {

    public AuthorizationHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
    }

    public void authorization(String login, String password) {
        type(By.cssSelector("#username"), login);
        type(By.cssSelector("#password"), password);
        click(By.cssSelector("input.button"));
    }
}
