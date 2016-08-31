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
public class ContactAddingInGroupsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().gotoContactPage();
            File photo = new File("src/test/resources/stru.png");
            app.contract().create(new ContactData().withFirstName("Dmitry").withMiddleName("Victorovich").withLastName("Popov").withNickName("popdv").withTitle("Title").withCompany("BSS").withAddress("Moscow Nagornyi p-d").withTelHome("+7(495)111-11-11").withTelMobile("+79295622211").withTelWork("+7(495)111-11-11").withFax("+7(495)111-11-13").withEmail1("asd@asd.ru").withEmail2("asd2@asd.ru").withEmail3("asd3@asd.ru").withHomePage("www.asdsa.ru").withbYear("1990").withaYear("2007").withAddress2("Moscow, Filevskyi bil").withPhone2("+7(495)123-11-22").withNotes("заметки").withPhoto(photo));
        }
    }

    @Test
    public void testContactAddingGroup() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        ContactData selectedContact = contacts.iterator().next();
        Groups groupsSelectedContact = selectedContact.getGroups();
        GroupData selectedGroup = groups.iterator().next();
        Iterator<ContactData> iteratorContacts = contacts.iterator();

        while (iteratorContacts.hasNext()) {
            if (groupsSelectedContact.equals(groups)) {
                selectedContact = iteratorContacts.next();
                groupsSelectedContact = selectedContact.getGroups();
            } else {
                break;
            }
        }
        if (groupsSelectedContact.equals(groups)) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test100"));
        }
        groups = app.db().groups();
        groupsSelectedContact = selectedContact.getGroups();
        groups.removeAll(groupsSelectedContact);

        if (groups.size() > 0) {
            selectedGroup = groups.iterator().next();
        } else {
            throw new RuntimeException("Нету доступных групп");
        }
        app.goTo().homePage();
        app.contract().selectContractById(selectedContact.getId());
        app.contract().addingInGroupById(selectedGroup.getId());
        app.goTo().homePageSelectedGroup(selectedGroup.getId());
        groupsSelectedContact = selectedContact.getGroups();

        assertThat(groupsSelectedContact, equalTo(
                groupsSelectedContact.withAdded(selectedGroup)));
    }
}


