package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

/**
 * Created by popdv on 25.07.2016.
 */
public class ContractHelper extends HelperBase {

    public ContractHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContractCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContractForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        attach(By.name("photo"), contactData.getPhoto());
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

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContractById(int id) {
        wd.findElement(By.cssSelector("input[id = '" + id + "']")).click();
    }

    public void initContractModificationById(int id) {
//        wd.findElement(By.xpath("//input[@id = '" + id + "']/following::img[2]")).click();
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void initViewDetailContractById(int id) {
        wd.findElement(By.cssSelector("a[href='view.php?id=" + id + "']")).click();
    }

    public void submitContractModification() {
        click(By.name("update"));
    }

    public void deleteSelectedContract() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void create(ContactData contact) {
        fillContractForm(contact, true);
        submitContractCreation();
        contactCache = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        initContractModificationById(contact.getId());
        fillContractForm(contact, false);
        submitContractModification();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContractById(contact.getId());
        deleteSelectedContract();
        contactCache = null;
    }

    public boolean isThereAContract() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;


    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name = entry]"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstName = element.findElement(By.cssSelector("td:nth-of-type(3)")).getText();
            String lastName = element.findElement(By.cssSelector("td:nth-of-type(2)")).getText();
            String address = element.findElement(By.cssSelector("td:nth-of-type(4)")).getText();
            String allEmail = element.findElement(By.cssSelector("td:nth-of-type(5)")).getText();
            String allPhones = element.findElement(By.cssSelector("td:nth-of-type(6)")).getText();

            contactCache.add(new ContactData().withId(id).withFirstName(firstName)
                    .withLastName(lastName).withAddress(address).withAllEmail(allEmail).withAllPhones(allPhones));
        }
        return new Contacts(contactCache);
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContractModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstName)
                .withLastName(lastName).withAddress(address).withEmail1(email).withEmail2(email2).withEmail3(email3).withTelHome(home).withTelMobile(mobile).withTelWork(work).withPhone2(phone2);
    }

    public ContactData infoFromDetailForm(ContactData contact) {
        initViewDetailContractById(contact.getId());
        String detailsInfo = wd.findElement(By.cssSelector("#content")).getText();
        wd.navigate().back();

        return new ContactData().withId(contact.getId()).withDetailsInfo(detailsInfo);
    }
}


