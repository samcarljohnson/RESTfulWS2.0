package com.johnson.resources;
import com.johnson.utils.InputStreamUtils;

import java.io.InputStream;
import java.sql.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

import org.apache.log4j.Logger;

import com.jaspersoft.jasperserver.jaxrs.client.apiadapters.reporting.ReportOutputFormat;
import com.jaspersoft.jasperserver.jaxrs.client.core.JasperserverRestClient;
import com.jaspersoft.jasperserver.jaxrs.client.core.RestClientConfiguration;
import com.jaspersoft.jasperserver.jaxrs.client.core.Session;
import com.jaspersoft.jasperserver.jaxrs.client.core.operationresult.OperationResult;
import com.johnson.dao.JasperSugarcrmConnection;

@Path("/test")
public class TestRestWS {
  
  public TestRestWS() {}
  
  /*
   * This method prints plain text in the browser window.
   * URL: http://localhost:8080/RESTfulWS2.0/rest/test/
   */
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getTestText() {
    return "This is test text from the plain text GET request!!";
  }
  
  /*
   * This method prints xml in the browser window.
   * URL: http://localhost:8080/RESTfulWS2.0/rest/test/
   * HEADER: "Accept: text/xml"
   */
  @GET
  @Produces(MediaType.TEXT_XML)
  public String getTestXML() {
    return "<?xml version=\"1.0\"?>"
        + "<hello> This is test text from the plain text GET request!!"
        + "</hello>";
  }
  
  /*
   * This method prints html in the browser window.
   * URL: http://localhost:8080/RESTfulWS2.0/rest/test/
   * HEADER: "Accept: text/html"
   */
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String getTestHTML() {
    return "<html>"
        + "<head>"
        + "<title>Hello From getTestHTML</title>"
        + "</head>"
        + "<body>"
        + "<h1> This is test text from the plain text GET request!! </h1>"
        + "</body>"
        + "</html>";
  }
  
  /*
   * This method queries the sugarcrm db on the local jasperserver for names.
   * URL: http://localhost:8080/RESTfulWS2.0/rest/test/getNames
   */
  @GET
  @Path("getNames")
  @Produces(MediaType.TEXT_HTML)
  public String getNames() throws Exception {
    PreparedStatement query = null;
    String stmt = "";
    String returnString = "";
    Connection conn = null;
    
    try {
      conn = JasperSugarcrmConnection.SugarcrmDbConn().getConnection();
      stmt = "select * from accounts;";
      query = conn.prepareStatement(stmt);
      ResultSet rs = query.executeQuery();
      
      while(rs.next()) {
        returnString = returnString + "<p>" + rs.getString("name") + "</p>";
      }
      query.close();
      
    } catch (Exception e) {
      
      e.printStackTrace();
    } finally {
      if(conn != null) conn.close();
    }
    return returnString;
  }
  
  /*
   * This method queries the sugarcrm db on the local jasperserver for names.
   * URL: http://localhost:8080/RESTfulWS2.0/rest/test/runReport
   */
  @GET
  @Path("/runReport")
  @Produces(MediaType.TEXT_HTML)
  public String runReport() {
    // JRS-Rest-Java-Client Impl
    RestClientConfiguration configuration = new RestClientConfiguration("http://localhost:8081/jasperserver");
    JasperserverRestClient client = new JasperserverRestClient(configuration);
    Session session = client.authenticate("jasperadmin", "jasperadmin");
    //session.logout(); will end the session.
    OperationResult<InputStream> result = client
        .authenticate("jasperadmin", "jasperadmin")
        .reportingService()
        .report("/reports/DevUIDMediaList")
        .prepareForRun(ReportOutputFormat.HTML, 1)
        //.parameter("Cascading_name_single_select", "A & U Stalker Telecommunications, Inc")
        .run();
    InputStream report = result.getEntity();
    String strReport = InputStreamUtils.convertStreamToString(report);
    return strReport;
  }
  
}
