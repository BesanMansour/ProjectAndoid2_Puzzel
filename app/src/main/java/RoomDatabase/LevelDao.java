package RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LevelDao {

    @Insert
    void InsertLevel(Level level);

    @Update
    void UpdateLevel(Level level);

    @Delete
    void DeleteLevel(Level level);

    @Query("SELECT * FROM LevelTable")
    LiveData<List<Level>> AllLevel();
}
