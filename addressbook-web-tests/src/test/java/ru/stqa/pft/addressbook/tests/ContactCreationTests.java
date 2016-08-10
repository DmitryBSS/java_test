package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        List<ContactData> before = app.getContractHelper().getContractList();
        app.getNavigationHelper().gotoContactPage();
        ContactData contact = new ContactData("Dmitry", "Victorovich", "Popov", "popdv", "Title", "BSS", "Moscow Nagornyi p-d", "+7(495)111-11-11", "+79295622211", "+7(495)111-11-11", "+7(495)111-11-13", "asd@asd.ru", "asd2@asd.ru", "asd3@asd.ru", "www.asdsa.ru", "1990", "2007", "Moscow, Filevskyi bil", "+7(495)123-11-22", "заметки", "test1");
        app.getContractHelper().createContract(contact);
        List<ContactData> after = app.getContractHelper().getContractList();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
