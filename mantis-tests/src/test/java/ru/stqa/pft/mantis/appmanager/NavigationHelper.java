package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by popdv on 05.09.2016.
 */
public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
    }

    public void userManagementPage() {
        click(By.linkText("управление"));
        click(By.linkText("Управление пользователями"));
    }
}
