package com.hitesh.todopod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNote extends AppCompatActivity {

    EditText editText;
    Button saveButton;

    String userInput;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editText = (EditText) findViewById(R.id.editText);
        saveButton = (Button) findViewById(R.id.saveButton);
        db = new DatabaseHelper(this);
    }

    public void saveNote(View view){
        userInput = editText.getText().toString();
        db.insertData(userInput);
        Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainActivity);
    }
}
