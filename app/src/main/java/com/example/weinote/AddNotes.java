package com.example.weinote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddNotes extends AppCompatActivity{
    EditText txtNoteTitle,txtNoteDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnotes_activity);
        txtNoteTitle = findViewById(R.id.txtNoteTitle);
        txtNoteDetail= findViewById(R.id.txtNoteDetail);
    }

    public void saveNotes(View view){
        String noteTitle = txtNoteTitle.getText().toString();
        String noteDetail = txtNoteDetail.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);

        MainActivity.noteTitle.add(noteTitle);
        MainActivity.noteDetails.add(noteDetail);
        startActivity(intent);
    }


}
