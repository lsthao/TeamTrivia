package edu.matc.teamtriviaapi.teamTrivia;

/**
 * Created by sarah on 11/7/2017.
 */
public class Formatter {
    public String formatJSONMessage(int status, String message) {

        return "{\"error\" : {\"status\" :\"" + status + "\", \"message\" : \"" + message + "\"}}";

    }
}
