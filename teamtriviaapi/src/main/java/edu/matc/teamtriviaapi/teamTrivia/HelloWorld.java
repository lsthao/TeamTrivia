package edu.matc.teamtriviaapi.teamTrivia;
/**
 * Created by sarah on 10/31/2017.
 */
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/hello")
public class HelloWorld {
    // The Java method will process HTTP GET requests
    @GET
    @Produces("text/plain")
    public Response getMessage() {
        // Return a simple message
        String output = "Hello World";
        return Response.status(200).entity(output).build();
    }

}