package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by popdv on 25.07.2016.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContractHelper().isThereAContract()) {
            app.getNavigationHelper().gotoContactPage();
            app.getContractHelper().createContract(new ContactData("Dmitry", "Victorovich", "Popov", "popdv", "Title", "BSS", "Moscow Nagornyi p-d", "+7(495)111-11-11", "+79295622211", "+7(495)111-11-11", "+7(495)111-11-13", "asd@asd.ru", "asd2@asd.ru", "asd3@asd.ru", "www.asdsa.ru", "1990", "2007", "Moscow, Filevskyi bil", "+7(495)123-11-22", "заметки", "test1"));
        }
        List<ContactData> before = app.getContractHelper().getContractList();
        app.getContractHelper().selectContract(before.size() - 1);
        app.getContractHelper().initContractModification();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Ivan", "Victorovich", "Ivanov", "popdv2", "Title2", "BSS", "Moscow Nagornyi p-d", "+7(495)111-11-11", "+79295622211", "+7(495)111-11-11", "+7(495)111-11-13", "asd@asd.ru", "asd2@asd.ru", "asd3@asd.ru", "www.asdsa.ru", "1990", "2007", "Moscow, Filevskyi bil", "+7(495)123-11-22", "заметки", "test1");
        app.getContractHelper().fillContractForm(contact, false);
        app.getContractHelper().submitContractModification();
        app.getContractHelper().returnToHomePage();
        List<ContactData> after = app.getContractHelper().getContractList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}

