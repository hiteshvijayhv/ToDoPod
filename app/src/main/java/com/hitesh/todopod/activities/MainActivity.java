package com.hitesh.todopod.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hitesh.todopod.helper.DatabaseHelper;
import com.hitesh.todopod.R;
import com.hitesh.todopod.adapter.RecyclerViewAdapter;
import com.hitesh.todopod.items;
import com.hitesh.todopod.ItemsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<items> itemsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;

    FloatingActionButton fab, fab1;
    boolean isFABOpen;
    LinearLayout fabLayout1;

    ItemsViewModel itemsViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fabLayout1 = (LinearLayout) findViewById(R.id.fabLayout1);
        //fab = findViewById(R.id.fab);
        // fab1 = findViewById(R.id.fab1);


        itemsViewModel = new ViewModelProvider(this,
                new ViewModelProvider
                        .AndroidViewModelFactory(getApplication()))
                .get(ItemsViewModel.class);



        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new RecyclerViewAdapter(itemsList);
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        itemsViewModel.getAllitemss().observe(this, new Observer<List<items>>() {
            @Override
            public void onChanged(List<items> items) {
                mAdapter.setNotes(items);
            }
        });

       /* mAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
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
                Intent addNote = new Intent(getApplicationContext(), AddNoteActivity.class);
                startActivity(addNote);
            }
        });*/
    }
   /* public void open(){
        fabLayout1.animate().translationY(-50);
        fab.animate().rotationBy(135);
        fabLayout1.animate().alpha(1).setDuration(500);
    }
    public void close(){
        fabLayout1.animate().translationY(10);
        fab.animate().rotationBy(-135);
        fabLayout1.animate().alpha(0.0f).setDuration(500);
    }*/
}
