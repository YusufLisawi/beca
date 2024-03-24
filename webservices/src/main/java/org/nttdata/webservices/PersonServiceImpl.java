package org.nttdata.webservices;

import org.nttdata.webservices.model.Person;
import org.nttdata.webservices.model.MyResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Path("/person")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class PersonServiceImpl implements PersonService {

    private static Map<Integer, Person> people = new HashMap<>();

    @Override
    @POST
    @Path("/add")
    public MyResponse addPerson(Person p) {
        MyResponse myResponse = new MyResponse();
        if (people.get(p.getId()) != null) {
            myResponse.setStatus(false);
            myResponse.setMessage("Person Already Exists");
            return myResponse;
        }
        people.put(p.getId(), p);
        myResponse.setStatus(true);
        myResponse.setMessage("Person created successfully");
        return myResponse;
    }

    @Override
    @DELETE
    @Path("/delete/{id}")
    public MyResponse deletePerson(@PathParam("id") int id) {
        MyResponse myResponse = new MyResponse();
        if (people.get(id) == null) {
            myResponse.setStatus(false);
            myResponse.setMessage("Person Doesn't Exists");
            return myResponse;
        }
        people.remove(id);
        myResponse.setStatus(true);
        myResponse.setMessage("Person deleted successfully");
        return myResponse;
    }

    @Override
    @GET
    @Path("/get/{id}")
    public Person getPerson(@PathParam("id") int id) {
        return people.get(id);
    }

    @Override
    @GET
    @Path("/getAll")
    public Person[] getAllPersons() {
        Person p1 = new Person();
        p1.setName("Ali");
        p1.setId(1);
        p1.setAge(30);
        Person p2 = new Person();
        p2.setName("Ahmed");
        p2.setId(2);
        p2.setAge(25);
        people.put(p1.getId(), p1);
        people.put(p2.getId(), p2);
        Set<Integer> ids = people.keySet();
        Person[] p = new Person[ids.size()];
        int i = 0;
        for (Integer id : ids) {
            p[i] = people.get(id);
            i++;
        }
        return p;
    }

    @GET
    @Path("/getDummy/{id}")
    public Person getDummyPerson(@PathParam("id") int id) {
        Person p = new Person();
        p.setAge(99);
        p.setName("Dummy");
        p.setId(id);
        return p;
    }
}