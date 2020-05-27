package com.hitesh.todopod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class EditNoteActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText editNote;
    String note;
    String newNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        editNote = (EditText) findViewById(R.id.editNote);
        db = new DatabaseHelper(this);

        editNote.setText(getIntent().getStringExtra("text"));

        Intent intent = getIntent();
        note = intent.getStringExtra("text");

        editNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                db.delete(note);
            }

            @Override
            public void afterTextChanged(Editable s) {
                newNote = editNote.getText().toString();
            }
        });


    }
    public void onBackPressed() {

        if(newNote != null){
            db.insertData(newNote);
        }
        Intent MainActivity = new Intent(getApplicationContext(), com.hitesh.todopod.MainActivity.class);
        startActivity(MainActivity);
            super.onBackPressed();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_note_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.deleteNote:
                db.delete(note);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return(true);
    }
        return(super.onOptionsItemSelected(item));
    }
}

