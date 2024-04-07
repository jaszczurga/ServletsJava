package com.jaszczurga.Service;

import com.jaszczurga.model.Note;

public class NoteService {
    public Note createNote(String title, String content) {
        return new Note(title, content);
    }
}
