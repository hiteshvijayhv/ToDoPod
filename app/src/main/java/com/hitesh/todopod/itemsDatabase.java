package com.hitesh.todopod;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {items.class}, version = 1)
public abstract class itemsDatabase extends RoomDatabase {
    private static itemsDatabase instance;
    public abstract itemsDAO itemsDAO();
    public static synchronized itemsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    itemsDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private itemsDAO itemsDao;
        private PopulateDbAsyncTask(itemsDatabase db) {
            itemsDao = db.itemsDAO();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
