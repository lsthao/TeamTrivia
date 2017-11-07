package edu.matc.teamtriviaapi.teamTrivia;
/**
 * Created by sarah on 10/31/2017.
 */

import edu.matc.teamtriviaapi.entity.Category;
import edu.matc.teamtriviaapi.entity.Difficulty;
import edu.matc.teamtriviaapi.persistence.CategoryDAO;
import edu.matc.teamtriviaapi.persistence.DifficultyDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/category")
public class CategoryAPI {
    CategoryDAO dao = new CategoryDAO();
    Formatter formatter = new Formatter();

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/html")
    public Response getMessage() {
        // Return a simple message
        String output = "We are always looking for new categories to add, contact us if there is a category you would like added.<br />" +
                "To view all available categories use /teamTrivia/category/all<br />" +
                "To view a specific category use /teamTrivia/category/< id ><br />" +
                "To access JSON values, use /teamTrivia/category/JSON/all or category/JSON/< id >";
        return Response.status(200).entity(output).build();
    }

    @GET
    @Produces({"text/html", "text/plain"})
    @Path("/{id}")
    public Response getCategoryHTML(@PathParam("id") String id) {
        if (!isNumeric(id)) {
            return Response.status(400).entity("Status 400: Ids should be numeric").build();
        }

        Category category = dao.getCategoryById(Integer.parseInt(id));
        String output = "";

        if (category != null) {
            output = category.toString(); // difficulty to string
            return Response.status(200).entity(output).build();

        } else {
            output = "Status 404: That category does not exist";
            return Response.status(404).entity(output).build();
        }
    }

    @GET
    @Produces({"text/html", "text/plain"})
    @Path("/all")
    public Response getAllCategoriesHTML() {

        List<Category> categories = dao.getAllCategories();
        String output = "";

        if (categories.size() > 0) {
            for (Category category: categories) {

                output += category.toString(); // difficulty to string
            }
            return Response.status(200).entity(output).build();

        } else {
            output = "Status 404: There are no categories here";
            return Response.status(404).entity(output).build();
        }

    }

    @GET
    @Produces({"application/json", "text/plain"})
    @Path("/JSON/{id}")
    public Response getCategoryJSON(@PathParam("id") String id) {

        if (!isNumeric(id)) {

            int status = 404;
            String error = formatter.formatJSONMessage(status, "Ids should be numeric");
            return Response.status(status).entity(error).build();
        }

        Category category = dao.getCategoryById(Integer.parseInt(id));
        String output = "";

        if (category != null) {
            output = category.toStringJSON(); // difficulty to string
            return Response.status(200).entity(output).build();

        } else {
            int status = 404;
            String error = formatter.formatJSONMessage(status, "That category does not exist");
            return Response.status(status).entity(error).build();
        }

    }

    @GET
    @Produces({"application/json", "text/plain"})
    @Path("/JSON/all")
    public Response getAllCategoriesJSON() {

        List<Category> categories = dao.getAllCategories();
        String output = "";

        if (categories.size() > 0) {

            output += "[";
            for (Category category: categories) {

                output += category.toStringJSON(); // difficulty to
                output += ",";
            }
            output = output.substring(0, output.length() - 1);
            output += "]";


            return Response.status(200).entity(output).build();

        } else {
            int status = 404;
            output = "There are no categories here";
            output = formatter.formatJSONMessage(status, output);
            return Response.status(status).entity(output).build();
        }

    }

    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

}