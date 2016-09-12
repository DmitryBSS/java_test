package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by popdv on 25.07.2016.
 */
public class ContactDeletionTests extends TestBase {

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
                    .withAddress2("Moscow, Filevskyi bil").withPhone2("+7(495)123-11-22").withNotes("заметки").withPhoto(photo));
        }
    }

    @Test
    public void testContactDeletion() {
        Contacts before = app.db().contacts();
        Iterator<ContactData> iterator = before.iterator();
        ContactData deletedContact = before.iterator().next();
        while (iterator.hasNext()) {
            deletedContact = iterator.next();
            if (deletedContact.getId() != 217) {
                break;
            }
        }
        app.contract().delete(deletedContact);
        app.goTo().homePage();
        assertThat(app.contract().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
        verifyContactListInUI();
    }
}
