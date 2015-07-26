package com.johnson.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.johnson.resources.TestRestWS;

@ApplicationPath("rest")
public class JerseyRestConfig extends Application{

  // This is where you add classes for the rest service to the application.
  @Override
  public Set<Class<?>> getClasses() {
    final Set<Class<?>> configuredClasses = new HashSet<Class<?>>();
    configuredClasses.add(TestRestWS.class);
    return configuredClasses;
  }
  

}
