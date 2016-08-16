package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by popdv on 14.08.2016.
 */
public class DetailInformationContactTests extends TestBase {

    @Test
    public void testDetailInformationContact() {
        app.goTo().homePage();
        ContactData contact = app.contract().all().iterator().next();
        ContactData contactInfoFromDetailForm = app.contract().infoFromDetailForm(contact);
        ContactData contactInfoFromEditForm = app.contract().infoFromEditForm(contact);

        assertThat(contactInfoFromDetailForm.getDetailsInfo(), equalTo(mergeInfoFromEditForm(contactInfoFromEditForm)));
    }


    private String mergeInfoFromEditForm(ContactData contact) {
        if (!(contact.getTelHome().equals(""))) {
            contact.withTelHome("H: " + contact.getTelHome());
        }
        if (!(contact.getTelMobile().equals(""))) {
            contact.withTelMobile("M: " + contact.getTelMobile());
        }
        if (!(contact.getTelWork().equals(""))) {
            contact.withTelWork("W: " + contact.getTelWork());
        }
        return Arrays.asList(contact.getFirstName() + " " + contact.getLastName(), contact.getAddress() + "\n", contact.getTelHome(), contact.getTelMobile(), contact.getTelWork())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}
