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
            return Response.status(400).entity("Ids should be numeric").build();
        }

        Category category = dao.getCategoryById(Integer.parseInt(id));
        String output = "";

        if (category != null) {
            output = category.toString(); // difficulty to string
            return Response.status(200).entity(output).build();

        } else {
            output = "That category does not exist";
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
            output = "There are no categories here";
            return Response.status(404).entity(output).build();
        }

    }


    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

}