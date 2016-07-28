package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by popdv on 25.07.2016.
 */
public class NavigationHelper extends HelperBase {
    private WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }

    public void gotoContactPage() {
        click(By.linkText("add new"));
    }

    public void gotoHomePage() {
        click(By.linkText("home"));
    }
}
