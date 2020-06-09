package com.hitesh.todopod;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemsViewModel extends AndroidViewModel {
    private itemsRepository repository;
    private LiveData<List<items>> allitemss;
    public ItemsViewModel(@NonNull Application application) {
        super(application);
        repository = new itemsRepository(application);
        allitemss = repository.getAllitemss();
    }
    public void insert(items items) {
        repository.insert(items);
    }
    public void update(items items) {
        repository.update(items);
    }
    public void delete(items items) {
        repository.delete(items);
    }
    public void deleteAllitemss() {
        repository.deleteAllitemss();
    }
    public LiveData<List<items>> getAllitemss() {
        return allitemss;
    }
}
