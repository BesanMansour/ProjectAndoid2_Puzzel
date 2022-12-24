package RoomDatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public class Repository {

    private UserDao userDao;
    private LevelDao levelDao;
    private MysteryDao mysteryDao;

    private LiveData<List<User>> AllUser;
    private LiveData<List<Level>> AllLevel;
    private LiveData<List<Mystery>> AllMystery;

    public Repository(Application application){
        MyRoomDatabase dp = MyRoomDatabase.getDatabase(application);
        userDao = dp.userDao();
        levelDao = dp.levelDao();
        mysteryDao = dp.mysteryDao();
        AllUser = userDao.AllUser();
        AllLevel = levelDao.AllLevel();
        AllMystery = mysteryDao.AllMystery();
    }

    void InsertUser(User user){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.InsertUser(user);
            }
        });
    }
    void UpdateUser(User user){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.UpdateUser(user);
            }
        });
    }
    void DeleteUser(User user){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.DeleteUser(user);
            }
        });
    }
    LiveData<List<User>> AllUser(){
        return userDao.AllUser();
    }


    void InsertLevel(Level level){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                levelDao.InsertLevel(level);
            }
        });
    }
    void UpdateLevel(Level level){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                levelDao.UpdateLevel(level);
            }
        });
    }
    void DeleteLevel(Level level){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                levelDao.DeleteLevel(level);
            }
        });
    }
    LiveData<List<Level>> AllLevel(){
        return levelDao.AllLevel();
    }


    void InsertMystery(Mystery mystery){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mysteryDao.InsertMystery(mystery);
            }
        });
    }
    void UpdateMystery(Mystery mystery){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mysteryDao.UpdateMystery(mystery);
            }
        });
    }
    void DeleteMystery(Mystery mystery){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mysteryDao.DeleteMystery(mystery);
            }
        });
    }
    LiveData<List<Mystery>> AllMystery(){
        return mysteryDao.AllMystery();
    }

//    LiveData<List<Mystery>> getQuestion(int levelId){
//        return mysteryDao.getQuestion(levelId);
//    }
}
