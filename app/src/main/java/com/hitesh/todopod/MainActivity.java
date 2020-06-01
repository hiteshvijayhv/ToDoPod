package com.hitesh.todopod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    Button addItem;
    EditText editText;

    private List<items> itemsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;

    FloatingActionButton fab, fab1;
    boolean isFABOpen;
    LinearLayout fabLayout1;

    DatabaseHelper db;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addItem = (Button) findViewById(R.id.addItem);
        editText = (EditText) findViewById(R.id.editText);
        db = new DatabaseHelper(this);

        fabLayout1 = (LinearLayout) findViewById(R.id.fabLayout1);
        fab = findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab1);

        cursor = db.loadDataa();

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                if(!name.isEmpty()){
                    db.insertData(name);
                    items items = new items("" + name, null, null);
                    itemsList.add(items);
                    mAdapter.notifyDataSetChanged();

                    Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                    editText.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });





        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new RecyclerViewAdapter(itemsList);
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        loadData();
        mAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String name = itemsList.get(position).getTitle();
                Intent EditNote = new Intent(getApplicationContext(), EditNoteActivity.class);
                EditNote.putExtra("text", name);
                startActivity(EditNote);
                mAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, name + " was clicked!", Toast.LENGTH_SHORT).show();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if(!isFABOpen){
                    open();
                    fabLayout1.setVisibility(View.VISIBLE);
                    isFABOpen = true;
                } else {
                    close();
                    isFABOpen = false;
                }
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
                Intent addNote = new Intent(getApplicationContext(), AddNote.class);
                startActivity(addNote);
            }
        });
    }
    public void open(){
        fabLayout1.animate().translationY(-50);
        fab.animate().rotationBy(135);
        fabLayout1.animate().alpha(1).setDuration(500);
    }
    public void close(){
        fabLayout1.animate().translationY(10);
        fab.animate().rotationBy(-135);
        fabLayout1.animate().alpha(0.0f).setDuration(500);
    }


    public void loadData(){

        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Database is empty", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                items items = new items("" + cursor.getString(1), null, null);
                itemsList.add(items);
            }
        }
    }
}
