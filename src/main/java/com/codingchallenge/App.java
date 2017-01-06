package com.codingchallenge;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import static spark.Spark.*;

import com.codingchallenge.model.*;

public class App 
{
    public static void main( String[] args )
    {
    	setPort(8888);
    	staticFileLocation("/public");
    	
    	/**
         * Creates new note.
         *
         * @param body        body of the note
         */
    	post("/api/notes", "application/json", (request, response) -> {
        	Type type =  new TypeToken<Map<String, String>>() {}.getType();
        	Map<String, String> data = new Gson().fromJson(request.body(), type);
        	
        	response.type("application/json");
        	if (!data.containsKey("body")) {
        		response.status(400); // Bad request
        		return null;
        	}
        	
        	response.status(201);      // Created
        	return NotesDao.newNote(data.get("body"));
        	
        }, new JsonTransformer());
        
    	
        /**
         * Returns all a list of all notes.
         */
        get("/api/notes", "application/json", (request, response) -> {
        	response.type("application/json");
        	response.status(200);
        	return NotesDao.allNotes(request.queryParams("query"));
        }, new JsonTransformer());
        
        
        /**
         * Returns a note of a specified note id.
         * 
         * @param id	ID of of the note
         */
        get("/api/notes/:id", "application/json", (request, response) -> {
        	String id = request.params("id");
        	int idx;
        	
        	response.type("application/json");
        	try {
        		idx = Integer.valueOf(id);
        	} catch (NumberFormatException e) {
        		response.status(400);   // Bad request
        		return null; 
        	}
        	
        	Note note = NotesDao.getNote(idx);
        	if (note == null) {
        		response.status(404);  // Not found
        		return null;
        	}
        	
        	response.status(200);
        	return note;
        	
        }, new JsonTransformer());
    }
}
