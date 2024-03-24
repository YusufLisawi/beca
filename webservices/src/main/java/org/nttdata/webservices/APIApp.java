package org.nttdata.webservices;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class APIApp extends Application {
    private final Set<Object> singletons;
    public APIApp() {
        singletons = new HashSet<Object>();
    }
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}