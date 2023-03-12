package com.example.todolist;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = REPLACE)
    void insert(Note note);

    @Query("SELECT * FROM note ORDER BY id DESC")
    List<Note> getAll();

    @Query("UPDATE note SET title = :title,notes = :notes WHERE id = :id ")
    void update(int id, String title, String notes);

    @Delete
    void delete(Note note);

    @Query("UPDATE note SET pinned = :pin WHERE id = :id ")
    void pin(int id, boolean pin);
}
