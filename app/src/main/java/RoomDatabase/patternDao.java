package RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface patternDao {
    @Insert
    void InsertPattern(Pattern pattern);

    @Update
    void UpdatePattern(Pattern pattern);

    @Delete
    void DeletePattern(Pattern pattern);

    @Query("SELECT * FROM patternTable")
    LiveData<List<Pattern>> AllPattern();
}
