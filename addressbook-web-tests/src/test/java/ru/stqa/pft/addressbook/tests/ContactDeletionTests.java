package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by popdv on 25.07.2016.
 */
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        app.getContractHelper().selectContract();
        app.getContractHelper().deleteSelectedContract();
    }
}
