package com.hitesh.todopod;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class itemsRepository {
    private itemsDAO itemsDao;
    private LiveData<List<items>> allitemss;
    public itemsRepository(Application application) {
        itemsDatabase database = itemsDatabase.getInstance(application);
        itemsDao = database.itemsDAO();
        allitemss = itemsDao.getAllNotes();
    }
    public void insert(items items) {
        new InsertitemsAsyncTask(itemsDao).execute(items);
    }
    public void update(items items) {
        new UpdateitemsAsyncTask(itemsDao).execute(items);
    }
    public void delete(items items) {
        new DeleteitemsAsyncTask(itemsDao).execute(items);
    }
    public void deleteAllitemss() {
        new DeleteAllitemssAsyncTask(itemsDao).execute();
    }
    public LiveData<List<items>> getAllitemss() {
        return allitemss;
    }
    private static class InsertitemsAsyncTask extends AsyncTask<items, Void, Void> {
        private itemsDAO itemsDao;
        private InsertitemsAsyncTask(itemsDAO itemsDao) {
            this.itemsDao = itemsDao;
        }
        @Override
        protected Void doInBackground(items... itemss) {
            itemsDao.insert(itemss[0]);
            return null;
        }
    }
    private static class UpdateitemsAsyncTask extends AsyncTask<items, Void, Void> {
        private itemsDAO itemsDao;
        private UpdateitemsAsyncTask(itemsDAO itemsDao) {
            this.itemsDao = itemsDao;
        }
        @Override
        protected Void doInBackground(items... itemss) {
            itemsDao.update(itemss[0]);
            return null;
        }
    }
    private static class DeleteitemsAsyncTask extends AsyncTask<items, Void, Void> {
        private itemsDAO itemsDao;
        private DeleteitemsAsyncTask(itemsDAO itemsDao) {
            this.itemsDao = itemsDao;
        }
        @Override
        protected Void doInBackground(items... itemss) {
            itemsDao.delete(itemss[0]);
            return null;
        }
    }
    private static class DeleteAllitemssAsyncTask extends AsyncTask<Void, Void, Void> {
        private itemsDAO itemsDao;
        private DeleteAllitemssAsyncTask(itemsDAO itemsDao) {
            this.itemsDao = itemsDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            itemsDao.deleteAllNotes();
            return null;
        }
    }
}
