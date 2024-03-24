package org.nttdata.webservices;

import org.nttdata.webservices.model.Person;
import org.nttdata.webservices.model.MyResponse;

public interface PersonService {
    public MyResponse addPerson(Person p);
    public MyResponse deletePerson(int id);
    public Person getPerson(int id);
    public Person[] getAllPersons();
}