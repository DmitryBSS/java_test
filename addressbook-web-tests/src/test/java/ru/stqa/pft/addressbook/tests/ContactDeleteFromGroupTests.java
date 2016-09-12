package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by popdv on 30.08.2016.
 */
public class ContactDeleteFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }

        if (app.db().contacts().size() == 0) {
            app.goTo().gotoContactPage();
            File photo = new File("src/test/resources/stru.png");
            ContactData contact = new ContactData().withFirstName("Dmitry").withMiddleName("Victorovich").withLastName("Popov")
                    .withNickName("popdv").withTitle("Title").withCompany("BSS").withAddress("Moscow Nagornyi p-d")
                    .withTelHome("+7(495)111-11-11").withTelMobile("+79295622211").withTelWork("+7(495)111-11-11")
                    .withFax("+7(495)111-11-13").withEmail1("asd@asd.ru").withEmail2("asd2@asd.ru").withEmail3("asd3@asd.ru")
                    .withHomePage("www.asdsa.ru").withbYear("1990").withaYear("2007").withAddress2("Moscow, Filevskyi bil")
                    .withPhone2("+7(495)123-11-22").withNotes("заметки").withPhoto(photo);
            app.contract().create(contact);
            ContactData contactFromDB = app.db().contacts().iterator().next();
            GroupData group = app.db().groups().iterator().next();
            app.goTo().homePage();
            app.contract().selectContractById(contactFromDB.getId());
            app.contract().addingInGroupById(group.getId());
            app.goTo().homePageSelectedGroup(group.getId());

        } else {
            ContactData contact = app.db().contacts().iterator().next();
            GroupData group = app.db().groups().iterator().next();
            app.goTo().homePage();
            app.contract().selectContractById(contact.getId());
            app.contract().addingInGroupById(group.getId());
            app.goTo().homePageSelectedGroup(group.getId());
        }
    }

    @Test
    public void testContactAddingGroup() {

        Contacts contacts = app.db().contacts();
        Iterator<ContactData> iteratorContacts = contacts.iterator();
        ContactData contact = iteratorContacts.next();
        GroupData group = contact.getGroups().iterator().next();

        app.goTo().homePage();

        while (iteratorContacts.hasNext()) {
            if (contact.getGroups().size() > 0) {
                group = contact.getGroups().iterator().next();
                app.contract().filterGroupsById(group.getId());
                break;
            } else {
                contact = iteratorContacts.next();
            }
        }

        app.contract().selectContractById(contact.getId());
        app.contract().removeFromGroup();
        app.goTo().homePageSelectedGroup(group.getId());

        Groups groupsContactAfterAfter = app.db().contactById(contact.getId()).iterator().next().getGroups();

        assertThat(groupsContactAfterAfter, equalTo(
                contact.getGroups().without(group)));
        app.contract().filterGroupsReset();
    }
}


