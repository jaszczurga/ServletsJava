package com.jaszczurga.repository;

import com.jaszczurga.model.Note;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotesRepository {

    private final DataSource dataSource;

    public NotesRepository() {
        this.dataSource = createDataSource();
    }

    private DataSource createDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:h2:mem:;INIT=RUNSCRIPT FROM 'classpath:notes.sql'");
        return ds;
    }

    public Note create(Note note) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO NOTES (title, content) VALUES (?, ?)");
            ps.setString(1, note.getTitle());
            ps.setString(2, note.getContent());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return note;
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM NOTES");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("TITLE");
                String content = resultSet.getString("CONTENT");
                notes.add(new Note(title, content));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

    public Note delete(Note note) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM NOTES WHERE id = ?");
            ps.setString(1, note.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return note;
    }

    public Note update(Note note) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE NOTES SET content = ? WHERE id = ?");
            ps.setString(1, note.getContent());
            ps.setString(2, note.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return note;
    }

}
