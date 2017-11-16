package edu.matc.teamtriviaapi.teamTrivia;
/**
 * Created by sarah on 10/31/2017.
 */
import edu.matc.teamtriviaapi.entity.Difficulty;
import edu.matc.teamtriviaapi.entity.Question;
import edu.matc.teamtriviaapi.persistence.DifficultyDAO;
import edu.matc.teamtriviaapi.persistence.QuestionDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/difficulties")
public class DifficultyAPI {
    DifficultyDAO dao = new DifficultyDAO();
    Formatter formatter = new Formatter();

    // The Java method will process HTTP GET requests
    @GET
    @Produces("text/html")
    public Response getMessage() {
        // Return a simple message with explanations
        String output = "Difficulty is randomly based on our opinions! Sorry for any issues that may cause.<br />" +
                "To view all available difficulties use /teamTrivia/difficulty/all<br />" +
                "To view a specific difficulty use /teamTrivia/difficulty/< id ><br />" +
                "To access JSON values, use /teamTrivia/difficulty/JSON/all or difficulty/JSON/< id >";
        return Response.status(200).entity(output).build();
    }

    @GET
    @Produces({"text/html", "text/plain"})
    @Path("/{id}")
    public Response getDifficultyHTML(@PathParam("id") String id) {
        // Gets single difficulty by ID

        if (!isNumeric(id)) {
            return Response.status(400).entity("Status 400: Ids should be numeric").build();
        }

        Difficulty difficulty = dao.getDifficultyById(Integer.parseInt(id));
        String output = "";

        if (difficulty != null) {
            output = difficulty.toString(); // difficulty to string
            return Response.status(200).entity(output).build();

        } else {
            output = "Status 404: That difficulty does not exist";
            return Response.status(404).entity(output).build();
        }
    }

    @GET
    @Produces({"text/html", "text/plain"})
    @Path("/all")
    public Response getAllDifficultiesHTML() {
        // Get all difficulties in html
        List<Difficulty> difficulties = dao.getAllDifficulties();
        String output = "";

        if (difficulties.size() > 0) {
            for (Difficulty difficulty: difficulties) {

                output += difficulty.toString(); // difficulty to string
            }
            return Response.status(200).entity(output).build();

        } else {
            output = "Status 404: There are no difficulties here";
            return Response.status(404).entity(output).build();
        }

    }

    @GET
    @Produces({"application/json", "text/plain"})
    @Path("/JSON/{id}")
    public Response getDifficultyJSON(@PathParam("id") String id) {
        // Get one difficulty by id
        if (!isNumeric(id)) {

            int status = 404;
            String error = formatter.formatJSONMessage(status, "Ids should be numeric");
            return Response.status(status).entity(error).build();
        }

        Difficulty difficulty = dao.getDifficultyById(Integer.parseInt(id));
        String output = "";

        if (difficulty != null) {
            output = difficulty.toStringJSON(); // difficulty to string
            return Response.status(200).entity(output).build();

        } else {
            int status = 404;
            String error = formatter.formatJSONMessage(status, "That difficulty does not exist");
            return Response.status(status).entity(error).build();
        }

    }

    @GET
    @Produces({"application/json", "text/plain"})
    @Path("/JSON/all")
    public Response getAllDifficultyJSON() {
        // get all difficulty in json
        List<Difficulty> difficulties = dao.getAllDifficulties();
        String output = "";

        if (difficulties.size() > 0) {

            output += "[";
            for (Difficulty difficulty: difficulties) {

                output += difficulty.toStringJSON(); // type to string
                output += ",";
            }
            output = output.substring(0, output.length() - 1);
            output += "]";


            return Response.status(200).entity(output).build();

        } else {
            int status = 404;
            output = "There are no difficulties here";
            output = formatter.formatJSONMessage(status, output);
            return Response.status(status).entity(output).build();
        }

    }


    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

}