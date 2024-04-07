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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equalsIgnoreCase("/")) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print(
                    "<html>\n" +
                            "<body>\n" +
                            "<h1>Hello World</h1>\n" +
                            "<p>This is my very first, embedded Tomcat, HTML Page!</p>\n" +
                            "</body>\n" +
                            "</html>");
        }
        else if (request.getRequestURI().equalsIgnoreCase("/notes")) {
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print("[]");
        }
    }

   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    if(request.getRequestURI().equalsIgnoreCase("/saveNote")){
        ObjectMapper mapper = new ObjectMapper();
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
