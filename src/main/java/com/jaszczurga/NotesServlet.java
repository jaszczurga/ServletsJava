package com.jaszczurga;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaszczurga.Service.NoteService;
import com.jaszczurga.context.Application;
import com.jaszczurga.model.Note;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class NotesServlet extends HttpServlet {

    private NoteService noteService = Application.noteService;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equalsIgnoreCase("/")) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print(
                    "<html>\n" +
                            "<body>\n" +
                            "<h1>Notes App</h1>\n" +
                            "<p>This is my playground application for creating REST api</p>\n" +
                            "</body>\n" +
                            "</html>");
        }
        else if (request.getRequestURI().equalsIgnoreCase("/notes")) {
            response.setContentType("application/json; charset=UTF-8");
            String json = mapper.writeValueAsString(noteService.getAllNotes());
            response.getWriter().print(json);
        }
    }

   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    if(request.getRequestURI().equalsIgnoreCase("/saveNote")){
        Note note = mapper.readValue(request.getReader(), Note.class);

        Note createdNote = noteService.createNote(note.getTitle(), note.getContent());

        response.setContentType("application/json; charset=UTF-8");
        String json = mapper.writeValueAsString(createdNote);
        response.getWriter().print(json);
    } else {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
}

}
