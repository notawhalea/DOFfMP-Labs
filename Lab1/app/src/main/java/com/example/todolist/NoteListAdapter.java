package com.example.todolist;

import android.content.Context;
import android.location.GnssAntennaInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bsuir.todolist.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NoteListAdapter extends RecyclerView.Adapter<NotesViewHolder>{

    private Context context;
    private List<Note> list;
    private NoteClickListener listener;

    public NoteListAdapter(Context context, List<Note> list, NoteClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    //This method calls onCreateViewHolder to create a new ViewHolder
    //and initializes some private fields to be used by RecyclerView.
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list, parent, false));
    }

    @Override
    //Called by RecyclerView to display the data at the specified position.
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.textView_title.setText(list.get(position).getTitle());
        holder.textView_title.setSelected(true);

        holder.textView_notes.setText(list.get(position).getNotes());

        holder.textView_date.setText(list.get(position).getDate());
        holder.textView_date.setSelected(true);

        if (list.get(position).isPinned()) {
            holder.imageView_pin.setImageResource(R.drawable.pin_icon);
        }
        else {
            holder.imageView_pin.setImageResource(0);
        }

//        int color_code = getRandomColor();
//        holder.note_container.setCardBackgroundColor(holder.itemView.getResources().getColor(color_code, null));

        holder.note_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(list.get(holder.getAdapterPosition()));
            }
        });

        holder.note_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongClick(list.get(holder.getAdapterPosition()),holder.note_container);
                return false;
            }
        });
    }

//    private int getRandomColor() {
//        List<Integer> colorCode = new ArrayList<>();
//        colorCode.add(R.color.color1);
//        colorCode.add(R.color.color2);
//        colorCode.add(R.color.color3);
//        colorCode.add(R.color.color4);
//        colorCode.add(R.color.color5);
//
//        Random random = new Random();
//        return colorCode.get(random.nextInt(colorCode.size()));
//    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(List<Note> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }
}

//A ViewHolder describes an item view and metadata about its place within the RecyclerView.
class NotesViewHolder extends RecyclerView.ViewHolder {

    CardView note_container;
    TextView textView_title;
    TextView textView_notes;
    TextView textView_date;
    ImageView imageView_pin;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);

        note_container = itemView.findViewById(R.id.note_container);
        textView_title = itemView.findViewById(R.id.textView_title);
        textView_notes = itemView.findViewById(R.id.textView_notes);
        textView_date = itemView.findViewById(R.id.textView_date);
        imageView_pin = itemView.findViewById(R.id.imageView_pin);
    }
}