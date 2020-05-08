package com.hitesh.todopod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    Button addItem;
    EditText editText;
    /*ListView listView;
    ArrayList<String> listItem;
    ArrayAdapter<String> arrayAdapter;*/

    private List<items> itemsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;

    DatabaseHelper db;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addItem = (Button) findViewById(R.id.addItem);
        editText = (EditText) findViewById(R.id.editText);
        db = new DatabaseHelper(this);
        /*listView = (ListView) findViewById(R.id.listView);

        listItem = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
        listView.setAdapter(arrayAdapter);*/
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

                    //listItem.add(name);
                    Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                    editText.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String item = arrayAdapter.getItem(position);
                db.delete(item);
                Toast.makeText(getApplicationContext(), "" + item, Toast.LENGTH_SHORT).show();
                arrayAdapter.remove(arrayAdapter.getItem(position));
                arrayAdapter.notifyDataSetChanged();
                return true;
            }
        });*/

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new RecyclerViewAdapter(itemsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        loadData();
    }

    private void addItems() {

    }
    public void loadData(){

        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Database is empty", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
               //listItem.add(cursor.getString(1));
                items items = new items("" + cursor.getString(1), null, null);
                itemsList.add(items);
            }
        }
    }
}
