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
import android.widget.Button;
import android.widget.EditText;
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

    ItemsViewModel itemsViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        final EditText editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();

                items item1 = new items("" + text, "", 0);
                itemsViewModel.insert(item1);

            }
        });
    }
}
