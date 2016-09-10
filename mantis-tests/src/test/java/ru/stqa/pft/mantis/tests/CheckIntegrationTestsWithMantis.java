package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by popdv on 10.09.2016.
 */
public class CheckIntegrationTestsWithMantis extends TestBase {

    @Test
    public void checkIntegrationTestsWithMantisTest() throws RemoteException, ServiceException, MalformedURLException {
        skipIfNotFixed(1);
        app.authorization().authorization(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        assertEquals(app.user().getTextLogged(), "администратор");
    }
}
