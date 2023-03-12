package com.example.todolist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.bsuir.todolist.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    private RecyclerView recyclerView;
    private FloatingActionButton fab_add;
    private NoteListAdapter noteListAdapter;
    private AppDatabase database;
    private List<Note> note = new ArrayList<>();
    private SearchView searchView_home;
    private Note selectedNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_home);
        fab_add = findViewById(R.id.fab_add);
        searchView_home = findViewById(R.id.searchView_home);
        database = AppDatabase.getInstance(this);
        note = database.noteDao().getAll();

        // create Adapter
        updateRecycle(note);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //For interaction between various activity objects, the key class is android.content.Intent.
                Intent intent = new Intent(MainActivity.this, NotesTakerActivity.class);
                startActivityForResult(intent, 101);
            }
        });

        searchView_home.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }

    private void filter(String newText) {
        List<Note> filteredList = new ArrayList<>();
        for (Note singleNote : note) {
            if (singleNote.getTitle().toLowerCase().contains(newText.toLowerCase())
                    || singleNote.getNotes().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(singleNote);
            }
        }
        // notify Adapter - data was changed
        noteListAdapter.filterList(filteredList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            if (resultCode == Activity.RESULT_OK) {
                Note new_notes = (Note) data.getSerializableExtra("note");
                database.noteDao().insert(new_notes);
                note.clear();
                note.addAll(database.noteDao().getAll());
                noteListAdapter.notifyDataSetChanged();
            }
        }

        if (requestCode == 102) {
            if (resultCode == Activity.RESULT_OK) {
                Note new_notes = (Note) data.getSerializableExtra("note");
                database.noteDao().update(new_notes.getId(),new_notes.getTitle(),new_notes.getNotes());
                note.clear();
                note.addAll(database.noteDao().getAll());
                noteListAdapter.notifyDataSetChanged();
            }
        }
    }

    private void updateRecycle(List<Note> note) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        noteListAdapter = new NoteListAdapter(MainActivity.this, note, noteClickListener);
        recyclerView.setAdapter(noteListAdapter);
    }

    private final NoteClickListener noteClickListener = new NoteClickListener() {
        @Override
        public void onClick(Note notes) {
            Intent intent = new Intent(MainActivity.this, NotesTakerActivity.class);
            intent.putExtra("old_note", (Serializable) notes);
            startActivityForResult(intent,102);
        }

        @Override
        public void onLongClick(Note notes, CardView cardView) {
            selectedNote = notes;
            showPopup(cardView);
        }
    };

    private void showPopup(CardView cardView) {
        PopupMenu popupMenu = new PopupMenu(this, cardView);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.pin:
                if (selectedNote.isPinned()) {
                    database.noteDao().pin(selectedNote.getId(),false);
                    Toast.makeText(MainActivity.this, "Unpinned", Toast.LENGTH_LONG).show();
                } else {
                    database.noteDao().pin(selectedNote.getId(),true);
                    Toast.makeText(MainActivity.this, "Pinned", Toast.LENGTH_LONG).show();
                }

                note.clear();
                note.addAll(database.noteDao().getAll());
                noteListAdapter.notifyDataSetChanged();

                return true;

            case R.id.delete:
                database.noteDao().delete(selectedNote);
                note.remove(selectedNote);
                noteListAdapter.notifyDataSetChanged();

                Toast.makeText(MainActivity.this, "Note removed", Toast.LENGTH_LONG).show();
                return true;

            default:
                return false;
        }
    }
}