package generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by popdv on 22.08.2016.
 */
public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n"
                    , contact.getFirstName(), contact.getMiddleName(), contact.getLastName()
                    , contact.getNickName(), contact.getTitle(), contact.getCompany(), contact.getAddress(), contact.getTelHome(), contact.getTelMobile()
                    , contact.getTelWork(), contact.getFax(), contact.getEmail1(), contact.getEmail2(), contact.getEmail3(), contact.getHomePage(), contact.getbYear()
                    , contact.getaYear(), contact.getAddress2(), contact.getPhone2(), contact.getNotes()));
        }
        writer.close();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("FirstName %s", i))
                    .withMiddleName(String.format("MiddleName %s", i)).withLastName(String.format("LastName %s", i))
                    .withNickName(String.format("NickName %s", i)).withTitle(String.format("Title %s", i))
                    .withCompany(String.format("Company %s", i)).withAddress(String.format("Address %s", i)).withTelHome(String.format("+7(495)123-11-2%s", i))
                    .withTelMobile(String.format("8 495 123-21-2%s", i)).withTelWork(String.format("8495123312%s", i)).withFax(String.format("8495122312%s", i))
                    .withEmail1(String.format("Email@pochta.ru%s", i)).withEmail2(String.format("Email2@pochta.ru%s", i)).withEmail3(String.format("Email3@pochta.ru%s", i))
                    .withHomePage(String.format("HomePage %s", i)).withbYear(String.format("201%s", i)).withaYear(String.format("201%s", i))
                    .withAddress2(String.format("Address2 %s", i)).withPhone2(String.format("+7(499)123-11-2%s", i)).withNotes(String.format("Notes %s", i)));
        }
        return contacts;
    }
}
