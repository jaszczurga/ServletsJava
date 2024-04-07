package com.jaszczurga.context;

import com.jaszczurga.Service.NoteService;
import com.jaszczurga.repository.NotesRepository;

public class Application {


    public static final NotesRepository notesRepository = new NotesRepository();
    public static final NoteService noteService = new NoteService(notesRepository);

}
