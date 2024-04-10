package com.jaszczurga.Service;

import com.jaszczurga.model.Note;
import com.jaszczurga.repository.NotesRepository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NoteService {

    List<Note> notes = new CopyOnWriteArrayList<>();
    NotesRepository notesRepository;

    public NoteService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public List<Note> getAllNotes() {
        return notesRepository.getAllNotes();
    }

    public Note createNote(String title, String content) {

        Note note = new Note(title, content);
        notesRepository.create(note);
        return note;
    }

    public void deleteNoteById(String id) {
        notesRepository.delete(id);
    }
}
