package RoomDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    Repository repository ;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    void InsertUser(User user){
        repository.InsertUser(user);
    }
    void UpdateUser(User user){
        repository.UpdateUser(user);
    }
    void DeleteUser(User user){
        repository.DeleteUser(user);
    }
    LiveData<List<User>> AllUser(){
        return repository.AllUser();
    }


    public void InsertLevel(Level level){
        repository.InsertLevel(level);
    }
    void UpdateLevel(Level level){
        repository.UpdateLevel(level);
    }
    void DeleteLevel(Level level){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                repository.DeleteLevel(level);
            }
        });
    }
    public LiveData<List<Level>> AllLevel(){
        return repository.AllLevel();
    }

    public void InsertMystery(Mystery mystery) {
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                repository.InsertMystery(mystery);
            }
        });
    }
    void UpdateMystery(Mystery mystery){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                repository.UpdateMystery(mystery);
            }
        });
    }
    void DeleteMystery(Mystery mystery){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                repository.DeleteMystery(mystery);
            }
        });
    }
    public LiveData<List<Mystery>> AllMystery(){
        return repository.AllMystery();
    }
}
