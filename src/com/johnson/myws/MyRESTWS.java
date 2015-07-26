package com.johnson.myws;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/myws")
public class MyRESTWS {
  
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String getInfo() {
    return "<html>" + "<title>" + "Some Info" + "</title>" +
        "<body>" + "This is the info" + "</body>" + "</html>";
  }
  
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getTextInfo() {
    return "This is the info";
  }
  
  @PUT
  @Path("{id}")
  @Produces(MediaType.TEXT_PLAIN)
  public String modifyId(@PathParam("id") String id) {
    
    return "Did something! (nothing right now though)";
  }
  
}
