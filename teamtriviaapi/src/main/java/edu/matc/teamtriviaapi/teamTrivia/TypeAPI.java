package edu.matc.teamtriviaapi.teamTrivia;
/**
 * Created by sarah on 10/31/2017.
 */

import edu.matc.teamtriviaapi.entity.Difficulty;
import edu.matc.teamtriviaapi.entity.Type;
import edu.matc.teamtriviaapi.persistence.DifficultyDAO;
import edu.matc.teamtriviaapi.persistence.TypeDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/types")
public class TypeAPI {
    TypeDAO dao = new TypeDAO();
    Formatter formatter = new Formatter();

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/html")
    public Response getMessage() {
        // Return a simple message
        String output = "To view all available types use /teamTrivia/type/all<br />" +
                "To view a specific type use /teamTrivia/type/< id ><br />" +
                "To access JSON values, use /teamTrivia/type/JSON/all or type/JSON/< id >";
        return Response.status(200).entity(output).build();
    }

    @GET
    @Produces({"text/html", "text/plain"})
    @Path("/{id}")
    public Response getTypeHTML(@PathParam("id") String id) {
        if (!isNumeric(id)) {
            return Response.status(400).entity("Status 400: Ids should be numeric").build();
        }

        Type type = dao.getTypeById(Integer.parseInt(id));
        String output = "";

        if (type != null) {
            output = type.toString(); // difficulty to string
            return Response.status(200).entity(output).build();

        } else {
            output = "Status 404: That type does not exist";
            return Response.status(404).entity(output).build();
        }
    }

    @GET
    @Produces({"text/html", "text/plain"})
    @Path("/all")
    public Response getAllTypesHTML() {

        List<Type> types = dao.getAllTypes();
        String output = "";

        if (types.size() > 0) {
            for (Type type: types) {

                output += type.toString(); // type to string
            }
            return Response.status(200).entity(output).build();

        } else {
            output = "Status 404: There are no types here";
            return Response.status(404).entity(output).build();
        }

    }


    @GET
    @Produces({"application/json", "text/plain"})
    @Path("/JSON/{id}")
    public Response getTypeJSON(@PathParam("id") String id) {

        if (!isNumeric(id)) {

            int status = 404;
            String error = formatter.formatJSONMessage(status, "Ids should be numeric");
            return Response.status(status).entity(error).build();
        }

        Type type = dao.getTypeById(Integer.parseInt(id));
        String output = "";

        if (type != null) {
            output = type.toStringJSON(); // type to string
            return Response.status(200).entity(output).build();

        } else {
            String error = formatter.formatJSONMessage(404, "That type does not exist");
            return Response.status(404).entity(error).build();
        }

    }

    @GET
    @Produces({"application/json", "text/plain"})
    @Path("/JSON/all")
    public Response getAllTypesJSON() {

        List<Type> types = dao.getAllTypes();
        String output = "";

        if (types.size() > 0) {

            output += "[";
            for (Type type: types) {

                output += type.toStringJSON(); // type to string
                output += ",";
            }
            output = output.substring(0, output.length() - 1);
            output += "]";


            return Response.status(200).entity(output).build();

        } else {
            int status = 404;
            output = "There are no types here";
            output = formatter.formatJSONMessage(status, output);
            return Response.status(status).entity(output).build();
        }

    }


    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

}