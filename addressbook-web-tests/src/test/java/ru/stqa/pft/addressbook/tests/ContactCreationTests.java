package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        Contacts before = app.contract().all();
        app.goTo().gotoContactPage();
        File photo = new File("addressbook-web-tests/src/test/resources/stru.png");
        ContactData contact = new ContactData().withFirstName("Dmitry").withMiddleName("Victorovich").withLastName("Popov")
                .withNickName("popdv").withTitle("Title").withCompany("BSS").withAddress("Moscow Nagornyi p-d")
                .withTelHome("+7(495)111-11-11").withTelMobile("+79295622211").withTelWork("+7(495)111-11-11")
                .withFax("+7(495)111-11-13").withEmail1("asd@asd.ru").withEmail2("asd2@asd.ru").withEmail3("asd3@asd.ru")
                .withHomePage("www.asdsa.ru").withbYear("1990").withaYear("2007").withAddress2("Moscow, Filevskyi bil")
                .withPhone2("+7(495)123-11-22").withNotes("заметки").withGroup("test1").withPhoto(photo);
        app.contract().create(contact);
        assertThat(app.contract().count(), equalTo(before.size() + 1));
        Contacts after = app.contract().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
