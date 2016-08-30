package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by popdv on 25.07.2016.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.goTo().gotoContactPage();
            File photo = new File("src/test/resources/stru.png");
            app.contract().create(new ContactData()
                    .withFirstName("Dmitry").withMiddleName("Victorovich").withLastName("Popov").withNickName("popdv")
                    .withTitle("Title").withCompany("BSS").withAddress("Moscow Nagornyi p-d").withTelHome("+7(495)111-11-11")
                    .withTelMobile("+79295622211").withTelWork("+7(495)111-11-11").withFax("+7(495)111-11-13").withEmail1("asd@asd.ru")
                    .withEmail2("asd2@asd.ru").withEmail3("asd3@asd.ru").withHomePage("www.asdsa.ru").withbYear("1990").withaYear("2007")
                    .withAddress2("Moscow, Filevskyi bil").withPhone2("+7(495)123-11-22").withNotes("заметки").withGroup("test1").withPhoto(photo));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        File photo = new File("src/test/resources/stru.png");
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("Dmitry").withMiddleName("Victorovich").withLastName("Popov")
                .withNickName("popdv").withTitle("Title").withCompany("BSS").withAddress("Moscow Nagornyi p-d")
                .withTelHome("+7(495)111-11-11").withTelMobile("+79295622211").withTelWork("+7(495)111-11-11").withFax("+7(495)111-11-13")
                .withEmail1("asd@asd.ru").withEmail2("asd2@asd.ru").withEmail3("asd3@asd.ru").withHomePage("www.asdsa.ru").withbYear("1990")
                .withaYear("2007").withAddress2("Moscow, Filevskyi bil").withPhone2("+7(495)123-11-22").withNotes("заметки").withGroup("test1").withPhoto(photo);
        app.contract().modify(contact);
        assertThat(app.contract().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }
}

