package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by popdv on 25.07.2016.
 */
public class ContractHelper extends HelperBase {

    public ContractHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContractCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContractForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getTelHome());
        type(By.name("mobile"), contactData.getTelMobile());
        type(By.name("work"), contactData.getTelWork());
        type(By.name("fax"), contactData.getFax());
        type(By.name("email"), contactData.getEmail1());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("homepage"), contactData.getHomePage());

        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[3]")).isSelected()) {
            click(By.xpath("//div[@id='content']/form/select[1]//option[3]"));
        }
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[2]")).isSelected()) {
            click(By.xpath("//div[@id='content']/form/select[2]//option[2]"));
        }
        type(By.name("byear"), contactData.getbYear());

        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[3]//option[4]")).isSelected()) {
            click(By.xpath("//div[@id='content']/form/select[3]//option[4]"));
        }
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[4]//option[3]")).isSelected()) {
            click(By.xpath("//div[@id='content']/form/select[4]//option[3]"));
        }
        type(By.name("ayear"), contactData.getaYear());
        type(By.name("address2"), contactData.getAddress2());
        type(By.name("phone2"), contactData.getPhone2());
        type(By.name("notes"), contactData.getNotes());
    }

    public void selectContract() {
        click(By.name("selected[]"));
    }

    public void initContractModification() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[5]/td[8]/a/img"));
    }

    public void submitContractModification() {
        click(By.name("update"));
    }

    public void deleteSelectedContract() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }
}
