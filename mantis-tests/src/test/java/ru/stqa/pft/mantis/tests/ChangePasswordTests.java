package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by popdv on 05.09.2016.
 */
public class ChangePasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException {
        String login = app.getProperty("web.adminLogin");
        String password = app.getProperty("web.adminPassword");
        app.authorization().authorization(login, password);
        app.goTo().userManagementPage();

        Iterator<UserData> iteratorUsers = app.db().users().iterator();
        UserData selectedUserData = iteratorUsers.next();
        String userName = selectedUserData.getUsername();
        String email = selectedUserData.getEmail();

        if (userName.equals("administrator")) {
            selectedUserData = iteratorUsers.next();
            userName = selectedUserData.getUsername();
            email = selectedUserData.getEmail();
        }
        app.user().initChangeUser(userName);
        app.user().resetPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.user().finish(confirmationLink, "12345");

        HttpSession session = app.newSession();
        assertTrue(session.login(userName, "12345"));
        assertTrue(session.isLoggedInAs(userName));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
