package ru.stqa.pft.mantis.appmanager;

/**
 * Created by popdv on 05.09.2016.
 */
public class UserManagementHelper extends HelperBase {

    public UserManagementHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
    }


}
