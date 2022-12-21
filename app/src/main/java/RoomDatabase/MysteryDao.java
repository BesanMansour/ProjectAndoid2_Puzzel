package RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MysteryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertMystery(Mystery mystery);

    @Update
    void UpdateMystery(Mystery mystery);

    @Delete
    void DeleteMystery(Mystery mystery);

    @Query("SELECT * FROM Mystery")
    LiveData<List<Mystery>> AllMystery();
}
