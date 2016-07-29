package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by popdv on 25.07.2016.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        app.getContractHelper().selectContract();
        app.getContractHelper().initContractModification();
        app.getContractHelper().fillContractForm(new ContactData("Dmitry2", "Victorovich2", "Popov2", "popdv2", "Title2", "BSS", "Moscow Nagornyi p-d", "+7(495)111-11-11", "+79295622211", "+7(495)111-11-11", "+7(495)111-11-13", "asd@asd.ru", "asd2@asd.ru", "asd3@asd.ru", "www.asdsa.ru", "1990", "2007", "Moscow, Filevskyi bil", "+7(495)123-11-22", "заметки", null), false);
        app.getContractHelper().submitContractModification();
        app.getContractHelper().returnToHomePage();
    }
}

