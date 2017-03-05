package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 25.02.2017.
 */
public class ContactDataGenerator {


  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {

    ContactDataGenerator generator = new ContactDataGenerator ();
    JCommander jCommander = new JCommander ( generator );
    try {
      jCommander.parse ( args );
    } catch (ParameterException ex) {
      jCommander.usage ();
      return;
    }
    generator.run ();


  }

  private void run() throws IOException {
    List <ContactData> contacts = generateContacts ( count );
    if (format.equals ( "json" )) {
      saveAsJson ( contacts, new File ( file ) );
    } else {
      System.out.println ( "Unrecognized format " + format );

    }
  }

  private void saveAsJson(List <ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder ().setPrettyPrinting ().excludeFieldsWithoutExposeAnnotation ().create ();
    String json = gson.toJson ( contacts );
    try (Writer writer = new FileWriter ( file )) {
      writer.write ( json );
    }

  }

  private List <ContactData> generateContacts(int count) {
    List <ContactData> contacts = new ArrayList <ContactData> ();
    for (int i = 0; i < count; i++) {
      contacts.add ( new ContactData ().withName ( String.format ( "name %s", i ) )
              .withLastname ( String.format ( "lastname %s", i ) ).withHomePhone ( String.format ( "12345%s", i ) )
              .withWorkPhone (String.format ( "12345%s", i )).withMobilePhone ( String.format ( "12345%s", i ) )
              .withEmail ( String.format ( "email%s", i ) ).withEmail2 (String.format ( "email2%s", i )  )
              .withEmail3 ( String.format ( "email3%s", i ) ).withAddress ( String.format ( "address%s", i ) ) );


    }

    return contacts;
  }
}
