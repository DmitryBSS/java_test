package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String title;
    private final String company;
    private final String address;
    private final String telHome;
    private final String telMobile;
    private final String telWork;
    private final String fax;
    private final String email1;
    private final String email2;
    private final String email3;
    private final String homePage;
    private final String bYear;
    private final String aYear;
    private final String address2;
    private final String phone2;
    private final String notes;

    public ContactData(String firstName, String middleName, String lastName, String nickName, String title, String company, String address, String telHome, String telMobile, String telWork, String fax, String email1, String email2, String email3, String homePage, String bYear, String aYear, String address2, String phone2, String notes) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.title = title;
        this.company = company;
        this.address = address;
        this.telHome = telHome;
        this.telMobile = telMobile;
        this.telWork = telWork;
        this.fax = fax;
        this.email1 = email1;
        this.email2 = email2;
        this.email3 = email3;
        this.homePage = homePage;
        this.bYear = bYear;
        this.aYear = aYear;
        this.address2 = address2;
        this.phone2 = phone2;
        this.notes = notes;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getTelHome() {
        return telHome;
    }

    public String getTelMobile() {
        return telMobile;
    }

    public String getTelWork() {
        return telWork;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getHomePage() {
        return homePage;
    }

    public String getbYear() {
        return bYear;
    }

    public String getaYear() {
        return aYear;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getNotes() {
        return notes;
    }
}
