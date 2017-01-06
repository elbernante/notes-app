package com.codingchallenge.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class NotesDao {
	
	private static int currentIndex = -1;
	private static Map<Integer, Note> pretendDB = new HashMap<>();
	
	public static Note newNote(String body) {
		Note note = new Note(++currentIndex, body);
		pretendDB.put(note.getId(), note);
		return note;
	}
	
	public static Note getNote(int id) {
		return pretendDB.get(id);
	}
	
	public static List<Note> allNotes(){
		return new ArrayList<Note>(pretendDB.values());
	}
	
	public static List<Note> allNotes(String filter) {
		if (filter == null || filter.trim().equals(""))
			return allNotes();
		
		return pretendDB.values()
				        .stream()
				        .filter(n -> n.getBody().contains(filter))
				        .collect(Collectors.toList());
	}

}
