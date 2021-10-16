package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.adapter.NoteListAdapter;
import com.example.myapplication.bean.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NoteListActivity extends AppCompatActivity {
    //change store a list of note data and note adapter
    RecyclerView recyclerView;
    private List<Note> noteData;
    FloatingActionButton add_button;
    NoteListAdapter NoteListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_note_list);

        recyclerView = findViewById(R.id.recyclerView);
        noteData = new ArrayList<>();
        NoteListAdapter = new NoteListAdapter(this,noteData);
        recyclerView.setAdapter(NoteListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteListActivity.this, NoteAddActivity.class);
                startActivity(intent);
            }
        });

        getDbData();
    }


    private void getDbData() {
        CustomerInfoDBHelper helper = new CustomerInfoDBHelper(this);
        // read all notes
        Cursor cursor = helper.readALLNoteData();

        try {
            while (cursor.moveToNext()){

                int nid = cursor.getInt(cursor.getColumnIndexOrThrow("nid"));
                String cid = cursor.getString(cursor.getColumnIndexOrThrow("cid"));
                String event = cursor.getString(cursor.getColumnIndexOrThrow("event"));
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                String addnote = cursor.getString(cursor.getColumnIndexOrThrow("note"));
                String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));

                Note note = new Note();
                note.setId(nid);
                note.setCid(cid);
                note.setEvent(event);
                note.setDate(date);
                note.setAddnote(addnote);
                note.setAddnote(type);

                noteData.add(note);
            }
            NoteListAdapter.notifyDataSetChanged();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}