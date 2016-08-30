package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by popdv on 14.08.2016.
 */
public class ContactAddressTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contract().all().size() == 0) {
            app.goTo().gotoContactPage();
            app.contract().create(new ContactData().withFirstName("Dmitry").withMiddleName("Victorovich").withLastName("Popov").withNickName("popdv").withTitle("Title").withCompany("BSS").withAddress("Moscow Nagornyi p-d").withTelHome("+7(495)111-11-11").withTelMobile("+79295622211").withTelWork("+7(495)111-11-11").withFax("+7(495)111-11-13").withEmail1("asd@asd.ru").withEmail2("asd2@asd.ru").withEmail3("asd3@asd.ru").withHomePage("www.asdsa.ru").withbYear("1990").withaYear("2007").withAddress2("Moscow, Filevskyi bil").withPhone2("+7(495)123-11-22").withNotes("заметки"));
        }
    }

    @Test
    public void testContactAddress() {
        app.goTo().homePage();
        ContactData contact = app.contract().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contract().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }


}
