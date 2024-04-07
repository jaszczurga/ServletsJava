package com.jaszczurga.Service;

import com.jaszczurga.model.Note;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NoteService {

    List<Note> notes = new CopyOnWriteArrayList<>();

    public List<Note> getAllNotes() {
        return notes;
    }

    public Note createNote(String title, String content) {

        Note note = new Note(title, content);
        notes.add(note);
        return note;
    }
}
