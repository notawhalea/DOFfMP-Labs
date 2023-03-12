package com.example.todolist;

import androidx.cardview.widget.CardView;

public interface NoteClickListener {
    void onClick(Note notes);

    void onLongClick(Note notes, CardView cardView);
}