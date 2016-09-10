package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by popdv on 06.09.2016.
 */
public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
    }

    public String getTextLogged(){
        return wd.findElement(By.cssSelector("#logged-in")).getText();
    }

    public void initChangeUser(String userName) {
        type(By.cssSelector("#username"), userName);
        click(By.cssSelector(".button[value='Изменить учетную запись']"));
    }

    public void resetPassword() {
        click(By.cssSelector(".button[value='Сбросить пароль']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.cssSelector("#password"), password);
        type(By.cssSelector("#password-confirm"), password);
        click(By.cssSelector("input.button"));
    }

}
