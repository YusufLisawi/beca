package publisher;

import model.Person;
import webservice.PersonService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class SOAPPublisherClient {
    public static void main(String[] args) throws MalformedURLException {
        URL wsdlURL = new URL("http://localhost:8888/ws/person?wsdl");
//check above URL in browser, you should see WSDL file
//creating QName using targetNamespace and name
        QName qname = new QName("http://webservice/", "PersonServiceImplService");
        Service service = Service.create(wsdlURL, qname);
        PersonService ps = service.getPort(PersonService.class);
        Person p1 = new Person();
        p1.setName("Ali");
        p1.setId(1);
        p1.setAge(30);
        Person p2 = new Person();
        p2.setName("Ahmed");
        p2.setId(2);
        p2.setAge(25);
        System.out.println("Add Person Status=" + ps.addPerson(p1));
        System.out.println("Add Person Status=" + ps.addPerson(p2));
        System.out.println(ps.getPerson(1));
        System.out.println(Arrays.asList(ps.getAllPersons()));
        System.out.println("Delete Person Status=" + ps.deletePerson(2));
        System.out.println(Arrays.asList(ps.getAllPersons()));
    }
}